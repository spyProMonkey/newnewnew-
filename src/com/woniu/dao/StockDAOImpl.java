package com.woniu.dao;

import java.util.List;

import com.woniu.bean.Stock;
import com.woniu.util.BaseDAO;

public class StockDAOImpl implements IStockDAO {
	BaseDAO<Stock> bd = new BaseDAO<>();
	@Override
	public void create(Stock stock) {
		String sql = "INSERT INTO stock VALUE(?,?,?,?,?,?,?)";
		Object[] objs= {stock.getSid(),stock.getGid(),stock.getAmount(),stock.getInprice(),
						stock.getSdate(),stock.getSupplier(),stock.getPurchaser()};
		bd.baseUpdate(sql, objs);
	}

	@Override
	public Stock retrieveOne(int sid) {
		String sql = "SELECT * FROM stock WHERE sid=?";
		Object[] objs = {sid};
		List<Stock> ss = bd.baseQuery(sql, objs, Stock.class);
		return ss.size()==0?null:ss.get(0);
	}
	
	@Override
	public List<Stock> retrieveCondition(String field, Object value) {
		String sql = "SELECT * FROM stock WHERE "+field+"?";
		Object[] objs = {value};
		List<Stock> ss = bd.baseQuery(sql, objs, Stock.class);
		return ss.size()==0?null:ss;
	}

	@Override
	public List<Stock> retrieveAll() {
		String sql = "SELECT * FROM stock";
		Object[] objs = {};
		List<Stock> ss = bd.baseQuery(sql, objs, Stock.class);
		return ss.size()==0?null:ss;
	}

	@Override
	public void update(Stock stock) {
		String sql = "UPDATE stock SET gid=?,amount=?,inprice=?,sdate=?,supplier=?,purchaser=? WHERE sid=?";
		Object[] objs= {stock.getGid(),stock.getAmount(),stock.getInprice(),
						stock.getSdate(),stock.getSupplier(),stock.getPurchaser(),stock.getSid()};
		bd.baseUpdate(sql, objs);
	}

	@Override
	public void delete(int sid) {
		String sql = "DELETE FROM stock WHERE sid=?";
		Object[] objs = {sid};
		bd.baseUpdate(sql, objs);
	}

}
