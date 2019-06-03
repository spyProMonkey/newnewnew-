package com.woniu.Service;

import java.util.List;

import com.woniu.bean.Goods;
import com.woniu.dao.DAOFactory;

public class GoodsServiceImpl implements IGoodsService {

	@Override
	public List<Goods> showGoods() {
		return DAOFactory.getGoodsDAO().retrieveAll();
	}

	@Override
	public void addGoods(Goods goods) {
		DAOFactory.getGoodsDAO().create(goods);
	}

	@Override
	public void editGoods(Goods goods) {
		DAOFactory.getGoodsDAO().update(goods);
	}

	@Override
	public void delGoods(int gid) {
		DAOFactory.getGoodsDAO().delete(gid);
	}

	@Override
	public void changeSaleing(int gid) {
		Goods goods = DAOFactory.getGoodsDAO().retrieveOne(gid);
		if(goods!=null) {
			if(goods.isSaleing()) {
				goods.setSaleing(false);
			}else {
				goods.setSaleing(true);
			}
		}
		DAOFactory.getGoodsDAO().update(goods);
	}

	@Override
	public List<Goods> queryCondition(String condition) {
		return DAOFactory.getGoodsDAO().retrieveCondition(condition);
	}
}
