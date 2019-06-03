package com.woniu.dao;

import java.util.List;

import com.woniu.bean.Goods;
import com.woniu.util.BaseDAO;

public class GoodsDAOImpl implements IGoodsDAO {
	BaseDAO<Goods> bd = new BaseDAO<>();
	@Override
	public void create(Goods goods) {
		String sql = "INSERT INTO goods VALUE(?,?,?,?,?,?,?,?,?)";
		Object[] objs= {goods.getGid(),goods.getGname(),goods.getBrand(),goods.getTid(),
						goods.getPrice(),goods.getUnit(),goods.getImage(),goods.isSaleing(),
						goods.getPriority()};
		bd.baseUpdate(sql, objs);
	}

	@Override
	public Goods retrieveOne(int gid) {
		String sql = "SELECT * FROM goods WHERE gid=?";
		Object[] objs = {gid};
		List<Goods> gs = bd.baseQuery(sql, objs, Goods.class);
		return gs.size()==0?null:gs.get(0);
	}

	@Override
	public List<Goods> retrieveAll() {
		String sql = "SELECT * FROM goods";
		Object[] objs = {};
		List<Goods> gs = bd.baseQuery(sql, objs, Goods.class);
		return gs.size()==0?null:gs;
	}
	
	@Override
	public List<Goods> retrieveCondition(String condition) {
		String sql = "SELECT * FROM goods WHERE "+condition;
		Object[] objs = {};
		List<Goods> gs = bd.baseQuery(sql, objs, Goods.class);
		return gs.size()==0?null:gs;
	}

	@Override
	public void update(Goods goods) {
		String sql = "UPDATE goods SET gname=?,brand=?,tid=?,price=?,unit=?,image=?,saleing=?,priority=? WHERE gid=?";
		Object[] objs= {goods.getGname(),goods.getBrand(),goods.getTid(),
						goods.getPrice(),goods.getUnit(),goods.getImage(),goods.isSaleing(),
						goods.getPriority(),goods.getGid()};
		bd.baseUpdate(sql, objs);
	}

	@Override
	public void delete(int gid) {
		String sql = "DELETE FROM goods WHERE gid=?";
		Object[] objs = {gid};
		bd.baseUpdate(sql, objs);
	}
}
