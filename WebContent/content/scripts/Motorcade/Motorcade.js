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
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        if (!exists(rows, row.ParentId)) {
            nodes.push({
                id: row.Id,
                text: row.Name,
                state: 'open'
            });
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
                var child = { id: row.Id, text: row.Name };
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
    var root = { id: "00000000-0000-0000-0000-000000000000", text: "管理" };
    root.children = nodes;
    treeNodes.push(root);
    return treeNodes;
}

function initMotorcadeTree() {
    $('#MotorcadeTree').tree({
        url: '/Areas/Motorcade/GetMotorcades',
        loadFilter: function (rows) {
            return convert(rows);
        },
        onLoadError: function (arguments) {
            alert("error:" + arguments);
        },
        lines: true,
        onClick: function (node) {
            if (typeof (nodeClicked) == "function") {
                nodeClicked(node.id, node.text);
            }
        }
    });
}

    function initStationTree() {
        $('#MotorcadeTree').tree({
        url: '/Areas/Station/GetStations',
        loadFilter: function (rows) {
            return convert(rows);
        },
        onLoadError: function (arguments) {
            alert("error:" + arguments);
        },
        lines: true,
        onClick: function (node) {
            if (typeof (nodeClicked) == "function") {
                nodeClicked(node.id, node.text);
            }
        }
    });
}