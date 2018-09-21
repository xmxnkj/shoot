function exists(rows, ParentId) {
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].Id == ParentId) {
            return true;
        }
        return false;
    }
}

function convert(rows) {
    var nodes = [];
    //alert(rows);
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        //   alert(row.Check);
        if (!exists(rows, row.ParentId)) {
            if (row.Check == true) {
                nodes.push({
                    id: row.Id,
                    text: row.Name,
                    attributes: { "url": row.Url, "check": row.Check },
                    state: 'open',
                    checked: true
                });
            } else {
                nodes.push({
                    id: row.Id,
                    text: row.Name,
                    attributes: { "url": row.Url, "check": row.Check },
                    state: 'open'
                });
            }
        }
    }

    var toDo = [];
    for (var i = 0; i < nodes.length; i++) {
        toDo.push(nodes[i]);
    }
    while (toDo.length) {
        var node = toDo.shift();
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            if (row.ParentId == node.id) {
                var child = { id: row.Id, text: row.Name, attributes: { "url": row.Url, "check": row.Check} };
                if (node.children) {
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    var treeNodes = [];
    var root = { id: "00000000-0000-0000-0000-000000000000", text: "管理", attributes: { "url": "", "check": false} };
    root.children = nodes;
    treeNodes.push(root);
    return treeNodes;
}

function initOperateTree() {
    $('#OperateTree').tree({
        url: '/Areas/Main/GetOperates',
        loadFilter: function (rows) {
            return convert(rows);
        },
        onLoadError: function (arguments) {
            alert("error:" + arguments);
        },
        lines: true,
        onClick: function (node) {
            if (typeof (nodeClicked) == "function") {
                nodeClicked(node.id, node.attributes.url);
            }
        }
    });
}


