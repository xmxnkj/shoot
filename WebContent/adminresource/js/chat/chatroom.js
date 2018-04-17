/**
 * 聊天室主界面
 */
 $dgchatuser=null;
 var id = [];	//记录索引
 function clientImageFormat(value,row,index){
	 id[row.clientId] = index;
		var val = value;
		if(!value)
			val = "1.jpg";
		if(row && row.readflag){
			if(val.indexOf("http")==-1){
				return '<img height="35px" width="35px" src="../../uploads/client/'+val+'" />';
			}else{
				return '<img height="35px" width="35px" src="'+val+'" />';
			}
		}else{	
			if(val.indexOf("http")==-1){
				return '<img height="35px" width="35px" src="../../uploads/client/'+val+'" />';
			}else{
				return '<img height="35px" width="35px" src="'+val+'" />';
			}
		}
	}
 
 $(function() {

 	 var columns = [
 	    {field :'clientImage',title:'用户头像',sortable:false,formatter:clientImageFormat,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
        {field :'clientName',title:'聊天用户',sortable:false,width :parseInt($(this).width()*0.12),halign:'center',align:'center'},
        {field :'unread',title:'未读信息数',sortable:false,width :parseInt($(this).width()*0.08),halign:'center',align:'center'}
     ];	
 	 
 	
 	$dgchatuser = $('#dgchatclient').datagrid({
		url: $homebasepath+'/admin/chat/consult/getChatUser.shtml',
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'consultTime',
		sortOrder:'desc',
		columns:[columns],
		onClickRow:function(index,row){
			$("#chatuserid").val(row.clientId);
			if($("#WD"+row.id)){
				$("#WD"+row.id).hide();
			}
			 readMessage();
			 $("#messageTable").html(""); 
		},
		onLoadSuccess:function(){
			getUnReadMsg();
		}
	});
 	
 	
 	
 	$('#sendface').bind('click', function(){
		showFace();
	});
 	 
	
	//加载
	loadDgData();
	
	$('#sendmessage').bind('click', function(){
		var url = $homebasepath+'admin/chat/chatmessage/SendMessage.shtml';
		var recclientid=$("#chatuserid").val();
		var content=$("#messagecontent").val();
		var submitParams = {
		  'recclientid':recclientid,
		  'content':content
	    };
	    var resultJson  = $.util.requestAjaxJson(url,submitParams);
	    //$.ajaxResult(resultJson);
	    if(resultJson.success){
	      $("#messagecontent").val("");
	    }else{
	       $.messager.show("操作提醒",resultJson.message, "error", "center");
	    }
	});
	

	$('#messageTable').everyTime('30s','U',function(){
	   loadDgData();
	},0,true);	
	
	$('#messageTable').everyTime('10s','C',function(){
	   readMessage();
	},0,true);


	
 });
 
//加载未读取的信息数量
function getUnReadMsg(){
	$.ajax({
		url:$homebasepath+'admin/chat/getUnReadMsgCounts.shtml',
		type:'POST',
		dataType:'json', 
		success:function(data){
			data = data.data;
			
			var row = $('#dgchatclient').datagrid("getRows");
			
			for(var i = 0;i<data.length;i++){
				var ind = id[data[i].SEND_CLIENT_ID];	//索引
				row[ind].unread = data[i].total;
				$('#dgchatclient').datagrid('refreshRow', ind);
			}
			
		}
	});
}
 
function loadDgData() {
	/*var options = $dgchatuser.datagrid('options');
	options.url = $homebasepath+'admin/chat/getChatClientList.shtml',*/
	//触发绑定
	$("#dgchatclient").datagrid('reload');
} 
 
 
/**
 * 读取消息
 */ 
function readMessage(){
	
	    var chatclientid =  $("#chatuserid").val();
	    var urlmessage = $homebasepath+'admin/chat/getAdminChatMessageList.shtml';
	    var params = {
	    		'sendclientid':chatclientid,
	    		'recclientid':'1'
	    };

	    $.ajax({  
	    	url: urlmessage,  
	    	cache: false,  
	    	data:params,
	    	async:true,
	    	global:false,
	    	type:'post',
	    	dataType: "json",
	    	success: function(resultJson){  
	    		if(resultJson.success){
	    			var str = "";
	    			$.each(resultJson.data,function(index,data) {
	    				if(data.sendClientId=="1"){//表示是自己发送
	    					str += "<table width='100%' style='margin-top:10px;'><tr><td height='30px' align='right' style='padding-right:20px'>"
	    						+"<div class='send'><div class='arrow1'></div>"+initWXR(data.content)+"</div></td><td width='40px' align='left'><img src='../../uploads/client/"+ data.sendClientImage +"' width='20px' height='20px' border='0'/></td></tr><table>";
	    					
	    				}else{//表示用户咨询
	    					if(data.contentType=="Image"){
	    						str += "<table  width='100%' style='margin-top:10px;'><tr>" +
	    								"<td height='30px' width='40px' align='right'>";
	    								if(data.sendClientImage.indexOf("http")==-1){
	    									str += "<img src='../../uploads/client/"+ data.sendClientImage +"' width='20px' height='20px' border='0'/>";
	    								}else{
	    									str += "<img src='"+ data.sendClientImage +"' width='20px' height='20px' border='0'/>";
	    								}
	    								str += "</td>" +
	    								"<td height='30px' width='40px' align='right'><img src='../../uploads/chat/image/"+data.content+"' width='20px' height='20px' border='0'/></td></tr>";
	    					}else if(data.contentType=="Text"){
	    						var text = initWXR(data.content);

	    						str += "<table  width='100%' style='margin-top:10px;'><tr><td height='30px' width='40px' align='right'>";
			    						if(!data.sendClientImage){
											str += "<img src='../../uploads/client/1.jpg' width='20px' height='20px' border='0'/>";
										}else if(data.sendClientImage.indexOf("http")==-1){
	    									str+="<img src='../../uploads/client/"+ data.sendClientImage +"' width='20px' height='20px' border='0'/>";
	    								}else{
	    									str+="<img src='"+ data.sendClientImage +"' width='20px' height='20px' border='0'/>";
	    								}
	    								str+="</td><td align='left' style='padding-left:20px'>"
	    						+"<div class='send'><div class='arrow'></div>"+text+"</div></td></tr>";
	    					}else if(data.contentType=="Voice"){
	    						var text = "<audio src='"+$homebasepath+"/uploads/chat/voice/"+data.content+"' controls='controls'></audio>";
	    						
	    						str += "<table  width='100%' style='margin-top:10px;'><tr><td height='30px' width='40px' align='right'>";
	    						if(data.sendClientImage.indexOf("http")==-1){
									str+="<img src='../../uploads/client/"+ data.sendClientImage +"' width='20px' height='20px' border='0'/>";
								}else{
									str+="<img src='"+ data.sendClientImage +"' width='20px' height='20px' border='0'/>";
								}		
	    						str += "</td><td align='left' style='padding-left:20px'>"
	    						+"<div class='send'><div class='arrow'></div>"+text+"</div></td></tr>";
	    					}
	    				}
	    			});
	    			$("#messageTable").html(str); 
	    			//$("#messageTable").after(str); 
	    			//$('#messageTable').html().appendTo(str);
	    			$('#messageTable').scrollTop($('#messageTable')[0].scrollHeight);
	    			//$('#chatAudio')[0].play(); //播放声音 
	    			//$.log($('#messageTable')[0].scrollHeight);
	    		}
	    	}  
	    });
}

//对外星人表情进行处理
function initWXR(str){
	
	var a = new Array();
	
	if(str.indexOf("外星人")==-1){
		return str;
	}else{
		//分割
		var arr = str.split("[");
		
		for(var i=0;i<arr.length;i++){
			if(arr[i].indexOf("外星人")==-1){
				continue;
			}else{
				var tmp = arr[i].split("]")[0];
				if(tmp.indexOf("外星人")!=-1){
					a[tmp] = "["+tmp+"]";
				}
			}
		}
		//替换
		for(var key in a){
			if(key.indexOf("外星人")==-1){
				break;
			}
			var b = str.split(a[key]);
			var t = "";
			for(var i = 0;i<b.length;i++){
				t+=b[i]+"<img width='30' height='30' src='"+$homebasepath+"static/"+a[key]+".png'/>";
			}
			str = t;
		}
		
		return str;
	}
	return str;
}