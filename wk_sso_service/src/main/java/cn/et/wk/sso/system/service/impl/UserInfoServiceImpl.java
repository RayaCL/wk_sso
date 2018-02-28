package cn.et.wk.sso.system.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.wk.common.entity.MenuInfo;
import cn.et.wk.common.entity.UserInfo;
import cn.et.wk.common.utils.PageResult;
import cn.et.wk.common.utils.SystemUtils;
import cn.et.wk.sso.system.dao.MenuInfoMapper;
import cn.et.wk.sso.system.dao.UserInfoMapper;
import cn.et.wk.sso.system.service.UserInfoService;

/**
 * 实现类
 * @author Administrator
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	private UserInfoMapper dao;
	@Autowired
	MenuInfoMapper menuMap;
	public UserInfo queryUserByName(String name){
		return dao.queryUserByName(name);
	}
	public List<MenuInfo> queryMenuByName(String name){
		return menuMap.queryMenuByName(name);
	}
	public List<MenuInfo> queryAllParentMenu(){
		List<MenuInfo> menuList=menuMap.loadRootTree();
		return menuList;
	}
	public PageResult queryUserInfo(int rows,int page,UserInfo user){
		int start=(page-1)*rows;
		int end=(page)*rows;
		
		//查询总行数
		int count=dao.queryUserInfoCount();
		//通过记录位置查询结果集
		List<UserInfo> userList=dao.queryUserInfo(end, start,user);
		PageResult pageResult=new PageResult();
		pageResult.setRows(userList);
		pageResult.setTotal(count);
		return pageResult;
	}
	public void deleteUser(int userId){
		dao.deleteUser(userId);
	}
	public void insertUser(UserInfo userInfo){
		dao.insertUser(userInfo);
	}
	public void updateUser(UserInfo userInfo){
		dao.updateUser(userInfo);
	}
	public void bindUserFunction(String userId,String roleId){
		dao.deleteRoleByUserId(userId);
		String[] role=roleId.split(",");
		for(String roleTemp:role){
			if(!SystemUtils.isNull(roleTemp)){
				dao.bindUserFunction(userId, roleTemp);
			}
		}
	}
}