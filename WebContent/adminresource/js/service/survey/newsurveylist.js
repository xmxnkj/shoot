/**
 * 
 */
var $dgsurveyactivitylist=null;
var $dig_addSurvey=null;//添加调查窗口
var $dig_editSurvey=null;//编辑调查窗口
$(function(){
	
	var columns = [
		       	    {field :'activitytitle',title:'活动名称',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
		       	    {field :'buliddatetime',title:'活动开始时间',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
		       	    {field :'finishdatetime',title:'活动结束时间',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center'}
		       	    ];
	$dgsurveyactivitylist = $('#dgsurveyactivitylist').datagrid({
		url: $homebasepath+'admin/service/survey/findDataBindDg.shtml',
		toolbar:'#surveyactivity_toolbar',
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
		onSelect:getClientSurvey,
		onDblClickRow:editSurvey
	});
	
	$('#btnAddSurvey').bind('click', function(){
		addSurvey();
	});
	
});

function addSurvey(){
	$dig_addSurvey =  $.easyui.showDialog({
        title: "新增问卷调查活动",
        width: 800, 
        height: 700,
        href: $homebasepath+"admin/service/survey/showAddView.shtml",
        topMost: true,
        cache: false,
	    modal: true,
        iniframe:true,
        autoVCenter: true,
        autoHCenter: true,
		enableSaveButton : true,
		saveButtonText : "保存",
		enableApplyButton : false,
		enableCloseButton : true,
        maximizable: true,
        autoRestore: true,	
		onSave : function(dig) {
			try {
				var miframe = dig.dialog('iframe');
				miframe[0].contentWindow.formsubmit();
				return false;
			} catch (e) {
				alert(e);
			}
		}
    });
}

function editSurvey(){
	var row = $('#dgsurveyactivitylist').datagrid('getSelected');
	if(row != null){
		$dig_editSurvey = $.easyui.showDialog({
	        title: "编辑问卷活动",
	        width: 800, 
	        height: 700,
	        href: $homebasepath+"admin/service/survey/showEditView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
            autoHCenter: true,
 	        enableSaveButton: true,
 	        saveButtonText:"保存",
 	        enableApplyButton: false,
 	        enableCloseButton: true,  
            maximizable: true,
 	        onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },
 	        onLoad:function(dig){
 	       	     var miframe = $dig_editSurvey.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function getClientSurvey(){
	var row = $('#dgsurveyactivitylist').datagrid('getSelected');
	var activityid = row.id;
	//var url = $homebasepath+'admin/service/survey/client/getClientSurvey.shtml';
	//var jsonData  = $.util.requestAjaxJson(url,{'activityid':activityid,'clientid':clientid});
	//通过接口返回值进行模板动态绑定 具体参数看API接口文档
	var template = $.templates("#surveryTmpl");//初始化模板
	var htmlOutput = template.render(row);//解析data数据
	$("#result").html(htmlOutput);//定位到模板处
}
