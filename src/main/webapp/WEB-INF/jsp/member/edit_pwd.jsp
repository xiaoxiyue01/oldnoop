<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<%@ include file="../include.jsp" %>
<script type="text/javascript">
function edit_pwd(){
	var form_data = $("#form").serialize();
	$.post('${ctx}/member/editPwd',form_data,function(res){
		if(res==='1'){
			layer.alert('密码已经修改');
			window.location='${ctx}/member/logs';
		}else{
			if (res==='04'){
				layer.alert('没有登录');
			}else if (res==='01'){
				layer.alert('输入的密码不能为空');
			}else if (res==='02'){
				layer.alert('两次输入密码不一致');
			}else if (res==='03'){
				layer.alert('密码没有修改');
			}else if (res==='05'){
				layer.alert('原密码不正确');
			}
		}
	});
}
</script>
</head>
<body>

<div class="container" style="margin-top:10px">
	<h3>修改密码:</h3>
	<form class="form-horizontal" id="form" style="width:500px">
		<div class="form-group">
			<label for="password" class="col-sm-3 control-label">原密码</label>
			<div class="col-sm-9">
				<input type="password" class="form-control" name="passwordOld" placeholder="请输入密码">
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-3 control-label">新密码</label>
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
				<button type="button" class="btn btn-primary" onclick="edit_pwd();">提交</button>
				<button type="reset" class="btn btn-default" >重置</button>
			</div>
		</div>
	</form>
</div>

</body>
</html>