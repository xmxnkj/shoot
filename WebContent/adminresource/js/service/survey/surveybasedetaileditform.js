/**
 * 
 */
$(function(){
	
	initForm();
	
	$("#surveytype").combobox({
    	id:'surveytype',
		data: [
		    {"id":"radio","text":"单选",selected:true},
		    {"id":"multi","text":"多选",selected:false},
		    {"id":"questions","text":"问答",selected:false},
		],        
        valueField: 'id', 
        textField: 'text',
        editable:false
    });
	
});

function initForm(){
	
	$.easyuiSubmitForm({
		 formid:'editform',
		 url: $homebasepath+"admin/service/survey/basedetail/saveJson.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			$.easyui.parent.$dig_editSurveybaseDetail.dialog('close');
        	$.easyui.parent.refreshDetailData();
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

function formLoadAddData(mainid){
	$("#mainid").val(mainid);
}

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){
	$('#editform').form("jsonLoad",rowdata);
	$('#mainid').val(rowdata.id);
	$('#surveytype').combobox('setValue',rowdata.surveytype);
	$('#seqnum').numberbox('setValue',rowdata.seqnum);
}