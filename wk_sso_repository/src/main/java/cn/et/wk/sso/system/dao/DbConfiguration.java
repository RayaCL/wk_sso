package cn.et.wk.sso.system.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
@RefreshScope
@ConfigurationProperties(prefix="db")
public class DbConfiguration {
	private String url;
	private String driverClassName;
	private String userName;
	private String password;
	private String defaultAutoCommit;
	private String filters;
	private boolean logAbandoned;
	private int maxActive;
	private int minIdle;
	private boolean removeAbandoned;
	private int removeAbandonedTimeout;
	private String validationQuery;

	@Bean
	@ConditionalOnClass(value=DruidDataSource.class)
	public DataSource dataSource() throws SQLException{
		DruidDataSource dds=new DruidDataSource();
		dds.setUrl(url);
		dds.setDriverClassName(driverClassName);
		dds.setUsername(userName);
		dds.setPassword(password);
		dds.setFilters(filters);
		dds.setLogAbandoned(logAbandoned);
		dds.setMaxActive(maxActive);
		dds.setMinIdle(minIdle);
		dds.setRemoveAbandoned(removeAbandoned);
		dds.setRemoveAbandonedTimeout(removeAbandonedTimeout);
		//dds.setValidationQuery(validationQuery);
		return dds;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getDefaultAutoCommit() {
		return defaultAutoCommit;
	}

	public void setDefaultAutoCommit(String defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public boolean isLogAbandoned() {
		return logAbandoned;
	}

	public void setLogAbandoned(boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public boolean isRemoveAbandoned() {
		return removeAbandoned;
	}

	public void setRemoveAbandoned(boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	public int getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}
	
	
	
}
