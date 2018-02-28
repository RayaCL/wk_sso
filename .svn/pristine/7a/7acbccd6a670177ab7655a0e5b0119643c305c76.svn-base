package cn.et.wk.sso.system.service;
import java.util.List;

import cn.et.wk.common.entity.MenuInfo;
import cn.et.wk.common.entity.UserInfo;
import cn.et.wk.common.utils.PageResult;

/**
 * 用户service.
 * @author lilan
 *
 */
public interface UserInfoService {
	 abstract UserInfo queryUserByName(String name);
	 PageResult queryUserInfo(int rows,int page,UserInfo user);
	 List<MenuInfo> queryAllParentMenu();
	 List<MenuInfo> queryMenuByName(String name);
	 void deleteUser(int userId);
	 void insertUser(UserInfo userInfo); 
	 void updateUser(UserInfo userInfo);
	 public void bindUserFunction(String userId,String roleId);
}