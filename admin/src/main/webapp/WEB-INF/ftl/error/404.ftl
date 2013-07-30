<#if ajax>页面不存在</#if>
<#if !ajax>
<#include "error.ftl">
<@error '<strong>${statusCode}: </strong>页面不存在'/>
</#if>