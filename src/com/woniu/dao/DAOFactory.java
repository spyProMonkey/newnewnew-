package com.woniu.dao;

public class DAOFactory {
	public static GoodsDAOImpl getGoodsDAO() {
		return new GoodsDAOImpl();
	}
	public static GtypeDAOImpl getGtypeDAO() {
		return new GtypeDAOImpl();
	}
	public static StockDAOImpl getStockDAO() {
		return new StockDAOImpl();
	}
}
