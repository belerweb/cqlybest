<#assign ContextPath = springMacroRequestContext.getContextPath() />

<#macro randImage images width height>
<#assign image = images[springx.rand(0, images?size-1)] />
${ContextPath}/image/${width}/${height}/${image.id}.${image.extension}</#macro>