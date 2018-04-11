/**
 * 
 */
var clientId;
$legaldoctype_combobox=null;
$(function(){
	$legaldoctype_combobox =  $("#docType").combobox({
    	id:'docType',
    	data: [
   		    {"id":"ClassicCase","text":"经典案例",selected:true}
   		],
        valueField: 'id', 
        textField: 'text',
        editable:false,
	});
	
	$classicCase_combobox =  $("#classicCase").combobox({
		id:'classicCase',
		data: [
		       {"id":"CivilMediationCase","text":"人民调解案例",selected:true},
		       {"id":"JudicialDecisionCase","text":"法院判决案例",selected:false}
       ],
       valueField: 'id', 
       textField: 'text',
       editable:false
	});
	
	/*$('#classicCase').combobox({
		onSelect: function(record){
			if(record == "JudicialDecisionCase"){
				document.getElementById("mediatorAgency").style.display ="none";
				document.getElementById("mediatorClient").style.display ="none";
			}
		}
	});*/

});
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


//加载调节机构
function getAgency(){
	$.post($homebasepath+"/admin/mediation/mediationagency/getMediationAgencyComboJson.shtml",function(data){
		data = eval('('+data+')');
		data[0]["selected"]=true;
		$("#mediatorAgency").combobox({
	    	id:'docType',
	    	data: data,
	        valueField: 'id', 
	        textField: 'text',
	        editable:false,
	        onChange:function(newValue,oldValue){
	        	var id = newValue;
	        	getAgencyClient(id);
	        }
		});
	});
}

//加载调解员
function getAgencyClient(agencyId){
	$.post($homebasepath+"/admin/client/getAgencyClient.shtml",{"agencyId":agencyId},function(data){
		data = eval('('+data+')');
		var j = [];
		$.each(data.data,function(index,obj){
			var i = [];
			i["id"] = obj.id;
			i["text"] = obj.identifyName;
			i["selected"]=(obj.id==clientId?true:false);
			j.push(i);
		});
		$("#mediatorClient").combobox({
	    	id:'docType',
	    	data: j,
	        valueField: 'id', 
	        textField: 'text',
	        editable:false,
	        onChange:function(newValue,oldValue){
	        	//复值给调解员ID
	        	$("#mediatorClientId").val(newValue);
	        }
		});

	});
}
/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){
		//调节机构
		getAgency();
		//显示图片
		$('#resource').attr("src",$homebasepath+"uploads/mediation/legaldoc/"+rowdata.image);
		
		
	   $('#editform').form("jsonLoad",rowdata);
	   $("#publishTime").datebox("setValue", rowdata.publishTime);
	   
		editor.html(rowdata.content);
		clientId = rowdata.mediatorClientId;

		$('#mediatorAgency').combobox('setValue',rowdata.mediatorClient.agencyName);
		$('#mediatorClient').combobox('setValue',rowdata.mediatorClient.identifyName);
}

function formLoadAddData(){
	var classicCase = $("#classicCase").combobox('getValue');
	if(classicCase == "JudicialDecisionCase"){
		document.getElementById("mediatorAgency").style.display ="none";
		document.getElementById("mediatorClient").style.display ="none";
	}else{
		//调节机构
		getAgency();
	}
	
}

/**
 * 表单提交
 */
function formsubmit(){
	 // $('#editform').submit();
	var id = $("#id").val();
	var docType = $("#docType").combobox('getValue');
	var classicCase = $("#classicCase").combobox('getValue');
	var title= $("#title").val(); 
	var publishUnit= $("#publishUnit").val(); 
	var publishTime= $("#publishTime").datetimebox('getValue');
	var mediatorClientId= "";
	if(classicCase == "JudicialDecisionCase"){
	}else{
		mediatorClientId = $("#mediatorClient").combobox("getValue");
	}
	var docDescription= $("#docDescription").val(); 
	var content= editor.html();
	var image = $("#image").val();
	submitParams = {
	   'entity.id':id,	
	   'entity.docType':docType,
	   'entity.classicCase':classicCase,
	   'entity.title':title,
	   'entity.publishUnit':publishUnit,
	   'entity.publishTime':publishTime,
	   'entity.docDescription':docDescription,
	   'entity.content':content,
	   'entity.commentCounts':0,
	   'entity.likeCounts':0,
	   'entity.mediatorClientId':mediatorClientId,
	   'entity.image':image
	   
	};
	//$.easyui.loading({ msg: "正在提交...", topMost: true });
	//try{
		var url = $homebasepath+"admin/mediation/legaldoc/saveJson.shtml";
		var resultJson  = $.util.requestAjaxJson(url,submitParams);
		if(resultJson.success){
			//关闭当前窗口
			$.easyui.parent.$dig_editLegalDoc.dialog('close');
        	$.easyui.parent.refreshDataClass();
		}else{
			$.easyui.messager.alert(resultJson.message);
			$.easyui.loaded(true);
		}
}

$(function(){
	$("#imgFile").change(function(){
		$("#img").html("");
		var length = this.files.length;
		for(var i=0;i<length;i++){
			var f = this.files[i];
			var list = f.name.split(".");
			var uploadPrefix = list[list.length-1];
			if(!(uploadPrefix.toLowerCase()=='png'||
			   uploadPrefix.toLowerCase()=='gif'||
			   uploadPrefix.toLowerCase()=='jpg'||
			   uploadPrefix.toLowerCase()=='bmp'||
			   uploadPrefix.toLowerCase()=='jpeg')){
				alert('图片格式必须为png,gif,jpg,bmp,jpeg');
				$(this).val("");	//清空
				return;
			}
			//临时地址
			var objUrl = getObjectURL(f);
			var img = new Image();
			//获取图片分辨率
			img.onload=function(){
				console.log("图片像素:宽:"+img.width+" 高:"+img.height);
			};
			img.src = objUrl;
        	if (objUrl) {
        		$("#resource").attr("src",objUrl);
        		//直接上传
        		saveData();
        	}
		}	
	});
});

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

//保存
function saveData(){
	 var files = $('#imgFile').prop('files');
	 var data = new FormData();
	 if(files.length>0){
		 data.append('imgFile', files[0]);
	 }else{
		 alert("请选择图片");
		 return;
	 }
	 var id = $("#id").val();
	 data.append('legaldocid',id);
	 
	 $.ajax({
	     url : $homebasepath+"admin/mediation/legaldoc/uploadImg.shtml",  
	     type : "POST",  
	     data : data,
	     cache: false,
	     processData: false,
	     contentType: false,
	     success : function(data) {
	    	//关闭当前窗口
	    	 data = eval('('+data+')');
	    	 $("#image").val(data.urlpath)
	     }
	});  
}