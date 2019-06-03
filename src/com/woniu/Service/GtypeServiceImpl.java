package com.woniu.Service;

import java.util.List;

import com.woniu.bean.Gtype;
import com.woniu.dao.DAOFactory;

public class GtypeServiceImpl implements IGtypeService {

	@Override
	public List<Gtype> showTypes() {
		return DAOFactory.getGtypeDAO().retrieveAll();
	}

	@Override
	public void addType(Gtype gtype) {
		DAOFactory.getGtypeDAO().create(gtype);
	}

	@Override
	public void editType(Gtype gtype) {
		DAOFactory.getGtypeDAO().update(gtype);
	}

	@Override
	public void delType(int tid) {
		DAOFactory.getGtypeDAO().delete(tid);
	}
}
