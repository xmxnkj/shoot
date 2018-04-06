/**
 * 
 */
$mediatorapplylist=null;//申请列表
$dig_editMediatorapply=null;//编辑案件窗口

$(function(){
	var columns = [
	       	    	{field :'applyClient',title:'申请人',formatter :clientFormat,sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	       	    	{field :'applyReason',title:'申请理由',width :parseInt($(this).width()*0.15),halign:'center',align:'left'},
	       	    	{field :'applyTime',title:'申请日期',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
	       	    	{field :'applyState',title:'申请状态',formatter :stateFormat,sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'auditTime',title:'审核时间',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'}
	       	    ];
	
	$mediatorapplylist = $('#mediatorapplylist').datagrid({
		url: $homebasepath+'/admin/client/mediatorapply/findDataBindDg.shtml',
		toolbar:'#mediatorapplylist_tabs',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'applyState',
		sortOrder:'asc',
		columns:[columns],
		onDblClickRow:editMediatorapply
	});
	
	$('#btnEdit').bind('click', function(){
		editMediatorapply();
	});

});

function editMediatorapply(){
	var row = $('#mediatorapplylist').datagrid('getSelected');
	if(row != null){
		$dig_editMediatorapply = $.easyui.showDialog({
	        title: "查看详情",
	        width: 800, 
	        height: 600,
	        href: $homebasepath+"admin/client/mediatorapply/showEditView.shtml",
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
 	        onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },
 	        onLoad:function(dig){
 	       	     var miframe = $dig_editMediatorapply.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function clientFormat(val,row){
	return val.identifyName;
}
function stateFormat(val,row){
	if(val=="Init"){
		
		return "初始化申请中";
	}
	else if(val=="Pass"){
		
		return "申请通过";
	}
	else if(val=="NotPass"){
		
		return '申请失败';
	}
}