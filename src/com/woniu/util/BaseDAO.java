package com.woniu.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseDAO<T> {
	public void baseUpdate(String sql, Object[] objs) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < objs.length; i++) {
				ps.setObject(i+1, objs[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(conn, ps, null);
		}
	}
	
	public List<T> baseQuery(String sql, Object[] objs, Class<T> c){
		List<T> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < objs.length; i++) {
				ps.setObject(i+1, objs[i]);
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				T t = c.newInstance();
				Method[] ms = c.getMethods();
				for (int i = 0; i < ms.length; i++) {
					String mname = ms[i].getName();
					if(mname.startsWith("set")) {
						Method m = ms[i];
						Class<?> type = m.getParameterTypes()[0];
						if(type==int.class) {
							m.invoke(t, rs.getInt(mname.substring(3)));
						}else if(type==String.class) {
							m.invoke(t, rs.getString(mname.substring(3)));
						}else if(type==double.class) {
							m.invoke(t, rs.getDouble(mname.substring(3)));
						}else if(type==Date.class) {
							m.invoke(t, rs.getDate(mname.substring(3)));
						}else if(type==boolean.class) {
							m.invoke(t, rs.getBoolean(mname.substring(3)));
						}
					}
				}
				list.add(t);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(conn, ps, rs);
		}
		return list;
	}
}
