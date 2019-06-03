<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品列表</title>
<script src="http://code.jquery.com/jquery-3.4.1.js"></script>
<script>
	function showList(dataMap){
		$("#goodsList").empty();
		var data = eval("("+dataMap+")");
		$.each(data.list,function(index,one){
			var tr = $("<tr></tr>");
			var td1 = $("<td></td>");
			td1.text(index+1);
			tr.append(td1);
			var td2 = $("<td></td>");
			td2.text(one.gid);
			tr.append(td2);
			var td3 = $("<td></td>");
			td3.text(one.gname);
			tr.append(td3);
			var td4 = $("<td></td>");
			td4.text(one.brand);
			tr.append(td4);
			var td5 = $("<td></td>");
			$.post("GtypeServlet.do",{"method":"typeList"},function(data,status){
				var types = eval("("+data+")");
				$.each(types,function(index,type){
					if(one.tid==type.tid){
						td5.text(type.tname);
					}
				});
			});
			td5.text(one.tid);
			tr.append(td5);
			var td6 = $("<td></td>");
			td6.text(one.price);
			tr.append(td6);
			var td7 = $("<td></td>");
			td7.text(one.unit);
			tr.append(td7);
			var td8 = $("<td></td>");
			if(one.image!=""){
				var imageTag = $("<img width='70' height='70'/>");
				imageTag.attr("alt",one.gname);
				imageTag.attr("src","<%=request.getContextPath()%>"+one.image);
				td8.append(imageTag);
			}else{
				td8.text("无");
			}
			tr.append(td8);
			var td9 = $("<td></td>");
			var change = $("<button type='button' onclick='changeSale("+one.gid+","+data.pageBean.page+");'>"+(one.saleing?"是":"否")+"</button>");
			td9.append(change);
			tr.append(td9);
			var td10 = $("<td></td>");
			td10.text(one.priority);
			tr.append(td10);
			var td11 = $("<td></td>");
			var edt = $("<button>编辑</button>");
			edt.click(function(){
				$("#hidden").show();
				$("#btn").text("编辑");
				$("#hidden").attr("action","GoodsServlet.do?method=editGoods");
				$("[name=gid]").val(one.gid);
				$("[name=gid]").prop("readonly",true);
				$("[name=gname]").val(one.gname);
				$("[name=brand]").val(one.brand);
				$("[name=gtype]").val(one.tid);
				$("[name=price]").val(one.price);
				$("[name=unit]").val(one.unit);
				$("[name=image]").attr("src","<%=request.getContextPath()%>"+one.image);
				$("[name=saleing]").prop("value",one.saleing?"true":"false");
				$("[name=priority]").val(one.priority);
			});
			td11.append(edt);
			tr.append(td11);
			var td12 = $("<td></td>");
			var del = $("<button>删除</button>");
			del.click(function(){
				if(confirm("是否删除商品："+one.gname+"？")){
					location.href="GoodsServlet.do?method=delGoods&gid="+one.gid;
				}
			});
			td12.append(del);
			tr.append(td12);
			$("#goodsList").append(tr);
		});
		var pageTr = $("<tr></tr>");
		var pageTd = $("<td colspan='12'></td>");
		var lastpn = data.pageBean.countPage;
		var fPage = $("<button type='button' onclick='trunPage(1);'>首页</button>");
		var prePage = $("<button type='button' onclick='trunPage(" + (data.pageBean.page>1?(data.pageBean.page-1):1) + ");'>上一页</button>");
		var nextPage = $("<button type='button' onclick='trunPage("+(data.pageBean.page<lastpn?(data.pageBean.page+1):lastpn)+");'>下一页</button>");
		var lPage = $("<button type='button' onclick='trunPage("+lastpn+");'>尾页</button>");
		pageTd.append(fPage);
		pageTd.append(prePage);
		pageTd.append($("<span>  当前第"+data.pageBean.page+"/"+data.pageBean.countPage+"页   </span>"));
		pageTd.append(nextPage);
		pageTd.append(lPage);
		pageTd.append( $("<span>  共"+data.pageBean.countRow+"条数据 </span>"));
		pageTr.append(pageTd);
		$("#goodsList").append(pageTr);
		$("td").attr("align","center");
	}
	
	function addGoods(){
		$("#hidden").show();
		$("#btn").text("添加");
		$("#hidden").attr("action","GoodsServlet.do?method=addGoods");
		$("[name=gid]").val("");
		$("[name=gid]").prop("readonly",false);
		$("[name=gname]").val("");
		$("[name=brand]").val("");
		$("[name=price]").val("");
		$("[name=unit]").val("");
		$("[name=img]").val("");
		$("[name=image]").attr("src","");
		$("[name=image]").attr("alt","图片");
		$("[name=saleing]").val("true");
		$("[name=priority]").val("1");
	}
	
	function queryLike(){
		$.post("GoodsServlet.do",{"method":"queryLike","likename":$("[name=likename]").val(),
			"likebrand":$("[name=likebrand]").val(),"liketype":$("[name=liketype]").val(),
			"bottom":$("[name=bottom]").val(),"top":$("[name=top]").val()},function(data,status){
			showList(data);
		});
	}
	
	function trunPage(pageNum){
		$.post("GoodsServlet.do",{"method":"turnPage","pageNum":pageNum},function(data,status){
			showList(data);
		});
	}
	
	function changeSale(gid,pageNum){
		$.post("GoodsServlet.do",{"method":"turnSaleing","gid":gid,"pageNum":pageNum},function(data,status){
			showList(data);
		});
	}
	
	$(document).ready(function(){
		$("#hidden").hide();
		$("#btn").click(function(){
			$("#hidden").hide();
		});
		$.post("GoodsServlet.do",{"method":"goodsList"},function(dataMap,status){
			showList(dataMap);
		});
		var sele = $("[name=gtype]");
		$.post("GtypeServlet.do",{"method":"typeList"},function(data,status){
			var types = eval("("+data+")");
			$.each(types,function(index,type){
				var op = $("<option></option>");
				op.val(type.tid);
				op.text(type.tname);
				sele.append(op);
			});
		});
		var selet = $("[name=liketype]");
		selet.empty();
		$.post("GtypeServlet.do",{"method":"typeList"},function(data,status){
			var types = eval("("+data+")");
			$.each(types,function(index,type){
				var op = $("<option></option>");
				op.val(type.tid);
				op.text(type.tname);
				selet.append(op);
			});
		});
	});
</script>
</head>
<body>
	<a href="TypeList.jsp">商品类型列表</a>&nbsp;&nbsp;
	<a href="StockList.jsp">进货明细</a>
	<br />
	<br /> 模糊查询：
	<br /> 商品名称查询：
	<input type="text" name="likename" style="width: 100px;" />&nbsp;&nbsp;
	品牌查询：
	<input type="text" name="likebrand" style="width: 100px;" />&nbsp;&nbsp;
	类型查询：
	<select name="liketype"></select>&nbsp;&nbsp; 价格区间：
	<input type="text" name="bottom" style="width: 50px;" /> 至
	<input type="text" name="top" style="width: 50px;" />
	<br />
	<button type="button" onclick="queryLike();">查询</button>
	<br />
	<button type="button" onclick="addGoods();">添加商品</button>
	<table border="1" align="center" width="900">
		<caption>商品列表</caption>
		<thead>
			<tr>
				<th>序号</th>
				<th>商品编号</th>
				<th>商品名称</th>
				<th>品牌</th>
				<th>商品类型</th>
				<th>售价</th>
				<th>商品单位</th>
				<th>商品图片</th>
				<th>是否在售</th>
				<th>显示优先级</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
		</thead>
		<tbody id="goodsList"></tbody>
	</table>
	<form action="" method="post" enctype="multipart/form-data" id="hidden">
		<table align="center">
			<tr>
				<td width="100">商品编号：</td>
				<td width="100" colspan="3"><input type="text" name="gid" /></td>
				<td rowspan="9"><img name="image" width="200"></td>
			</tr>
			<tr>
				<td>商品名称：</td>
				<td width="100" colspan="3"><input type="text" name="gname" /></td>
			</tr>
			<tr>
				<td>品牌：</td>
				<td width="100" colspan="3"><input type="text" name="brand" /></td>
			</tr>
			<tr>
				<td>商品类型：</td>
				<td width="100" colspan="3"><select name="gtype"></select></td>
			</tr>
			<tr>
				<td>售价：</td>
				<td width="100" colspan="3"><input type="text" name="price" /></td>
			</tr>
			<tr>
				<td>商品单位：</td>
				<td width="100" colspan="3"><input type="text" name="unit" /></td>
			</tr>
			<tr>
				<td>商品图片：</td>
				<td width="100" colspan="3"><input name="img" type="file" /></td>
			</tr>
			<tr>
				<td>是否在售：</td>
				<td><select name="saleing"><option value="true">是</option>
						<option value="false">否</option></select></td>
				<td width="100">显示优先级：</td>
				<td><select name="priority">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="4"><button id="btn">添加</button></td>
			</tr>
		</table>
	</form>
</body>
</html>