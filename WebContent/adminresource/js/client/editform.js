/**
 * 
 */
$(function(){
	$.log($homebasepath);	
	initUpload();
});

/**
 * 表单加载编辑数据
 * @param row
 */

var clientStatus;
function formLoadEditData(rowdata){
	   $('#editform').form("jsonLoad",rowdata);
	   $('#clientType').val(clientTypeFormat(rowdata));
	   $('#clientState').val(clientState(rowdata));
	   $("#skill").html(rowdata.skill);
	   $("#description").html(rowdata.description);
	   $("#imgFile").attr("src",$homebasepath+"uploads/client/"+rowdata.headImgFile);
	   $("#showDisplay").html(rowdata.showDisplay);
	   var temp = "";
	   
	   switch(rowdata.mediatorType){
	   	case 1:
	   		temp = "<option value='1' selected >行政</option><option value='2'>司法</option><option value='3'>人民</option>";
	   		break;
	   	case 2:
	   		temp = "<option value='1'  >行政</option><option value='2' selected>司法</option><option value='3'>人民</option>";
	   		break;
	   	case 3:
	   		temp = "<option value='1'  >行政</option><option value='2'>司法</option><option value='3' selected>人民</option>";
	   		break;
	   	default:
	   		temp = "<option value='1'>行政</option><option value='2'>司法</option><option value='3'>人民</option>";
	   		break;
	   }
	   $("#mediatorType").html(temp);
}


function clientTypeFormat(rowdata){  
	if(rowdata.clientType=="Normal"){ 
	       return "普通会员";
	}else if(rowdata.clientType=="Visitor"){
       return "游客";
	}else if(rowdata.clientType=="Mediator"){
    	 return "调解员";
    }
       
}

function clientState(rowdata){
	if(rowdata.clientState=="MediationCenter") 
		return "调解中心管理员";
	else if(rowdata.clientState=="MediationAgency") 
		return "调解机构管理员";
	else if(rowdata.clientState=="Mediator") 
		return "普通调解员";
	else if(rowdata.clientState=="Certificated")
		return "实名认证";
	else if(rowdata.clientState=="Normal")
		return "已注册未认证";
	else if(rowdata.clientState=="Visitor")
		return "游客";
}

function formsubmit(){
	var skill  =  $("#skill").val();
	var id  =  $("#id").val();
	var headImgFile  =  $("#headImgFile").val();
	var description = $("#description").val();
	
	var nickName = $("#nickName").val();
	var account = $("#account").val();
	var tel = $("#tel").val();
	var identifyName = $("#identifyName").val();
	var identify = $("#identify").val();
	account = account.replace(/\s+/g,"");
	tel = tel.replace(/\s+/g,"");
	var mediatorType = $("#mediatorType").val();
	var showDisplay = $("#showDisplay").val();
	$.ajax({
		url:$homebasepath+'/admin/client/editAgencyClientSkill.shtml',
		data:{
				"skill":skill,
				"id":id,
				"headImgFile":headImgFile,
				"description":description,
				"nickName":nickName,
				"account":account,
				"identifyName":identifyName,
				"identify":identify,
				"tel":tel,
				"mediatorType":mediatorType,
				"showDisplay":showDisplay,
			},
		type:'POST',
		async: false,
		dataType:'json', 
		success:function(data){
			$.easyui.parent.$dig_editClient.dialog('close');
			$.easyui.parent.refreshData();
		}
	});
}

/**
 *上传附件
 */
function initUpload(){
	var bt_uploadacc = $('#btn_uploadres');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/client/headImg.shtml",
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
			if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG)$/.test(ext))){		
				alert('对不起您选择文件系统限制上传!');
				return false;	
			}
			var img = $("[name='imgFile']")[1].files[0];
			 //读取图片数据
          var image = new Image();
          image.src = getObjectURL(img);
          image.onload=function(){
              var width = image.width;
              var height = image.height;
              if(width<100 || height<100){
              	alert("当前图片尺寸为:"+width+"*"+height+",建议图片的宽大于100*100");
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
				var url = $homebasepath+"uploads/client/"+response.resultDescription.urlpath+"";
				$("#imgFile").attr("src",url);
				$("#headImgFile").val(response.resultDescription.urlpath);
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