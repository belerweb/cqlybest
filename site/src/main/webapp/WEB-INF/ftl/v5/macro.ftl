<#assign ContextPath = springMacroRequestContext.getContextPath() />

<#macro randImage images width height>
<#assign image = images[springx.rand(0, images?size-1)] />
http://${ImageServer}/${image.qiniuKey}?imageView/1/w/${width}/h/${height}</#macro>

<#macro mdlink item type>
href="${ContextPath}/maldives/${item[type]!island.id}.html" title="<@mdalt item />" target="_blank"</#macro>

<#macro mdalt item>
${item.zhName!} ${item.enName!} 马代 马尔代夫 海岛 自由行 海岛自由行 马代酒店 马尔代夫酒店</#macro>

<#macro mqlink item type>
href="${ContextPath}/mauritius/${item[type]!hotel.id}.html" title="<@mqalt item />" target="_blank"</#macro>

<#macro mqalt item>
${item.zhName!} ${item.enName!} 毛球 毛求 毛里求斯 毛里球斯 海岛 自由行 海岛自由行 毛求自由行 毛求酒店 毛里求斯自由行 毛里求斯酒店</#macro>
