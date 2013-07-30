<#if ajax>非法请求</#if>
<#if !ajax>
<#include "error.ftl">
<@error '<strong>${statusCode}: </strong>非法请求'/>
</#if>