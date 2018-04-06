/**
 *列表list 
 */
 
$dgclientlist=null;
$dgmediatorlist=null;
$dig_addMediator=null;//新增调解员窗口
$dig_showidentifyview=null;

function timeStyle(val,row){
	if(val){
		var date = new Date(val);
		return  date.getFullYear()+"-"+
		(date.getMonth()+1<10?"0"+(date.getMonth()+1):(date.getMonth()+1))+"-"+
		(date.getDate()<10?"0"+date.getDate():date.getDate())+"  "+
		(date.getHours()<10?"0"+date.getHours():date.getHours())+":"+
		(date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes());
	}else{
		return "";
	}
	
}

$(function() {
	
	$.log($homebasepath);
	$.log($homepath);
	
	var columns1 = [
	    {field :'identifyName',title:'姓名',sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'left'},
	    {field :'headImgFile',title:'会员头像',sortable:true,formatter: formatImg,width :parseInt($(this).width()*0.07),halign:'center',align:'left'},
	    {field :'resgitTime',title:'注册时间',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'left',formatter:timeStyle},
	    {field :'account',title:'会员账号',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'left'},
	    {field :'clientType',title:'会员类型',sortable:true,formatter:clientTypeFormat,width :parseInt($(this).width()*0.07),halign:'center',align:'center'},
	    {field :'address',title:'地址',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'right'},
	    {field :'identify',title:'身份证号码',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'right'},
	    {field :'identifyImgFile1',title:'身份证正面照',formatter:formatImg,width :parseInt($(this).width()*0.07),halign:'center',align:'left'},
	    {field :'identifyImgFile2',title:'身份证反面照',formatter:formatImg,width :parseInt($(this).width()*0.07),halign:'center',align:'left'},
	    {field :'identifyImgFile3',title:'身份证手持照',formatter:formatImg,width :parseInt($(this).width()*0.07),halign:'center',align:'left'},
	    {field :'auditInfo',title:'认证信息',formatter:formatCertificate,width :parseInt($(this).width()*0.07),halign:'center',align:'left'},
	    {field :'operate',title:'实名认证审核操作',sortable:true,formatter:formatOper,width :parseInt($(this).width()*0.07),halign:'center',align:'center'}
	    ];
	var columns2 = [
       {field :'identifyName',title:'姓名',sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'left'},
       {field :'headImgFile',title:'会员头像',sortable:true,formatter: formatImg,width :parseInt($(this).width()*0.07),halign:'center',align:'left'},
       {field :'resgitTime',title:'注册时间',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'left',formatter:timeStyle},
       {field :'account',title:'会员账号',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'left'},
       {field :'clientType',title:'会员类型',sortable:true,formatter:clientTypeFormat,width :parseInt($(this).width()*0.07),halign:'center',align:'center'},
       {field :'clientState',title:'会员身份',sortable:true,formatter:clientStateFormat,width :parseInt($(this).width()*0.07),halign:'center',align:'center'},
       {field :'agencyName',title:'属于调解机构',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'right'},
       {field :'mediatorType',title:'调解员类型',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'right',formatter:function(val,row){
    	   switch(val){
    	   		case 1:
    	   			return "行政";
    	   			break;
    	   		case 2:
    	   			return "司法";
    	   			break;
    	   		case 3:
    	   			return "人民";
    	   			break;
    	   		default:
    	   			return "还未设置类型";
    	   			break;
    	   }
       }},
       {field :'address',title:'地址',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'right'},
	               ];
	
	$dgclientlist = $('#dgclientlist').datagrid({
		url: $homebasepath+'/admin/client/findDataBindDg.shtml',
		toolbar:'#clientlist_toolbar',
		queryParams:{
			clientType:$('#CertificateState').combobox('getValue')
			},
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'showDisplay',
		sortOrder:'desc',
		columns:[columns1],
		onDblClickRow:edit,
		onClickCell:showIdentifyView
	});
	
	$dgmediatorlist = $('#dgmediatorlist').datagrid({
		url: $homebasepath+'/admin/client/getClientsDatas.shtml',
		toolbar:'#mediatorlist_toolbar',
		queryParams:{
			clientType:"Mediator"
			},
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		/*sortName:'createtime',
		sortOrder:'desc',*/
		columns:[columns2],
		onDblClickRow:edit
	});
	
	$('#btnSearch').bind('click', function(){
		refreshData();
	});
	$('#btnEdit').bind('click', function(){
		edit();
	});
	$('#btnAddMediator').bind('click', function(){
		addMediator();
	});
	$('#btnSearchMediator').bind('click', function(){
		refreshData();
	});
	$('#btnEditMediator').bind('click', function(){
		edit();
	});
});
function refreshData(){
	var params={};
	
	var tab = $('#clientlist_tabs').tabs('getSelected');
	var index = $('#clientlist_tabs').tabs('getTabIndex',tab);
	var status =$('#CertificateState').combobox('getValue');
	if(index==0){
		params["entityQuery.identifyName"] = $("#nickName01").val();
		params["entityQuery.tel"] = $("#tel01").val();
		params["entityQuery.resgitTimeStart"] = $("#resgitTimeStart01").datebox('getValue');
		params["entityQuery.resgitTimeEnd"] = $("#resgitTimeEnd01").datebox('getValue');
		params["entityQuery.clientType"] = "Normal";
		if(status){params["entityQuery.auditInfo"] = status;}
		$("#dgclientlist").datagrid("reload",params);
	}else if(index==1){
		params["entityQuery.identifyName"] = $("#nickName02").val();
		params["entityQuery.resgitTimeStart"] = $("#resgitTimeStart02").datebox('getValue');
		params["entityQuery.resgitTimeEnd"] = $("#resgitTimeEnd02").datebox('getValue');
		params["entityQuery.tel"] = $("#tel02").val();
		params["entityQuery.clientType"] = "Mediator";
		$("#dgmediatorlist").datagrid("reload",params);
	}
}
var $dig_editClient;
function edit(){
	var tab = $('#clientlist_tabs').tabs('getSelected');
	var index = $('#clientlist_tabs').tabs('getTabIndex',tab);
	var row = null;
	if(index==0){
		row = $('#dgclientlist').datagrid('getSelected');
	}else if(index==1){
		row = $('#dgmediatorlist').datagrid('getSelected');
	}
	if(row == null){
		$.messager.alert('提示', '请选择一行进行修改！');
		return;
	}
		/*$dig_editClient = $.easyui.showDialog({
	        title: "详细信息",
	        width: 600, 
	        height: 500,
	        href: $homebasepath+"admin/client/showEditView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
            autoHCenter: true,
 	        enableSaveButton: false,
 	        saveButtonText:false,
 	        saveButtonText : "保存",
 	        enableApplyButton: false,
 	        enableCloseButton: true,  
 	        onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },
 	        onLoad:function(dig){
 	       	     var miframe = $dig_editClient.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });*/
	

		$dig_editClient = $.easyui.showDialog({
	        title: "详细信息",
	        width: 600, 
	        height: 500,
	        href: $homebasepath+"admin/client/showEditView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
            autoHCenter: true,
 	        saveButtonText:false,
 	        enableSaveButton : true,
 			saveButtonText : "保存",
 	        enableApplyButton: false,
 	        enableCloseButton: true,  
 	        onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },
 	        onLoad:function(dig){
 	       	     var miframe = $dig_editClient.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
}

function addMediator(){
	$dig_addMediator =  $.easyui.showDialog({
        title: "新增文档",
        width: 700, 
        height: 500,
        href: $homebasepath+"admin/client/showAddView.shtml",
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

function clientTypeFormat(val,row){  
    if(val=="Normal") 
       return "普通会员";
    else if(val=="Visitor") 
       return "游客";
    else if(val=="Mediator") 
        return "调解员";
}
function clientStateFormat(val,row){  
	if(val=="MediationCenter") 
		return "调解中心管理员";
	else if(val=="MediationAgency") 
		return "调解机构管理员";
	else if(val=="Mediator") 
		return "普通调解员";
}
function formatCertificate(val,row){  
	if(val=="WaitAudit") 
		return "待审核";
	else if(val=="NotPass") 
		return "未通过";
	else if(val=="Pass")
		return "已通过";
	else
		return "待提交";
}

function formatCellTooltip(value){  
    //return "<span title='" + value + "'>" + value + "</span>";  
    strs=value.split(";");
    var tip = '<img width="100" height="100" src="../../uploads/client/'+value+'"/>';
    return "<span title='" + tip + "'>" + tip + "</span>";  
} 

function formatImg(val,row){
	
	if(val){
		return '<img width="50" height="50" src="../../uploads/client/'+val+'"/>';
	}else{
		return '<img width="50" height="50" src="../../uploads/client/2.jpg"/>';
	}
}	
	

function formatOper(val,row,index){
	return '<a href="javascript:void(0);" onclick="auditCertificate('+index+',0)"><font color="#FF0000"><u>通过</u></font></a>/<a href="javascript:void(0);" onclick="auditCertificate('+index+',1)"><font color="#006400"><u>拒绝</u></font></a>';
}

function auditCertificate(index,oper){
	var rows = $dgclientlist.datagrid('getRows');
	var row = rows[index];
	var url = $homebasepath+'admin/client/auditCertificate.shtml?';
	if(row.auditInfo!='WaitAudit'){
		alert("只有待审核才能执行此操作！！");
		return;
	}
	submitParams = {
			   'id':row.id,
			   'oper':oper
			};
	if(confirm("是否确定操作？")){
		var jsonData  = $.util.requestAjaxJson(url,submitParams);
		if(jsonData.success){
			$.easyui.messager.alert("操作成功!");
			$('#dgclientlist').datagrid("reload");
		}else{
			$.easyui.messager.alert(jsonData.message);
		}
	}
}

function showIdentifyView(index,field,value){
	if(field == "identifyImgFile1" || field == "identifyImgFile2" || field == "identifyImgFile3"){
		$dig_showidentifyview =  $.easyui.showDialog({
	        title: "查看图片",
	        width: 700, 
	        height: 500,
	        href: $homebasepath+"admin/client/showIdentifyView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
	        autoHCenter: true,
			enableSaveButton : false,
			//saveButtonText : "保存",
			enableApplyButton : false,
			enableCloseButton : true,
	        maximizable: true,
	        autoRestore: true,	
 	        onLoad:function(dig){
	       	     var miframe = $dig_showidentifyview.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(value);	
	        }
	    });		
	}
}

function init_clientlist_tabs(){
	$("#clientlist_tabs").tabs({
		border:true,
		width:'auto',
		height:'auto',
		fit:true,
		onSelect: function(title,index){
			if(index==0){
				$("#dgclientlist").datagrid("reload",params);
			}else if(index==1){
				$("#dgmediatorlist").datagrid("reload",params);
			}
	    }
	});	
}
//删除
function kill(tableId){
	var row = $('#'+tableId).datagrid('getSelected');
	if(row){
		if(row.id==1){
			alert("中心管理员不能删除！");
			return;
		}
		$.ajax({
			url:$homebasepath+'/admin/client/kill.shtml',
			data:{"id":row.id},
			type:'POST',
			async: false,
			dataType:'json', 
			success:function(data){
				$("#"+tableId).datagrid("reload");
			}
		});
	}else{
		alert("请选择一条数据进行删除！");
	}
}

function exportExcel(){
	var tab = $('#clientlist_tabs').tabs('getSelected');
	var index = $('#clientlist_tabs').tabs('getTabIndex',tab);
	var status =$('#CertificateState').combobox('getValue');
	var query = "";
	query+="entityQuery.identifyName="+$("#nickName01").val()+"&";
	query+="entityQuery.tel="+$("#tel01").val()+"&";
	query+="entityQuery.resgitTimeStart="+$("#resgitTimeStart02").datebox('getValue')+"&";
	query+="entityQuery.resgitTimeEnd="+$("#resgitTimeEnd02").datebox('getValue')+"&";
	query+="entityQuery.clientType=Normal&";
	query+="entityQuery.paging.page=1&entityQuery.paging.pageSize=200&";
	if(status){
		query+="entityQuery.auditInfo="+status;
	}
	window.location.href = $homebasepath+"/admin/client/exportClients.shtml?" + query;
}