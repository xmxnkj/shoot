/**
 * AJAX插件
 */
(function($) {
	$.extend({
		/**
		 *APIAJAX请求
		 */
		apiAjaxRequest:function(options){
			var opts = $.extend({
	       	   loadingtext:"加载中..."
	        }, options);
	    	$.showLoading(opts.loadingtext);
	    	var params = {
			   code:opts.code,
			   inbo:opts.inbo
		    };
			$.ajax({
				type : 'post',
				url : opts.ajaxurl,
				dataType : 'json',
				data : params,
				success : function(data) {
				   if(data.resultstate==0) {
				   	  if(opts.callbackfun){
				   	  	opts.callbackfun(data.outbo);
				   	  }
				   	  $.hideLoading();
				   }else{
					 $.hideLoading();
					 $.alert(data.message);
				   }
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.hideLoading();
					$.alert('抱歉提交错误');
				}
			}); 
	    },
	    ajaxCallbackFun:function(options){
			var opts = $.extend({
		       	   suctext:null,
		       	   result:null
		    }, options);
			var resultjson = $.parseJSON(opts.result);
			if(resultjson.success){
				 if(opts.suctext!=null){
					  $.messager.show("操作提醒",opts.suctext, "info", "center");
				 }
				 if(opts.succallbackfun){
					 opts.succallbackfun(resultjson,resultjson.data);
				 }
			}else{
			 	 $.messager.show("操作失败",resultjson.message, "warning", "center");
				 if(opts.faicallbackfun){
					 opts.faicallbackfun(resultjson,resultjson.message);
				 }
			}
	    },
	    easyuiSubmitForm:function(options){
			var opts = $.extend({
				formid:'',
				url:'',
				suctext:''
		     }, options);
			$("#"+opts.formid).form({
		        url: opts.url,
		        onSubmit: function () {
		    		parent.$.messager.progress({
						title : '提示',
						text : '请稍后....'
					});
		    		if(opts.valid){
		    			opts.valid();
		    		}else{
						var isValid = $(this).form('validate');
						if (!isValid) {
							parent.$.messager.progress('close');
						}
						return isValid;
		    		}
		        },
		        success: function (data) {
		        	parent.$.messager.progress('close');
		        	$.ajaxCallbackFun({
		        		result:data,
		        		suctext:opts.suctext,
		        		succallbackfun:function(result,resultdata){
		        			 parent.$.messager.progress('close');
			   				 if(opts.succallbackfun){
								 opts.succallbackfun(result,resultdata);
							 }		        			
		        		},
		        		faicallbackfun:function(result,message){
		        			 parent.$.messager.progress('close');
			   				 if(opts.faicallbackfun){
								 opts.faicallbackfun(result,message);
							 }
		        		}
		        	});		        	
		        }
		    });	 
	    }
	});
})(jQuery); 

 
