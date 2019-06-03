package com.woniu.dao;

import java.util.List;

import com.woniu.bean.Gtype;

public interface IGtypeDAO {
	void create(Gtype gtype);
	Gtype retrieveOne(int tid);
	List<Gtype> retrieveAll();
	List<Gtype> retrieveCondition(String field, Object value);
	void update(Gtype gtype);
	void delete(int tid);
}
