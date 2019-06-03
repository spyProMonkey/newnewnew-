<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>进货明细</title>
<script src="http://code.jquery.com/jquery-3.4.1.js" ></script>
<script>
	function showList(data){
		$("#stockList").empty();
		var list = eval("("+data+")");
		$.each(list,function(index,stock){
			var tr = $("<tr></tr>");
			var td1 = $("<td></td>");
			td1.text(index+1);
			tr.append(td1);
			var td2 = $("<td></td>");
			td2.text(stock.sid);
			tr.append(td2);
			var td3 = $("<td></td>");
			td3.text(stock.gid);
			tr.append(td3);
			var td4 = $("<td></td>");
			td4.text(stock.amount);
			tr.append(td4);
			var td5 = $("<td></td>");
			td5.text(stock.inprice);
			tr.append(td5);
			var td6 = $("<td></td>");
			td6.text(stock.sdate);
			tr.append(td6);
			var td7 = $("<td></td>");
			td7.text(stock.supplier);
			tr.append(td7);
			var td8 = $("<td></td>");
			td8.text(stock.purchaser);
			tr.append(td8);
			var td9 = $("<td></td>");
			var edt = $("<button>编辑</button>");
			edt.click(function(){
				$("form").show();
				$("#btn").text("编辑");
				$("form").attr("action","StockServlet.do?method=editStock");
				$("[name=tid]").val(type.tid);
				$("[name=tname]").val(type.tname);
			});
			td9.append(edt);
			tr.append(td9);
			var td10 = $("<td></td>");
			var del = $("<button>删除</button>");
			del.click(function(){
				if(confirm("是否删除记录"+stock.sid+"？")){
					location.href="StockServlet.do?method=delStock&sid="+stock.sid;
				}
			});
			td10.append(del);
			tr.append(td10);
			$("#stockList").append(tr)
		});
		$("td").attr("align","center");
	}
	$(function(){
		$("form").hide();
		$.post("StockServlet.do",{"method":"stockList"},function(data,status){
			showList(data);
		});
	});
</script>
</head>
<body>
	<a href="TypeList.jsp">商品类型列表</a>&nbsp;&nbsp;
	<a href="GoodsList.jsp">商品列表</a><br /><br />
	<button type="button" onclick="">添加进货记录</button>
	<table border="1" align="center" width="700">
		<caption>进货明细</caption>
		<thead>
			<tr>
				<th>序号</th>
				<th>进货编号</th>
				<th>商品编号</th>
				<th>进货数量</th>
				<th>进货价</th>
				<th>进货日期</th>
				<th>供应商</th>
				<th>进货人</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
		</thead>
		<tbody id="stockList"></tbody>
	</table>
	<form action="">
		<table align="center">
			<tr><td>进货编号</td><td><input type="text" name="sid"/></td><td>进货日期</td><td><select name="sdate"></select></td></tr>
			<tr><td>商品编号</td><td><input type="text" name="gid"/></td><td>供应商</td><td><input type="text" name="supplier"/></td></tr>
			<tr><td>进货数量</td><td><input type="text" name="amount"/></td><td>进货人</td><td><input type="text" name="purchaser"/></td></tr>
			<tr><td>进货价</td><td><input type="text" name="inprice"/></td><td colspan="2"><button id="btn"></button></td></tr>
		</table>
	</form>
</body>
</html>