<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>주문 목록 페이지</title>
</head>
<body>
	<table>
		<tr>
			<th>id</th>
			<th>owner</th>
			<th>products</th>
			<th></th>
		</tr>
		<c:if test="${orderList.size()!=0}">
		<c:forEach var="order" items="${orderList}">
			<tr>
				<td>${order._id}</td>
				<td>${order.owner_id}</td>
				<td>${order.product_id}</td>
				<td><button type="button" onclick="completeOrder(${order._id})">완료</button></td>
			</tr>
		</c:forEach>
		</c:if>
	</table>
	<c:if  test="${orderList.size()==0}">
		<h2>주문이 없습니다.</h2>
	</c:if>
	<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script>
		function completeOrder(_id){
			$.ajax({
				url:'completeOrder',
				type:'POST',
				data:{
					"_id":_id
				},
				error: function(){
					alert("오류가 발생하였습니다. 다시 시도해주세요");
				},
				success: function(result){
					location.reload();
				}
				
			});
		}
	</script>
</body>
</html>