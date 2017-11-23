

var $dig_newsDetails;//  新增/修改新闻内容窗口
var editor;	//编辑器对象
var status = 0;	

KindEditor.ready(function(K) {
	editor = K.create('#newsContent', {
		allowImageRemote : false, //上传图片框网络图片的功能，false为隐藏，默认为true
		allowImageUpload: true,
	    height:'600px',
	    //fileManagerJson:$homebasepath+"/content/scripts/kindEditor/jsp/file_manager_json.jsp",
	    cssData: ".ke-content img {max-width: 500px;height:auto;}", 
	    items : [
	     		'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
	     		'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
	     		'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
	     		'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
	     		'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
	     		'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
	     		'table', 'hr', 'emoticons', 'baidumap', 'pagebreak','anchor', 'link', 'unlink', '|', 'about'
	     	],
		uploadJson :  $homebasepath+"/admin/news/momentsResourcesAction/uploadDetailsImg.shtml",//上传路径
		afterUpload : function(url) {
		},
		afterCreate: function () {  //要取值设置这里 这个函数就是同步KindEditor的值到textarea文本框  
	         this.sync();  
	    },  
	    afterBlur: function () {  //同时设置这里   
	         this.sync();  
	    },
	    afterChange:function(){
	    	 this.sync();
	    }
	});
});


$(function(){
	initForm();
	//编辑器
	/*editor = CKEDITOR.replace('newsContent',{
		toolbar: "Full",
		height:	400,
	    filebrowserUploadUrl: $homebasepath+"/admin/news/momentsResourcesAction/uploadDetailsImg.shtml"
	});*/
	
	//kind 编辑器
	$("#newsform_tabs").tabs({
		border:true,
		width:'auto',
		height:'auto',
		fit:true,
		onSelect: function(title,index){
			var newsid = $("#id").val();
			if(index>0){
			    //验证一下form
				var isValid = $("#addform").form('validate');
				$.log('验证:'+isValid);
				if(!isValid||newsid==""){
					$("#newsform_tabs").tabs('select',0);	
					$.messager.alert('提示','请先保存基本资料后才可进行操作','info');
				}
				//验证是否保存了
				
			}
			if(index==1){
				initMainImgUpload(newsid);
			}
	    }
	});
});

function formsubmit(){
	$.ajax({
        type: "POST",
        url: $homebasepath+"admin/news/saveNewsHeadInfo.shtml",
        data:{
        	"entity.articletitle":$("#articletitle").val(),
        	"entity.newsUnit":$("#newsUnit").val(),
        	"entity.authorName":$("#authorName").val(),
        	"entity.simpleDesc":$("#simpleDesc").val(),
        	"entity.newsContent":editor.html(),
        },
        async: false,
        error: function(request) {
            alert("提交失败");
        },
        success: function(data) {
        	$.easyui.parent.$dig_newsHeadInfoAdd.dialog('close');
           	$.easyui.parent.refreshData();
        }
    });
}

//初始化表单
function initForm(){
	/*
	$.easyuiSubmitForm({
		 formid:'addform',
		 url: $homebasepath+"admin/news/saveNewsHeadInfo.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			$('#id').val(resultdata.id);
        	$.easyui.parent.refreshData();
		 },
		 faicallbackfun:function(result,message){

		 }
	});*/
}


//初始化上传按钮
function initMainImgUpload(newsid){
	var bt_uploadacc = $('#btn_uploadres');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/news/momentsResourcesAction/uploadMainImg.shtml",
		data:{"id":newsid},
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
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
			$("#imgFile").attr("src",url);
			
		}
	});
}

