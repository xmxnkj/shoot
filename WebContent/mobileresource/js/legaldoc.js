/**
 * 文章详情JS
 */
$(function(){
var $error=document.getElementById("error").value;
  if($error!=null&&$error!=''){
 	 $.alert($error);
 	 return;
   }
  loadData();
});
 
 
 function loadData(){
	 var legaldocid=document.getElementById("legaldocid").value;
	 var $homeBasePath=document.getElementById("homeBasePath").value;
	//var inboparams="{legaldocid:'"+$legaldocid+"'}"; 	
	var inboparams="{legaldocid:'"+legaldocid+"'}"; 	
 	
 	$.apiAjaxRequest({
 	   ajaxurl:$homeBasePath+'app/mediation/apic.shtml',
 	   loadingtext:'加载中...',
 	   code:"wapApiLegalDocController_getLegalDocDetailById",
 	   inbo:inboparams,
 	   callbackfun:function(outbo){
 	   	 $.log(outbo);
 	   	 //通过接口返回值进行模板动态绑定 具体参数看API接口文档
		  var titletemplate = $.templates("#infoTmpl");//初始化模板
		  //var contenttemplate = $.templates("#contentTmpl");//初始化模板
		  var htmlOutput01 = titletemplate.render(outbo);//解析data数据
		  //var htmlOutput02 = contenttemplate.render(outbo.list);//解析data数据
		  $("#title").html(htmlOutput01);//定位到模板处
		  //$("#content").html(htmlOutput02);//定位到模板处
 	   }
 	});
 }