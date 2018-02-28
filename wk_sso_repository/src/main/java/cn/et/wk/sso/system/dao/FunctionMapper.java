package cn.et.wk.sso.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.et.wk.common.entity.Function;
import cn.et.wk.common.entity.UserInfo;

/**
 * 对权限管理进行增删改�?
 */
@Mapper
public interface FunctionMapper {
	/**
	 * 通过名称查找权限
	 * @param name
	 * @return
	 */
	@Select("select f.funid,f.funname,f.funpath,f.fundesc,f.createtime,f.createperson,f.lastupdatetime,f.lastupdatepersonid "+
			"from tb_function_info f "+
			"where funname=#{funName}")
	Function queryFunByName(String funName);
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	@Select("select tfi.* from tb_user_info tu inner join tb_user_role_relation turr on tu.userid=turr.userid "+
			"inner join tb_role_info tr on tr.roleid=turr.roleid "+
			"inner join tb_role_function_relation trfr on tr.roleid=trfr.roleid "+
			"inner join tb_function_info tfi on trfr.funid=tfi.funid where tu.username=#{userName}")
	List<Function> queryFunByUser(String userName);
	/**
	 * 分页查询集合
	 * @param max
	 * @param min
	 * @param functionInfo
	 * @return
	 */
	@SelectProvider(type=FunctionProvider.class,method="querySql")
	List<Function> queryFunctionInfo(@Param("max")int max,@Param("min")int min,@Param("fun")Function functionInfo);
	
	/**
	 * 查询总数�?
	 * @return
	 */
	@Select("select count(funId) from tb_function_info")
	int queryFunctionInfoCount();
	
	/**
	 * 根据自动生成主键增加权限
	 * @param functionInfo
	 */
	@SelectKey(keyProperty="fun.funId",statement="select max(funId)+1 from tb_function_info",resultType=Integer.class,before=true)
	@Insert("insert into tb_function_info values(" +
			"#{fun.funId}," +
			"#{fun.funName}," +
			"#{fun.funPath}," +
			"#{fun.funDesc}," +
			"sysdate," +
			"#{userInfo.userFullName}," +
			"sysdate," +
			"#{userInfo.userId})")
	void addFunction(@Param("fun")Function functionInfo,@Param("userInfo")UserInfo userInfo);
	
	/**
	 * 删除权限
	 * 
	 * @param id
	 */
	@Delete("delete from tb_function_info where funId=#{id}")
	void deleteFunction(int id);
	
	
	/**
	 * 根据编号修改权限
	 * @param function
	 */
	@Update("update tb_function_info set funName=#{fun.funName}," +
			"funPath=#{fun.funPath}," +
			"funDesc=#{fun.funDesc}," +
			"lastUpdateTime=sysdate," +
			"lastUpdatePersonId=#{userInfo.userId}" +
			" where funId=#{fun.funId}")
	void updateFunction(@Param("fun")Function function,@Param("userInfo")UserInfo userInfo);
	
}
