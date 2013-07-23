<#assign ContextPath=springMacroRequestContext.getContextPath() />
<#assign User = securityContextHolder.getContext().getAuthentication().getPrincipal() />
${User.id}