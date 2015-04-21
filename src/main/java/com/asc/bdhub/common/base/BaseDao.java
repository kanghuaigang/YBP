package com.asc.bdhub.common.base;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class BaseDao {
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public <T> List<T> findList(String sql,final Class<T> clazz) {
		return findList(sql, null, clazz);
	}
	public <T> List<T> findList(String sql,int start,int limit ,final Class<T> clazz) {
		return findList(formatPageSql(sql, start, limit), null, clazz);
	}
	public <T> List<T> findList(String sql,int start,int limit ,Object[]  parameterValue,final Class<T> clazz) {
		return findList(formatPageSql(sql, start, limit), parameterValue, clazz);
	}
	public <T> List<T> findList(String sql,Object[]  parameterValue,final Class<T> clazz) {
		return jdbcTemplate.query(sql,parameterValue, new UserRowMapper<T>(clazz));
	}
	public <T> List<T> findListToBean(String sql,int start,int limit ,Object[]  parameterValue,final Class<T> clazz) {
		return jdbcTemplate.query(formatPageSql(sql, start, limit),parameterValue, new BeanPropertyRowMapper<T>(clazz));
	}
	public <T> List<T> findListToBean(String sql,Object[]  parameterValue,final Class<T> clazz) {
		return jdbcTemplate.query(sql,parameterValue, new BeanPropertyRowMapper<T>(clazz));
	}
	public void executeSql(String sql){
		jdbcTemplate.execute(sql);
	}
	public int updateSql(String sql){
		return jdbcTemplate.update(sql);
	}
	public int[] updateSql(String[] sql){
		return jdbcTemplate.batchUpdate(sql);
	}
	public int[] updateSql(List<String> listSql){
//		String[] sql = new String[listSql.size()];
//		for (int i = 0; i < listSql.size(); i++) {
//			sql[i] = listSql.get(i);
//		}
		String[] sql = listSql.toArray(new String[0]);
		return jdbcTemplate.batchUpdate(sql);
	}
	public List<Map<String, Object>> findListMap(String sql,Object[]  parameterValue) {
		return jdbcTemplate.queryForList(sql,parameterValue);
	}

	private String formatPageSql(String sql, int start, int limit){
		//mysql
//		StringBuffer sbf = new StringBuffer();
//		sbf.append("select AA.* ");
//		sbf.append(" FROM ( ").append(sql).append("  )  AA ");
//		sbf.append("  LIMIT ").append(start);
//		sbf.append("  ,").append(limit);
		//oracle
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT * FROM ");
		sbf.append(" (");
		sbf.append(" SELECT A.*, ROWNUM RN ");
		sbf.append(" FROM ("+sql+") A ");
		sbf.append(" WHERE ROWNUM <="+(start+limit));
		sbf.append(" )");
		sbf.append(" WHERE RN > "+start);
		
		return sbf.toString();
	}
	public int getTotal(String sql,Object...  parameterValue) {
		String countSql = MessageFormat.format(
				"SELECT COUNT(*) COU FROM ( {0} ) DAS_TOTAL", sql);
		return jdbcTemplate.queryForInt(countSql,parameterValue);
	}
}
