package com.woniu.dao;

import java.util.List;

import com.woniu.bean.Gtype;
import com.woniu.util.BaseDAO;

public class GtypeDAOImpl implements IGtypeDAO {
	BaseDAO<Gtype> bd = new BaseDAO<>();
	@Override
	public void create(Gtype gtype) {
		String sql = "INSERT INTO gtype VALUE(?,?)";
		Object[] objs= {gtype.getTid(),gtype.getTname()};
		bd.baseUpdate(sql, objs);
	}

	@Override
	public Gtype retrieveOne(int tid) {
		String sql = "SELECT * FROM gtype WHERE tid=?";
		Object[] objs= {tid};
		List<Gtype> ts = bd.baseQuery(sql, objs, Gtype.class);
		return ts.size()==0?null:ts.get(0);
	}
	
	@Override
	public List<Gtype> retrieveCondition(String field, Object value) {
		String sql = "SELECT * FROM gtype WHERE "+field+"=?";
		Object[] objs = {value};
		List<Gtype> ts = bd.baseQuery(sql, objs, Gtype.class);
		return ts.size()==0?null:ts;
	}

	@Override
	public List<Gtype> retrieveAll() {
		String sql = "SELECT * FROM gtype";
		Object[] objs = {};
		List<Gtype> gs = bd.baseQuery(sql, objs, Gtype.class);
		return gs.size()==0?null:gs;
	}

	@Override
	public void update(Gtype gtype) {
		String sql = "UPDATE gtype SET tname=? WHERE tid=?";
		Object[] objs= {gtype.getTname(),gtype.getTid()};
		bd.baseUpdate(sql, objs);
	}

	@Override
	public void delete(int tid) {
		String sql = "DELETE FROM gtype WHERE tid=?";
		Object[] objs = {tid};
		bd.baseUpdate(sql, objs);
	}
}
