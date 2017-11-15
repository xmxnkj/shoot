/**
 * 
 */
$(function(){
	
	initForm();
	
});

function initForm(){
	
	$.easyuiSubmitForm({
		 formid:'editform',
		 url: $homebasepath+"admin/service/survey/basemain/saveJson.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			$.easyui.parent.$dig_editSurveybaseMain.dialog('close');
 			$.easyui.parent.refreshBaseMainData();
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

function formLoadAddData(activityid){
	$("#surveyactivityid").val(activityid);
}

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){
	$('#editform').form("jsonLoad",rowdata);
	$('#bulidtime').datetimebox('setValue',rowdata.bulidtime);
	$('#seqnum').numberbox('setValue',rowdata.seqnum);
}