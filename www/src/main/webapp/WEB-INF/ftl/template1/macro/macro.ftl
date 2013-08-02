<#macro composeProductName product>
${product.name!}<#if product.productType==1><#if product.days?has_content>${product.days}天</#if><#if product.nights?has_content>${product.nights}晚</#if><#if (product.detail.room1)?has_content && (product.detail.room1Unit)?has_content>${product.detail.room1}${product.detail.room1Unit}</#if><#if (product.detail.room2)?has_content && (product.detail.room2Unit)?has_content>${product.detail.room2}${product.detail.room2Unit}</#if><#if (product.detail.room3)?has_content && (product.detail.room3Unit)?has_content>${product.detail.room3}${product.detail.room3Unit}</#if></#if>
</#macro>

<#macro getOneImageUrl images>
<#assign image = images[springx.rand(0, images?size-1)] />
${ContextPath}/image/${image.id}.${image.imageType}</#macro>
