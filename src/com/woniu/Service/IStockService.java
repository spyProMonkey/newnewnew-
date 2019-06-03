package com.woniu.Service;

import java.util.List;

import com.woniu.bean.Stock;

public interface IStockService {
//	进货查看
	List<Stock> showStocks();
//	进货添加
	void addStock(Stock stock);
//	进货编辑
	void editStock(Stock stock);
//	进货删除
	void delStock(int sid);
//	进货按商品名查询
	List<Stock> showLike(String gName);
}
