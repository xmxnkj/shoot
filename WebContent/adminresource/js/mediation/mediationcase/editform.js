/**
 * 
 */
$(function(){
	init_mediationform_tabs();
});

/**
 * 表单加载编辑数据
 * @param row
 */

var caseState; 
function formLoadEditData(rowdata){
		//$('#gender').val(genderFormat(rowdata));
	   //var url = $homebasepath+'admin/mall/goodsinfo/loadJsonById.shtml';
	   //var url = '../platmanager/drawcashrecord/loadJsonById.html';
	   //var jsonData  = $.util.requestAjaxJson(url,{'id':rowdata.id});
	   $('#editform').form("jsonLoad",rowdata);
	   var temp = "";
	   for(var i=0;i<rowdata.complainant.length;i++){
		   temp+=rowdata.complainant[i]+",";
	   }
	   $("#complainant").val(temp.substring(0,temp.length-1));
	   
	   switch(rowdata.caseState){
  		case "Init":
  			$("#caseState").val("申请中");
  			break;
  		case "Allocation":
  			$("#caseState").val("分配中");
  			break;
  		case "Mediating":
  			$("#caseState").val("调解中");
  			break;
  		case "WaitSign":
  			$("#caseState").val("待签署");
  			break;
  		case "Closed":
  			$("#caseState").val("已调解");
  			break;
  		case "Refused":
  			$("#caseState").val("已拒绝");
  			break;
  		case "WaitComplete":
  			$("#caseState").val("待结案");
  			break;
  		case "Completed":
  			$("#caseState").val("已结案");
  			break;
  		case "GiveUp":
  			$("#caseState").val("用户放弃调解");
  			break;
  }
	   
  caseState = rowdata.caseState;
}

/**
 * 初始化选项卡
 */
function init_mediationform_tabs(){
	$("#editform_tabs").tabs({
		border:true,
		width:'auto',
		height:'auto',
		fit:true,
		onSelect: function(title,index){
			var caseid = $("#id").val();
			if(index==1){
				getMediationRecordList(caseid);//获取调节记录
			}
			if(index==2){
				getMediationResourceList(caseid);//获取证据列表
			}
			if(index==3){
				getMediationProtocol(caseid);//获取协议书
			}
			if(index==4){
				getChatRecord(caseid);//获取聊天记录
			}
	    }
	});	
}
//获取调节记录
function getMediationRecordList(caseid){
	
	var columns = [
		       	    {field :'involvedPerson',title:'当事人',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'joinPerson',title:'参与者',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'recordContent',title:'笔录内容',sortable:true,width :parseInt($(this).width()*0.4),halign:'center',align:'left'},
		       	    {field :'address',title:'发生地',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
		       	    {field :'recordTime',title:'记录时间',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
		       	    ];
	
	$dgmediationrecordlist = $('#dgmediationrecordlist').datagrid({
		url: $homebasepath+'/admin/mediation/mediationrecord/getRecords.shtml',
		//toolbar:'#mediationcaselist_toolbar',
		queryParams:{
				"caseId":caseid,
				"recordType":"AskRecord"
					},
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'recordTime',
		sortOrder:'desc',
		columns:[columns],
		//onDblClickRow:editMediationCase
	});
	
}

function getMediationResourceList(caseid){
	/*var columns = [
		       	    {field :'resType',title:'资源类型',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    //{field :'resseq',title:'资源排序',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'resuploadfileid',title:'文件id',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'left'},
		       	    {field :'resuploadfilepath',title:'文件路径',sortable:true,width :parseInt($(this).width()*0.3),halign:'center',align:'center'},
		       	    {field :'rescreatedatatime',title:'时间',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'center'},
		       	    ];*/
	var columns = [
		       	    {field :'involvedPerson',title:'当事人',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'joinPerson',title:'参与者',sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'recordContent',title:'笔录内容',sortable:true,width :parseInt($(this).width()*0.4),halign:'center',align:'left'},
		       	    {field :'address',title:'发生地',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
		       	    {field :'recordTime',title:'记录时间',sortable:true,width :parseInt($(this).width()*0.15),halign:'center',align:'center'},
		       	    ];
	
	$dgmediationresourcelist = $('#dgmediationresourcelist').datagrid({
		url: $homebasepath+'/admin/mediation/mediationrecord/getRecords.shtml',
		//toolbar:'#mediationcaselist_toolbar',
		queryParams:{
			"caseId":caseid,
			"recordType":"SurveyRecord"
				},
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'rescreatedatatime',
		sortOrder:'desc',
		columns:[columns],
		//onDblClickRow:editMediationCase
	});
}

function getMediationProtocol(caseid){
	//var url = $homebasepath+'admin/mediation/mediationprotocol/loadEntityJson.shtml';
	var url = $homebasepath+'admin/mediation/mediationprotocol/getMediationProtocolDetail.shtml';
	var jsonData  = $.util.requestAjaxJson(url,{'caseid':caseid});
	$('#protocolform').form("jsonLoad",jsonData.entity);
	if(caseState=='Completed'){
		$('#partAState').val("已同意");
	}else{
		if($('#partAState').val() == true){
			$('#partAState').val("已同意");
		}else{
			$('#partAState').val("未同意");
		}
	}
	//$('#partbform').form("jsonLoad",jsonData.list);
	var url2 = $homebasepath+'admin/client/tempclient/getTempClientList.shtml';
	var jsonData2  = $.util.requestAjaxJson(url2,{'caseid':caseid});
	var tempclienttemplate = $.templates("#tempclientTmpl");//初始化模板
	var htmlOutput = tempclienttemplate.render(jsonData2);//解析data数据
	
	var tempclienttemplate2 = $.templates("#tempclientTmpl2");//初始化模板
	var htmlOutput2 = tempclienttemplate2.render(jsonData2);//解析data数据
	var tmp1 = "";
	var tmp2 = "";
	for(var i=0;i<jsonData2.length;i++){
		tmp1+="<div id='wraper'>"
				+"<div class='tempclient'>"
					+"<div class='data'>"
						+"<div class='data-body'>"
							+"<p>"+jsonData2[i].identifyName+"</p>"
						+"</div>"
					+"</div>"
				+"</div>"
			+"</div>";
		
		tmp2+="<div id='wraper'>"
				+"<div class='tempclient'>"
					+"<div class='data'>"
						+"<div class='data-body'>"
							+"<p>"+(jsonData2[i].sign=='Init'?"初始化":jsonData2[i].sign=='Accepted'?"同意":"不同意")+"</p>"
						+"</div>"
					+"</div>"
				+"</div>"
			+"</div>";
	}
	$("#tempclient").html(tmp1);//定位到模板处
	$("#tempclient2").html(tmp2);//定位到模板处
}

//获取结案卷宗
function getMediationCaseFile(){
	var caseid = $("#id").val();
	if(caseState!='Completed'){
		alert("该案件还未结案，不能导出卷宗！");
		return;
	}
	$.ajax({
		url:$homebasepath+'/admin/mediation/mediationcase/getMediationCaseFile.shtml',
		data:{"caseid":caseid},
		type:'POST',
		async: false,
		dataType:'json', 
		success:function(data){
			window.location.href=$homebasepath+data.url;
		}
	});
}

function getChatRecord(caseid){
	var columns = [
		       	    {field :'sendClient',title:'发送用户',formatter:function(val,row){return row.sendClient.identifyName;},sortable:true,width :parseInt($(this).width()*0.1),halign:'center',align:'left'},
		       	    {field :'content',title:'内容',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'center',formatter:function(val,row){
		       	    	switch(row.contentType){
	       	    		case "Text":
	       	    				return initWXR(val);
	       	    			break;
	       	    		case "Image":
	       	    				return "<img width='50' height='50' src='"+$homebasepath+"/uploads/chat/image/"+val+"'/>";
	       	    			break;
	       	    		case "Voice":
	       	    				return "<audio src='"+$homebasepath+"/uploads/chat/voice/"+val+"' controls='controls'></audio>";
	       	    			break;
	       	    		case "Video":
	       	    			return val;
	       	    			break;
	       	    		case "File":
	       	    			return val;
	       	    			break;
	       	    	}
		       	    }},
		       	    {field :'contentType',title:'内容类型',sortable:true,width :parseInt($(this).width()*0.05),halign:'center',align:'center',formatter:function(val,row){
		       	    	switch(val){
		       	    		case "Text":
		       	    			return "文本";
		       	    			break;
		       	    		case "Image":
		       	    			return "图片";
		       	    			break;
		       	    		case "Voice":
		       	    			return "语音";
		       	    			break;
		       	    		case "Video":
		       	    			return "视频";
		       	    			break;
		       	    		case "File":
		       	    			return "文件";
		       	    			break;
		       	    	}
		       	    }},
		       	    {field :'sendTime',title:'发送时间',sortable:true,width :parseInt($(this).width()*0.2),halign:'center',align:'center',formatter:function(val,row){
		       	    	var date = new Date(val);
		       	    	return  date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()<10?"0"+date.getDate():date.getDate())+"  "+(date.getHours()<10?"0"+date.getHours():date.getHours())+":"+(date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes())+":"+(date.getSeconds()<10?"0"+date.getSeconds():date.getSeconds());
		       	    }},
		       	    
		       	  ];
	
	 $('#chatList').datagrid({
		url: $homebasepath+'/admin/chat/chatmessage/findDataBindDg.shtml',
		toolbar:'#chatList_layout',
		queryParams:{"entityQuery.caseId":caseid},
		fit:true,
		striped:true,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		rownumbers:true,
		singleSelect:true,
		pagination:true,
		pageSize:$.pagesize(true),
		sortName:'sendTime',
		sortOrder:'desc',
		columns:[columns],
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