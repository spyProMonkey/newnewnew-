package com.woniu.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.woniu.Service.ServiceFactory;
import com.woniu.bean.Empty;
import com.woniu.bean.Goods;
import com.woniu.bean.PageBean;
import com.woniu.dao.DAOFactory;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GoodsList
 */
@WebServlet("/GoodsServlet.do")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method==null||"goodsList".equals(method)) {
			getList(true,request,response);
		}else if("addGoods".equals(method)) {
			addGoods(request,response);
		}else if("delGoods".equals(method)) {
			delGoods(request,response);
		}else if("editGoods".equals(method)) {
			editGoods(request,response);
		}else if("queryLike".equals(method)) {
			queryLike(request,response);
		}else if("turnPage".equals(method)) {
			getList(false,request,response);
		}else if("turnSaleing".equals(method)) {
			turnSaleing(request,response);
		}
	}

	private void turnSaleing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int gid = Integer.parseInt(request.getParameter("gid"));
		ServiceFactory.getGoodsService().changeSaleing(gid);
		getList(false,request,response);
	}

	private void queryLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer sql = new StringBuffer("");
		boolean first = false;
		String name = request.getParameter("likename");
		String brand = request.getParameter("likebrand");
		String tid = request.getParameter("liketype");
		String bottom = request.getParameter("bottom");
		String top = request.getParameter("top");
		if(!name.equals("")) {
			name = name.trim();
			sql.append(" gname LIKE '%"+name+"%'");
			first = true;
		}
		if(!brand.equals("")) {
			brand = brand.trim();
			sql.append((first?" AND ":"")+" brand LIKE '%"+brand+"%'");
			first = true;
		}
		try {
			if(!tid.equals("")) {
				Integer.parseInt(tid);
				sql.append((first?" AND ":"")+" tid="+tid);
				first = true;
			}
			if(!bottom.equals("")) {
				Integer.parseInt(bottom);
				sql.append((first?" AND ":"")+" price>="+bottom);
				first = true;
			}
			if(!top.equals("")) {
				Integer.parseInt(top);
				sql.append((first?" AND ":"")+" price<="+top);
			}
		} catch (NumberFormatException e) {
			response.sendRedirect("GoodsList.jsp");
		}
		if(!first) {
			response.sendRedirect("GoodsList.jsp");
		}
		List<Goods> goods = ServiceFactory.getGoodsService().queryCondition(sql.toString());
		PageBean pb = new PageBean(1, 0);
		Map<String,Object> map = new HashMap<>();
		map.put("pageBean", pb);
		if(goods!=null) {
			map.put("list", goods);
		}else {
			map.put("list", Empty.getEmpty());
		}
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().write(json.toString());
		response.getWriter().flush();
	}

	private void editGoods(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		List<FileItem> items = null;
		int gid = 0;
		Goods goods = null;
		String image = null;
		try {
			items = sfu.parseRequest(request);
			gid = Integer.parseInt(items.get(0).getString());
			goods = DAOFactory.getGoodsDAO().retrieveOne(gid);
			String path = this.getServletContext().getRealPath("/photo");
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			FileItem item = items.get(6);
			if(!item.getName().equals("")) {
				image = path+File.separator+gid+item.getName().substring(item.getName().lastIndexOf("."));
				item.write(new File(image));
				goods.setImage(image.substring(image.lastIndexOf("\\photo")));
			}
		} catch (FileUploadException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		goods.setGname(items.get(1).getString("utf-8"));
		goods.setBrand(items.get(2).getString("utf-8"));
		goods.setTid(Integer.parseInt(items.get(3).getString()));
		goods.setPrice(Double.parseDouble(items.get(4).getString()));
		goods.setUnit(items.get(5).getString("utf-8"));
		goods.setSaleing(Boolean.parseBoolean(items.get(7).getString()));
		goods.setPriority(Integer.parseInt(items.get(8).getString()));
		ServiceFactory.getGoodsService().editGoods(goods);
		response.sendRedirect("GoodsList.jsp");
	}

	private void delGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServiceFactory.getGoodsService().delGoods(Integer.parseInt(request.getParameter("gid")));
		response.sendRedirect("GoodsList.jsp");
	}

	private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		List<FileItem> items = null;
		int gid = 0;
		String image = null;
		try {
			items = sfu.parseRequest(request);
			gid = Integer.parseInt(items.get(0).getString());
			String path = this.getServletContext().getRealPath("/photo");
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			FileItem item = items.get(6);
			if(!item.getName().equals("")) {
				image = path+File.separator+gid+item.getName().substring(item.getName().lastIndexOf("."));
				item.write(new File(image));
			}
		} catch (FileUploadException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(image!=null) {
			image = image.substring(image.lastIndexOf("\\photo"));
		}else {
			image = "无图片";
		}
		String gname = items.get(1).getString("utf-8");
		String brand = items.get(2).getString("utf-8");
		int tid = Integer.parseInt(items.get(3).getString());
		double price = Double.parseDouble(items.get(4).getString());
		String unit = items.get(5).getString("utf-8");
		boolean saleing = Boolean.parseBoolean(items.get(7).getString());
		int priority = Integer.parseInt(items.get(8).getString());
		Goods goods = new Goods(gid, gname, brand, tid, price, unit, image, saleing, priority);
		ServiceFactory.getGoodsService().addGoods(goods);
		response.sendRedirect("GoodsList.jsp");
	}
	
	private void getList(boolean isFirst, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Goods> goods = ServiceFactory.getGoodsService().showGoods();
		PageBean pb = null;
		JSONObject json = null;
		if(goods!=null) {
			int pageNum = isFirst?1:Integer.parseInt(request.getParameter("pageNum"));
			pb = new PageBean(pageNum, goods.size());
			List<Goods> goodsPage = new ArrayList<Goods>();
			for (int i = (pb.getPage()-1)*pb.getPageRow(); i < Math.min(pb.getPage()*pb.getPageRow(), goods.size()); i++) {
				goodsPage.add(goods.get(i));
			}
			Map<String,Object> map = new HashMap<>();
			map.put("list", goodsPage);
			map.put("pageBean", pb);
			json = JSONObject.fromObject(map);
		}else {
			pb = new PageBean(1, 0);
			Map<String,Object> map = new HashMap<>();
			map.put("list", Empty.getEmpty());
			map.put("pageBean", pb);
			json = JSONObject.fromObject(map);
		}
		response.getWriter().write(json.toString());
		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
