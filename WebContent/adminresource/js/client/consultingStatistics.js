$(function(){  
	getConsultingStatisticsData();
});
//咨询统计数据
function getConsultingStatisticsData(){
	$.ajax({
		url:$homebasepath+'/admin/client/getConsultingStatisticsData.shtml',
		type:'POST',
		async: false,
		dataType:'json', 
		success:function(data){
			var type = [];
			var rows = [];
			var temp = "";
			for(var i=0;i<data.resultDescription.list.length;i++){
				switch(i){
					case 0:
						type.push("用户注册");
						/*var tmp = [];
						tmp["value"]=data.resultDescription.list[i].count;
						tmp["name"]="用户注册";
						rows.push(tmp);*/
						temp+="[{value:"+data.resultDescription.list[i].count+",name:'用户注册'},";
						break;
					case 1:
						type.push("自动生成");
						/*var tmp = [];
						tmp["value"]=data.resultDescription.list[i].count;
						tmp["name"]="自动生成";
						rows.push(tmp);*/
						temp+="{value:"+data.resultDescription.list[i].count+",name:'自动生成'},";
						break;
					case 2:
						type.push("小程序");
						/*var tmp = [];
						tmp["value"]=data.resultDescription.list[i].count;
						tmp["name"]="小程序";
						rows.push(tmp);*/
						temp+="{value:"+data.resultDescription.list[i].count+",name:'小程序'},";
						break;
					case 3:
						type.push("第三方授权登录");
						/*var tmp = [];
						tmp["value"]=data.resultDescription.list[i].count;
						tmp["name"]="第三方授权登录";
						rows.push(tmp);*/
						temp+="{value:"+data.resultDescription.list[i].count+",name:'第三方授权登录'}]";
						break;
				}
			}
			test(type,temp);
		}
	});
}

function test(name,temp){
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
    var myChart = echarts.init(document.getElementById('chart'));  
    myChart.setOption(option);  
} 