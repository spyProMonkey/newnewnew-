package com.woniu.Service;

import java.util.List;

import com.woniu.bean.Goods;
import com.woniu.bean.Stock;
import com.woniu.dao.DAOFactory;

public class StockServiceImpl implements IStockService {
	
	@Override
	public List<Stock> showStocks() {
		return DAOFactory.getStockDAO().retrieveAll();
	}

	@Override
	public void addStock(Stock stock) {
		DAOFactory.getStockDAO().create(stock);
	}

	@Override
	public void editStock(Stock stock) {
		DAOFactory.getStockDAO().update(stock);
	}

	@Override
	public void delStock(int sid) {
		DAOFactory.getStockDAO().delete(sid);
	}

	@Override
	public List<Stock> showLike(String gname) {
		String sql = " LIKE '%"+gname+"%'";
		List<Goods> goods = new GoodsServiceImpl().queryCondition(sql);
		StringBuffer gid = new StringBuffer("(");
		for (int i = 0; i < goods.size(); i++) {
			if(i!=0) {
				gid.append(",");
			}
			gid.append(goods.get(i).getGid());
		}
		gid.append(")");
		return DAOFactory.getStockDAO().retrieveCondition("gid IN ", gid.toString());
	}

}
