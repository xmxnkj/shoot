/**
 * 
 */
$legaldoctype_combobox=null;
var editor;
KindEditor.ready(function(K) {
	editor = K.create('#content', {
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
	
	
	$.log($homebasepath);
	
	/*//初始化编辑器
	editor = CKEDITOR.replace('content',{
	    filebrowserUploadUrl: $homebasepath+"/admin/news/momentsResourcesAction/uploadDetailsImg.shtml"
	});*/
	
	$legaldoctype_combobox =  $("#docType").combobox({
    	id:'docType',
    	data: [
   		    {"id":"Policy","text":"政策",selected:true},
   		    {"id":"Legal","text":"法规",selected:false},
   		    {"id":"OfficeCopy","text":"文书",selected:false}
   		],
        valueField: 'id', 
        textField: 'text',
        editable:false,
	});
	
});

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){
	$('#editform').form("jsonLoad",rowdata);
	editor.html(rowdata.content);
	$("#publishTime").datetimebox('setValue',rowdata.publishTime);
	//判断是否有附件
	if(rowdata.filePath){
		$("#hasFile").html("<a href='javascript:showFile("+'"'+rowdata.filePath+'"'+")'>查看附件</a>");
	}else{
		$("#hasFile").html("改文案还没有附件");
	}
}

function initForm(){
	
	$.easyuiSubmitForm({
		 formid:'editform',
		 url: $homebasepath+"admin/mediation/legaldoc/saveJson.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			//$.easyui.parent.$dig_editLegalDoc.dialog('close');
        	$.easyui.parent.refreshData();
		 },
		 faicallbackfun:function(result,message){
			 $.easyui.alert(message);
		 }
	});
}

function formsubmit(){
	//$.easyui.loading({ msg: "正在提交...", topMost: true });
    //取得表单的值
	var id = $("#id").val();
	var docType = $("#docType").combobox('getValue');
	var title= $("#title").val(); 
	var publishUnit= $("#publishUnit").val(); 
	var publishTime= $("#publishTime").datetimebox('getValue'); 
	var orderdisplay = $("#orderdisplay").val();
	var reg = /^[0-9]+.?[0-9]*$/;
	if (!reg.test(orderdisplay)) {
		alert("请输入数字！");
		return;
	}
	var content= editor.html(); 
	submitParams = {
	   'id':id,	
	   'docType':docType,
	   'title':title,
	   'publishUnit':publishUnit,
	   'publishTime':publishTime,
	   'content':content,
	   'orderdisplay':orderdisplay
	};
	$.easyui.loading({ msg: "正在提交...", topMost: true });
	//try{
		var url = $homebasepath+"admin/mediation/legaldoc/saveLegalDoc.shtml";
		var resultJson  = $.util.requestAjaxJson(url,submitParams);
		if(resultJson.success){
			//关闭当前窗口
			$.easyui.parent.$dig_editLegalDoc.dialog('close');
			$.easyui.parent.refreshData();
			$.easyui.loaded(true);
		}else{
			$.easyui.messager.alert(resultJson.message);
			$.easyui.loaded(true);
		}
	/*}catch(e){
		$.log(e);
		$.easyui.loaded(true);
		$.easyui.messager.alert("提交请求异常错误!");
	}*/
	
}

/**
 * 表单提交
 */
/*function formsubmit(){
	
	  $('#editform').submit();
}*/

$(function(){
	$("#imgFile").change(function(){
		var f = this.files[0];
		var list = f.name.split(".");
		var uploadPrefix = list[list.length-1];
		if(!(uploadPrefix.toLowerCase()=='doc'||
		   uploadPrefix.toLowerCase()=='dos'||
		   uploadPrefix.toLowerCase()=='docx')){
			alert('图片格式必须为word');
			$(this).val("");	//清空
			return;
		}
	});
});

//本地预览
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
	return url;
}

function upload(){
	 var files = $('#imgFile').prop('files');
	 var data = new FormData();
	 if(files.length==0){
		 alert('请选择上传文件');
		 return;
	 }
	 
	 var legaldocid = $("#id").val();
	 data.append('legaldocid',legaldocid);//文案id
	 data.append('imgFile', files[0]);//加入文件
	 
	 $.ajax({  
	     url : $homebasepath+"admin/mediation/legaldoc/UploadDoc.shtml",  
	     type : "POST",  
	     data : data,
	     cache: false,
	     processData: false,
	     contentType: false,
	     success : function(data) {
	    	data = eval('('+data+')'); 
	    	if(data.state=='OK'){
	    		alert("附件上传成功");
	    		$("#hasFile").html("<a href='javascript:showFile("+'"'+data.urlpath+'"'+")'>查看附件</a>");
	    	}else{
	    		alert("附件上传失败");
	    		$("#hasFile").html("改文案还没有附件");
	    	}
	     }
	});
}

//下载附件
function showFile(filename){
	//window.location.href=$homebasepath+"uploads/mediation/legaldoc/"+filename;
	var legaldocid = $("#id").val();
	window.open($homebasepath+"/admin/mediation/legaldoc/DownLoadDoc.shtml?legaldocid="+legaldocid);
}