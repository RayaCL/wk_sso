package cn.et.wk.sso.system.service;

import java.util.List;

import cn.et.wk.common.entity.Function;
import cn.et.wk.common.entity.UserInfo;
import cn.et.wk.common.utils.PageResult;

public interface FunctionService {
	/**
	 * 通过权限名称查找
	 * @param name
	 * @return
	 */
	Function queryFunByName(String name);
	/**
	 * 查找用户绑定的权限
	 * @param userName
	 * @return
	 */
	List<Function> queryFunByUser(String userName);
	
	/**
	 * 分页显示数据
	 * @param rows 总条数
	 * @param page 页数
	 * @param functionInfo 要显示的对象的数据
	 * @return
	 */
	PageResult queryFunctionInfo(int rows,int page,Function functionInfo);
	
	/**
	 * 增加权限
	 * @param functionInfo
	 * @param userInfo
	 */
	void addFunction(Function functionInfo,UserInfo userInfo);
	
	/**
	 * 通过id删除权限
	 * @param id
	 */
	void deleteFunction(int id);
	
	/**
	 * 修改权限
	 * @param function
	 * @param userInfo
	 */
	void updateFunction(Function function,UserInfo userInfo);
}
