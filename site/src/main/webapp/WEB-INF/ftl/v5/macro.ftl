<#assign ContextPath = springMacroRequestContext.getContextPath() />

<#macro randImage images width height>
<#assign image = images[springx.rand(0, images?size-1)] />
http://${ImageServer}/${image.qiniuKey}?imageView/1/w/${width}/h/${height}</#macro>

<#macro mdlink island type>
href="${ContextPath}/maldives/${island[type]!island.id}.html" title="${island.zhName!} ${island.enName!} 马代 马尔代夫 海岛 自由行" target="_blank"</#macro>

<#macro mdalt island>
alt="${island.zhName!} ${island.enName!} 马代 马尔代夫 海岛 自由行 海岛自由行"</#macro>

<#macro mqlink hotel type>
href="${ContextPath}/mauritius/${hotel[type]!hotel.id}.html" title="<@mqalt hotel />" target="_blank"</#macro>

<#macro mqalt hotel>
${hotel.zhName!} ${hotel.enName!} 毛球 毛求 毛里求斯 毛里球斯 海岛 自由行 海岛自由行 毛求自由行 毛求酒店 毛里求斯自由行 毛里求斯酒店</#macro>
