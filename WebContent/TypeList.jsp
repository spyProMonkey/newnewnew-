<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品类型列表</title>
<script src="http://code.jquery.com/jquery-3.4.1.js" ></script>
<script>
	function showList(data){
		$("#typeList").empty();
		var list = eval("("+data+")");
		$.each(list,function(index,type){
			var tr = $("<tr></tr>");
			var td1 = $("<td></td>");
			td1.text(index+1);
			tr.append(td1);
			var td2 = $("<td></td>");
			td2.text(type.tid);
			tr.append(td2);
			var td3 = $("<td></td>");
			td3.text(type.tname);
			tr.append(td3);
			var td4 = $("<td></td>");
			var edt = $("<button>编辑</button>");
			edt.click(function(){
				$("#hidden").show();
				$("#btn").text("编辑");
				$("#hidden").attr("action","GtypeServlet.do?method=editType");
				$("[name=tid]").val(type.tid);
				$("[name=tname]").val(type.tname);
			});
			td4.append(edt);
			tr.append(td4);
			var td5 = $("<td></td>");
			var del = $("<button>删除</button>");
			del.click(function(){
				if(confirm("是否删除类型："+type.tname+"？")){
					location.href="GtypeServlet.do?method=delType&tid="+type.tid;
				}
			});
			td5.append(del);
			tr.append(td5);
			$("#typeList").append(tr);
		});
	}
	function addType(){
		$("#hidden").show();
		$("#btn").text("添加");
		$("#hidden").attr("action","GtypeServlet.do?method=addType");
	}
	$(function(){
		$("#hidden").hide();
		$.post("GtypeServlet.do",{"method":"typeList"},function(data,status){
			showList(data);
		});
	});
</script>
</head>
<body>
	<a href="GoodsList.jsp">商品列表</a>&nbsp;&nbsp;
	<a href="StockList.jsp">进货明细</a><br/>
	<button type="button" onclick="addType();">添加商品类型</button><br />
	<form action="GtypeServlet.do?method=importTypes" method="post" enctype="multipart/form-data">
		<input type="file" name="excel" /><button>导入Excel数据</button>
	</form>
	<table border="1" align="center" width="700">
		<caption>商品列表</caption>
		<thead>
			<tr>
				<th>序号</th>
				<th>商品类型编号</th>
				<th>商品类型名称</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
		</thead>
		<tbody id="typeList"></tbody>
	</table>
	<form action="" method="post" id="hidden">
		<table align="center">
			<tr>
				<td>商品类型编号：</td><td><input type="text" name="tid"/></td>
			</tr>
			<tr>
				<td>商品类型名称：</td><td><input type="text" name="tname"/></td>
			</tr>
			<tr><td colspan="2"><button id="btn">添加</button></td></tr>
		</table>
	</form>
</body>
</html>