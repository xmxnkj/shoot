var $dig_newsHeadInfo=null;	// 编辑窗口
var $dig_newsHeadInfoAdd=null;	// 新增窗口
/**
 * 
 */
$dgnewslist=null;//新闻列表
$(function(){
	
	var columns = [
	       	    	{field :'articletitle',title:'标题',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	       	    	{field :'authorName',title:'作者',width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	       	    	{field :'likenums',title:'点赞数',sortable:true,width :parseInt($(this).width()*0.10),halign:'center',align:'left'},
	       	    	{field :'orderdisplay',title:'排序',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'commentnums',title:'评论数',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'newsUnit',title:'新闻单位',width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'articledatetime',title:'发布时间',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'ishow',title:'显示状态',formatter : ishow ,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'momentsResources',title:'主图',formatter : MainImg ,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
	       	    	{field :'newComments',title:'未读评论数',formatter : function(val){if(val){return val;}else{return 0;}} ,width :parseInt($(this).width()*0.15),halign:'center',align:'center'}
	       	    ];
	
	$dgnewslist = $('#newslist').datagrid({
		url: $homebasepath+'/admin/news/getNewsHeadInfoList.shtml',
		toolbar:'#newslist_toolbar',
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
		sortOrder:'desc',
		columns:[columns],
		onDblClickRow:edit
	});
	
	$('#btnSearch').bind('click', function(){
		refreshData();
	});
	
	$('#btnEdit').bind('click', function(){
		edit();
	});
	
	$('#btnRemove').bind('click', function(){
		removeNewsHead();
	});
	
	$('#btnAdd').bind('click', function(){
		addNewsHeadInfo();
	});
	
	$('#btnOut').bind('click', function(){
		outExcel();
	});
	
});

//新增一新的新闻
function addNewsHeadInfo(){
	$dig_newsHeadInfoAdd = $.easyui.showDialog({
        title: "新增数据",
        width: 1000, 
        height: 800,
        href: $homebasepath+"admin/news/addPage.shtml",
        topMost: true,
        cache: false,
	    modal: true,
        iniframe:true,
        autoVCenter: true,
        autoHCenter: true,
        enableSaveButton: true,
        saveButtonText:'保存',
        enableApplyButton: false,
        enableCloseButton: true,  
        onSave:function(dig){
        	var miframe = dig.dialog('iframe');
        	miframe[0].contentWindow.formsubmit();
        }
    });
}

function edit(){
	var row = $('#newslist').datagrid('getSelected');
	if(row != null){
		$dig_newsHeadInfo = $.easyui.showDialog({
	        title: "详细信息",
	        width: 1000, 
	        height: 800,
	        href: $homebasepath+"admin/news/showEditView.shtml",
	        topMost: true,
	        cache: false,
		    modal: true,
	        iniframe:true,
	        autoVCenter: true,
            autoHCenter: true,
 	        enableSaveButton: true,
 	        saveButtonText:'保存',
 	        enableApplyButton: false,
 	        enableCloseButton: true,  
 	        onSave:function(dig){
	 	   		var miframe = dig.dialog('iframe');
	        	miframe[0].contentWindow.formsubmit();
	        	return false;
 	        },
 	        onLoad:function(dig){
 	       	     var miframe = $dig_newsHeadInfo.dialog('iframe');
 	       	     miframe[0].contentWindow.formLoadEditData(row);	//新闻头部信息
		         miframe[0].contentWindow.formLoadMainImg(row);		//新闻主图
		         miframe[0].contentWindow.initMainImgUpload(row); 	//新闻主图上传按钮初始化
		         //miframe[0].contentWindow.initNewsDetails(row); 	//加载内容列表
		         miframe[0].contentWindow.initNewsComments(row);		//加载评论列表
 	        }
	    });

	}else{
		$.messager.alert('提示', '请选择一行进行修改！');
	}
}

function removeNewsHead(){
	var row = $('#newslist').datagrid('getSelected');
	if(row != null){
		$.post($homebasepath+"admin/news/deleteNewsHeadInfo.shtml",{"id":row.id},function(data){
			refreshData();
		});
	}else{
		$.messager.alert('提示', '请选择一行进行删除！');
	}
}

function refreshData(){
	var params={};
	var articletitle = $("#articletitle").val();
	if(articletitle!=""){
		params["entityQuery.articletitle"] = articletitle;
	}
	$("#newslist").datagrid("reload",params);
}

function ishow(val,row){  
    if(val=='YES'){
       return "<a href='javascript:toshow("+'"'+row.id+'"'+")' class='easyui-linkbutton' data-options='iconCls:"+'"'+"icon-add"+'"'+",toggle:true'>显示</a>";
    }else{
       return "<a href='javascript:toshow("+'"'+row.id+'"'+")' class='easyui-linkbutton' data-options='iconCls:"+'"'+"icon-add"+'"'+",toggle:true'>隐藏</a>";
    }
}

function toshow(id){
	$.ajax({
		url:$homebasepath+'admin/news/ishow.shtml',
		type:'POST',
		data:{"id":id},
		dataType:'json', 
		success:function(data){
			$("#newslist").datagrid("reload");
		}
	});
}

function MainImg(val,row){
	var url = (val==null?"":val.url);
	return "<img width='100' height='100' src='"+$homebasepath+"uploads/news/"+url+"'/>";
}
