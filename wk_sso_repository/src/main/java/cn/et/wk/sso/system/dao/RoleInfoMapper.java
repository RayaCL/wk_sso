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

import cn.et.wk.common.entity.RoleInfo;

/**
 * 角色操作的接口映射类
 * @author 把婷
 *
 */
@Mapper
public interface RoleInfoMapper {
	/**
	 * 通过名称查询角色
	 * @param name
	 * @return
	 */
	@Select("select roleId,roleName,roleDesc,foundTime,foundMan,lastUpdateTime,lastUpdateMan from tb_role_info where rolename=#{name}")
	RoleInfo queryRoleByName(String name);
	
	/**
	 * 查询总数�?
	 * @return
	 */
	@Select("select count(roleId) from tb_role_info")
	int queryRoleInfoCount();
	
	/**
	 * 分页查询集合
	 * @param max    �?��取的�?
	 * @param min    �?��取的�?
	 * @param ri
	 * @return
	 */
	@SelectProvider(type=RoleProvider.class,method="querySql")
	List<RoleInfo> queryRoleInfo(@Param("max")int max,@Param("min")int min,@Param("role")RoleInfo roleInfo);
	
	/**
	 * 通过编号删除角色
	 * @param roleInfo
	 */
	@Delete("delete from tb_role_info where roleId=#{roleId} and roleName!='系统管理�?")
	void deleteRole(int roleId);
	
	/**
	 * 通过编号修改角色
	 * @param roleInfo
	 */
	@Update("update tb_role_info set " +
			"roleName=#{role.roleName}," +    
			"roleDesc=#{role.roleDesc}," +
			//"foundTime=#{role.foundTime}," +     //创建时间不用修改
			"foundMan=#{role.foundMan}," +
			"lastUpdateTime=sysDate," +            //�?��修改时间为当前时�?
			"lastUpdateMan=#{role.lastUpdateMan} " +
			"where roleId=#{role.roleId} and roleName!='系统管理�?")
	void updateRole(@Param("role")RoleInfo role);
	
	@Update("declare myRowCount number;"+
	" begin "+
	"   select count(*) into myRowCount  from tb_role_function_relation t where t.roleid=#{0} and t.funid=#{1}; "+
	"   if myRowCount=0 then "+
	"      insert into tb_role_function_relation values(#{0},#{1}); "+
	"    end if;  "+
	" end;") 
	void bindRoleFunction(String roleId,String funId);
	
	/**
	 * 删除权限通过roleid
	 *时间�?017-2-18 上午10:35:05
	 *作�?�?LM
	 *联系方式�?73465719@qq.com
	 * @param id
	 */
	@Delete("delete from tb_role_function_relation where roleId=#{id}")
	void deleteFunctionByRoleId(String id);
	/**
	 * 通过编号增加角色
	 * @param roleInfo�?
	 */
	@SelectKey(keyProperty="role.roleId",statement="select max(roleId)+1 from tb_role_info",resultType=Integer.class,before=true)
	@Insert("insert into tb_role_info " +
			"values(#{role.roleId}," +
			"#{role.roleName}," +
			"#{role.roleDesc}," +
			"sysDate," +         //增加角色时：创建时间为当前时�?
			"#{role.foundMan}," +
			"sysDate," +         //增加角色时：�?��修改时间为当前时�?
			"#{role.lastUpdateMan})")
	void addRole(@Param("role")RoleInfo roleInfo); 
}
