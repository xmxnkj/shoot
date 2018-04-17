/**
 *设备列表list 
 */
 
$dgbasissetlist=null;
$(function() {
	
	$.log($homebasepath);
	$.log($homepath);
	
	var columns = [
	    {field :'parametername',title:'参数名称',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	    {field :'parametercode',title:'参数代码',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'left'},
	    //{field :'parameterseq',title:'参数序号',sortable:true,width :parseInt($(this).width()*0.25),halign:'center',align:'left'},
	    {field :'parameterinitvla',title:'参数值 ',formatter:stateFormat,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
	    //{field :'deleteflag',title:'是否删除',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	    {field :'buliddatetime',title:'生成时间',sortable:true,formatter:timeFormat,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
	    {field :'operate',title:'操作',sortable:true,formatter:formatOper,width :parseInt($(this).width()*0.07),halign:'center',align:'center'}
	    ];
	
	$dgbasissetlist = $('#dgbasissetlist').datagrid({
		url: $homebasepath+'/admin/basisset/findDataBindDg.shtml',
		toolbar:'#basissetlist_toolbar',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		columns:[columns]
	});
	$('#btnSearch').bind('click', function(){
		refreshData();
	});
	$('#btnAdd').bind('click', function(){
		add();
	});
	$('#btnEdit').bind('click', function(){
		edit();
	});
	$('#btnDel').bind('click', function(){
		remove();
	});
});
function refreshData(){
	var params={};
	params["entityQuery.parametername"] = $("#parametername").val();
	$("#dgbasissetlist").datagrid("reload",params);
}
var $dig_addMessage;
function add(){
	$dig_addMessage =  $.easyui.showDialog({
        title: "新增基础数据",
        width: 750, 
        height: 500,
        href: $homebasepath+"admin/basisset/showAddView.shtml",
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
var $dig_editMessage;
function edit(){
	var row = $('#dgbasissetlist').datagrid('getSelected');
	if(row != null){
		$dig_editMessage = $.easyui.showDialog({
	        title: "详细信息",
	        width: 600, 
	        height: 500,
	        href: $homebasepath+"admin/basisset/showEditView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
            autoHCenter: true,
 	        enableSaveButton: true,
 			saveButtonText : "保存",
 	        enableApplyButton: false,
 	        enableCloseButton: true,  
 	        onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },
 	        onLoad:function(dig){
 	       	     var miframe = $dig_editMessage.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });					

		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}



/*格式化时间*/
function timeFormat(val,row){
	if(val==null){
		return "";
	}else{
		var   now=new   Date(val);
		var   year=now.getFullYear();     
		var   month=now.getMonth()+1;     
		var   date=now.getDate();     
		var   hour=now.getHours();     
		var   minute=now.getMinutes();     
		var   second=now.getSeconds();    
		return   year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
	}
}

function stateFormat(val,row){
	if(val=="0"){
		return "关闭中";
	}else if(val=="1"){
		return "开启中";
	}
}

function formatOper(val,row,index){
	return '<a href="javascript:void(0);" onclick="certificateSwitch('+index+',0)"><font color="#FF0000"><u>关闭</u></font></a>/<a href="javascript:void(0);" onclick="certificateSwitch('+index+',1)"><font color="#006400"><u>开启</u></font></a>';
}

function certificateSwitch(index,oper){
	var rows = $dgbasissetlist.datagrid('getRows');
	var row = rows[index];
	var url = $homebasepath+'admin/basisset/certificateSwitch.shtml?';
	submitParams = {
			   'id':row.id,
			   'oper':oper
			};
	if(confirm("是否确定操作？")){
		var jsonData  = $.util.requestAjaxJson(url,submitParams);
		if(jsonData.success){
			$.easyui.messager.alert("操作成功!");
			$('#dgbasissetlist').datagrid("reload");
		}else{
			$.easyui.messager.alert(jsonData.message);
		}
	}
}


