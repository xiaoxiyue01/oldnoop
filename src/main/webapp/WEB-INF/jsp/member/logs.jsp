<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志信息</title>
<%@ include file="../include.jsp" %>
</head>
<body>
<div class="container" style="margin-top:10px">
	<div style="margin: 0 auto;">
			<h2 style="text-align: center">登录日志</h2>
			<table class="table table-bordered">
				<tr>
					<th>用户名</th>
					<th>登录状态</th>
					<th>登录IP</th>
					<th>登录地</th>
					<th>时间</th>
				</tr>
				<c:forEach items="${logs}" var="log">
				<tr>
					<td>${log.account}</td>
					<td>
						<c:if test="${log.status==1 }">成功</c:if>
						<c:if test="${log.status==0 }">失败</c:if>
					</td>
					<td>${log.loginIp}</td>
					<td>${log.loginZone}</td>
					<td>
						<fmt:formatDate value="${log.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>	
				</c:forEach>
			</table>
		</div>
	<br>
	<a type="button" href="${ctx}/member/toEdit" class="btn btn-primary" >修改账户信息</a>&nbsp;&nbsp;
	<a type="button" href="${ctx}/member/toEditPwd" class="btn btn-primary" >修改密码</a>&nbsp;&nbsp;
	<a type="button" href="${ctx}/member/logout" class="btn btn-danger">退出</a><br><br>
	
	<br>
	<div style="margin: 0 auto;">
			<h2 style="text-align: center">修改日志</h2>
			<table class="table table-bordered">
				<tr>
					<th>密码</th>
					<th>手机</th>
					<th>邮箱</th>
					<th>时间</th>
				</tr>
				<c:forEach items="${infoLogs}" var="log">
				<tr>
					<td>${log.password}</td>
					<td>${log.phone}</td>
					<td>${log.email}</td>
					<td>
						<fmt:formatDate value="${log.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>	
				</c:forEach>
			</table>
		</div>
</div>
</body>
</html>