<#if objectFiles?? && (objectFiles?size gt 0 || canDownload?? && canDownload)>
	<div id="files">
		<#list objectFiles as file>
			<#if file.fileType?? && file.fileType!='Image'>
				<div style="padding: 0px 5px 0px 0px">
					<#if file.converted?? && file.converted>
						<!-- <a href="<@s.url action="viewdoc" namespace="/web"/>?id=${file.id }" target="_blank">
							${file.originalName }
						</a> -->
						<a href="#" onclick="viewDoc('${file.id}');return false;">
							${file.originalName }
							<#if canDownload?? && canDownload>
								&nbsp;&nbsp;<a href="<@s.url action="download" namespace="/kfbase/objectfile"/>?id=${file.id }">下载</a>
							</#if>
						</a>
					<#else>
						<a href="<@s.url action="download" namespace="/kfbase/objectfile"/>?id=${file.id }">
							${file.originalName }
						</a>
					</#if>
					&nbsp;&nbsp;
				</div>
			</#if>
		</#list>
	</div>
</#if>