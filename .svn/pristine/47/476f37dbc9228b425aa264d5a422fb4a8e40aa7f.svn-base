package cn.et.wk.sso.system.dao;
import java.util.Map;

import cn.et.wk.common.entity.UserInfo;

public class UserProvider {
	
	@SuppressWarnings("unchecked")
	public String querySql(Map map){
		
		@SuppressWarnings("unused")
		int max=(Integer)map.get("max");
		@SuppressWarnings("unused")
		int min=(Integer)map.get("min");
		//SQL sql=new SQL();
		UserInfo user=(UserInfo)map.get("user");
		String sql="select * from (select t.*,rownum rn from tb_user_info t where rownum<=#{max}) where rn>=#{min}";
		if(user.getUserFullName()!=null && !"".equals(user.getUserFullName())){
			sql+=" and (userFullName like '%"+user.getUserFullName()+"%' or userName like '%"+user.getUserFullName()+"%') ";
		}
		return sql;
	}
}