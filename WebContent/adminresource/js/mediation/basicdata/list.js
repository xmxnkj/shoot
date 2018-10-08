/**
 * 
 */
$dgbasicdatalistlist=null;//列表
$dgbasicdatadetaillist=null;
$dig_editBasicData=null;
$(function(){
	
	var columns = [
	       	    {field :'dataTypeDesc',title:'数据类型',sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'left'},
	       	    {field :'dataValue',title:'值',sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'left'},
	       	    {field :'dataType',hidden:true,sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'left'}
];
	
	$dgbasicdatalistlist = $('#dgbasicdatalistlist').datagrid({
		url: $homebasepath+'/admin/mediation/basicdata/getBasicDataList.shtml',
		queryParams:{parenttype:""},
		toolbar:'#basicdatalist_toolbar',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'dataType',
		sortOrder:'asc',
		columns:[columns],
		onDblClickRow:editBasicData,
		onSelect:getBasicDataDetailList
	});
	
	$('#btnAdd').bind('click', function(){
		addBasicData();
	});
	$('#btnEdit').bind('click', function(){
		editBasicData();
	});
	$('#btnDelete').bind('click', function(){
		deleteBasicData();
	});
	$('#btnAddBasicDataDetail').bind('click', function(){
		addBasicDataDetail();
	});
	$('#btnEditBasicDataDetail').bind('click', function(){
		editBasicDataDetail();
	});
	$('#btnDeleteBasicDataDetail').bind('click', function(){
		deleteBasicDataDetail();
	});
	
});

function refreshData(){
	var params={};
	//params["entityQuery.title"] = $("#title").val();
	$("#dgbasicdatalistlist").datagrid("reload",params);
}
function refreshData2(){
	getBasicDataDetailList();
}

function addBasicData(){
	$dig_editBasicData =  $.easyui.showDialog({
        title: "新增参数",
        width: 550, 
        height: 300,
        href: $homebasepath+"admin/mediation/basicdata/showAddView.shtml",
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

function editBasicData(){
	var row = $('#dgbasicdatalistlist').datagrid('getSelected');
	if(row != null){
		$dig_editBasicData = $.easyui.showDialog({
	        title: "编辑",
	        width: 550, 
	        height: 300,
	        href: $homebasepath+"admin/mediation/basicdata/showEditView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
            autoHCenter: true,
 	        enableSaveButton: true,
 	        saveButtonText:"保存",
 	        enableApplyButton: false,
 	        enableCloseButton: true,  
            maximizable: true,
 	        onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },
 	        onLoad:function(dig){
 	       	     var miframe = $dig_editBasicData.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function getBasicDataDetailList(){
	var columns = [
		       	    {field :'dataTypeDesc',title:'数据类型',sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'left'},
		       	    {field :'dataValue',title:'值',sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'left'},
		       	    {field :'parentType',hidden:true,title:'父类型id',sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'left'}
	];
		
	var parenttypeid = "1";
	var row = $('#dgbasicdatalistlist').datagrid('getSelected');
	if(row!=null){
		parenttypeid = row.id;
	}
	
	$dgbasicdatadetaillist = $('#dgbasicdatadetailist').datagrid({
		url: $homebasepath+'/admin/mediation/basicdata/findDataBindDg.shtml',
		toolbar:'#basicdatadetailist_toolbar',
		queryParams:{
			parenttype:parenttypeid
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
		columns:[columns],
		onDblClickRow:editBasicDataDetail
	});
}

function addBasicDataDetail(){
	var row = $('#dgbasicdatalistlist').datagrid('getSelected');
	if(row != null && row.dataType != "RefuseReason"){
		$dig_editBasicDataDetail = $.easyui.showDialog({
	        title: "编辑子参数",
	        width: 700, 
	        height: 500,
	        href: $homebasepath+"admin/mediation/basicdata/showDetailAddView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
            autoHCenter: true,
 	        enableSaveButton: true,
 	        saveButtonText:"保存",
 	        enableApplyButton: false,
 	        enableCloseButton: true,  
            maximizable: true,
 	        onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },
 	        onLoad:function(dig){
 	        	//var parenttypeid = row.id;
 	        	var miframe = $dig_editBasicDataDetail.dialog('iframe');
 	        	miframe[0].contentWindow.formLoadAddData(row);	
 	        }
	    });		
	}else if(row.dataType == "RefuseReason"){
		$.messager.alert('提示', '拒绝理由没有子类型！');
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function editBasicDataDetail(){
	var row = $('#dgbasicdatadetailist').datagrid('getSelected');
	if(row != null){
		$dig_editBasicDataDetail = $.easyui.showDialog({
	        title: "编辑文档",
	        width: 700, 
	        height: 500,
	        href: $homebasepath+"admin/mediation/basicdata/showDetailEditView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
            autoHCenter: true,
 	        enableSaveButton: true,
 	        saveButtonText:"保存",
 	        enableApplyButton: false,
 	        enableCloseButton: true,  
            maximizable: true,
 	        onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },
 	        onLoad:function(dig){
 	       	     var miframe = $dig_editBasicDataDetail.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function deleteBasicDataDetail(){
	var row = $('#dgbasicdatadetailist').datagrid('getSelected');
	if(row != null){
		if(confirm("确定删除该参数？")){
			var url = $homebasepath+'admin/mediation/basicdata/deleteBasicDataDetail.shtml?';
			submitParams = {
					   'id':row.id
					};
			var jsonData  = $.util.requestAjaxJson(url,submitParams);
			if(jsonData.success){
				$.easyui.messager.alert(jsonData.message);
				$('#dgbasicdatadetailist').datagrid("reload");
			}else{
				$.easyui.messager.alert(jsonData.message);
			}
	    }
	}else{
		$.messager.alert('提示', '请选择一行进行删除!');
	}
}

function deleteBasicData(){
	var row = $('#dgbasicdatalistlist').datagrid('getSelected');
	if(row != null && row.dataTypeDesc != "类别"){
		if(confirm("确定删除该参数？")){
			var url = $homebasepath+'admin/mediation/basicdata/deleteBasicDataDetail.shtml?';
			submitParams = {
					   'id':row.id
					};
			var jsonData  = $.util.requestAjaxJson(url,submitParams);
			if(jsonData.success){
				$.easyui.messager.alert(jsonData.message);
				$('#dgbasicdatalistlist').datagrid("reload");
			}else{
				$.easyui.messager.alert(jsonData.message);
			}
	    }
	}else if(row.dataTypeDesc == "类别"){
		$.messager.alert('提示', '类别无法删除!');
	}else{
		$.messager.alert('提示', '请选择一行进行删除!');
	}
}



