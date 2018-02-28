package cn.et.wk.sso.system.service;

import cn.et.wk.common.entity.RoleInfo;
import cn.et.wk.common.utils.PageResult;

/**
 * 角色service类
 * @author 把婷
 *
 */
public interface RoleInfoService {
	/**
     * 通过名称查询角色
     * @param name
     * @return
     */
	abstract RoleInfo queryRoleByName(String name);
	
	/**
	 * 查询所有的角色信息
	 * @param rows
	 * @param page
	 * @param role
	 * @return
	 */
	PageResult queryRoleInfo(int rows,int page,RoleInfo roleInfo);
	
	/**
	 * 增加角色
	 * @param roleInfo
	 */
	void addRole(RoleInfo roleInfo);
	
	/**
	 * 删除角色
	 * @param roleInfo
	 */
	void deleteRole(int roleId);
	
	/**
	 * 修改角色
	 * @param roleInfo
	 */
	void updateRole(RoleInfo roleInfo);
	/**
	 * 角色绑定权限
	 *时间：2017-2-16 下午04:54:24
	 *作者： LM
	 *联系方式：973465719@qq.com
	 * @param roleId
	 * @param funId
	 */
	void bindRoleFunction(String roleId,String funId);
}
