package com.woniu.Service;

public class ServiceFactory {
	public static GoodsServiceImpl getGoodsService() {
		return new GoodsServiceImpl();
	}
	public static GtypeServiceImpl getGtypeService() {
		return new GtypeServiceImpl();
	}
	public static StockServiceImpl getStockService() {
		return new StockServiceImpl();
	}
}
