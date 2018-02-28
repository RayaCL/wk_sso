package cn.et.wk.sso.system.dao;

import java.util.Map;

import cn.et.wk.common.entity.RoleInfo;

public class RoleProvider {
	public String querySql(Map map){
		//�?��取多少条数据
		int max = (Integer)map.get("max");
		//�?��取多少条数据
		int min = (Integer)map.get("min");
		RoleInfo ri = (RoleInfo)map.get("role");
		String sql="select roleId,roleName,roleDesc,foundTime,foundMan,lastUpdateTime,lastUpdateMan " +
				"from (select t.roleId,t.roleName,t.roleDesc,t.foundTime,t.foundMan,t.lastUpdateTime,u.userfullname as lastUpdateMan,rownum rn " +
				"from tb_role_info t " +
				"inner join tb_user_info u on u.userid=t.lastupdateman " +
				"where rownum<=#{max}) where rn>=#{min}";
		if((ri.getRoleName()!=null) && (!"".equals(ri.getRoleName()))){
			sql+=" and (roleName like '%"+ri.getRoleName()+"%') ";
		}
		return sql;
	}
}
