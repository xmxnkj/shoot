/**
 * 
 */
$dgclientlist=null;
$(function(){
	
	initDepartmentTree();
	
	var columns = [
	       	    {field :'clientName',title:'姓名',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	       	    {field :'client',title:'会员账号',formatter:clientFormat,sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'left'},
	       	    //{field :'clientType',title:'会员类型',sortable:true,formatter :clientTypeFormat,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    //{field :'tel',title:'电话',sortable:true,width :parseInt($(this).width()*0.25),halign:'center',align:'right'}
	       	    
	       	    ];
	
	$dgclientlist = $('#tbl').datagrid({
		url: $homebasepath+'/admin/client/clientauthoritygroup/findDataBindDg.shtml',
		toolbar:'#clientlist_toolbar',
		queryParams:{
			clientType:"Normal"
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
		columns:[columns]
		//onDblClickRow:edit
	});
	
	
	
});


function add() {
	top.openDialog('<s:url action="edit" namespace="/uac/user" />',
			"添加用户", 500, 540);
}


function refreshData(departmentId) {
	var node = $("#ulTree").tree("getSelected");
	
	var params = {};
	if(node != null){
		params["entityQuery.authorityGroupId"] = node.id;
	}
	
	$("#tbl").datagrid("reload", params);
}


function clientFormat(value) {
	if(value != null){
		return value.account;
	}
}

function initDepartmentTree(){
	$("#ulTree").tree({
        url: $homebasepath+'/admin/client/authority/getAuthorityGroups.shtml',
        loadFilter: function (rows) {
            return convert(rows, "权限组");
        },
        onClick:function(node){
        	refreshData(node.id);
        }
    });
}


function openSearch(){
	$("#dlg").dialog("open");
}

function search(){
	refreshData();
	$("#dlg").dialog("close");
}

function closeSearch(){
	$("#dlg").dialog("close");
}

