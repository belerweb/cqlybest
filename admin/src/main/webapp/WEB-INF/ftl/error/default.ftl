<#if ajax>发生了预期外的错误，请提交BUG。</#if>
<#if !ajax>
<#include "error.ftl">
<@error '<strong>${statusCode}: </strong>发生了预期外的错误，请提交BUG。'/>
</#if>