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
<PicUrl><![CDATA[${article.picUrl!}]]></PicUrl>
<Url><![CDATA[${article.url!}]]></Url>
</item>
</#list>
</Articles>
</xml> 