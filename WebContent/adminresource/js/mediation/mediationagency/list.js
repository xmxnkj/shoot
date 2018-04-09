/**
 * 
 */
$dgmediationagencylist=null;//案件列表
$dig_editMediationAgency=null;//编辑案件窗口
$dgmediatorlist=null;
$(function(){
	
	var columns = [
		       	    {field :'agencyType',title:'机构类型',formatter:typeFormat,sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'left'},
		       	    {field :'agencyName',title:'机构名称',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'address',title:'地址',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'left'},
		       	    {field :'tel',title:'电话',sortable:true,width :parseInt($(this).width()*0.07),halign:'center',align:'left'},
		       	    {field :'managerClient',title:'管理员',formatter:managerFormat,sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
		       	    {field :'openOrNot',title:'是否开启在线服务',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
		       	    {field :'description',title:'简介',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'center'},
		       	    {field :'mediationResourceId',title:'缩略图',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'center',formatter:function(val,row){
		       	    	return "<img width='50' height='50' src='"+$homebasepath+"/uploads/mediation/"+val+"'/>";
		       	    }},
	       	    ];
	
	$dgmediationagencylist = $('#dgmediationagencylist').datagrid({
		url: $homebasepath+'/admin/mediation/mediationagency/findDataBindDg.shtml',
		toolbar:'#mediationagencylist_toolbar',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		//sortName:'createtime',
		//sortOrder:'desc',
		columns:[columns],
		onLoadSuccess:getMediatorListInAgency,
		onDblClickRow:editMediationAgency,
		onSelect:getMediatorListInAgency
	});
	
	$('#btnSearch').bind('click', function(){
		refreshData();
	});
	
	$('#btnEdit').bind('click', function(){
		editMediationAgency();
	});
	
	$('#btnAdd').bind('click', function(){
		addMediationAgency();
	});
	
});

function refreshData(){
	var params={};
	params["entityQuery.agencyName"] = $("#agencyName").val();
	$("#dgmediationagencylist").datagrid("reload",params);
}

function addMediationAgency(){
	$dig_editMediationAgency = $.easyui.showDialog({
        title: "新增机构",
        width: 900, 
        height: 700,
        href: $homebasepath+"admin/mediation/mediationagency/showAddView.shtml",
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
	        }
    });		
}

function editMediationAgency(){
	var row = $('#dgmediationagencylist').datagrid('getSelected');
	if(row != null){
		$dig_editMediationAgency = $.easyui.showDialog({
	        title: "机构详情",
	        width: 900, 
	        height: 700,
	        href: $homebasepath+"admin/mediation/mediationagency/showEditView.shtml",
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
 	       	     var miframe = $dig_editMediationAgency.dialog('iframe');
		         miframe[0].contentWindow.formLoadEditData(row);	
 	        }
	    });		
	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}
function typeFormat(val,row){
	if(val=="CivilCaseType"){
		return "人民调解机构";
	}
	if(val=="AdministrationCaseType"){
		return "行政调解机构";
	}
	if(val=="JudicialCaseType"){
		return "司法调解机构";
	}
}
function managerFormat(val,row){
	if(val!=null){
		return val.identifyName;
	}
}

function clientTypeFormat(val,row){  
    if(val=="Normal") 
       return "普通会员";
    else if(val=="Visitor") 
       return "游客";
    else if(val=="Mediator") 
        return "调解员";
}
function clientStateFormat(val,row){  
	if(val=="MediationCenter") 
		return "调解中心管理员";
	else if(val=="MediationAgency") 
		return "调解机构管理员";
	else if(val=="Mediator") 
		return "普通调解员";
}

function formatImg(val,row){
	return "<img width='30' height='30' src='"+$homebasepath+"/uploads/client/"+val+"'/>";
}
var address = "";
var agencyName = "";
function getMediatorListInAgency(){
	var columns = [
	                {field :'identifyName',title:'姓名',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	                {field :'headImgFile',title:'会员头像',sortable:true,formatter: formatImg,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	                {field :'account',title:'会员账号',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'left'},
	                {field :'clientType',title:'会员类型',sortable:true,formatter:clientTypeFormat,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
	                {field :'clientState',title:'会员身份',sortable:true,formatter:clientStateFormat,width :parseInt($(this).width()*0.1),halign:'center',align:'center'},
	                {field :'agencyName',title:'属于调解机构',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'right'},
	                {field :'address',title:'地址',sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'right'}
	         	   ];
		
	var agencyid = "1";
	var row = $('#dgmediationagencylist').datagrid('getSelected');
	if(row!=null){
		agencyid = row.id;
		address = row.address;
		agencyName = row.agencyName;
	}
	
	$dgmediatorlist = $('#dgmediatorlist').datagrid({
		url: $homebasepath+'/admin/client/findDataBindDg.shtml',
		//toolbar:'#legaldocldetailist_toolbar',
		queryParams:{
			agencyId:agencyid
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
		sortName:'clientState',
		sortOrder:'asc',
		columns:[columns],
		onLoadSuccess:function(data){
			var datas = $('#dgmediatorlist').datagrid("getRows");
			for(var i = 0;i<datas.length;i++){
				datas[i].address = address;
				datas[i].agencyName = agencyName;
				$('#dgmediatorlist').datagrid('refreshRow', i);
			}
		}
		
		//onDblClickRow:editLegalDocDetail
	});
}
function kill(){
	var row = $('#dgmediationagencylist').datagrid('getSelected');
	if(row){
		$.ajax({
			url:$homebasepath+'/admin/mediation/mediationagency/kill.shtml',
			data:{"id":row.id},
			type:'POST',
			async: false,
			dataType:'json', 
			success:function(data){
				$("#dgmediationagencylist").datagrid("reload");
			}
		});
	}else{
		alert("请选择一条数据进行删除！");
	}
}

