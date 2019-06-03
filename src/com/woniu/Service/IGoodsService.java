package com.woniu.Service;

import java.util.List;

import com.woniu.bean.Goods;

public interface IGoodsService {
//  商品查看
	List<Goods> showGoods();
//	商品添加
	void addGoods(Goods goods);
//	商品编辑
	void editGoods(Goods goods);
//	商品删除
	void delGoods(int gid);
//	商品上下架
	void changeSaleing(int gid);
//	商品条件查询
	List<Goods> queryCondition(String condition);
}
