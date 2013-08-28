<xml>
<ToUserName><![CDATA[${data.toUserName}]]></ToUserName>
<FromUserName><![CDATA[${data.fromUserName}]]></FromUserName>
<CreateTime>${data.createTime}</CreateTime>
<MsgType><![CDATA[${data.msgType}]]></MsgType>
<ArticleCount>${data.count}</ArticleCount>
<Articles>
<#list data.articles as article>
<item>
<Title><![CDATA[${article.title!}]]></Title>
<Description><![CDATA[${article.description!}]]></Description>
<PicUrl><![CDATA[${article.picUrl!}<#if article_index==0>?width=640&height=320<#else>?width=80&height=80</#if>]]></PicUrl>
<Url><![CDATA[${article.url!}]]></Url>
</item>
</#list>
</Articles>
</xml> 