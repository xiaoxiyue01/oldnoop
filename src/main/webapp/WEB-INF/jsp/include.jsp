<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common.jsp" %>
<!-- 引入ace需要的样式 -->
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<!-- 日期和时间选择框 -->
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/daterangepicker.min.css" />
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/bootstrap-colorpicker.min.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/fonts.googleapis.com.css" />

<!--[if lte IE 9]>
	<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
<![endif]-->
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
  <link rel="stylesheet" href="${ctx}/static/js/ace/assets/css/ace-ie.min.css" />
<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="${ctx}/static/js/ace/assets/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
<script src="${ctx}/static/js/ace/assets/js/html5shiv.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/respond.min.js"></script>
<![endif]-->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${ctx}/static/js/ace/assets/js/jquery-2.1.4.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
	<script src="${ctx}/static/js/ace/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/static/js/ace/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${ctx}/static/js/ace/assets/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="${ctx}/static/js/ace/assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="${ctx}/static/js/ace/assets/js/jquery-ui.custom.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/jquery.easypiechart.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/jquery.sparkline.index.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/jquery.flot.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/jquery.flot.pie.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/jquery.flot.resize.min.js"></script>

<%--引入layer弹出框插件--%>
<link rel="stylesheet" href="${ctx}/static/js/layer/theme/default/layer.css">
<script type="text/javascript" src="${ctx}/static/js/layer/layer.js"></script>
<%--引入jquery validator插件--%>
<script type="text/javascript"
	src="${ctx}/static/js/jquery-validator/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/jquery-validator/localization/messages_zh.js"></script>

<%--引入日期格式化插件--%>
<script type="text/javascript" src="${ctx}/static/js/format-date.js"></script>
<%-- 引入jquery.datatables插件 --%>
<link rel="stylesheet" href="${ctx}/static/js/datatables/css/dataTables.bootstrap.css">
<script type="text/javascript" src="${ctx}/static/js/datatables/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/static/js/datatables/js/dataTables.bootstrap.js"></script>
<%--引入日期时间插件--%>
<script src="${ctx}/static/js/ace/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/bootstrap-timepicker.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/moment.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/daterangepicker.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${ctx}/static/js/ace/assets/js/bootstrap-colorpicker.min.js"></script>

<%--设置jquery.datatables的默认设置 --%>
<script type="text/javascript">
(function($){
	//设置分页条中文
	var language = {
		"lengthMenu": "每页 _MENU_ 条",
	     "emptyTable": "没有找到数据",
	     "info": "第 _PAGE_ / _PAGES_ 页,共 _TOTAL_ 条",
	     "infoEmpty": "没有找到数据",
	     "infoFiltered": "共 _MAX_ 条",
	   	"loadingRecords": 	"正在加载数据...",
	   	"processing":   	"正在加载数据...",
	   	"search":       	"搜索",
	   	"zeroRecords":		"没有找到数据",
	   	"paginate": {
	   		"first":    	"首页",
	   		"previous": 	"上一页",
	   		"next":     	"下一页",
	   		"last":     	"尾页"
	   	}
	};
	//设置表格初始化之后的操作,设置多选
	var initComplete = function(settings){
		console.log('initComplete');
		//设置多选
		var ths = $(settings.nTHead).find('tr').children();
		for(var i = 0; i < ths.length; i++){
			var cbx = $(ths[i]).find(':checkbox');
			//有多选框列
			if(cbx.length > 0){
				cbx.click(function(){
					var checked = $(this).prop('checked');
					var trs = $(settings.nTBody).children();
					for(var j = 0; j < trs.length; j++){
						$(trs[j]).find('td:eq('+i+') :checkbox')
							.prop('checked',checked);
					}
				});
				break;
			}
		}
	    //设置分页样式
	    //var pagerDiv = $(settings.nTable).parent().next();
	    //pagerDiv.attr('style','padding:2px');
	    //pagerDiv.find('ul.pagination').attr('style','margin:2px 0px');
	    //pagerDiv.find('.pagination > li > a').attr('style','padding:2px 12px');
	};
	//设置表格绘制之后的操作,设置序号
	var drawCallback = function(settings){
		console.log('drawCallback');
	    var api = this.api();
	    //设置序号
	    var colno = -1;
		var ths = $(settings.nTHead).find('tr').children();
		//console.log(ths);
		for(var i = 0; i < ths.length; i++){
			//console.log(ths[i]);
			if($(ths[i]).hasClass('sequenceno')){
				colno = i;
				break;
			}
		}
		if(colno < 0){
			return;	
		}
		//有序号列,就设置序号的值
	    api.column(colno, {page:'current'} ).nodes() 
	       .each(function ( group, i ) {
	    		$(group).text(settings.json.start+ (i+1));
	    	});
	  //设置多选
		var ths = $(settings.nTHead).find('tr').children();
		for(var i = 0; i < ths.length; i++){
			var cbx = $(ths[i]).find(':checkbox');
			//有多选框列
			if(cbx.length > 0){
				cbx.prop("checked",false);
			}
		}
	}
	//设置表格的默认选项
	$.extend( true, $.fn.dataTable.defaults, {
		"processing": true,
		"serverSide": true,
		"searching": false,
		"ordering": false,
		"paging" :true,
		"pagingType" :"full_numbers",
		"language":language,
		"dom": '<t><"row"<"col-xs-4"i><"col-xs-8"p>>',
		"initComplete": initComplete,
		"drawCallback": drawCallback
	} );
})(jQuery);
</script>