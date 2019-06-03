package com.woniu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.woniu.Service.ServiceFactory;
import com.woniu.bean.Empty;
import com.woniu.bean.Gtype;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GoodsList
 */
@WebServlet("/GtypeServlet.do")
public class GtypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method==null||"typeList".equals(method)) {
			typeList(request,response);
		}else if("addType".equals(method)) {
			addType(request,response);
		}else if("editType".equals(method)) {
			editType(request,response);
		}else if("delType".equals(method)) {
			delType(request,response);
		}else if("importTypes".equals(method)) {
			importTypes(request,response);
		}
	}

	private void importTypes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		FileItem item = null;
		String name = null;
		try {
			item = sfu.parseRequest(request).get(0);
			name = item.getName();
			if(!"".equals(name)) {
				String excelTemp = request.getServletContext().getRealPath("/excelTemp");
				File dir = new File(excelTemp);
				if(!dir.exists()){
					System.out.println(excelTemp);
					dir.mkdirs();
				}
				File targetFile = new File(dir,name);
				item.write(targetFile);
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String path = request.getServletContext().getRealPath("/excelTemp/"+name);
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		POIFSFileSystem poi = new POIFSFileSystem(fis);
		HSSFWorkbook wb = new HSSFWorkbook(poi);
		HSSFSheet sheet = wb.getSheetAt(0);
		for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
			HSSFRow row = sheet.getRow(i);
			if(row==null){
				continue;
			}
			Gtype type = new Gtype((int)row.getCell((short) 0).getNumericCellValue(), row.getCell((short) 1).getRichStringCellValue().toString());
			ServiceFactory.getGtypeService().addType(type);
		}
		response.sendRedirect("TypeList.jsp");
	}

	private void delType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServiceFactory.getGtypeService().delType(Integer.parseInt(request.getParameter("tid")));
		response.sendRedirect("TypeList.jsp");
	}

	private void editType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServiceFactory.getGtypeService().editType(new Gtype(Integer.parseInt(request.getParameter("tid")), request.getParameter("tname")));
		response.sendRedirect("TypeList.jsp");
	}

	private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServiceFactory.getGtypeService().addType(new Gtype(Integer.parseInt(request.getParameter("tid")), request.getParameter("tname")));
		response.sendRedirect("TypeList.jsp");
	}

	private void typeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Gtype> types = ServiceFactory.getGtypeService().showTypes();
		JSONArray json = null;
		if(types!=null) {
			json = JSONArray.fromObject(types);
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
		doGet(request, response);
	}
}
