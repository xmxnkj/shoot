<#if objectFiles?? && objectFiles?size gt 0>
	<#list objectFiles as file>
		<#if file.fileType?? && file.fileType=='Image'>
			<div style="padding:5px;width:100%;text-align:center">
				<img alt="" src="<@s.url action="download" namespace="/kfbase/objectfile" />?id=${file.id }" />
			</div>
		</#if>
	</#list>
</#if>