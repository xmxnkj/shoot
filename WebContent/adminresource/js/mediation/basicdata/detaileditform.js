/**
 * 
 */
$basicdatatype_combobox=null;
$(function(){
	
	$.log($homebasepath);
	
	initForm();
	
	/*$basicdatatype_combobox =  $("#dataType").combobox({
    	id:'dataType',
    	data: [
   		    {"id":"RefuseReason","text":"拒绝理由",selected:true}
   		],
        valueField: 'id', 
        textField: 'text',
        editable:false,
	});*/
	
});

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadAddData(rowdata){
	if(rowdata.dataValue=="人民"){
		$('#dataType').val("CivilCaseType");
		$('#dataTypeDesc').val("人民类型");
	}else if(rowdata.dataValue=="行政"){
		$('#dataType').val("AdministrationCaseType");
		$('#dataTypeDesc').val("行政类型");
	}else if(rowdata.dataValue=="司法"){
		$('#dataType').val("JudicialCaseType");
		$('#dataTypeDesc').val("司法类型");
	}
	$('#parentType').val(rowdata.id);
}

function formLoadEditData(rowdata){
	$('#editform').form("jsonLoad",rowdata);
}

function initForm(){
	
	$.easyuiSubmitForm({
		 formid:'editform',
		 url: $homebasepath+"admin/mediation/basicdata/saveJson.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			$.easyui.parent.$dig_editBasicDataDetail.dialog('close');
        	$.easyui.parent.refreshData2();
		 },
		 faicallbackfun:function(result,message){
			 $.easyui.alert(message);
		 }
	});
}

/**
 * 表单提交
 */
function formsubmit(){
	  $('#editform').submit();
}

