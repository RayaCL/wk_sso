package cn.et.wk.sso.system.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.et.wk.common.entity.UserInfo;
import cn.et.wk.common.utils.SystemUtils;
import cn.et.wk.sso.system.service.FunctionService;
import cn.et.wk.sso.system.service.MenuInfoService;
import cn.et.wk.sso.system.service.UserInfoService;

@Controller
public class LoginController {
	@Autowired
	StringRedisTemplate rt;
	@Autowired
	
	/**
	 * 上传的图标的位置 图标的尺寸都是 16*16像素图片
	 */
	public static final String uploadPath="/resource/css/themes/icons/extra/";
	@Autowired
	UserInfoService service;
	@Autowired
	FunctionService fservice;
	@Autowired
	MenuInfoService mservice;
	@Value("${sso_url}")
	String  sso_url;
	@Value("${token_timeout}")
	int token_timeout;
	
	/**
	 * 所有需要登录的网页 都需要先在自己的配置中心中配置 sso_url指向 当前sso服务的主机和ip 比如 sso_url=http://localhost:8081
	 * 当前项目的配置中心中也需要配置一个sso_url 表示当任意网站调用/authenticate时需要同时调用该url指定的地址的/system/login
	 *   sso_url=http://localhost:8080/?1,http://localhost:8888 ?1表示必须是管理员登录才需要单点调用的 没有?1所有用户都能单点
	 *   具体实现参考 wk_manager_web login.html
	 *时间：2017-3-6 上午10:11:06
	 *作者： LM
	 *联系方式：973465719@qq.com
	 * @param user
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws OAuthSystemException 
	 */
	@ResponseBody
	@RequestMapping("/authenticate")
	public String authorize(@ModelAttribute("user") UserInfo user,String callback) throws UnsupportedEncodingException, IOException, OAuthSystemException{
		Map resultMap=new HashMap();
		int statusCode=0;
		String message=null;
		Map loginMap=new HashMap();
		if(!SystemUtils.isNull(user.getUserName())){
			UserInfo ruserInfo=service.queryUserByName(user.getUserName());
			if(ruserInfo!=null && ruserInfo.getPassword().equals(user.getPassword())){
				statusCode=1;	
				resultMap.put("sso_url", sso_url);
				OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());  
		        String accessToken = oauthIssuerImpl.accessToken(); 
		        resultMap.put("sso_token", accessToken);
		        resultMap.put("isAdmin", ruserInfo.getIsAdministrators());
		        //设置token 其他地址传入token用于对比的
		        BoundValueOperations<String, String> bvo=rt.boundValueOps("userlogin_token_"+accessToken);
		        bvo.expire(token_timeout, TimeUnit.HOURS);
		        bvo.set(user.getUserName());
			}else{
				message="密码输入错误";
			}
		}else{
			message="用户名不能为空";
		}
		
		resultMap.put("status", statusCode);
		resultMap.put("message", message);
		//os.write(.toString().getBytes(UserConstant.ENCODING));
		return callback+"("+JSONObject.toJSONString(resultMap)+")";
	}
			
	
}
