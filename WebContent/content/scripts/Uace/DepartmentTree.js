
function initDeptComboTree(selectId, hiddenId, url, width) {
	
	var w = 350;
	if(width)
		w = width;
	
	$("#" + selectId).combotree({
		url : url,
		loadFilter : function(rows) {
			return convert(rows, "部门");
		},
		onLoadError : function(arguments) {
			$.messager.alert("部门", arguments);
		},
		onLoadSuccess : function(node, data) {
			var selectedId = $('#' + hiddenId).val();
			setTreeNode(selectId, selectedId);
			treeLoaded = true;
		},
		editable : false,
		width : w,
		panelHeight : 180,
		onClick:function(node){$("#" + hiddenId).val(node.id);}
	});			
}


