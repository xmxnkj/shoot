/**
 * 
 */
$basicdatatype_combobox=null;
$(function(){
	
	$.log($homebasepath);
	
	initForm();
	
	$basicdatatype_combobox =  $("#dataType").combobox({
    	id:'dataType',
    	data: [
   		    {"id":"RefuseReason","text":"拒绝理由",selected:false},
   		    {"id":"CaseSource","text":"来源",selected:false},
   		    {"id":"ProtocolForm","text":"协议形式",selected:false}
   		],
        valueField: 'id', 
        textField: 'text',
        editable:false,
        onSelect: function(rec){
        	var value = rec.text;
        	document.getElementById("dataTypeDesc").value=value;
        }
	});
	var selectObj = document.getElementById('dataType');
	selectId.onchange = function(){ 
        // 通过对象获取value和text 
        alert(selectObj.value); 
        alert(selectObj.options[selectObj.selectedIndex].text); 
        // 通过 id 获取value和text 
        alert(selectId.value); 
        alert(selectId.options[selectId.selectedIndex].text); 
        // 还可以通过this获取value和text 
        alert(this.value); 
        alert(this.options[this.selectedIndex].text); 
    }; 
	
});

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){
	   $('#editform').form("jsonLoad",rowdata);
}

function initForm(){
	
	$.easyuiSubmitForm({
		 formid:'editform',
		 url: $homebasepath+"admin/mediation/basicdata/saveJson.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			$.easyui.parent.$dig_editBasicData.dialog('close');
        	$.easyui.parent.refreshData();
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

