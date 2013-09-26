<#assign ContextPath = springMacroRequestContext.getContextPath() />

<#macro randImage images width height>
<#assign image = images[springx.rand(0, images?size-1)] />
http://${ImageServer}/${image.qiniuKey}?imageView/1/w/${width}/h/${height}</#macro>

<#macro mdlink island type>
href="${ContextPath}/maldives/${island[type]!island.id}.html" title="${island.zhName!} ${island.enName!} 马代 马尔代夫 海岛 自由行" target="_blank"</#macro>

<#macro mdalt island>
alt="${island.zhName!} ${island.enName!} 马代 马尔代夫 海岛 自由行"</#macro>
