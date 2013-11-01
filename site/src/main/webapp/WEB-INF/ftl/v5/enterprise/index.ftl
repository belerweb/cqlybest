<#assign Title='标杆企业|标杆考察|企业考察' />
<#assign Description='标杆企业|标杆考察|企业考察' />
<#assign Keywords='标杆考察 企业考察' />
<#include "../index/header.ftl">
<#include "../index/menu.ftl">
<div class="container-fluid">
	<div class="row-fluid margin-bottom-30">
		<div class="span9">
			<div class="headline"><h3>企业考察</h3></div>
			<div class="testimonial-body margin-bottom-20">
				${(Settings.example.description)!}
			</div>
			<div class="headline">
				<h3>成功案例</h3>
				<a href="${ContextPath}/case.html" class="more">全部案例</a>
			</div>
			<ul class="portfolio cases clearfix">
				<#list cases.items as item>
				<#if item.cover?has_content>
				<li class="span3">
					<a href="${ContextPath}/case/${item.id}.html">
						<em class="overflow-hidden"><img src="http://${ImageServer}/${item.cover.qiniuKey}-casecover"></em>
						<span>
							<strong>${item.name!}</strong>
						</span>
					</a>
				</li>
				</#if>
				</#list>
			</ul>
			<div class="headline" style="margin-top:-30px;">
				<h3>标杆企业</h3>
				<a href="${ContextPath}/enterprise/partner.html" class="more">全部标杆</a>
			</div>
			<ul class="partners clearfix">
				<#assign item_i=0>
				<#list companys.items as item>
				<#if item.logo?has_content>
				<li class="span2 <#if item_i%6==0>first</#if>">
					<a title="${item.name!}" href="${ContextPath}/enterprise/partner/${item.id}.html">
						<img alt="${item.name!}" src="http://${ImageServer}/${item.logo.qiniuKey}-companylogo"></em>
					</a>
				</li>
				<#assign item_i=item_i+1>
				</#if>
				</#list>
			</ul>
		</div>
		<div class="span3">
		</div>
	</div>
</div>
<#include "../index/copyright.ftl">
<script type="text/javascript">
	window.PageContext = {
		fixCopyright: false,
		activeNav: 'example',
		init: function(){
			$('.partners a').height($('.partners a').width()*121/163);
		}
	};
</script>
<#include "../index/footer.ftl">