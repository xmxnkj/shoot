$(function(){
	
	getManagerList();
});


function getManagerList(){
	 var columns = [
	         	    {field :'identifyName',title:'姓名',sortable:false,width :parseInt($(this).width()*0.05),halign:'center',align:'center'},
	                {field :'clientState',title:'身份',sortable:false,width :parseInt($(this).width()*0.12),halign:'center',align:'center',formatter:function(val,row){
	                	
	                	if(val=='MediationCenter'){
	                		return "调解中心管理员";
	                	}
	                	
	                	if(val=='MediationAgency'){
	                		return "调解机构管理员";
	                	}
	                	
	                }},
	                {field :'agencyName',title:'所属机构',sortable:false,width :parseInt($(this).width()*0.15),halign:'center',align:'center'}
	             ];	

	         	$dgchatuser = $('#managerList').datagrid({
	        		url: $homebasepath+'/admin/client/getAllMediation.shtml',
	        		toolbar:"#mediationcaselist_toolbar",
	        		fit:true,
	        		striped:true,
	        		singleSelect:true,
	        		checkOnSelect:false,
	        		selectOnCheck:false,
	        		rownumbers:true,
	        		singleSelect:true,
	        		pagination:true,
	        		pageSize:$.pagesize(true),
	        		/*sortName:'consultTime',
	        		sortOrder:'desc',*/
	        		columns:[columns],
	        		onClickRow:function(index,row){
	        			var row = $('#managerList').datagrid('getSelected');
	        			initPie(row.id,row.mediationAgencyId);
	        		}
	        	});
}

//加载饼状图
function initPie(clientId,mediationAgencyId){
	var startTime = $("#start").datebox('getValue');
	var endTime = $("#end").datebox('getValue');
	$.ajax({
		url:$homebasepath+'/admin/mediation/mediationcase/getCaseDistribution.shtml',
		data:{
				"clientId":clientId,
				"mediationAgencyId":mediationAgencyId,
				"startTime":startTime,
				"endTime":endTime
			},
		type:'POST',
		async: false,
		dataType:'json', 
		success:function(data){
			var total = data[0];	//在线调解总数
			var name = [];
			var value = "";
			for(var i=0;i<total.list.length;i++){
				var type = "";
				switch(total.list[i].name){
					case 0:
						type="申请中";
						break;
					case 1:
						type="分配中";
						break;
					case 2:
						type="调解中";
						break;
					case 3:
						type="待签署";
						break;
					case 4:
						type="待结案";
						break;
					case 5:
						type="已关闭";
						break;
					case 6:
						type="已拒绝";
						break;
					case 7:
						type="已结案";
						break;
					case 8:
						type="已取消";
						break;
				}
				
				var baifenshu = Number((total.list[i].state/total.total)*100).toFixed(1)+"%";
				name.push(type+"("+baifenshu+")");
				value+="{value:"+total.list[i].state+",name:'"+type+"("+baifenshu+")'},";
			}

			if(value.length>0){
				value ="["+ value.substring(0,value.length-1)+"]";
			}
			test(name,value,"chart");
			
			total = data[1];	//成功
			name = [];
			value = "";
			for(var i=0;i<total.list.length;i++){
				var baifenshu = Number((total.list[i].state/total.total)*100).toFixed(1)+"%"
				name.push(total.list[i].name+"("+baifenshu+")");
				
				value+="{value:"+total.list[i].state+",name:'"+total.list[i].name+"("+baifenshu+")'},";
			}
			
			if(value.length>0){
				value ="["+ value.substring(0,value.length-1)+"]";
			}
			test(name,value,"chart1");
		}
	});
}

function test(name,temp,id){
    var myChart = echarts.init(document.getElementById(id));
    if(!temp){
    	myChart.clear();
    	return;
    }
    var option = {  
    title : {  
        text: '',  
        subtext: '',  
        x:'center'  
    },  
    tooltip : {  
        trigger: 'item',  
        formatter: "{a} <br/>{b} : {c} ({d}%)"  
    },  
    legend: {  
        orient : 'vertical',  
        x : 'left',  
        data:name
    },  
    toolbox: {  
        show : true,  
        feature : {  
            mark : {show: true},  
            dataView : {show: true, readOnly: false},  
            restore : {show: true},  
            saveAsImage : {show: true}  
        }  
    },  
    calculable : true,  
    series : [  
        {  
            name:'咨询统计',  
            type:'pie',  
            radius : '55%',  
            center: ['50%', '60%'],  
            data:eval('('+temp+')')
        }  
    ]  
};  
    myChart.setOption(option);
}