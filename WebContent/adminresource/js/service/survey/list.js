/**
 * 
 */
var $dgsurveyactivitylist=null;
var $dgactivityclientlist=null;
$(function(){
	
	var columns = [
		       	    {field :'activitytitle',title:'活动名称',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
		       	    {field :'buliddatetime',title:'活动开始时间',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
		       	    {field :'finishdatetime',title:'活动结束时间',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center'}
		       	    ];
	$dgsurveyactivitylist = $('#dgsurveyactivitylist').datagrid({
		url: $homebasepath+'admin/service/survey/findDataBindDg.shtml',
		//toolbar:'#movementaudiolist_toolbar',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		fitColumns:true,
		columns:[columns],
		onSelect:getActivityClientList
	});
	
});

function getActivityClientList(){
	var columns = [
		       	    {field :'nickName',title:'调查会员',sortable:true,width :parseInt($(this).width()*1),halign:'center',align:'right'},
		       	    {field :'id',hidden:true,title:'会员id',sortable:true,width :parseInt($(this).width()*1),halign:'center',align:'right'}
		       	    ];
	var activityid = "1";
	var row = $('#dgsurveyactivitylist').datagrid('getSelected');
	if(row!=null){
		activityid = row.id;
	}
	$dgactivityclientlist = $('#dgactivityclientlist').datagrid({
		url: $homebasepath+'admin/service/survey/client/findActivityClientList.shtml',
		//toolbar:'#movementlist_toolbar',
		queryParams:{
			activityid:activityid
			},
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'createTime',
		sortOrder:'desc',
		fitColumns:true,
		columns:[columns],
		onSelect:getClientSurvey
	});
}

function getClientSurvey(){
	var row = $('#dgactivityclientlist').datagrid('getSelected');
	var activityrow = $('#dgsurveyactivitylist').datagrid('getSelected');
	var activityid = activityrow.id;
	var clientid = row.id;
	var url = $homebasepath+'admin/service/survey/client/getClientSurvey.shtml';
	var jsonData  = $.util.requestAjaxJson(url,{'activityid':activityid,'clientid':clientid});
	//通过接口返回值进行模板动态绑定 具体参数看API接口文档
	var template = $.templates("#surveryTmpl");//初始化模板
	var htmlOutput = template.render(jsonData);//解析data数据
	$("#result").html(htmlOutput);//定位到模板处
}
