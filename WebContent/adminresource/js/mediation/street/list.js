
var $streetlist;

$(function(){
	
	var columns = [
	       	    	{field :'streetNname',title:'街道名称',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'left'},
	       	    	{field :'a',title:'删除',sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'left',formatter:function(val,row){
	       	    		return "<a href='javascript:deleteEntity("+'"'+row.id+'"'+")'><font color='red'>删 除</font></a>";
	       	    	}},
	       	    ];
	
	$streetlist = $('#streetList').datagrid({
		url: $homebasepath+'/admin/mediation/street/getList.shtml',
		toolbar:'#streetList_toolbar',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		columns:[columns],
		onDblClickRow:function(){
			var row = $streetlist.datagrid('getSelected');
			initEdit(row);
		}
	});
});

function initEdit(row){
	$("#id").val(row.id);
	$("#streetName").val(row.streetNname);
}

function editEntity(){
	$.ajax({
		url:$homebasepath+'admin/mediation/street/updateEntity.shtml',
		data:{	
				"id":$("#id").val(),
				"streetName":$("#streetName").val()
			},
		type:'POST',
		dataType:'json', 
		success:function(data){
			if(data.status=='OK'){
				$('#streetList').datagrid("reload");
				$("#id").val("");
				$("#streetName").val("");
			}
		}
	});
}

function deleteEntity(id){
	$.ajax({
		url:$homebasepath+'admin/mediation/street/deleteEntity.shtml',
		data:{	
				"entity.id":id,
			},
		type:'POST',
		dataType:'json', 
		success:function(data){
			if(data.status=='OK'){
				$('#streetList').datagrid("reload");
			}
		}
	});
}