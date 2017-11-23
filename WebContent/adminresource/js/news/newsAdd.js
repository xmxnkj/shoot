$(function(){
	$.log($homebasepath);
});

function submitForm(){
	$.ajax({
        type: "POST",
        url: $homebasepath+"admin/news/saveNewsHeadInfo.shtml",
        data:$('#editAddform').serialize(),
        async: false,
        error: function(request) {
            alert("提交失败");
        },
        success: function(data) {
        	$.easyui.parent.$dig_newsHeadInfoAdd.dialog('close');
           	$.easyui.parent.refreshData();
        }
    });
}