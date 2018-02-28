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

import cn.et.wk.common.entity.UserInfo;
/**
 * 用户操作的接口映射类
 * @author 
 *
 */
@Mapper
public interface UserInfoMapper {
	/**
	 * 通过名称查询用户
	 * @param name 用户�?
	 * @return
	 */
	 @Select("select userId,userName,password,email,phone,address,userFullName,isadministrartor as isAdministrators from tb_user_info where username=#{name}")
	 UserInfo queryUserByName(String name);
	/**
	 * 分页查询集合
	 * @param max
	 * @param min
	 * @return
	 */
	 @SelectProvider(type=UserProvider.class,method="querySql")
	 List<UserInfo> queryUserInfo(@Param("max")int max,@Param("min")int min,@Param("user") UserInfo userInfo);
	/**
	 * 查询总数�?
	 * @return
	 */
	 @Select("select count(userId) from tb_user_info ")
	 int queryUserInfoCount();
	/**
	 * 通过编号删除用户
	 * @return
	 */
	 @Delete("delete from tb_user_info where userId=#{userId}")
	 void deleteUser(int userId);
	/**
	 * 通过编号修改用户
	 * @return
	 */
	 @Update("update tb_user_info set userFullName=#{user.userFullName},email=#{user.email},phone=#{user.phone}, address=#{user.address} where userId=#{user.userId}")
	 void updateUser(@Param("user")UserInfo userInfo);
	
	/**
	 * 通过编号增加用户
	 * @return
	 */
	 @SelectKey(keyProperty="user.userId",statement="select max(userId)+1 from tb_user_info",resultType=Integer.class,before=true)
	 @Insert("insert into tb_user_info(userId,userName,password,email,phone,address,userFullName) values(#{user.userId},#{user.userName},#{user.password},#{user.email},#{user.phone},#{user.address},#{user.userFullName})")
	 void insertUser(@Param("user")UserInfo userInfo);
	 
	 /**
		 * 删除权限通过roleid
		 *时间�?017-2-18 上午10:35:05
		 *作�?�?LM
		 *联系方式�?73465719@qq.com
		 * @param id
		 */
	@Delete("delete from tb_user_role_relation where userId=#{id}")
	void deleteRoleByUserId(String id);
	
	@Update("declare myRowCount number;"+
			" begin "+
			"   select count(*) into myRowCount  from tb_user_role_relation t where t.userid=#{0} and t.roleid=#{1}; "+
			"   if myRowCount=0 then "+
			"      insert into tb_user_role_relation values(#{0},#{1}); "+
			"    end if;  "+
			" end;") 
	void bindUserFunction(String userId,String roleId);
	
	 
  }