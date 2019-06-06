<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<script type="text/javascript">
function login(){
	var form_data = $("#form").serialize();
	$.post("${ctx}/member/login",form_data,function(res){
		if (res==="1"){
			layer.alert("登录成功");
			window.location="${ctx}/member/toEdit";
		}else if (res==="2"){
			layer.alert("登录成功");
			window.location="${ctx}/member/logs";
		}else if (res==="0"){
			layer.alert("用户名或密码错误！");
		}
	});
}
</script>

</head>
<body>
<div class="container">
	<h3>用户登录：</h3>
	<form class="form-horizontal" id="form" style="width:500px">
		<div class="form-group">
			<label for="account" class="col-sm-3 control-label">账号</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="account" placeholder="请输入账号">
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-3 control-label">密码</label>
			<div class="col-sm-9">
				<input type="password" class="form-control" name="password" placeholder="请输入密码">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-8">
				<button type="button" class="btn btn-primary" onclick="login();">登录</button>
				<button type="reset" class="btn btn-default" style="margin-right:25px">重置</button>
				<a type="button" class="btn btn-info" href="${ctx}/member/toRegister" >注册账号</a>
			</div>
		</div>
	</form>
</div>
</body>
</html>