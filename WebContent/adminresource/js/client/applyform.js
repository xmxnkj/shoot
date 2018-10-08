/**
 * 
 */
$mediationagency_combobox=null;
$(function(){
	
	$.log($homebasepath);
	
   $("#auditState").combobox({
    	id:'auditState',
		data: [
		    {"id":"Pass","text":"申请通过",selected:true},
		    {"id":"NotPass","text":"拒绝申请",selected:false}
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
    	},
        //加载完成后,设置选中第一项
        onLoadSuccess: function () { 
            var val = $(this).combobox("getData");
            for (var item in val[0]) {
                if (item == "id") {
                   $(this).combobox("select", val[0][item]);
                }
            }
        }  
    });
	
});

function formsubmit(){
	//$.easyui.loading({ msg: "正在提交...", topMost: true });
    //取得表单的值
	var id = $("#id").val();
	var applyClientId = $("#applyClientId").val();//{Init:0,Pass:1,NotPass:2};
	var auditState= $("#auditState").combobox('getValue'); 
	var mediationAgencyId= $("#mediationAgencyId").combobox('getValue'); 
	submitParams = {
	   'id':id,	
	   'applyClientId':applyClientId,
	   'auditState':auditState,
	   'mediationAgencyId':mediationAgencyId
	};
	var applyState = $("#applyState").val();
	if(applyState =="申请中"){
		$.easyui.loading({ msg: "正在提交...", topMost: true });
		try{
			var url = $homebasepath+"admin/client/mediatorapply/saveMediatorApply.shtml";
			var resultJson  = $.util.requestAjaxJson(url,submitParams);
			$.ajaxResult(resultJson);
			//关闭当前窗口
			$.easyui.parent.$dig_editMediatorapply.dialog('close');
			$.easyui.parent.refreshData();
			$.easyui.loaded(true);
		}catch(e){
			$.log(e);
			$.easyui.loaded(true);
			$.easyui.messager.alert("提交请求异常错误!");
		}
	}else{
		$.messager.alert('提示', '以审核无需再操作！');
	}
	
}


/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(row){
	   $('#applyform').form("jsonLoad",row);
	   $('#applyName').val(row.applyClient.identifyName);
	   if(row.applyState=="Init"){
		   $('#applyState').val("申请中");
	   }else if(row.applyState=="Pass"){
		   $('#applyState').val("申请通过");
	   }else if(row.applyState=="NotPass"){
		   $('#applyState').val("不通过");
	   }
	}

function appState(s1,s2){
	if(s1==s2){
		return true;
	}
	return false;
}

function clientTypeFormat(rowdata){  
	if(rowdata.clientType=="Normal") 
	       return "普通会员";
    else if(rowdata.clientType=="Visitor") 
       return "游客";
    else if(rowdata.clientType=="Mediator") 
        return "员";
}