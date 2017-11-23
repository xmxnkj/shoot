
var uploadFileContentType = "";

$(function(){
	loadNewsId();
}); 

function formLoadEditData(row){
	$('#uploadresform').form("jsonLoad",row);
	$("#resource").attr("src",$homebasepath+"uploads/news/"+row.url);
	
	var id = $("#id").val();	
	loadMyNews(row.link);
	$("#newId").val(row.link);
}

function submitForm(){
	var id = $("#id").val();
	var url = $("#url").val();
	var newId = $("#newId").val();
	var title = $("#title").val();
	
	if(!newId){
		alert("请选择新闻列表");
		return;
	}
	if(!url){
		alert("请上传图片");
		return;
	}
	if(!title){
		alert("标题不能为空");
		return;
	}

	$.ajax({
        type: "POST",
        url: $homebasepath+"admin/news/momentsResourcesAction/getForm.shtml",
        data:{"id":id,"url":url,"newId":newId,"title":title},
        async: false,
        error: function(request) {
            alert("提交失败");
        },
        success: function(data) {
        	$.easyui.parent.$dig_uploadMallResources.dialog('close');
        	$('#newsResourceslist').datagrid("reload");
        }
    });
}

/**
 *上传附件
 */
function initUpload(id){
	var bt_uploadacc = $('#btn_uploadres');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/news/momentsResourcesAction/uploadResources.shtml",
		data:{"id":id},
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
			var newId = $("#newId").val();
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG)$/.test(ext))){		
				alert('对不起您选择文件系统限制上传!');
				return false;	
			}
			
			var img = $("[name='imgFile']")[0].files[0];
			 //读取图片数据
            var image = new Image();
            image.src = getObjectURL(img);
            image.onload=function(){
                var width = image.width;
                var height = image.height;
                if(width<750 || height<750){
                	alert("当前图片尺寸为:"+width+"*"+height+",建议图片的宽大于750*750");
                }
            };
            
            if(img.size>2097152){
            	alert("上传的图片不超过2M！");
            	return false;
            }
			$.messager.progress({ title:'请稍后', msg:'正在拼命上传中,不要急...' });
		},
		onComplete: function(file, response){//文件提交完成后可执行的方法
			//进度条隐藏 TODO
			$.messager.progress('close');
			if(response.resultDescription.state=='OK'){
				//预览
				var url = $homebasepath+"uploads/news/"+response.resultDescription.urlpath+"";
				$("#resource").attr("src",url);
				$("#url").val(response.resultDescription.urlpath);
			}else{
				alert(response.resultDescription.msg);
			}
			
		}
	});
}

//建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}

//新闻列表
function loadNewsId(){

	var columns = [
	       	    	{field :'articletitle',title:'标题',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	       	    	{field :'authorName',title:'作者',width :parseInt($(this).width()*0.2),halign:'center',align:'left'},
	       	    	//{field :'likenums',title:'点赞数',sortable:true,width :parseInt($(this).width()*0.10),halign:'center',align:'left'},
	       	    	//{field :'commentnums',title:'评论数',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'newsUnit',title:'新闻单位',width :parseInt($(this).width()*0.2),halign:'center',align:'center'},
	       	    	{field :'articledatetime',title:'发布时间',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
	       	    	//{field :'ishow',title:'是否显示',formatter : ishow ,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'momentsResources',title:'主图',formatter : MainImg ,width :parseInt($(this).width()*0.25),halign:'center',align:'center'}
	       	      ];
	
	$dgnewslist = $('#newslist').datagrid({
		url: $homebasepath+'/admin/news/getNewsHeadInfoList.shtml',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'articledatetime',
		sortOrder:'desc',
		columns:[columns],
		onDblClickRow:function(){
			var row = $('#newslist').datagrid('getSelected');
			$("#newId").val(row.id);
		}
	});
}

//加入新闻链接
/*function addNews(newsId){
	var id = $("#id").val();
	$.post($homebasepath+"/admin/news/momentsResourcesAction/addNews.shtml",{"id":id,"newsId":newsId},function(data){
		data = eval('('+data+')');
		if(data.resultDescription.newsId){
			$("#link").val(data.resultDescription.newsId);
			loadMyNews(data.resultDescription.newsId);
		}else{
			loadMyNews('');
		}
		$('#newsResourceslist').datagrid("reload");
	});
}*/

//当前链接指向
function loadMyNews(id){
	
	if(!id){
		id = '0';
	}
	
	var columns = [
	       	    	{field :'articletitle',title:'标题',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
	       	    	{field :'authorName',title:'作者',width :parseInt($(this).width()*0.2),halign:'center',align:'left'},
	       	    	//{field :'likenums',title:'点赞数',sortable:true,width :parseInt($(this).width()*0.10),halign:'center',align:'left'},
	       	    	//{field :'commentnums',title:'评论数',sortable:true,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'newsUnit',title:'新闻单位',width :parseInt($(this).width()*0.2),halign:'center',align:'center'},
	       	    	{field :'articledatetime',title:'发布时间',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
	       	    	//{field :'ishow',title:'是否显示',formatter : ishow ,width :parseInt($(this).width()*0.08),halign:'center',align:'center'},
	       	    	{field :'momentsResources',title:'主图',formatter : MainImg ,width :parseInt($(this).width()*0.25),halign:'center',align:'center'}
	       	      ];
	
	$dgnewslist = $('#myNewslist').datagrid({
		url: $homebasepath+'/admin/news/getNewsHeadInfoList.shtml',
		queryParams: {
			"entityQuery.id":id
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
		sortName:'articledatetime',
		sortOrder:'desc',
		columns:[columns],
		onDblClickRow:function(){
			//链接滞空
			$("#newId").val("");
		}
	});
	
}	
function MainImg(val,row){
	var url = (val==null?"":val.url);
	return "<img width='100' height='100' src='"+$homebasepath+"uploads/news/"+url+"'/>";
}
