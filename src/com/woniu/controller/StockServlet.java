package com.woniu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniu.Service.ServiceFactory;
import com.woniu.bean.Empty;
import com.woniu.bean.Stock;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class StockServlet
 */
@WebServlet("/StockServlet.do")
public class StockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method==null||"stockList".equals(method)) {
			stockList(request,response);
		}else if("addStock".equals(method)) {
			addStock(request,response);
		}else if("editStock".equals(method)) {
			editStock(request,response);
		}else if("delStock".equals(method)) {
			delStock(request,response);
		}
	}

	private void delStock(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void editStock(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addStock(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void stockList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Stock> stocks = ServiceFactory.getStockService().showStocks();
		JSONArray json = null;
		if(stocks!=null) {
			json = JSONArray.fromObject(stocks);
		}else {
			json = JSONArray.fromObject(Empty.getEmpty());
		}
		response.getWriter().write(json.toString());
		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
