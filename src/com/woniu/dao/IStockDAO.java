package com.woniu.dao;

import java.util.List;

import com.woniu.bean.Stock;

public interface IStockDAO {
	void create(Stock stock);
	Stock retrieveOne(int sid);
	List<Stock> retrieveCondition(String field, Object value);
	List<Stock> retrieveAll();
	void update(Stock stock);
	void delete(int sid);
}
