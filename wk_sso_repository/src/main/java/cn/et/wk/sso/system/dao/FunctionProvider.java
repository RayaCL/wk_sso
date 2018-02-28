package cn.et.wk.sso.system.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.et.wk.common.entity.Function;

/**
 * 该类是做分页显示数据
 */
@Mapper
public class FunctionProvider {
	
	public String querySql(Map map){
		int max=(Integer)map.get("max");
		int min=(Integer)map.get("min");
		Function funInfo=(Function)map.get("fun");
		String sql="select * from (select f.funid,f.funname,f.funPath,f.fundesc,"+
				"f.createTime as createTime,"+
				"u.userfullname as createPerson,"+
				"f.lastUpdateTime as lastUpdateTime,"+
				"u.userfullname as lastUpdatePersonId,"+
				"rownum rn from tb_function_info f "+
				"inner join tb_user_info u on f.lastupdatepersonid=u.userid"+
				" where rownum <= #{max}) where rn >=#{min}";
		if((funInfo.getFunName()!=null)&& (!"".equals(funInfo.getFunName()))){
			sql+=" and (funname like '%"+funInfo.getFunName()+"%' or funpath like '%"+funInfo.getFunName()+"%')";
		}
		return sql;
	}
	
}
