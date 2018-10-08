/**
 * 
 */
$dglegaldoclist=null;//经典案例文档列表
$dglegaldoccommentslist=null;
$dig_editLegalDoc=null;
$(function(){
	
	var columns = [
	       	    {field :'docType',title:'文档类型',formatter:typeFormat,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'left'},
	       	    {field :'classicCase',title:'经典案例类型',formatter:typeFormatClassic,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'left'},
	       	    {field :'title',title:'标题',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'left'},
	       	    {field :'publishUnit',title:'作者',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	       	    {field :'mediatorClient',title:'员',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left',formatter:function(val,row){
	       	    	if(row.mediatorClient){
	       	    		return row.mediatorClient.identifyName;
	       	    	}
	       	    	return "未分配员";
	       	    }},
	       	    {field :'publishTime',title:'时间',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    {field :'publishUnit',title:'发布单位',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
	       	    //{field :'download',title:'能否下载',formatter:downloadFormat,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
	       	    {field :'display',title:'是否上架',formatter:displayFormat,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
	       	    {field :'operate',title:'操作',sortable:true,formatter:formatOper,width :parseInt($(this).width()*0.05),halign:'center',align:'center'}
	       	    //{field :'image',title:'配图',sortable:true,formatter:showImg,width :parseInt($(this).width()*0.15),halign:'center',align:'center'}
];
	
	$dglegaldoclist = $('#dglegaldoclist').datagrid({
		url: $homebasepath+'/admin/mediation/legaldoc/findDataBindDg.shtml',
		toolbar:'#legaldoclist_toolbar',
		queryParams:{
			classic:"true"
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
		sortName:'publishTime',
		sortOrder:'desc',
		columns:[columns],
		onLoadSuccess:getLegalDocCommentsList,
		onDblClickRow:editLegalDoc,
		onSelect:getLegalDocCommentsList
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
	
});
//刷新法规文档
function refreshData(){
	var params={};
	params["entityQuery.title"] = $("#title").val();
	$dglegaldoclist.datagrid("reload",params);
}

//刷新经典案例
function refreshDataClass(){
	var params={};
	params["entityQuery.title"] = $("#title").val();
	params["classic"] = "true";
	$dglegaldoclist.datagrid("reload",params);
}

function addLegalDoc(){
	$dig_editLegalDoc =  $.easyui.showDialog({
        title: "新增文档",
        width: 1000, 
        height: 800,
        href: $homebasepath+"admin/mediation/legaldoc/showClassicCaseAddView.shtml",
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
		},
		onLoad:function(dig){
			var miframe = $dig_editLegalDoc.dialog('iframe');
			miframe[0].contentWindow.formLoadAddData();	
		}
    });		
}

function editLegalDoc(){
	var row = $('#dglegaldoclist').datagrid('getSelected');
	if(row != null){
		$dig_editLegalDoc = $.easyui.showDialog({
	        title: "编辑文档",
	        width: 1000, 
	        height: 800,
	        href: $homebasepath+"admin/mediation/legaldoc/showClassicCaseEditView.shtml",
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

function getLegalDocCommentsList(){
	var columns = [
		       	    {field :'commentsClientName',title:'评论人',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'left'},
		       	    {field :'commentsContent',title:'评论内容',sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'left'},
		       	    {field :'commentsTime',title:'评论时间',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'left'},
		       	    {field :'audit',title:'审核状态',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'operate',title:'审核',sortable:true,formatter:formatOperComments,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
		       	    {field :'del',title:'删除',sortable:true,formatter:function(val,row){
		       	    	return "<a href='javascript:deleteById("+'"'+row.id+'"'+");'><font color='blue'><u>删除</u></font></a>";
		       	    },width :parseInt($(this).width()*0.1),halign:'center',align:'center'}
		       	    
		       	    ];
		
	var legaldocid = "1";
	var row = $('#dglegaldoclist').datagrid('getSelected');
	if(row!=null){
		legaldocid = row.id;
	}
	
	$dglegaldoccommentslist = $('#dglegaldoccommentslist').datagrid({
		url: $homebasepath+'/admin/mediation/legaldoccomments/findDataBindDg.shtml',
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
		sortName:'commentsTime',
		sortOrder:'desc',
		columns:[columns]
	});
}

function deleteById(id){
	$.ajax({
		url:$homebasepath+'/admin/mediation/legaldoccomments/deleteComment.shtml',
		data:{"id":id},
		type:'POST',
		async: false,
		dataType:'json', 
		success:function(data){
			$('#dglegaldoccommentslist').datagrid('reload');
		}
	});
}

function formatOperComments(val,row,index){
	if(row.audit){
		return '<font color="green"><u>已通过</u></font></a>';
	}
	return '<a href="javascript:void(0);" onclick="auditComments('+index+')"><font color="#FF0000"><u>通过</u></font></a>';
}

function auditComments(index,oper){
	var rows = $dglegaldoccommentslist.datagrid('getRows');
	var row = rows[index];
	var url = $homebasepath+'/admin/mediation/legaldoccomments/auditComments.shtml?';
	submitParams = {
			   'id':row.id
			};
	if(confirm("是否确定审核通过？")){
		var jsonData  = $.util.requestAjaxJson(url,submitParams);
		if(jsonData.success){
			$.easyui.messager.alert("操作成功!");
			$('#dglegaldoccommentslist').datagrid("reload");
		}else{
			$.easyui.messager.alert(jsonData.message);
		}
	}
}

function typeFormat(val,row){
	if(val=="Policy"){
		return "政策";
	}
	else if(val=="Legal"){
		return "法规";
	}
	else if(val=="OfficeCopy"){
		return "文书";
	}
	else if(val=="ClassicCase"){
		return "经典案例";
	}
}

function typeFormatClassic(val,row){
	if(val=="CivilMediationCase"){
		return "人民案例";
	}
	else if(val=="JudicialDecisionCase"){
		return "法院判决案例";
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
function showImg(val,row){
	var url = (val==null?"":val);
	return "<img width='100' height='100' src='"+$homebasepath+"uploads/mediation/legaldoc/"+url+"'/>";
}
