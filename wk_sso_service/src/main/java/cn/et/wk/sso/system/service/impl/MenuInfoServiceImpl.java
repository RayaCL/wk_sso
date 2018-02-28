package cn.et.wk.sso.system.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.et.wk.common.entity.MenuInfo;
import cn.et.wk.common.entity.UserInfo;
import cn.et.wk.sso.system.dao.MenuInfoMapper;
import cn.et.wk.sso.system.service.MenuInfoService;

@Service
public class MenuInfoServiceImpl implements MenuInfoService {

	@Autowired
	private MenuInfoMapper menuInfoDao;

	public List<MenuInfo> loadRootTree() { // 加载所有一级菜单和根菜单。
		return menuInfoDao.loadRootTree();
	}

	public List<MenuInfo> loadChildrenTree(Long menuId) { // 加载所有子菜单。
		return menuInfoDao.loadChildrenTree(menuId);
	}

	public MenuInfo isExistMenu(String MenuName) { // 判断菜单名称是否存在。
		return menuInfoDao.isExistMenu(MenuName);
	}

	public List<MenuInfo> loadAllChildMenuId() { // 获取所有的子菜单的名称
		return menuInfoDao.loadAllChildMenuId();
	}

	public void addMenu(MenuInfo menuInfo, Long parentMenuId,
			UserInfo userInfo, String destPath) throws IllegalStateException,
			IOException { // 增加菜单
		MultipartFile iconFile = menuInfo.getIconFile();
		if (!iconFile.isEmpty()) {
			String fileName = iconFile.getOriginalFilename();
			menuInfo.setMenuIconPath(fileName);
		}
		menuInfoDao.addMenu(menuInfo, parentMenuId, userInfo);
		// 等数据库创建完成后再去控制上传文件
		if (!iconFile.isEmpty()) {
			// 将我们的菜单对象，和它的父菜单编号传到dao里面去。
			String fileName = iconFile.getOriginalFilename();
			iconFile.transferTo(new File(destPath + File.separatorChar
					+ fileName));
		}

	}

	public void updateMenu(MenuInfo menuInfo, Long parentMenuId,
			UserInfo userInfo, String realPath) throws IllegalStateException, IOException { // 修改菜单
		MultipartFile iconFile = menuInfo.getIconFile();
		if (!iconFile.isEmpty()) {
			String fileName = iconFile.getOriginalFilename();
			menuInfo.setMenuIconPath(fileName);
		}
		menuInfoDao.updateMenu(menuInfo, parentMenuId, userInfo);
		// 等数据库创建完成后再去控制上传文件
		if (!iconFile.isEmpty()) {
			// 将我们的菜单对象，和它的父菜单编号传到dao里面去。
			String fileName = iconFile.getOriginalFilename();
			iconFile.transferTo(new File(realPath + File.separatorChar
					+ fileName));
		}
	}

	public void deleteMenu(Long parentMenuId) { // 删除菜单
		menuInfoDao.deleteMenu(parentMenuId);
	}

	public MenuInfo getMenuByMenuId(Long parentMenuId) { // 根据菜单id获取菜单对象。
		return menuInfoDao.getMenuByMenuId(parentMenuId);
	}
}
