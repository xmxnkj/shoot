/**
 * 
 */
$dgmediationcaselist=null;//列表
$dig_editMediationCase=null;//编辑窗口
$(function(){
	
	loadData();
	
	$('#btnEdit').bind('click', function(){
		editMediationCase();
	});
	
	$('#btnExportExce').bind('click', function(){
//		var rows = $dg_drawcashrecord.datagrid('getChecked');
		var beginDate = $('#beginDate').datebox('getValue');
		var endDate = $('#endDate').datebox('getValue');
//		if(year == null){
//			$.easyui.messager.alert("请选择年份!");
//			return;
//		}
	    var url = $homebasepath+'/admin/mediation/mediationcase/exportMediationCaseToExcel.shtml?beginDate='+beginDate+'&endDate='+endDate;  
	   /* url = decodeURIComponent(url,true);  
	    url = encodeURI(encodeURI(url)); */ 
	    window.location.href=url;
	});
	
	$('#beginDate').datebox({
		formatter : function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+m+'-'+d;
		}
//	    formatter: function(date){ return date.getFullYear();},
	    //parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/")));}
	});
	
	$('#endDate').datebox({
		formatter : function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+m+'-'+d;
		}
		//parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/")));}
	});
	
	//初始化状态
	$("#caseStatus").combobox({
    	id:'caseStatus',
    	data: [
   		    {"id":"Init","text":"申请中",selected:false},
   		    {"id":"Allocation","text":"分配中",selected:false},
   		    {"id":"Mediating","text":"调节中",selected:false},
   		    {"id":"WaitSign","text":"待签署",selected:false},
   		    {"id":"WaitComplete","text":"待结案",selected:false},
   		    {"id":"Closed","text":"已关闭",selected:false},
   		    {"id":"Refused","text":"已拒绝",selected:false},
   		    {"id":"Completed","text":"已结案",selected:false},
   		    {"id":"GiveUp","text":"用户放弃",selected:false}
   		],
        valueField: 'id', 
        textField: 'text',
        editable:false
	});
	
	//初始化机构
	mediationAgency();
});

function loadData(){
	var beginDate = $("#beginDate").datebox('getValue');
	var endDate = $("#endDate").datebox('getValue');
	
	var mediationAgency = $("#mediationAgency").combobox('getValue');	//委员会
	var mediatorClient = $("#mediatorClient").combobox('getValue');		//员
	var caseStatus = $("#caseStatus").combobox('getValue');				//状态
	
	var columns = [
		       	    {field :'caseType',title:'类型',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'caseExplain',title:'申述点',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'address',title:'发生地',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'left'},
		       	    {field :'applyClient',title:'申诉人',formatter :clientFormat,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
		       	    {field :'applyTime',title:'申请时间',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
		       	    {field :'caseState',title:'状态',formatter:stateFormat,sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
		       	    //{field :'allocationState',title:'分配状态',formatter:allstateFormat,sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
		       	    {field :'refuseReason',title:'拒绝理由',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
		       	    {field :'mediatorClient',title:'人',formatter:mediatorclient,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'center'}
		       	    ];
		
		$dgmediationcaselist = $('#dgmediationcaselist').datagrid({
			url: $homebasepath+'/admin/mediation/mediationcase/getList.shtml',
			toolbar:'#mediationcaselist_toolbar',
			queryParams:{
				"beginDate":beginDate,
				"endDate":endDate,
				"mediationAgency":mediationAgency,
				"mediatorClient":mediatorClient,
				"caseStatus":caseStatus,
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
			sortName:'applyTime',
			sortOrder:'desc',
			columns:[columns],
			onDblClickRow:editMediationCase
		});
}

//初始化机构
function mediationAgency(){
	$.ajax({
		url:$homebasepath+'/admin/mediation/mediationagency/getMediationAgencyComboJson.shtml',
		type:'POST',
		async: false,
		dataType:'json', 
		success:function(data){
			data[0]["selected"] = false;
			$("#mediationAgency").combobox({
		    	id:'docType',
		    	data: data,
		        valueField: 'id', 
		        textField: 'text',
		        editable:false,
		        onChange:function(newValue,oldValue){
		        	var id = newValue;
		        	mediationClient(id);
		        }
			});
		}
	});
}
//加载员
function mediationClient(id){
	$.ajax({
		url:$homebasepath+'/admin/client/getAgencyClient.shtml',
		type:'POST',
		data:{"agencyId":id},
		async: false,
		dataType:'json', 
		success:function(data){
			data = data.data;
			var tmp = "[";
			for(var i=0;i<data.length;i++){
				tmp+="{'id':'"+data[i].id+"','text':'"+data[i].identifyName+"','selected':'false'},";
			}
			tmp = tmp.substring(0,tmp.length-1)+"]";
			tmp = eval('('+tmp+')');
			$("#mediatorClient").combobox({
		    	id:'mediatorClient',
		    	data: tmp,
		        valueField: 'id', 
		        textField: 'text',
		        editable:false
			});
			
		}
	});
}


function editMediationCase(){
	var row = $('#dgmediationcaselist').datagrid('getSelected');
	if(row != null){
		$dig_editMediationCase = $.easyui.showDialog({
	        title: "查看详情",
	        width: 800, 
	        height: 600,
	        href: $homebasepath+"admin/mediation/mediationcase/showEditView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
            autoHCenter: true,
 	        //enableSaveButton: true,
 	        //saveButtonText:"保存",
 	        enableApplyButton: false,
 	        enableCloseButton: true,  
            maximizable: true,
 	        /*onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },*/
 	        onLoad:function(dig){
 	       	     var miframe = $dig_editMediationCase.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function search(){
	loadData();
}

function clientFormat(val,row){
	if(val != null){
		return val.identifyName;
	}
}
function mediatorclient(val,row){
	if(val != null){
		return val.identifyName;
	}
}
function stateFormat(val,row){
	if(val=="Init"){
		return "申请中";
	}
	else if(val=="Allocation"){
		return "分配中";
	}
	else if(val=="Mediating"){
		return "中";
	}
	else if(val=="WaitSign"){
		return "待签署";
	}
	else if(val=="WaitComplete"){
		return "待结案";
		
	}
	else if(val=="Closed"){
		return "已";
	}
	else if(val=="Refused"){
		return "已拒绝";
	}
	else if(val=="Completed"){
		return "已结案";
	}
}
function allstateFormat(val,row){
	if(val=="MediationCenterNotAccepted"){
		return "中心未受理";
	}
	else if(val=="MediationCenterAccepted"){
		return "中心已受理未分配";
	}
	else if(val=="MediationCenterAllocated"){
		return "中心已分配";
	}
	else if(val=="AgencyManagerAccepted"){
		return "机构管理员已受理未分配";
	}
	else if(val=="AgencyManagerAllocated"){
		return "机构管理员已分配";
	}
	else if(val=="MediatorAccepted"){
		return "普通员";
	}
	else if(val=="Refuse"){
		return "已拒绝";
	}
}