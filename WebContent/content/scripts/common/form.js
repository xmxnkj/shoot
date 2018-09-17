$(function(){
	initForm();
	$("#_name").focus();
});
function initForm(){
	$("#frm").form({
        url: saveUrl,
        onSubmit: function () {
        	var isValid = $(this).form('validate');
            if (isValid) {
                $.messager.progress();
            }
            return isValid;
        },
        success: function (data) {
            $.messager.progress('close');
            if(dealJsonResult(data, saveSucInfo, function(rst){ 
            	if(typeof(saveClearForm)=='undefined'||saveClearForm){
            		clearForm();
            	}
            	$("#_name").focus();
            	},function(rst){
            		if($("#_id").length>0){
            			try{
            				$("#_id").val(rst.id);
            			}catch(e){
            				
            			}
            		}
            		
            	})){
            	if(typeof(saveClearForm)=='undefined'||saveClearForm||refreshContent){
            		top.refreshContent();
            	}
            }
        }
    });	 
	
	if(nameRequire){
		$("#_name").validatebox({
			required:true,
			validType:'length[0, ' + nameMaxLength + ']',
			missingMessage:'名称必填，请输入！',
			invalidMessage:'名称长度不超过' + nameMaxLength + '个汉字！',
			deltaX:-200
		});
	}
	if(nameServerValidate){
		$("#_name").validatebox({
			validType: "remote['" + serverValidateName + "?id=" + $('#_id').val() + "', 'entity.name']",
			invalidMessage:'名称已存在，请修改名称！',
			deltaX:-200
		});
	}
	
	if(descriptionLengthValidate){
		$("#_description").validatebox({
			validType:'length[0, ' + descriptionMaxLength + ']',
			invalidMessage:'备注说明长度不超过' + descriptionMaxLength + '个汉字！',
			deltaX:-200
		});
	}
}

function clearForm(){
	$("#frm").form("clear");
}