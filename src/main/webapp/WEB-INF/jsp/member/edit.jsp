<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>完善账号信息</title>
<%@ include file="../include.jsp" %>
<script type="text/javascript">
function edit(){
	var form_data = $("#form").serialize();
	$.post('${ctx}/member/edit',form_data,function(res){
		if(res==='1'){
			layer.alert('操作成功');
			window.location='${ctx}/member/logs';
		}else{
			if (res==="04"){
				layer.alert('没有登录');
			}else if (res==="01"){
				layer.alert('此邮箱已存在');
			}else if (res==="02"){
				layer.alert('手机号码已存在');
			}else if (res==="03"){
				layer.alert('手机号或邮箱不能为空');
			}
		}
	});
}
</script>
</head>
<body>
<div class="container">
	<h3>完善账号信息:</h3>
	<form class="form-horizontal" id="form" style="width:500px">
		<input type="hidden" name ="id" value="${member.id}">
		<div class="form-group">
			<label for="phone" class="col-sm-3 control-label">手机号码</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="phone" value="${member.phone}" placeholder="请输入手机号码">
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-3 control-label">邮箱</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="email" value="${member.email}" placeholder="请输入邮箱">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-8">
				<button type="button" class="btn btn-primary" onclick="edit();">提交</button>
				<button type="reset" class="btn btn-default" >重置</button>
			</div>
		</div>
	</form>	
</div>

</body>
</html>