if(typeof(toolbars)=='undefined'){
	var toolbars=[];
}
if(typeof(defaultToolbar) == 'undefined'){
	toolbars[0] = {iconCls:'icon-add', text:'添加', handler:add};
	toolbars[1] = {iconCls:'icon-edit', text:'修改', handler:edit};
	toolbars[2] = {iconCls:'icon-remove', text:'删除', handler:del};
}
$(function(){
	if(typeof(listjsonUrl) != 'undefined'){
		$('#tbl').datagrid({
			toolbar:toolbars,
			fit:true,
			url:listjsonUrl,
			onDblClickRow:edit,
			singleSelect: true,
			pagination:true
		});
	}
}); 
function add(){
	if(typeof(isPage)=='undefined' || !isPage){
		top.openDialog(editUrl,
				addTitle,
				dlgWidth, dlgHeight
				);
	}else{
		location.href = editUrl;
		return false;
	}
}

function edit(){
	var row = $('#tbl').datagrid('getSelected');
	if(row != null){
		if(editUrl.lastIndexOf('?')>=0){
			url = editUrl + '&id=' + row.id;
		}else{
			url = editUrl + '?id=' + row.id;
		}
		if(typeof(isPage)=='undefined' || !isPage){
			top.openDialog(url,
					editTitle,
					dlgWidth, dlgHeight
					);
		}else{
			location.href=url;
		}
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function del(){
	var row = $('#tbl').datagrid('getSelected');
	if(row != null){
		$.messager.confirm(confirmDeleteTitle, confirmDeleteInfo, function (data) {
            if (data) {
                $.ajax({
                    url: deleteUrl + '?id=' + row.id+ "&rnd="+ Math.random(),
                    type: 'get',
                    timeout: TIMEOUT,
                    success: function (data) {
                        if(dealJsonResult(data, deleteSuccess)){
                        	$('#tbl').datagrid('reload');	
                        }
                    },
                    error: function () {
                        $.messager.alert(WINDOW_CAPTION, '网络原因导致删除失败！', 'error');
                    }
                });
            }
        });
	}
}

function refreshData(){
	$("#tbl").datagrid("reload");
}