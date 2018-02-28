package cn.et.wk.sso.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import cn.et.wk.common.entity.MenuInfo;
import cn.et.wk.common.entity.UserInfo;

/**
 * 菜单的接口映射类
 * 
 * @author Etop_1402_Candy_TangLianyuan
 *
 */
@Mapper
public interface MenuInfoMapper {
	
	/**
	 * 通过名称查询用户   
	 * @param name 用户�?
	 * @return
	 */
	 @Select("select tmi.menuid,tmi.menuname,tmi.menuiconpath,tfi.funpath,tmi.parentmenuid,tfi.android_activity as androidActivity  "+
	 " from tb_user_info ui "+
	 " inner join tb_user_role_relation turr on ui.userid = turr.userid "+
	 " inner join tb_role_info r on turr.roleid=r.roleid "+
	 " inner join tb_role_function_relation trfr on r.roleid=trfr.roleid "+
	 " inner join tb_function_info tfi on trfr.funid=tfi.funid "+
	 " inner join tb_menu_info tmi on tfi.funid=tmi.funid where ui.username=#{name} order by tmi.sortnumber asc ")
	 List<MenuInfo> queryMenuByName(String name);
	
	/**
	 * 查询�?��的一级菜�?
	 * @return
	 */
	 @Select("select menuId , funId , menuName , parentMenuId ,menuiconpath,menuDesc " +
			"from tb_menu_info " +
			"where parentmenuid=0 order by sortnumber asc")
	 List<MenuInfo> loadRootTree();
	
	/**
	 * 查询出某个父菜单下的�?��子菜�?
	 */
	 @Select("select t.menuid, t.menuname, t.menudesc,menuiconpath ,t.parentmenuid " +
			"from tb_menu_info t " +
			"where t.parentmenuid = #{menuId}  order by sortnumber asc")
	 List<MenuInfo> loadChildrenTree(Long menuId);

    /**
     * 查询出菜单名称是否存在�?
     * @param menuName
     * @return
     * 参数名称里面不能用和数据里面的名字一样，否则会发生冲突�?
     */
	 @Select("select menuName " +
			"from tb_menu_info " +
			"where menuName = #{name}")
	 MenuInfo isExistMenu(String name);
	
	/**
	 * 这个方法是查询出�?��的子菜单的菜单id�?
	 */
	 @Select("select menuId " +
			"from tb_menu_info " +
			"where parentMenuid != 0")
	 List<MenuInfo> loadAllChildMenuId();
	
	/**
	 * 增加菜单�?
	 * @param menuInfo
	 */
	@SelectKey(keyProperty="menuInfo.menuId",statement="select max(menuId)+1 from tb_menu_info",resultType=Long.class,before=true)
	@Insert("insert into tb_menu_info values(#{menuInfo.menuId}," +
			"#{menuInfo.funId,jdbcType=VARCHAR}," +
			"#{menuInfo.menuName}," +
			"#{parentsMenuId}," +
			"#{menuInfo.menuDesc}," +
			"#{menuInfo.menuIconPath,jdbcType=VARCHAR}," +
			"#{menuInfo.sortNumber}," +
			"sysDate," +
			"#{userInfo.userFullName}," +
			"sysDate,"+
			"#{userInfo.userId})")
	 void addMenu(@Param("menuInfo")MenuInfo menuInfo,@Param("parentsMenuId")Long parentsMenuId,@Param("userInfo")UserInfo userInfo);
	
	/**
	 * 修改菜单�?
	 * @param menuInfo
	 * @param parentsMenuId
	 * @param userInfo
	 */
	@Update("update " +
			"tb_menu_info " +
			"set menuName = #{menuInfo.menuName} , " +
			"menuDesc=#{menuInfo.menuDesc }, " +
			"funId = #{menuInfo.funId} ," +
			"sortNumber = #{menuInfo.sortNumber} ," +
			"menuIconPath=nvl(#{menuInfo.menuIconPath,jdbcType=VARCHAR},menuIconPath),"+
			"lastModifyTime=sysDate," +
			"lastModifyPerson = #{userInfo.userId} " +
			"where menuId = #{parentsMenuId }")
	 void updateMenu(@Param("menuInfo")MenuInfo menuInfo,@Param("parentsMenuId")Long parentsMenuId,@Param("userInfo")UserInfo userInfo);
	
	/**
	 * 删除菜单 ,要把我们要删除的某个指定的菜单的编号传过来�?
	 * @param parentsMenuId
	 */
	 @Delete("delete " +
	 		"from " +
	 		"tb_menu_info " +
	 		"where menuId = #{parentsMenuId}")
	  void deleteMenu(@Param("parentsMenuId")Long parentsMenuId);
	 
	 /**
	  * 根据菜单编号查询出菜单名称�?
	  * @param parentsMenuId
	  * @return
	  */
	 @Select("select menuId , funId , menuName , parentMenuId ,menuDesc,sortNumber" +
	 	     " from tb_menu_info " +
	 		 " where menuId=#{parentsMenuId}")
	  MenuInfo getMenuByMenuId(@Param("parentsMenuId")Long parentsMenuId);
	 
}
