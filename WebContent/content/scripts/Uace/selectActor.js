var split = ";";
var arrIds = [];
var arrNames = [];
function isContainId(id) {
	return isArrContainId(arrIds, id);
}

function isArrContainId(arr, id) {
	var result = false;
	if (arr != null) {
		for ( var i = 0; i < arr.length; i++) {
			if (arr[i] == id)
				return true;
		}
	}
	return false;
}

function initGridChecked(getNames, getIds) {
	if (typeof(getNames) == "function") {
		var ids = getIds();
		if (ids != null && ids != "") {
			arrIds = ids.split(';');
			var names = getNames(); //window.parent.getSelectedUserNames();
			arrNames = names.split(';');
		} else {
			arrIds = [];
			arrNames = [];
		}
	}
}

function addId(idArr, nameArr, id, name){
	if(idArr != null && nameArr != null && !isArrContainId(idArr, id)){
		idArr[idArr.length] = id;
		nameArr[nameArr.length] = name;
	}
}

function removeId(idArr, nameArr, id){
	if (idArr != null && nameArr != null) {
        for (var i = 0; i < idArr.length; i++) {
            if (idArr[i] == id) {
                idArr.splice(i, 1);
                nameArr.splice(i, 1);
                break;
            }
        }
    }
}

function setGridChecked() {
	if (arrIds != null) {
		var rows = $("#tbl").datagrid("getRows");
		for ( var i = 0; i < rows.length; i++) {
			for ( var j = 0; j < arrIds.length; j++) {
				if (rows[i].id == arrIds[j]) {
					$("#tbl").datagrid("checkRow", i);
					break;
				}
			}
		}
	}
}

function arrToString(arr) {
	var ids = "";
	if (arr != null) {

		for ( var i = 0; i < arr.length; i++) {
			if (i == 0) {
				ids = arr[i];
			} else {
				ids += split + arr[i];
			}
		}
	}

	return ids;
}

function setParentValues(parentFunc){
	if(typeof(parentFunc) == "function"){
		parentFunc(arrToString(arrIds), arrToString(arrNames));
	}
}


function checkRow(rowIndex, rowData){
	if (!isSingle) {
        if (arrIds != null && !isContainId(rowData.id)) {
            arrIds[arrIds.length] = rowData.id;
            arrNames[arrNames.length] = rowData.name;
            
        }
    } else {
        arrIds = [rowData.id];
        arrNames = [rowData.name];
        
    }
}

function uncheckRow (rowIndex, rowData) {
    if (arrIds != null) {
        for (var i = 0; i < arrIds.length; i++) {
            if (arrIds[i] == rowData.id) {
                arrIds.splice(i, 1);
                arrNames.splice(i, 1);
                break;
            }
        }
    }
}