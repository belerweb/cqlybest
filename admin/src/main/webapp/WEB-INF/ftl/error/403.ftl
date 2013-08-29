<#if ajax>未授权</#if>
<#if !ajax>
<#include "error.ftl">
<@error '<strong>${statusCode}: </strong>未授权。<a href="${ContextPath}/logout">重新登录</a>或<a href="javascript:location.reload();">刷新</a>。'/>
</#if>