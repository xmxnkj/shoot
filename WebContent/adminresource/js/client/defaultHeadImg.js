$(function(){
	$("#imgFile1").attr("src",$homebasepath+"uploads/client/1.jpg");
	initUpload1();
	$("#imgFile2").attr("src",$homebasepath+"uploads/client/2.jpg");
	initUpload2();
	$("#imgFile3").attr("src",$homebasepath+"uploads/client/3.jpg");
	initUpload3();
});


function initUpload1(){
	var bt_uploadacc = $('#btn_uploadres1');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/client/defaultImg.shtml",
		data:{"name":"1.jpg"},
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG)$/.test(ext))){		
				alert('对不起您选择文件系统限制上传!');
				return false;	
			}
			
			if(file.size>112000){
	          	alert("上传的图片不超过500k！");
	          	return false;
	        }
			
			$.messager.progress({ title:'请稍后', msg:'正在拼命上传中,不要急...' });
		},
		onComplete: function(file, response){//文件提交完成后可执行的方法
			//进度条隐藏 TODO
			$.messager.progress('close');
			//预览
			$("#imgFile1").attr("src",$homebasepath+"uploads/client/1.jpg");
		}
	});
}

function initUpload2(){
	var bt_uploadacc = $('#btn_uploadres2');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/client/defaultImg.shtml",
		data:{"name":"2.jpg"},
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG)$/.test(ext))){		
				alert('对不起您选择文件系统限制上传!');
				return false;	
			}
			if(file.size>112000){
	          	alert("上传的图片不超过500k！");
	          	return false;
	        }
			
			//使用EASYUI的进度条 TODO
			$.messager.progress({ title:'请稍后', msg:'正在拼命上传中,不要急...' });
			
		},
		onComplete: function(file, response){//文件提交完成后可执行的方法
			//进度条隐藏 TODO
			$.messager.progress('close');
			//预览
			$("#imgFile2").attr("src",$homebasepath+"uploads/client/2.jpg");
		}
	});
}

function initUpload3(){
	var bt_uploadacc = $('#btn_uploadres3');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/client/defaultImg.shtml",
		data:{"name":"3.jpg"},
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG)$/.test(ext))){		
				alert('对不起您选择文件系统限制上传!');
				return false;	
			}
			if(file.size>112000){
	          	alert("上传的图片不超过500k！");
	          	return false;
	        }
			//使用EASYUI的进度条 TODO
			$.messager.progress({ title:'请稍后', msg:'正在拼命上传中,不要急...' });
		},
		onComplete: function(file, response){//文件提交完成后可执行的方法
			//进度条隐藏 TODO
			$.messager.progress('close');
			//预览
			$("#imgFile3").attr("src",$homebasepath+"uploads/client/3.jpg");
		}
	});
}