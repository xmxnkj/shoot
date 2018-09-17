
function s4() 
{   
   return (((1+Math.random())*0x10000)|0).toString(16).substring(1);   
}    
function newGuid() 
{   
   return (s4()+s4()+"-"+s4()+"-"+s4()+"-"+s4()+"-"+s4()+s4()+s4());   
}

(function($) {
	
	var basePath = BASE_URL;
	var suffix=".html";
	var myflow = $.myflow;
	
	$.extend(true, myflow.config, {
		propurl: basePath + 'workflow/designer/nameprop' + suffix + "?type=flow",
		propwidth:400,
		propheight:250,
		pathPropUrl:basePath + 'workflow/designer/nameprop' + suffix,
		pathPropWidth:400,
		pathPropHeight:150
	});
	
	$.extend(true, myflow.config.props.props, {
		name : {
			name : 'name',
			label : '名称',
			value : '新建流程',
			editor : function() {
				return new myflow.editors.inputEditor();
			}
		},
		desc : {
			name : 'desc',
			label : '备注说明',
			value : '',
			editor : function() {
				return new myflow.editors.inputEditor();
			}
		}
	});

	$.extend(true, myflow.config.tools.states, {
		start : {
			type : 'start',
			name : {
				text : '<<start>>'
			},
			text : {
				text : '开始'
			},
			img : {
				src : basePath + 'content/images/workflow/16/start_event_empty.png',
				width : 16,
				height : 16
			},
			propurl: basePath + "workflow/designer/nameprop" + suffix,
			propwidth:400,
			propheight:150,
			props : {
				
			}
		},
		end : {
			type : 'end',
			name : {
				text : '<<end>>'
			},
			text : {
				text : '结束'
			},
			img : {
				src : basePath + 'content/images/workflow/16/end_event_terminate.png',
				width : 16,
				height : 16
			},
			propurl: basePath + "workflow/designer/nameprop" + suffix,
			propwidth:400,
			propheight:150,
			props : {
				
				temp1 : {
					name : 'temp1',
					label : '文本',
					value : '',
					editor : function() {
						return new myflow.editors.inputEditor();
					}
				},
				temp2 : {
					name : 'temp2',
					label : '选择',
					value : '',
					editor : function() {
						return new myflow.editors.selectEditor([ {
							name : 'aaa',
							value : 1
						}, {
							name : 'bbb',
							value : 2
						} ]);
					}
				}
			}
		},
		'end-cancel' : {
			type : 'end-cancel',
			name : {
				text : '<<end-cancel>>'
			},
			text : {
				text : '取消'
			},
			img : {
				src : basePath + 'content/images/workflow/16/end_event_cancel.png',
				width : 16,
				height : 16
			},
			props : {
				
				temp1 : {
					name : 'temp1',
					label : '文本',
					value : '',
					editor : function() {
						return new myflow.editors.inputEditor();
					}
				},
				temp2 : {
					name : 'temp2',
					label : '选择',
					value : '',
					editor : function() {
						return new myflow.editors.selectEditor([ {
							name : 'aaa',
							value : 1
						}, {
							name : 'bbb',
							value : 2
						} ]);
					}
				}
			}
		},
		'end-error' : {
			type : 'end-error',
			name : {
				text : '<<end-error>>'
			},
			text : {
				text : '错误'
			},
			img : {
				src : basePath + 'content/images/workflow/16/end_event_error.png',
				width : 16,
				height : 16
			},
			props : {
				
				temp1 : {
					name : 'temp1',
					label : '文本',
					value : '',
					editor : function() {
						return new myflow.editors.inputEditor();
					}
				},
				temp2 : {
					name : 'temp2',
					label : '选择',
					value : '',
					editor : function() {
						return new myflow.editors.selectEditor([ {
							name : 'aaa',
							value : 1
						}, {
							name : 'bbb',
							value : 2
						} ]);
					}
				}
			}
		},
		state : {
			type : 'state',
			name : {
				text : '<<state>>'
			},
			text : {
				text : '状态'
			},
			img : {
				src : basePath + 'content/images/workflow/16/task_empty.png',
				width : 16,
				height : 16
			},
			props : {
				
				temp1 : {
					name : 'temp1',
					label : '文本',
					value : '',
					editor : function() {
						return new myflow.editors.inputEditor();
					}
				},
				temp2 : {
					name : 'temp2',
					label : '选择',
					value : '',
					editor : function() {
						return new myflow.editors.selectEditor([ {
							name : 'aaa',
							value : 1
						}, {
							name : 'bbb',
							value : 2
						} ]);
					}
				}
			}
		},
		fork : {
			type : 'fork',
			name : {
				text : '<<fork>>'
			},
			text : {
				text : '分支'
			},
			img : {
				src : basePath + 'content/images/workflow/16/gateway_parallel.png',
				width : 16,
				height : 16
			},
			propurl: basePath + 'workflow/designer/conditionprop' + suffix,
			propwidth:400,
			propheight:300,
			props : {
				id:{
					name:'entity.id',
					value:newGuid()
				},
				temp1 : {
					name : 'temp1',
					label : '文本',
					value : '',
					editor : function() {
						return new myflow.editors.inputEditor();
					}
				},
				temp2 : {
					name : 'temp2',
					label : '选择',
					value : '',
					editor : function() {
						return new myflow.editors.selectEditor('select.json');
					}
				}
			}
		},
		join : {
			type : 'join',
			name : {
				text : '<<join>>'
			},
			text : {
				text : '合并'
			},
			img : {
				src : basePath + 'content/images/workflow/16/gateway_parallel.png',
				width : 16,
				height : 16
			},
			props : {
				
				temp1 : {
					name : 'temp1',
					label : '文本',
					value : '',
					editor : function() {
						return new myflow.editors.inputEditor();
					}
				},
				temp2 : {
					name : 'temp2',
					label : '选择',
					value : '',
					editor : function() {
						return new myflow.editors.selectEditor('select.json');
					}
				}
			}
		},
		task : {
			type : 'task',
			name : {
				text : '<<task>>'
			},
			text : {
				text : '任务'
			},
			img : {
				src : basePath + 'content/images/workflow/16/task_empty.png',
				width : 16,
				height : 16
			},
			propurl: basePath + 'workflow/designer/sequenceprop' + suffix,
			propwidth:550,
			propheight:300,
			props : {
				id:{
					name:'entity.id',
					value:''
				},
				autoSelectUserType : {
					name : 'entity.autoSelectUserType',
					label : '自动选人',
					value : 'NotAutoSelectUser',
					editor : function() {
						return new myflow.editors.selectEditor([{name:'不自动选人', value:'NotAutoSelectUser'},
						                                        {name:'自动选择部门主管领导', value:'SelectChargeLeader'},
						                                        {name:'自动选择部门分管领导', value:'SelectPartChargeLeader'},
						                                        {name:'自动选择部门负责人', value:'SelectLeader'},
						                                        {name:'自动选择部门副职', value:'SelectDeputyLeader'},
						                                        {name:'自动选择申请人', value:'FlowCreater'}
							]);
					}
				},
				executeTimeLimit : {
					name : 'entity.executeTimeLimit',
					label : '办理时限(小时)',
					value : '0',
					editor : function() {
						return new myflow.editors.inputEditor();
					}
				},
				userIds : {
					name : 'userIds',
					label : '审批人',
					value : ''
				},
				userNames : {
					name : 'userNames',
					label : '审批人',
					value : ''
				},
				roleIds : {
					name : 'roleIds',
					label : '审批人',
					value : ''
				},
				roleNames : {
					name : 'roleNames',
					label : '审批人',
					value : ''
				},
				positionIds : {
					name : 'positionIds',
					label : '审批人',
					value : ''
				},
				positionNames : {
					name : 'positionNames',
					label : '审批人',
					value : ''
				},
				activityUserSelectorIds:{
					name:'activityUserSelectorIds',
					lable:'',
					value:''
				},
				activityUserSelectorNames:{
					name:'activityUserSelectorNames',
					label:'',
					value:''
				},
				departmentChargeLeaderIds:{
					name:'departmentChargeLeaderIds',
					lable:'',
					value:''
				},
				departmentChargeLeaderNames:{
					name:'departmentChargeLeaderNames',
					lable:'',
					value:''
				},
				departmentPartChargeLeaderIds:{
					name:'departmentPartChargeLeaderIds',
					lable:'',
					value:''
				},
				departmentPartChargeLeaderNames:{
					name:'departmentPartChargeLeaderNames',
					lable:'',
					value:''
				},
				departmentLeaderIds:{
					name:'departmentLeaderIds',
					lable:'',
					value:''
				},
				departmentLeaderNames:{
					name:'departmentLeaderNames',
					lable:'',
					value:''
				},
				departmentDeputyLeaderIds:{
					name:'departmentDeputyLeaderIds',
					lable:'',
					value:''
				},
				departmentDeputyLeaderNames:{
					name:'departmentDeputyLeaderNames',
					lable:'',
					value:''
				},
				actionNames:{
					name:'entity.actionNames',
					lable:'',
					value:''
				},
				startActionNames:{
					name:'entity.startActionNames',
					lable:'',
					value:''
				},
				userDefine:{
					name:'userDefine',
					lable:'',
					value:''
				},
				auditYesType:{
					name:'entity.activityAuditYesType',
					value:''
				},
				executedActionNames:{
					name:'entity.executedActionNames',
					value:''
				},
				canCloseFlow:{
					name:'entity.canCloseFlow',
					value:''
				},
				manualSelectUser:{
					name:'entity.manualSelectUser',
					value:''
				}
			}
		}
	});
})(jQuery);