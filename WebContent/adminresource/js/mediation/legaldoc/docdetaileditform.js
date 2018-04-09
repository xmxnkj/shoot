/**
 * 
 */
$(function(){
	
	initForm();
	
});

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){
	//初始化编辑器
	editor = CKEDITOR.replace('content',{
	    filebrowserUploadUrl: $homebasepath+"/admin/news/momentsResourcesAction/uploadDetailsImg.shtml"
	});
	
	$('#editform').form("jsonLoad",rowdata);
	$('#seq').numberspinner('setValue',rowdata.seq);
}

function formLoadAddData(legaldocid){
	$('#legalDocId').val(legaldocid);
}

function initForm(){
	
	$.easyuiSubmitForm({
		 formid:'editform',
		 url: $homebasepath+"admin/mediation/legaldocdetail/saveJson.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			$.easyui.parent.$dig_editLegalDocDetail.dialog('close');
        	$.easyui.parent.refreshDetailsData();
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