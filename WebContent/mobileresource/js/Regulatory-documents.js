/*
 * new页面点赞
 * 效果点击一次后无法再点赞，颜色修改，图标颜色修改
 * */
$(document).ready(function () {
    var appreciate = true;
    $("#laud").click(function () {
        if (!appreciate) { //！代表“非”的意思。!true 就是false的意思
            alert("您已经点赞过了哦！");
            return;//return进行结束
        }
        $(".corb1").addClass("cor028");
        $("#laud").find("img").attr("src", "images/Thumbs_Up.png");
        $("#appreciate_num").text(parseInt($("#appreciate_num").text()) + 1);
        alert("点赞成功！！！");
        appreciate = false;
    });
    $("#input_box").click(function () {
        $("#discuss_input").animate({
            height: '150px',
            lineHeight: '150px'
        }, "slow");
        $("#input_box").animate({
            height: '90px',
            borderRadius: '10px'
        }, "slow");
        $("#input_box").attr('placeholder', '优质评论将会被优先展示')
    });
    $("#input_box").blur(function () {
        $("#discuss_input").animate({
            height: '60px',
            lineHeight: '60px'
        }, "slow");
        $("#input_box").animate({
            height: '30px',
            borderRadius: '30px'
        }, "slow");
        $("#input_box").val('');
        $("#input_box").attr('placeholder', '请输入...')
    });
})