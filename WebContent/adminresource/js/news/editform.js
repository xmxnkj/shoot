

var $dig_newsDetails;//  新增/修改新闻内容窗口
var editor;	//编辑器对象
/**
 * 
 */
KindEditor.ready(function(K) {
	editor = K.create('#newsContent', {
		allowImageRemote : false, //上传图片框网络图片的功能，false为隐藏，默认为true
		allowImageUpload: true,
	    height:'600px',
	    allowFileManager:true,
	    filterMode: false,
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
			this.sync();  
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
});

function formsubmit(){
	var reg = /^[0-9]+.?[0-9]*$/;
	var orderdisplay = $("#orderdisplay").val();
	if (!reg.test(orderdisplay)) {
		alert("请输入数字！");
		return;
	}
	  $('#editform').submit();
}

//初始化表单
function initForm(){
	
	$.easyuiSubmitForm({
		 formid:'editform',
		 url: $homebasepath+"admin/news/saveNewsHeadInfo.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			$.easyui.parent.$dig_newsHeadInfo.dialog('close');
        	$.easyui.parent.refreshData();
		 },
		 faicallbackfun:function(result,message){

		 }
	});
}

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){
	   $('#editform').form("jsonLoad",rowdata);
	   $("#articledatetime").datebox("setValue", rowdata.articledatetime);
	   editor.html(rowdata.newsContent);
	   initMainImgUpload(rowdata);
		//初始化编辑器
	   //initNewsDetails(rowdata);
}

//加载主图
function formLoadMainImg(rowdata){
	$.ajax({
		url:$homebasepath+'admin/news/getMainImg.shtml',
		type:'POST',
		data:{"id":rowdata.id},
		dataType:'json', 
		success:function(data){
			if(data.message=='OK'){
				var url = $homebasepath+"uploads/news/"+data.data.url;
				$("#imgFile").attr("src",url);
			}
		}
	});
}

//初始化上传按钮
function initMainImgUpload(rowdata){
	var bt_uploadacc = $('#btn_uploadres');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/news/momentsResourcesAction/uploadMainImg.shtml",
		data:{"id":rowdata.id},
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG)$/.test(ext))){		
				alert('对不起您选择文件系统限制上传!');
				   return false;
			}
			var img = $("[name='imgFile']")[2].files[0];
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
			//预览
			if(response.resultDescription.state=='OK'){
				var url = $homebasepath+"/uploads/news/"+response.resultDescription.urlpath+"";
				$("#imgFile").attr("src",url);
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

//加载内容
var $dgnewsCommentslist;
function initNewsComments(row){
	var columns = [
	             
		       	    {field :'client',title:'评论者',formatter:clientFormat,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
		       	    {field :'commentcontent',title:'评论内容',formatter:contentFormat,width:parseInt($(this).width()*0.15),halign:'center',align:'center'},
		       	    {field :'commentdatetime',title:'评论时间',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'ishow',title:'是否已读',formatter:isShow,width :parseInt($(this).width()*0.1),halign:'center',align:'center'}
		       	  ];
	
	$dgnewsDetailslist = $('#newsCommentslist').datagrid({
		url: $homebasepath+'admin/news/newsComments/getComments.shtml',
		queryParams: {
			"newsId":row.id
		},
		toolbar:'#newsCommentslist_toolbar',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		sortName:'commentdatetime',
		sortOrder:'dasc',
		pageSize:$.pagesize(true),
		fitColumns:true,
		columns:[columns],
		//onDblClickRow:showDetail
	});
	
	$("#btnRemoveComment").bind("click",function(){
		rwmcomment();
	});

}

function contentFormat(val,rowdata){  
	return val.length>10?val.substring(0,10)+"...":val;
}

function clientFormat(val,row){
	if(val){
		return val.nickName;
	}
	return "";
}

function isShow(val,row){
	if(val=='YES'){
		return "已读";
	}else{
		return "<a href='javascript:toShowComment("+'"'+row.id+'"'+")'>未读</a>";
	}
	
}
//改变审核状态
function toShowComment(id){
	var newsId = $("#id").val();
	$.post($homebasepath+'admin/news/newsComments/qualified.shtml',{"id":id,"newsId":newsId},function(data){
		$dgnewsDetailslist.datagrid("reload");
	});
}

function rwmcomment(id){
	var row = $('#newsCommentslist').datagrid('getSelected');
	if(row != null){
		$.post($homebasepath+"/admin/news/newsComments/rwmcomment.shtml",{"entity.id":row.id},function(data){
			$('#newsCommentslist').datagrid("reload");
		});
	}else{
		$.messager.alert('提示', '请选择一行进行删除！');
	}
	
}

//多图
$(function(){
	$("#imgs").change(function(){
		var list = $(this)[0].files;
		var formData = new FormData();
		for(var i = 0;i < list.length; i++){
			formData.append("img["+i+"]",list[i]);
			formData.append("imgFileName["+i+"]",list[i].name);	//文件名
		}
		 $.ajax({  
	         url:	$homebasepath+"/admin/news/momentsResourcesAction/uploadImgs.shtml",  
	         type: 'POST',  
	         data: formData,  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function (returndata) {
	          console.log(returndata);  
	         },  
	         error: function (returndata) {  
	           alert("error");
	             console.log(returndata);  
	         }  
	    });
		
	});
});