package cn.et.wk.sso.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.wk.common.entity.Function;
import cn.et.wk.common.entity.UserInfo;
import cn.et.wk.common.utils.PageResult;
import cn.et.wk.sso.system.dao.FunctionMapper;
import cn.et.wk.sso.system.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService{
	@Autowired
	private FunctionMapper functionDao;
	
	/**
	 * 删除权限
	 */
	public void deleteFunction(int id) {
		functionDao.deleteFunction(id);
	}
	
	/**
	 * 查找用户绑定的权限
	 */
	public List<Function> queryFunByUser(String userName){
		return functionDao.queryFunByUser(userName);
	}
	
	/**
	 * 新增权限
	 */
	public void addFunction(Function functionInfo,UserInfo userInfo) {
		functionDao.addFunction(functionInfo,userInfo);
	}
	
	/**
	 * 根据权限名称查找
	 */
	public Function queryFunByName(String name) {
		return functionDao.queryFunByName(name);
	}
	
	/**
	 * 分页显示数据
	 * @param rows 总条数
	 * @param page 页数
	 * @param functionInfo 要显示的对象的数据
	 * @return
	 */
	public PageResult queryFunctionInfo(int rows, int page,Function functionInfo) {
		int start=(page-1)*rows;
		int end=(page)*rows;
		int count=functionDao.queryFunctionInfoCount();
		List<Function> list=functionDao.queryFunctionInfo(end, start,functionInfo);
		PageResult pageResult=new PageResult();
		pageResult.setRows(list);
		pageResult.setTotal(count);
		return pageResult;
	}
	
	/**
	 * 修改权限
	 */
	public void updateFunction(Function function,UserInfo userInfo) {
		functionDao.updateFunction(function,userInfo);
	}

}
