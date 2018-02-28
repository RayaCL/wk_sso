package cn.et.wk.sso.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.wk.common.entity.RoleInfo;
import cn.et.wk.common.utils.PageResult;
import cn.et.wk.common.utils.SystemUtils;
import cn.et.wk.sso.system.dao.RoleInfoMapper;
import cn.et.wk.sso.system.service.RoleInfoService;

@Service
public class RoleInfoServiceImpl implements RoleInfoService{

	@Autowired
	RoleInfoMapper roleInfoDao;
	
	/**
     * 通过名称查询角色
     * @param name
     * @return
     */
	public RoleInfo queryRoleByName(String name){
		return roleInfoDao.queryRoleByName(name);
	}
	 
	/**
	 * 查询所有的角色信息
	 */
	public PageResult queryRoleInfo(int rows,int page,RoleInfo roleInfo){
		//计算记录开始的位置
		int start=(page-1)*rows;
		//计算记录结束的位置
		int end=(page)*rows;
		//查询总行数
		int count=roleInfoDao.queryRoleInfoCount();
		//通过记录位置查询结果集
		List<RoleInfo> list=roleInfoDao.queryRoleInfo(end, start,roleInfo);
		PageResult pageResult=new PageResult();
		pageResult.setRows(list);
		pageResult.setTotal(count);
		return pageResult;
	}
	
    /**
     * 增加角色
     */
	public void addRole(RoleInfo roleInfo) {
		roleInfoDao.addRole(roleInfo);
	}
	
	/**
	 * 删除角色
	 */
	public void deleteRole(int roleId) {
		roleInfoDao.deleteRole(roleId);
	}
	
    /**
     * 修改角色
     */
	public void updateRole(RoleInfo roleInfo) {
		roleInfoDao.updateRole(roleInfo);
	}
	/**
	 * 角色绑定权限
	 *时间：2017-2-16 下午04:53:55
	 *作者： LM
	 *联系方式：973465719@qq.com
	 * @param roleId
	 * @param funId
	 */
	public void bindRoleFunction(String roleId,String funId){
		roleInfoDao.deleteFunctionByRoleId(roleId);
		String[] fun=funId.split(",");
		for(String funTemp:fun){
			if(!SystemUtils.isNull(funTemp)){
				roleInfoDao.bindRoleFunction(roleId, funTemp);
			}
		}
	}
}
