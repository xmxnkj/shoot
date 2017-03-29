/**
 * File Name: WorkFlowBaseAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.workflow.actions.runtime;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.kfbase.entity.Property;
import com.hsit.common.kfbase.service.PropertyService;
import com.hsit.workflow.runtime.entity.ActivityWorkItem;
import com.hsit.workflow.runtime.entity.AuditSuggest;
import com.hsit.workflow.runtime.entity.ExecutionResult;
import com.hsit.workflow.runtime.entity.FlowWorkItem;
import com.hsit.workflow.runtime.service.ActivityWorkItemService;
import com.hsit.workflow.runtime.service.AuditSuggestService;
import com.hsit.workflow.runtime.service.ExecutionResultService;
import com.hsit.workflow.runtime.service.FlowWorkItemService;

/**
 * @ClassName:WorkFlowBaseAction
 * 
 */
public abstract class WorkFlowBaseAction<T extends DomainEntity, Q extends EntityQueryParam> extends BaseAction<T, Q> {
	
	private AuditSuggestService auditSuggestService;

	@Autowired
	public void setAuditSuggestService(AuditSuggestService auditSuggestService) {
		this.auditSuggestService = auditSuggestService;
	}

	private String flowWorkItemId;

	public String getFlowWorkItemId() {
		return flowWorkItemId;
	}

	public void setFlowWorkItemId(String flowWorkItemId) {
		this.flowWorkItemId = flowWorkItemId;
	}
	
	private List<ExecutionResult> executionResults;

	public List<ExecutionResult> getExecutionResults() {
		return executionResults;
	}
	
	private String flowId;
	
	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
		executingFlowId=flowId;
	}
	
	private String executingFlowId;

	public String getExecutingFlowId() {
		return executingFlowId;
	}

	public void setExecutingFlowId(String executingFlowId) {
		this.executingFlowId = executingFlowId;
	}

	private FlowWorkItem flowWorkItem;

	public FlowWorkItem getFlowWorkItem() {
		return flowWorkItem;
	}

	public void setFlowWorkItem(FlowWorkItem flowWorkItem) {
		this.flowWorkItem = flowWorkItem;
	}

	public void setExecutionResults(List<ExecutionResult> executionResults) {
		this.executionResults = executionResults;
	}
	
	private FlowWorkItemService flowWorkItemService;
	
	public FlowWorkItemService getFlowWorkItemService() {
		return flowWorkItemService;
	}

	@Autowired
	public void setFlowWorkItemService(FlowWorkItemService flowWorkItemService) {
		this.flowWorkItemService = flowWorkItemService;
	}
	
	private ExecutionResultService executionResultService;

	public ExecutionResultService getExecutionResultService() {
		return executionResultService;
	}

	@Autowired
	public void setExecutionResultService(
			ExecutionResultService executionResultService) {
		this.executionResultService = executionResultService;
	}
	
	private ActivityWorkItemService activityWorkItemService;

	@Autowired
	public void setActivityWorkItemService(
			ActivityWorkItemService activityWorkItemService) {
		this.activityWorkItemService = activityWorkItemService;
	}

	private String flowTypeName;
	
	public String getFlowTypeName() {
		return flowTypeName;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}
	
	private String secondFlowTypeName;

	public String getSecondFlowTypeName() {
		return secondFlowTypeName;
	}

	public void setSecondFlowTypeName(String secondFlowTypeName) {
		this.secondFlowTypeName = secondFlowTypeName;
	}
	
	private FlowWorkItem secondFlowWorkItem;

	public FlowWorkItem getSecondFlowWorkItem() {
		return secondFlowWorkItem;
	}

	public void setSecondFlowWorkItem(FlowWorkItem secondFlowWorkItem) {
		this.secondFlowWorkItem = secondFlowWorkItem;
	}
	
	private List<ExecutionResult> secondExecutionResults;

	public List<ExecutionResult> getSecondExecutionResults() {
		return secondExecutionResults;
	}

	public void setSecondExecutionResults(
			List<ExecutionResult> secondExecutionResults) {
		this.secondExecutionResults = secondExecutionResults;
	}

	protected void setFlowWorkItemInfo(String objectId, String flowTypeName) {
		flowWorkItem = flowWorkItemService.getObjectLatestFlowWorkItem(objectId, flowTypeName);
	}
	
	protected void setFlowWorkItemInfo() {
		
		
		if(getEntity()!=null){
			flowWorkItem = flowWorkItemService.getObjectLatestFlowWorkItem(getEntity().getId(), flowTypeName);
			
	//		if (!StringUtils.isEmpty(flowTypeName)) {
	//			flowWorkItem = flowWorkItemService.getFinishFlowWorkItem(getEntity().getId(), flowTypeName);			
	//			//flowWorkItem = flowWorkItemService.getFlowWorkItem(getEntity().getId(), flowTypeName);
	//		}else{
	//			flowWorkItem = flowWorkItemService.getFlowWorkItem(getEntity().getId(), "");
	//		}
			
			if (!StringUtils.isEmpty(secondFlowTypeName)) {
				secondFlowWorkItem = flowWorkItemService.getObjectLatestFlowWorkItem(getEntity().getId(), secondFlowTypeName);
			}
			
			if(flowWorkItem != null){
				flowWorkItemId = flowWorkItem.getId();
				executionResults = executionResultService.getFlowWorkItemExecutionResult(flowWorkItemId);
				
				executingActivityWorkItems = activityWorkItemService.getFlowActivityWorkItems(flowWorkItemId);
			}
			
			if (secondFlowWorkItem != null) {
				secondExecutionResults = executionResultService.getFlowWorkItemExecutionResult(secondFlowWorkItem.getId());
				
				secondExecutingActivityWorkItems = activityWorkItemService.getFlowActivityWorkItems(secondFlowWorkItem.getId());
			}
		}
	}
	
	private PropertyService propertyService;
	
	@Autowired
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	protected String getActivityProperty(String propertyName) {
		if (executingActivityWorkItems != null && executingActivityWorkItems.size()>0) {
			for (ActivityWorkItem awi : executingActivityWorkItems) {
				Property property = propertyService.getProperty(awi.getActivityId(), propertyName);
				if (property != null) {
					return property.getPropertyValue();
				}
			}
		}
		return null;
	}
	
	private String returnUrl;

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	
	private String executionResultTitle;

	/**
	 * 审批记录的说明性文字
	 * @return
	 */
	public String getExecutionResultTitle() {
		return executionResultTitle;
	}

	public void setExecutionResultTitle(String executionResultTitle) {
		this.executionResultTitle = executionResultTitle;
	}
	
	private String secondExecutionResultTitle;

	/**
	 * 第二审批记录的说明性文字
	 * @return
	 */
	public String getSecondExecutionResultTitle() {
		return secondExecutionResultTitle;
	}

	public void setSecondExecutionResultTitle(String secondExecutionResultTitle) {
		this.secondExecutionResultTitle = secondExecutionResultTitle;
	}
	
	private Boolean isMyAuditing;

	public Boolean getIsMyAuditing() {
		return isMyAuditing;
	}

	public void setIsMyAuditing(Boolean isMyAuditing) {
		this.isMyAuditing = isMyAuditing;
	}
	
	
	protected void saveAuditSuggest() {
		if (saveSuggest != null
				&& saveSuggest.booleanValue()
				&& executionResult != null 
				&& !StringUtils.isEmpty(executionResult.getSuggest())) {
			auditSuggestService.save(getLoginUserId(), executionResult.getSuggest());
		}
	}
	
	
	private Boolean saveSuggest;
	
	private List<AuditSuggest> auditSuggests = new ArrayList<>();

	public Boolean getSaveSuggest() {
		return saveSuggest;
	}

	public void setSaveSuggest(Boolean saveSuggest) {
		this.saveSuggest = saveSuggest;
	}

	public List<AuditSuggest> getAuditSuggests() {
		return auditSuggests;
	}

	public void setAuditSuggests(List<AuditSuggest> auditSuggests) {
		this.auditSuggests = auditSuggests;
	}
	
	private ExecutionResult executionResult;

	public ExecutionResult getExecutionResult() {
		return executionResult;
	}

	public void setExecutionResult(ExecutionResult executionResult) {
		this.executionResult = executionResult;
	}
	
	private Boolean isAuditModify;

	public Boolean getIsAuditModify() {
		return isAuditModify;
	}

	public void setIsAuditModify(Boolean isAuditModify) {
		this.isAuditModify = isAuditModify;
	}
	
	
	private List<ActivityWorkItem> executingActivityWorkItems;
	
	private List<ActivityWorkItem> secondExecutingActivityWorkItems;

	public List<ActivityWorkItem> getExecutingActivityWorkItems() {
		return executingActivityWorkItems;
	}

	public void setExecutingActivityWorkItems(
			List<ActivityWorkItem> executingActivityWorkItems) {
		this.executingActivityWorkItems = executingActivityWorkItems;
	}

	public List<ActivityWorkItem> getSecondExecutingActivityWorkItems() {
		return secondExecutingActivityWorkItems;
	}

	public void setSecondExecutingActivityWorkItems(
			List<ActivityWorkItem> secondExecutingActivityWorkItems) {
		this.secondExecutingActivityWorkItems = secondExecutingActivityWorkItems;
	}
	
	
}
