package com.yjy.bsq.common.base;


import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;


public class UserRowMapper<T> implements RowMapper<T>{

	private Class<T> requiredType;
	
	public UserRowMapper(Class<T> requiredType) {
		this.requiredType = requiredType;
	}
	
	public static <T> UserRowMapper<T> newInstance(Class<T> clazz) {
		UserRowMapper<T> newInstance = new UserRowMapper<T>(clazz);
		return newInstance;
	}
	
	
	@Override
	public T mapRow(ResultSet arg0, int arg1) throws SQLException {
		init(arg0.getMetaData());
		T tt = null;
		try {
			tt= requiredType.newInstance();
		} catch (Exception e) {
			System.err.println("newInstance error");
			return null;
		}
		for (int i = 0; i < count; i++) {
			try {
				if(methods[i] == null) continue;
				String parameterType = methods[i].getParameterTypes()[0].getName();
				if (parameterType.equals(String.class.getName())) {
					methods[i].invoke(tt, arg0.getString(i+1));
				} else if (parameterType.equals(Integer.class.getName()) || parameterType.equals(int.class.getName())) {
					if (arg0.getObject(i+1)!=null) {
						methods[i].invoke(tt, arg0.getInt(i+1));
					}
				} else if (parameterType.equals(double.class.getName())||parameterType.equals(Double.class.getName())) {
					methods[i].invoke(tt, arg0.getDouble(i+1));
				} else if (parameterType.equals(Date.class.getName())) {
					methods[i].invoke(tt, arg0.getTimestamp(i+1));
				} else if (parameterType.equals(java.sql.Date.class.getName())) {
					methods[i].invoke(tt, arg0.getDate(i+1));
				}
				                               
				
			} catch (Exception e) {
				System.err.println("invoke error");
			}
		}
		return tt;
	}
	boolean initFlag = false;
	Method[] methods = null;
	int count = 0;
	private void init(ResultSetMetaData md) throws SQLException {
		if (!initFlag) {
			initFlag = true;
			count = md.getColumnCount();
			methods = new Method[count];
			Method m[] =  requiredType.getMethods();
			String temp = null;
			for (int i = 0; i < count; i++) {
				for (int j = 0; j < m.length; j++) {
					temp = md.getColumnLabel(i+1).replace("_", "");
					if (m[j].getName().equalsIgnoreCase("set"+temp)) {
						m[j].setAccessible(true);
						methods[i] = m[j];
						break;
					}
				}
			}
		}
	}
}

