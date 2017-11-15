/**
 *服务-投诉list 
 */
 
$dgcomplain=null;
$(function() {
	
	$.log($homebasepath);
	$.log($homepath);
	
	var columns = [
	    {field :'clientnickname',title:'投诉人',formatter:clientNickNameFormat,sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'left'},
	    {field :'complaindatetime',title:'投诉时间',sortable:true,width :parseInt($(this).width()*0.12),halign:'center',align:'center'},
	    {field :'complaincontent',title:'投诉内容',sortable:true,width :parseInt($(this).width()*0.12),halign:'center',align:'left'}
    ];
	
	$dgcomplain = $('#dgcomplain').datagrid({
		url: $homebasepath+'admin/service/complain/findDataBindDg.shtml',
		toolbar:'#complainlist_toolbar',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'complaindatetime',
		sortOrder:'asc',
		columns:[columns]
	});
	
});


function clientNickNameFormat(val,row){  
	 try{
	   	if(row.client!=undefined && row.client) 
	   	   return row.client.nickName;  
	    else return "";
	 }catch(e){
	 	return "";
	 }
} 
