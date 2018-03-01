/**
 * 工具类插件
 */
(function($) {
	$.extend({
	    log: function(message) {
	        var now = new Date(),
	            y = now.getFullYear(),
	            m = now.getMonth() + 1, //！JavaScript中月分是从0开始的
	            d = now.getDate(),
	            h = now.getHours(),
	            min = now.getMinutes(),
	            s = now.getSeconds(),
	            time = y + '/' + m + '/' + d + ' ' + h + ':' + min + ':' + s;
	        console.log(time + ' MyLog:' + message);
	    },
	    pagesize:function(defaultflag,pagesizenum){
	      if(defaultflag){
	      	 return 50;
	      }else{
	      	return pagesizenum;
	      }
	    }
	});
})(jQuery); 

 
