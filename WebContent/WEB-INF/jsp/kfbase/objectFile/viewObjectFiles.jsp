<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="objectFiles != nulll && objectFiles.size>0">
	<div>
		附件：
		<div id="files">
			<s:iterator value="objectFiles">
				<s:if test="objectName!='content'">
				<div style="padding: 5px">
					<a href="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="id" />">
						<s:property value="originalName" />
					</a>
					&nbsp;&nbsp;
				</div>
				</s:if>
			</s:iterator>
		</div>
	</div>
</s:if>