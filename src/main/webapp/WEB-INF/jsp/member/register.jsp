<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<%@ include file="../include.jsp" %>
<script type="text/javascript">
function register(){
	var form_data = $("#form").serialize();
	$.post('${ctx}/member/register',form_data,function(res){
		if(res==='1'){
			layer.alert('注册成功，请登录');
			window.location='${ctx}/member/toLogin';
		}else{
			if (res==='01'){
				layer.alert('两次输入密码不一致');
			}else if (res==='02'){
				layer.alert('用户名已存在');
			}
		}
	});
}
</script>
</head>
<body>
<div class="container" style="margin-top:10px">
	<h3>用户注册:</h3>
	<form class="form-horizontal" id="form" style="width:500px">
		<div class="form-group">
			<label for="username" class="col-sm-3 control-label">用户名</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="username" placeholder="请输入用户名">
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-3 control-label">密码</label>
			<div class="col-sm-9">
				<input type="password" class="form-control" name="password" placeholder="请输入密码">
			</div>
		</div>
		<div class="form-group">
			<label for="password2" class="col-sm-3 control-label">确认密码</label>
			<div class="col-sm-9">
				<input type="password" class="form-control" name="passwordConfirm" placeholder="请再次输入密码">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-8">
				<button type="button" class="btn btn-primary" onclick="register();">注册</button>
				<button type="reset" class="btn btn-default" >重置</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>