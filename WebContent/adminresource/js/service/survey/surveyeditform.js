/**
 * 
 */
var $dgsurveyquestionlist=null;//问卷问题列表
var $dgsurveyquesdetaillist=null;//问题答案列表
var $dig_editSurveybaseDetail=null;//薪增问答题目窗口
var $dig_editSurveybaseMain=null;//编辑问答题目窗口
$(function(){
	
	initForm();
	init_surveyform_tabs();
	
	$('#btn_surveyques_Add').bind('click', function(){
		addSurveybaseMain();
	});
	$('#btn_surveyques_Edit').bind('click', function(){
		editSurveybaseMain();
	});
	$('#btn_surveyquesdetail_Add').bind('click', function(){
		addSurveybaseDetail();
	});
	$('#btn_surveyquesdetail_Edit').bind('click', function(){
		editSurveybaseDetail();
	});
	
});

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){
   $('#editform').form("jsonLoad",rowdata);
   $('#buliddatetime').datetimebox('setValue',rowdata.buliddatetime);
   $('#finishdatetime').datetimebox('setValue',rowdata.finishdatetime);
}

function initForm(){
	
	$.easyuiSubmitForm({
		 formid:'editform',
		 url: $homebasepath+"admin/service/survey/saveJson.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			//$('#editform').form('load', resultdata);
 			$('#id').val(resultdata.id);
 			$.easyui.parent.refreshData();
		 },
		 faicallbackfun:function(result,message){
			
		 }
	});
}

/**
 * 表单提交
 */
function formsubmit(){
	  $('#editform').submit();
}

/**
 * 初始化选项卡
 */
function init_surveyform_tabs(){
	$("#editsurveyform_tabs").tabs({
		border:true,
		width:'auto',
		height:'auto',
		fit:true,
		onSelect: function(title,index){
			var activitysid = $("#id").val();
			if(index>0){
			    //验证一下form
				var isValid = $("#editform").form('validate');
				$.log('验证:'+isValid);
				if(!isValid||activitysid==""){
					$("#editsurveyform_tabs").tabs('select',0);	
					$.messager.alert('提示','请先保存基本资料后才可进行操作','info');
				}
				//验证是否保存了
				
			}
			if(index==1){
				getSurveyQuestionList();
			}
	    }
	});	
}

function getSurveyQuestionList(){
	var activityid = $("#id").val();
	var columns = [
		       	    {field :'seqnum',title:'问题编号',sortable:true,width :parseInt($(this).width()*0.03),halign:'center',align:'center'},
		       	    {field :'surveytitle',title:'题目标题',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
		       	    {field :'bulidtime',title:'生成时间',sortable:true,width :parseInt($(this).width()*0.07),halign:'center',align:'center'}
		       	    ];
	$dgsurveyquestionlist = $('#dgsurveyqueslist').datagrid({
		url: $homebasepath+'admin/service/survey/basemain/findDataBindDg.shtml',
		toolbar:'#surveyactivity_toolbar',
		queryParams:{activityid:activityid},
		fit:true,
		striped:true,
		sortName:'seqnum',
		sortOrder:'asc',
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		fitColumns:true,
		columns:[columns],
		onSelect:getSurveyQuesDetail,
		onDblClickRow:editSurveybaseMain
	});
}

function getSurveyQuesDetail(){
	var columns = [
	                {field :'seqnum',title:'选项序号',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
	                {field :'surveycontent',title:'选项内容',sortable:true,width :parseInt($(this).width()*0.5),halign:'center',align:'center'},
	                {field :'surveytype',title:'单选还是多选',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'center'}
	                ];
	
	var row = $('#dgsurveyqueslist').datagrid('getSelected');
	var surveybasemainid = "1";
	if(row!=null){
		surveybasemainid = row.id;
	}
	$dgsurveyquesdetaillist = $('#dgsurveyquesdetaillist').datagrid({
		url: $homebasepath+'admin/service/survey/basedetail/findDataBindDg.shtml',
		queryParams:{surveybasemainid:surveybasemainid},
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		columns:[columns],
		onDblClickRow:editSurveybaseDetail
	});
}

function addSurveybaseMain(){
	$dig_editSurveybaseMain =  $.easyui.showDialog({
        title: "新增问卷调查题目",
        width: 600, 
        height: 400,
        href: $homebasepath+"admin/service/survey/basemain/showAddView.shtml",
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
		},
		onLoad:function(dig){
		       var miframe = $dig_editSurveybaseMain.dialog('iframe');
		       var activityid = $("#id").val();
			   miframe[0].contentWindow.formLoadAddData(activityid);	
		    }
    });
}

function editSurveybaseMain(){
	var row = $('#dgsurveyqueslist').datagrid('getSelected');
	if(row != null){
		$dig_editSurveybaseMain = $.easyui.showDialog({
	        title: "编辑问卷题目",
	        width: 600, 
	        height: 400,
	        href: $homebasepath+"admin/service/survey/basemain/showEditView.shtml",
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
 	       	     var miframe = $dig_editSurveybaseMain.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function addSurveybaseDetail(){
	var row = $('#dgsurveyqueslist').datagrid('getSelected');
	if(row != null){
		$dig_editSurveybaseDetail =  $.easyui.showDialog({
			title: "新增问卷调查题目答案",
			width: 600, 
			height: 400,
			href: $homebasepath+"admin/service/survey/basedetail/showAddView.shtml",
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
			},
			onLoad:function(dig){
				var miframe = $dig_editSurveybaseDetail.dialog('iframe');
				var surveybasemainid = row.id;
				miframe[0].contentWindow.formLoadAddData(surveybasemainid);	
			}
		});
	}else{
		$.messager.alert('提示', '请选择一个题目再增加答案！');
	}
}

function editSurveybaseDetail(){
	var row = $('#dgsurveyquesdetaillist').datagrid('getSelected');
	if(row != null){
		$dig_editSurveybaseDetail = $.easyui.showDialog({
			title: "编辑问卷题目答案",
			width: 600, 
			height: 400,
			href: $homebasepath+"admin/service/survey/basedetail/showEditView.shtml",
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
				var miframe = $dig_editSurveybaseDetail.dialog('iframe');
				miframe[0].contentWindow.formLoadEditData(row);	
			}
		});		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function refreshBaseMainData(){
	$('#dgsurveyqueslist').datagrid('reload');
}
function refreshDetailData(){
	$('#dgsurveyquesdetaillist').datagrid('reload');
}
