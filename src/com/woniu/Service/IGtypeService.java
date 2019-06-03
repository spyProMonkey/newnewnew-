package com.woniu.Service;

import java.util.List;

import com.woniu.bean.Gtype;

public interface IGtypeService {
//	商品类型查看
	List<Gtype> showTypes();
//	商品类型添加
	void addType(Gtype gtype);
//	商品类型修改
	void editType(Gtype gtype);
//	商品类型删除
	void delType(int tid);
}
