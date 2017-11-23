$dgnewsResourceslist=null;//新闻列表
$(function(){
	
	var columns = [
	       	    
	       	    	{field :'url',title:'图片',sortable:true,formatter:formatImg,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
	       	    	{field :'rescreatedatatime',title:'创建时间',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
	       	    	{field :'link',title:'链接地址',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
	       	    	{field :'title',title:'标题',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},

	       	      ];
	
	$dgnewsResourceslist = $('#newsResourceslist').datagrid({
		url: $homebasepath+'/admin/news/momentsResourcesAction/lunboImgList.shtml',
		toolbar:'#newsResourceslist_toolbar',
		queryParams: {
			"newsHeadInfoId":"",
			"newsDetailsId":""
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
		columns:[columns],
		onDblClickRow:function(){
			uploadMallResoueces('1');
		}
	});

	$('#uploadSearch').bind('click', function(){
		uploadMallResoueces('0');
	});

	$('#uploadEdit').bind('click', function(){
		uploadMallResoueces('1');
	});
	
	$('#uploadRemove').bind('click', function(){
		deleteById();
	});
});

//删除轮播图
function deleteById(){
	var row = $dgnewsResourceslist.datagrid("getSelected");
	if(row != null){
		$.post($homebasepath+"admin/news/momentsResourcesAction/removeById.shtml",{"id":row.id},function(data){
			//$.messager.alert(data.message);
			$dgnewsResourceslist.datagrid("reload");
		})
	}else{
		$.messager.alert('提示', '请选择一行进行删除！');
	}
}

function uploadMallResoueces(type){
	var row;
	if(type=='1'){	//编辑
		row = $dgnewsResourceslist.datagrid("getSelected");
		if(row == null){
			$.messager.alert('提示', '请选择一行进行编辑！');
			return;
		}
	}
	
	$dig_uploadMallResources = $.easyui.showDialog({
        title: "上传新闻广告图片",
        width: 800, 
        height: 800,
        href: $homebasepath+"/admin/news/MomentsResources/uploadResources.shtml",
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
		closeButtonText:"取消",
		onSave : function(dig) {
			var miframe = $dig_uploadMallResources.dialog('iframe');
			miframe[0].contentWindow.submitForm();
			$('#newsResourceslist').datagrid("reload");
		},
		onLoad:function(dig){
		    var miframe = $dig_uploadMallResources.dialog('iframe');
		    if(row!=null){
		    	 miframe[0].contentWindow.formLoadEditData(row);	//表单填充
		    	 miframe[0].contentWindow.initUpload(row.id);		//传递ID
		    }else{
		    	miframe[0].contentWindow.initUpload('0');
		    }
		}
    });		
}

function formatImg(val,row){
	return "<img width='100' height='100' src='"+$homebasepath+"uploads/news/"+val+"'/>";
}