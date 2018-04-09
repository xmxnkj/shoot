/**
 * 
 */
$dglegaldoclist=null;//法规文档列表
$dglegaldocdetaillist=null;
$dig_editLegalDoc=null;
$dig_editLegalDocDetail=null;//编辑文档段落
$(function(){
	
	$("#display").combobox({
		id:'display',
		data:[
			{"id":"true","text":"上架状态"},
			{"id":"false","text":"查看所有","selected":true}
		],
	   	valueField: 'id',
	   	textField: 'text'
	});
	
	
	var columns = [
	       	    {field :'docType',title:'文档类型',formatter:typeFormat,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'left'},
	       	    {field :'title',title:'标题',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'left'},
	       	    {field :'orderdisplay',title:'排序',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	       	    {field :'publishUnit',title:'发布单位',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	       	    {field :'publishTime',title:'发布时间',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
	       	    {field :'display',title:'是否上架',formatter:displayFormat,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
	       	    {field :'operate',title:'操作',sortable:true,formatter:formatOper,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
	       	    {field :'ID',title:'删除',sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'center',formatter:function(val,row){
	       	    	return "<a href='javascript:deleteByID("+'"'+row.id+'"'+");'><font color='orange'><u>删除</u></font></a>";
	       	    }}
];
	
	$dglegaldoclist = $('#dglegaldoclist').datagrid({
		url: $homebasepath+'/admin/mediation/legaldoc/findDataBindDg.shtml',
		toolbar:'#legaldoclist_toolbar',
		queryParams: {          
			"entityQuery.display":$("#display").combobox('getValue')
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
		sortName:'orderdisplay',
		sortOrder:'asc',
		columns:[columns],
		onDblClickRow:editLegalDoc
	});
	
	$('#btnSearch').bind('click', function(){
		refreshData();
	});
	$('#btnAdd').bind('click', function(){
		addLegalDoc();
	});
	$('#btnEdit').bind('click', function(){
		editLegalDoc();
	});
	$('#btnAddLegalDocDetail').bind('click', function(){
		addLegalDocDetail();
	});
	$('#btnEditLegalDocDetail').bind('click', function(){
		editLegalDocDetail();
	});
	$('#btnDeleteLegalDocDetail').bind('click', function(){
		deleteLegalDocDetail();
	});
	
});

function deleteByID(docId){
	if(confirm("确定删除该记录？")){
		 $.ajax({  
		     url : $homebasepath+"admin/mediation/legaldoc/deleteDocById.shtml",  
		     type : "POST",  
		     data : {'legaldocId':docId},
		     type:'POST',
			 dataType:'json', 
		     success : function(data) {
		    	 if(data.state=='OK'){
		    		 alert("删除一条记录");
		    		 $('#dglegaldoclist').datagrid("reload");
		    	 }else{
		    		 alert("删除失败");
		    	 }
		     }
		});
	}
}

function refreshData(){
	var params={};
	params["entityQuery.title"] = $("#title").val();
	params["entityQuery.display"] = $("#display").combobox('getValue');
	$("#dglegaldoclist").datagrid("reload",params);
}

function addLegalDoc(){
	$dig_editLegalDoc =  $.easyui.showDialog({
        title: "新增文档",
        width: 1000, 
        height: 700,
        href: $homebasepath+"admin/mediation/legaldoc/showAddView.shtml",
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

function editLegalDoc(){
	var row = $('#dglegaldoclist').datagrid('getSelected');
	if(row != null){
		$dig_editLegalDoc = $.easyui.showDialog({
	        title: "编辑文档",
	        width: 1000, 
	        height: 700,
	        href: $homebasepath+"admin/mediation/legaldoc/showEditView.shtml",
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
 	       	     var miframe = $dig_editLegalDoc.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function getLegalDocDetailList(){
	var columns = [
		       	    {field :'seq',title:'段落排序',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'content',title:'段落内容',sortable:true,width :parseInt($(this).width()*0.9),halign:'center',align:'left'}
		       	    ];
		
	var legaldocid = "1";
	var row = $('#dglegaldoclist').datagrid('getSelected');
	if(row!=null){
		legaldocid = row.id;
	}
	
	$dglegaldocdetaillist = $('#dglegaldocldetailist').datagrid({
		url: $homebasepath+'/admin/mediation/legaldocdetail/findDataBindDg.shtml',
		toolbar:'#legaldocldetailist_toolbar',
		queryParams:{
			legaldocid:legaldocid
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
		onDblClickRow:editLegalDocDetail
	});
}

function addLegalDocDetail(){
	var row = $('#dglegaldoclist').datagrid('getSelected');
	if(row != null){
		$dig_editLegalDocDetail = $.easyui.showDialog({
	        title: "编辑文档段落",
	        width: 700, 
	        height: 500,
	        href: $homebasepath+"admin/mediation/legaldocdetail/showAddView.shtml",
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
 	        	var legaldocid = row.id;
 	        	var miframe = $dig_editLegalDocDetail.dialog('iframe');
 	        	miframe[0].contentWindow.formLoadAddData(legaldocid);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function editLegalDocDetail(){
	var row = $('#dglegaldocldetailist').datagrid('getSelected');
	if(row != null){
		$dig_editLegalDocDetail = $.easyui.showDialog({
	        title: "编辑文档",
	        width: 700, 
	        height: 500,
	        href: $homebasepath+"admin/mediation/legaldocdetail/showEditView.shtml",
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
 	       	     var miframe = $dig_editLegalDocDetail.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function deleteLegalDocDetail(){
	var row = $('#dglegaldocldetailist').datagrid('getSelected');
	if(row != null){
		if(confirm("确定删除该记录？")){
			var url = $homebasepath+'admin/mediation/legaldocdetail/deleteLegalDetail.shtml';
			var jsonData  = $.util.requestAjaxJson(url,{'id':row.id});
			/*if(jsonData.success){
				$.easyui.messager.alert("删除成功!");
			}else{
				$.easyui.messager.alert("删除失败!");
			}*/
			$('#dglegaldocldetailist').datagrid("reload");
		}
	}else{
		$.messager.alert('提示', '请选中一行进行删除!');
	}
}

function refreshDetailsData(){
	getLegalDocDetailList();
}

function typeFormat(val,row){
	if(val=="Policy"){
		return "政策文件";
	}
	else if(val=="Legal"){
		return "法律法规";
	}
	else if(val=="OfficeCopy"){
		return "文书格式";
	}
}

function displayFormat(val,row){
	if(val==true){
		return "上架中";
	}
	else if(val==false){
		return "下架中";
	}
}

function downloadFormat(val,row){
	if(val==true){
		return "可下载";
	}
	else if(val==false){
		return "不能下载";
	}
}

function formatOper(val,row,index){
	$.log(row.display);
	if(row.display==true){
		return '<a href="javascript:void(0);" onclick="upAndDownShelve('+index+',0)"><font color="#FF0000"><u>下架</u></font></a>';
	}else{
		return '<a href="javascript:void(0);" onclick="upAndDownShelve('+index+',1)"><font color="#006400"><u>上架</u></font></a>';
	}
}

function upAndDownShelve(index,oper){
	var rows = $dglegaldoclist.datagrid('getRows');
	var row = rows[index];
	var url = $homebasepath+'admin/mediation/legaldoc/upAndDownShelve.shtml?';
	submitParams = {
			   'id':row.id,
			   'oper':oper
			};
	if(confirm("是否确定操作？")){
		var jsonData  = $.util.requestAjaxJson(url,submitParams);
		if(jsonData.success){
			$.easyui.messager.alert("操作成功!");
			$('#dglegaldoclist').datagrid("reload");
		}else{
			$.easyui.messager.alert(jsonData.message);
		}
	}
}

