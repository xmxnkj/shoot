/**
 * 
 */
$agencytype_combobox=null;
$agencyclassfy_combobox=null;
$(function(){
	
	getStreet("");

	$.log($homebasepath);
	
	initUpload();
	
	initMap();
	
	$agencyclassfy_combobox =  $("#agencyClassify").combobox({
    	id:'agencyClassify',
    	data: [
   		    {"id":"0","text":"企事业单位调委会调解案件数",selected:false},
   		    {"id":"1","text":"社会团体和其他组织调委会调解案件数",selected:false},
   		    {"id":"2","text":"村调委会调解案件数",selected:false},
   		    {"id":"3","text":"居调委会调解案件数",selected:false},
   		    {"id":"4","text":"乡镇调委会调解案件数",selected:false},
   		    {"id":"5","text":"街道调委会调解案件数",selected:false},
   		],
        valueField: 'id', 
        textField: 'text',
        editable:false,
	});
	
	$agencytype_combobox =  $("#agencyType").combobox({
    	id:'agencyType',
    	data: [
   		    {"id":"CivilCaseType","text":"人民调解机构",selected:false},
   		    {"id":"AdministrationCaseType","text":"行政调解机构",selected:false},
   		    {"id":"JudicialCaseType","text":"司法调解机构",selected:false}
   		],
        valueField: 'id', 
        textField: 'text',
        editable:false,
        onSelect: function(rec){
        	getAgencyClassfy(rec);
        }
	});
	
	$('#serchByAddress').bind('click', function(){
		searchByStationName();
	});
	
});

function getStreet(belongsTo){
	$.ajax({
		url:$homebasepath+'/admin/mediation/street/getList.shtml',
		type:'POST',
		async: false,
		dataType:'json', 
		success:function(data){
			var tmp = "[";
			for(var i=0;i<data.length;i++){
				tmp+="{'id':'"+data[i].streetNname+"','text':'"+data[i].streetNname+"','selected':"+(data[i].streetNname==belongsTo?'true':'false')+"},";
			}
			tmp = tmp.substring(0,tmp.length-1)+"]";
			tmp = eval('('+tmp+')');
			$("#belongsTo").combobox({
		    	id:'belongsTo',
		    	data: tmp,
		        valueField: 'id', 
		        textField: 'text',
		        editable:false,
		        onSelect: function(rec){
		        	getAgencyClassfy(rec);
		        }
			});
		}
	});
}

function getAgencyClassfy(rec){
	
	/*if(rec.id=="CivilCaseType"){
		document.getElementById("agencyClassify").value="";
		$agencyclassfy_combobox =  $("#agencyClassify").combobox({
	    	id:'agencyClassify',
	    	data: [
	   		    {"id":"0","text":"企事业单位调委会调解案件数",selected:false},
	   		    {"id":"1","text":"社会团体和其他组织调委会调解案件数",selected:false},
	   		    {"id":"2","text":"村调委会调解案件数",selected:false},
	   		    {"id":"3","text":"居调委会调解案件数",selected:false},
	   		    {"id":"4","text":"乡镇调委会调解案件数",selected:false},
	   		    {"id":"5","text":"街道调委会调解案件数",selected:false},
	   		],
	        valueField: 'id', 
	        textField: 'text',
	        editable:false,
		});
	}else if(rec.id=="AdministrationCaseType"){
		document.getElementById("agencyClassify").value="行政调解";
	}else if(rec.id=="JudicialCaseType"){
		document.getElementById("agencyClassify").value="司法调解";
	}*/
}

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){

	   $('#editform').form("jsonLoad",rowdata);
	   $("#description").val(rowdata.description);
	   
	   $("#agencyClassify").combobox("setValue",rowdata.agencyClassify);
	   //加载街道
	   if(rowdata.belongsTo){
		   getStreet(rowdata.belongsTo);
	   }
	  
	   $("#resource").attr("src",$homebasepath+"uploads/mediation/"+rowdata.mediationResourceId);
	   
	   initMap(rowdata);
}

/**
 *上传附件
 */
function initUpload(){
	var bt_uploadacc = $('#btn_uploadres');
	new AjaxUpload(bt_uploadacc,{
		action: $homebasepath+"/admin/mediation/mediationagency/headImg.shtml",
		name: 'imgFile',
		type:"POST",//提交方式
		responseType:"json",
		onSubmit : function(file, ext){		
			uploadFileContentType = file;
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
			//预览
			if(response.resultDescription.state=='OK'){
				var url = $homebasepath+"uploads/mediation/"+response.resultDescription.urlpath+"";
				$("#resource").attr("src",url);
				$("#mediationResourceId").val(response.resultDescription.urlpath);
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
var map;//地图对象
var data_info = [];	//默认点
function initMap(row){
	var map = new BMap.Map("container");
   // map.centerAndZoom(new BMap.Point(118.03619,24.48665), 15);
	if(row && row.lonBaiDu && row.latBaiDu){
		map.centerAndZoom(new BMap.Point(row.lonBaiDu,row.latBaiDu), 15);
		//将点显示在地图上
		var arr = [];
		arr.push(row.lonBaiDu);
		arr.push(row.latBaiDu);
		arr.push(row.address);
		data_info.push(arr);
		for(var i=0;i<data_info.length;i++){
			var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
			var content = data_info[i][2];
			map.addOverlay(marker);               // 将标注添加到地图中
			addClickHandler(content,marker);
		}
		
	}else{
		map.centerAndZoom(new BMap.Point(118.03619,24.48665), 15);
	}
    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
    map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
    
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		document.getElementById("lonBaiDu").value = e.point.lng;
        document.getElementById("latBaiDu").value = e.point.lat;
        bd_decrypt(e.point.lng,e.point.lat);	//转换地图
        map.clearOverlays();//清空原来的标注
        var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));  // 创建标注，为要查询的地方对应的经纬度
        map.addOverlay(marker);
	});
}


//百度坐标转高德（传入经度、纬度）
function bd_decrypt(bd_lng, bd_lat) {
  var X_PI = Math.PI * 3000.0 / 180.0;
  var x = bd_lng - 0.0065;
  var y = bd_lat - 0.006;
  var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * X_PI);
  var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * X_PI);
  var gg_lng = z * Math.cos(theta);
  var gg_lat = z * Math.sin(theta);
  document.getElementById("lon").value = gg_lng;
  document.getElementById("lat").value = gg_lat;
}


function addClickHandler(content,marker){
	marker.addEventListener("click",function(e){
		openInfo(content,e)}
	);
}

function searchByStationName() {
	
	var map = new BMap.Map("container");
    map.centerAndZoom(new BMap.Point(118.03619,24.48665), 15);
    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
    map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));
	
    //单击获取点击的经纬度
	map.addEventListener("click",function(e){
		document.getElementById("lonBaiDu").value = e.point.lng;
        document.getElementById("latBaiDu").value = e.point.lat;
        map.clearOverlays();//清空原来的标注
        var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));  // 创建标注，为要查询的地方对应的经纬度
        map.addOverlay(marker);
	});
    
    var localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小
    
    map.clearOverlays();//清空原来的标注
    var keyword = document.getElementById("address").value;
    localSearch.setSearchCompleteCallback(function (searchResult) {
        var poi = searchResult.getPoi(0);
        document.getElementById("lonBaiDu").value = poi.point.lng;
        document.getElementById("latBaiDu").value = poi.point.lat;
        map.centerAndZoom(poi.point, 15);
        var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
        map.addOverlay(marker);
        var content = document.getElementById("address").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
        var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
        marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
        // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    });
    localSearch.search(keyword);
}

/**
 * 表单提交
 */
function formsubmit(){
	var id = $("#id").val();
	var managerClientId = $("#managerClientId").val();
	var mediationResourceId = $("#mediationResourceId").val();
	var agencyType = $("#agencyType").combobox('getValue');
	var agencyName = $("#agencyName").val();
	var tel = $("#tel").val();
	var belongsTo = $("#belongsTo").combobox('getValue');
	var agencyClassify = $("#agencyClassify").combobox('getValue');
	var address = $("#address").val();
	var lonBaiDu = $("#lonBaiDu").val();	//百度经纬度
	var latBaiDu = $("#latBaiDu").val();	
	var lon = $("#lon").val();				//高德经纬度
	var lat = $("#lat").val();
	var description = $("#description").val();
	
	if(!agencyType){
		alert("机构类型必选！");
		return;
	}
	if(!agencyName){
		alert("机构名称必填！");
		return;
	}
	if(!tel){
		alert("电话必填！");
		return;
	}
	if(!belongsTo){
		alert("所属街道必填！");
		return;
	}
	if(!agencyClassify){
		alert("机构主体分类必选！");
		return;
	}
	if(!address){
		alert("地址必填！");
		return;
	}
	if(!lon || !lat){
		alert("请在地图上选择地址！");
		return;
	}
	
	if(!description){
		alert("机构简介必填！");
		return;
	}
	$.ajax({
		url:$homebasepath+'/admin/mediation/mediationagency/getEditMediationAgency.shtml',
		type:'POST',
		async: false,
		dataType:'json', 
		data:{
			"id":id,
			"managerClientId":managerClientId,
			"mediationResourceId":mediationResourceId,
			"agencyType":agencyType,
			"agencyName":agencyName,
			"tel":tel,
			"belongsTo":belongsTo,
			"agencyClassify":agencyClassify,
			"address":address,
			"lon":lon,
			"lat":lat,
			"lonBaiDu":lonBaiDu,
			"latBaiDu":latBaiDu,
			"description":description
		},
		success:function(data){
			if(data.status=='success'){
				alert("修改成功");
			}else{
				alert("修改失败");
			}
			$.easyui.parent.$dig_editMediationAgency.dialog('close');
        	$.easyui.parent.refreshData();
		}
	});
}

function genderFormat(rowdata){  
    if(rowdata.gender==true) 
       return "男";
    else if(rowdata.gender==false) 
       return "女";
}
function clientTypeFormat(val,row){  
    if(val=="Normal") 
       return "普通会员";
    else if(val=="Trainer") 
       return "私教";
}