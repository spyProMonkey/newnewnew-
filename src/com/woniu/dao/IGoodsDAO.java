package com.woniu.dao;

import java.util.List;

import com.woniu.bean.Goods;

public interface IGoodsDAO {
	void create(Goods goods);
	Goods retrieveOne(int gid);
	List<Goods> retrieveAll();
	List<Goods> retrieveCondition(String condition);
	void update(Goods goods);
	void delete(int gid);
}
