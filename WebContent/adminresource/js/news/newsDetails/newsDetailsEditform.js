var editor;
$(function(){
	editor = CKEDITOR.replace('newsContent',{
		filebrowserBrowseUrl: '/browser/browse.php',
	    filebrowserUploadUrl: $homebasepath+"/admin/news/momentsResourcesAction/uploadDetailsImg.shtml"
	});
})

function formsubmit(){
	//获取编辑器文本
	var newsContent = CKEDITOR.instances.newsContent.getData();
	var id = $("#id").val();
	var newsId = $("#newsId").val();
	$.ajax({
		url:$homebasepath+"/admin/news/newsDetails/saveNewsDetails.shtml",
		type:'POST',
		data:{"id":id,"newsId":newsId,"newsContent":newsContent},
		dataType:'json', 
		cache:false,
        error: function(request) {
            alert("提交失败");
        },
		success:function(data){
			//关闭窗口
 			$.easyui.parent.$dig_newsDetails.dialog('close');
 			//刷新父页面
 			$.easyui.parent.$('#newsDetailslist').datagrid("reload");
		}
	});
}

function formLoadEditData(row){
	$('#newsDetailsEditform').form("jsonLoad",row);
	
	//初始化上传按钮
	if(row.id){
		initMainImgUpload(row.id);
		//加载图片
		loadImgById(row.id);
	}else{
		$.messager.alert('请先添加文本!');

	}
	
}

//初始化上传按钮
function initMainImgUpload(id){
	if(!id){
		alert("请先添加文本");
		return;
	}
	var bt_uploadacc = $('#newsdetailsUploadButton');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/news/momentsResourcesAction/uploadDetailsImg.shtml",
		data:{"id":id},	//新闻内容id
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
			if(!id){
				$.messager.alert('提示', '请先添加内容！');
				return false;
			}
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG)$/.test(ext)))
			{		
				alert('对不起您选择文件系统限制上传!');
				   return false;
			}
			else
			{
				//使用EASYUI的进度条 TODO
				$.messager.progress({ title:'请稍后', msg:'正在拼命上传中,不要急...' });
			}
		},
		onComplete: function(file, response){//文件提交完成后可执行的方法
			//进度条隐藏 TODO
			$.messager.progress('close');
			//预览
			var url = $homebasepath+"/uploads/news/"+response.resultDescription.urlpath+"";
			$("#detailsImgFile").attr("src",url);
		}
	});
}
//加载图片
function loadImgById(id){
		$.ajax({
		url:$homebasepath+"admin/news/newsDetails/getDetailsImg.shtml",
		type:'POST',
		data:{"id":id},
		dataType:'json', 
		success:function(data){
			if(data.data){
				var url = $homebasepath+"/uploads/news/"+data.data.url;
				$("#detailsImgFile").attr("src",url);
			}
		}
	})
}