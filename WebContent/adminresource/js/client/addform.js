/**
 * 
 */
$mediationagency_combobox=null;
$(function(){
	
	$.log($homebasepath);
	
	initForm();
	
	initUpload();
	
	$("#clientState").combobox({
    	id:'clientState',
		data: [
		    {"id":"MediationCenter","text":"调解中心管理员",selected:true},
		    {"id":"MediationAgency","text":"调解机构管理员",selected:false},
		    {"id":"Mediator","text":"普通调解员",selected:false},
		],        
        valueField: 'id', 
        textField: 'text',
        editable:false
    });
	
	$mediationagency_combobox =  $("#mediationAgencyId").combobox({
    	id:'mediationAgencyId',
        valueField: 'id', 
        textField: 'text',
        editable:false,
        onBeforeLoad: function(param){
        	var Url=$homebasepath+'admin/mediation/mediationagency/getMediationAgencyComboJson.shtml';
        	var ajaxdata  = $.util.requestAjaxJson(Url);
        	$(this).combobox('loadData',ajaxdata);
    	}/*,        
        //加载完成后,设置选中第一项
        onLoadSuccess: function () { 
            var val = $(this).combobox("getData");
            for (var item in val[0]) {
                if (item == "id") {
                   $(this).combobox("select", val[0][item]);
                }
            }
        }   */   
    });
	
});


/**
 *上传附件
 */
function initUpload(){
	var bt_uploadacc = $('#btn_uploadres');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/client/headImg.shtml",
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG)$/.test(ext))){		
				alert('对不起您选择文件系统限制上传!');
				return false;	
			}else{
				//使用EASYUI的进度条 TODO
				$.messager.progress({ title:'请稍后', msg:'正在拼命上传中,不要急...' });
			}
		},
		onComplete: function(file, response){//文件提交完成后可执行的方法
			//进度条隐藏 TODO
			$.messager.progress('close');
			//预览
			var url = $homebasepath+"uploads/client/"+response.resultDescription.urlpath+"";
			$("#resource").attr("src",url);
			$("#headImgFile").val(response.resultDescription.urlpath);
		}
	});
}


function initForm(){
	
	$.easyuiSubmitForm({
		 formid:'addform',
		 url: $homebasepath+"admin/client/saveMediatorClient.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			$.easyui.parent.$dig_addMediator.dialog('close');
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
	if(!$("#account").val()){
		alert("账号必填！");
		return;
	}
	if(!$("#passwd").val()){
		alert("密码必填！");
		return;
	}
	if(!$("#identifyName").val()){
		alert("姓名必填！");
		return;
	}
	if(!$("#identify").val()){
		alert("身份证必填！");
		return;
	}
	if(!$("#tel").val()){
		alert("手机号必填！");
		return;
	}

	
	if(!$("#clientState").combobox("getValue")){
		alert("用户身份必选！");
		return;
	}
	if(!$("#mediationAgencyId").combobox("getValue")){
		alert("所属机构必选！");
		return;
	}
	  $('#addform').submit();
}

