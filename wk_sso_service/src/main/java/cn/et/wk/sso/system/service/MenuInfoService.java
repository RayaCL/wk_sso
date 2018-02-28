package cn.et.wk.sso.system.service;

import java.io.IOException;
import java.util.List;

import cn.et.wk.common.entity.MenuInfo;
import cn.et.wk.common.entity.UserInfo;
/**
 * 菜单service类
 * @author etop_1402_TangLianyuan
 *
 */
public interface MenuInfoService {
	
	   List<MenuInfo> loadRootTree();      // 加载所有一级菜单和根菜单。
	
	   List<MenuInfo> loadChildrenTree(Long menuId);   //  加载所有子菜单。
	  
	   MenuInfo isExistMenu(String MenuName);            // 判断菜单名称是否存在。
	    
	   List<MenuInfo> loadAllChildMenuId();               // 获取所有的子菜单的名称
	  
	   void addMenu(MenuInfo menuInfo,Long parentMenuId,UserInfo userInfo,String destPath)throws IllegalStateException, IOException; // 增加菜单
	  
	   void updateMenu(MenuInfo menuInfo,Long parentMenuId,UserInfo userInfo,String realPath) throws IllegalStateException, IOException; // 修改菜单
	  
	   void deleteMenu(Long parentMenuId);       // 删除菜单
	  
	   MenuInfo getMenuByMenuId(Long parentMenuId);  // 根据菜单id获取菜单对象。
	  
}
