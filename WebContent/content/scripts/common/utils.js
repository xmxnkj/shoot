function dealJsonResult(data, msg, fn, callback,errmsg){
	var result = false;
	try{
		result = top.$.parseJSON(data);
	}catch(e){
		
	}
	if (result && result.isSuccess) {
		if(result.resultDescription!=null&&result.resultDescription!=""){
			top.$.messager.alert(WINDOW_CAPTION, result.resultDescription, 'info');
		}else if(msg!=null && msg != ""){
			top.$.messager.alert(WINDOW_CAPTION, msg, 'info', fn);
		}else {
			top.$.messager.alert(WINDOW_CAPTION, "成功", 'info', fn);
		}
        if(typeof(callback) == "function"){
        	callback(result);
        }
        return true;
    } else if (result) {
        top.$.messager.alert(WINDOW_CAPTION, result.resultDescription, 'error');
    } else {
    	if(errmsg!=null && errmsg!="")
    		top.$.messager.alert(WINDOW_CAPTION, errmsg, 'error');
    	else
    		top.$.messager.alert(WINDOW_CAPTION, DEFAULT_ERROR, 'error');
    }
    return false;
}

function checkDateTime(str){	  
    var reg = /^(\d+)-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
    var r = str.match(reg); 
    if(r==null)return false; 
    r[2]=r[2]-1; 
    var d= new Date(r[1], r[2],r[3], r[4],r[5], r[6]); 
    if(d.getFullYear()!=r[1])return false; 
    if(d.getMonth()!=r[2])return false; 
    if(d.getDate()!=r[3])return false; 
    if(d.getHours()!=r[4])return false; 
    if(d.getMinutes()!=r[5])return false; 
    if(d.getSeconds()!=r[6])return false; 
    return true;
}

function checkDate(dateStr){
	var datePat = /^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$/;
    var matchArray = dateStr.match(datePat);
    if (matchArray == null) return false;
    var month = matchArray[3];
    var day = matchArray[5];
    var year = matchArray[1];
    if (month < 1 || month > 12) return false;
    if (day < 1 || day > 31) return false;
    if ((month==4 || month==6 || month==9 || month==11) && day==31) return false;
    if (month == 2){
        var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day==29 && !isleap)) return false;
    }
    return true;
}