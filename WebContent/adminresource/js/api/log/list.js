/**
 *api日志list 
 */
 
$dgapilog=null;
$(function() {
	
	$.log($homebasepath);
	$.log($homepath);
	
	var columns = [
	    {field :'errorflag',title:'错误否',sortable:true,formatter:errorflagFormat,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
	    {field :'modulecode',title:'模块代码',sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'left'},
	    {field :'bizcode',title:'业务代码',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'left'},
	    {field :'bizmethod',title:'业务方法',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'left'},
        {field :'errorcode',title:'错误代码',sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'left'}
    ];
	
	$dgapilog = $('#dgapilog').datagrid({
		url: $homebasepath+'/admin/api/log/findDataBindDg.shtml',
		toolbar:'#apiloglist_toolbar',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'createtime',
		sortOrder:'desc',
		columns:[columns],
		onDblClickRow:details
	});
	
});

function details(){
	var row = $('#dgapilog').datagrid('getSelected');
	if(row != null){
			$dig_apilogdetails = $.easyui.showDialog({
		            title: "日志详情信息",
			        width: 600, 
			        height: 500,
			        href: $homebasepath+"/admin/api/log/showEditView.shtml",
			        topMost: true,
			        cache: false,
				    modal: true,
			        iniframe:true,
			        autoVCenter: true,
		            autoHCenter: true,
		 	        enableSaveButton: false,
		 	        enableApplyButton: false,
		 	        enableCloseButton: true, 
                    maximizable: true,
                    autoRestore: true,		 	        
		 	        onLoad:function(dig){
		 	       	     var miframe = $dig_addMessage.dialog('iframe');
				         miframe[0].contentWindow.formLoadEditData(row);	
		 	        }
			});
	}
	
	
}

function errorflagFormat(val,row){  
    if(val=="Y") 
       return '<font color="#FF0000">错误</font>';
    else if(val=="N") 
       return '<font color="#006400">正常</font>';
    else
    	return "";
}