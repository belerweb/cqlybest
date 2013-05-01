<#assign ContextPath=springMacroRequestContext.getContextPath() />
<style type="text/css">
.ui-menu .ui-menu-item a.ui-state-hover, .ui-menu .ui-menu-item a.ui-state-active {
	font-weight: bold;
}
</style>
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-plus"></i></div>
				<span>增加新 产品</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form id="main-content-form" action="${ContextPath}/product/add.html" method="post" class="form-horizontal grid-content">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0);" data-toggle="tab" data-target="#product-base-tab">基本信息</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-detail-tab">介绍/描述</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-poster-tab">海报图片 </a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-photo-tab">相册图片</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-line-tab">行程地图</a></li>
				</ul>
				<div class="tab-content">
					<div id="product-base-tab" class="tab-pane active">
						<div class="row-fluid">
							<div class="span12">
								<div class="control-group">
									<label class="control-label">产品名称：</label>
									<div class="controls">
										<input type="text" class="span" name="name" required="true" maxlength="64">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">关键词/标签：</label>
									<div class="controls">
										<input type="text" class="input" id="product-keyword">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">出发城市：</label>
									<div class="controls">
										<input type="text" class="input" id="product-departure-city">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">目的地：</label>
									<div class="controls">
										<input type="text" class="input" id="product-destination">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">交通方式：</label>
									<div class="controls">
										<#list traffics as dict>
										<label class="checkbox inline"><input name="trafficIds" value="${dict.id!}" type="checkbox"> ${dict.name!}</label>
										</#list>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">产品类型：</label>
									<div class="controls">
										<#list types as dict>
										<label class="checkbox inline"><input name="productTypeIds" value="${dict.id!}" type="checkbox"> ${dict.name!}</label>
										</#list>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">产品等级：</label>
									<div class="controls">
										<#list grades as dict>
										<label class="checkbox inline"><input name="productGradeIds" value="${dict.id!}" type="checkbox"> ${dict.name!}</label>
										</#list>
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span6">
								<div class="control-group">
									<label class="control-label">行程天数：</label>
									<div class="controls">
										<input type="text" class="input-small" id="product-days" name="days" value="1" pattern="(([0-9])|([1-9]\d*))" data-validation-pattern-message="天数是非负数">
										<select name="daysUnit" class="input-small">
											<option value="d">天</option>
											<option value="m">月</option>
											<option value="y">年</option>
										</select>
									</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label class="control-label">价格：</label>
									<div class="controls">
										<div class="input-prepend">
											<span class="add-on">￥</span>
											<input type="text" class="input-small" id="product-price" pattern="(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?" data-validation-pattern-message="价格是正数，最多两位小数">
											<input type="hidden" name="price">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span12">
								<div class="control-group">
									<label class="control-label">费用说明：</label>
									<div class="controls">
										<textarea rows="3" name="priceDescription" class="span input same-height-1" maxlength="1024"></textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">推荐月份：</label>
									<div class="controls">
										<label class="checkbox inline"><input name="recommendedMonths" value="1" type="checkbox"> 1月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="2" type="checkbox"> 2月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="3" type="checkbox"> 3月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="4" type="checkbox"> 4月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="5" type="checkbox"> 5月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="6" type="checkbox"> 6月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="7" type="checkbox"> 7月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="8" type="checkbox"> 8月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="9" type="checkbox"> 9月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="10" type="checkbox"> 10月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="11" type="checkbox"> 11月</label>
										<label class="checkbox inline"><input name="recommendedMonths" value="12" type="checkbox"> 12月</label>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">适合人群：</label>
									<div class="controls">
										<label class="checkbox inline"><input name="crowds" value="1" type="checkbox"> 个人旅行</label>
										<label class="checkbox inline"><input name="crowds" value="2" type="checkbox"> 团体旅行</label>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">行程特色：</label>
									<div class="controls">
										<textarea rows="3" name="tripCharacteristic" class="span input same-height-1" maxlength="1024"></textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">服务标准：</label>
									<div class="controls">
										<textarea rows="3" name="serviceStandard" class="span input same-height-1" maxlength="1024"></textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">友情提示：</label>
									<div class="controls">
										<textarea rows="3" name="friendlyReminder" class="span input same-height-1" maxlength="1024"></textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">推荐项目：</label>
									<div class="controls">
										<textarea rows="3" name="recommendedItem" class="span input same-height-1" maxlength="1024"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="product-detail-tab" class="tab-pane">
						<div class="row-fluid">
							<div class="span12">
								<script type="text/plain" id="product-description" name="description"></script>
							</div>
						</div>
					</div>
					<div id="product-poster-tab" class="tab-pane">
						<div class="row-fluid">
							<div class="span12">
								<div class="control-group">
									<label class="control-label">图片：</label>
									<div class="controls"><button id="add-product-poster" type="button" class="btn">添加</button></div>
								</div>
							</div>
						</div>
					</div>
					<div id="product-photo-tab" class="tab-pane">
						<div class="row-fluid">
							<div class="span12">
								<div class="control-group">
									<label class="control-label">图片：</label>
									<div class="controls"><button id="add-product-photo" type="button" class="btn">添加</button></div>
								</div>
							</div>
						</div>
					</div>
					<div id="product-line-tab" class="tab-pane">
						<div class="row-fluid">
							<div class="span6">
								<div class="control-group">
									<label class="control-label">地图：</label>
									<div class="controls">
										<select name="map" class="input-small">
											<option value="baidu">百度</option>
											<option value="google">谷歌</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">行程：</label>
									<div class="controls">
										<label>
											<span class="label label-info">时间</span>
											<span> : </span>
											<span class="label label-inverse">出发地</span>
											<span> -&gt; </span>
											<span class="label label-success">目的地</span>
										</label>
									</div>
									<div class="controls">
										<input type="text" class="input-small">
										<span> : </span>
										<input type="text" class="input-small">
										<span> -&gt; </span>
										<input type="text" class="input-small">
									</div>
									<div class="controls">
										<input type="text" class="input-small">
										<span> : </span>
										<input type="text" class="input-small">
										<span> -&gt; </span>
										<input type="text" class="input-small">
									</div>
									<div class="controls">
										<input type="text" class="input-small">
										<span> : </span>
										<input type="text" class="input-small">
										<span> -&gt; </span>
										<input type="text" class="input-small">
									</div>
								</div>
							</div>
							<div class="span6">
								<div id="travel-map"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="text-center">
				<br>
				<button class="btn btn-primary" type="submit">保存</button>
			</div>
		</form>
	</div>
</div>
<script>
var destinations = ${dests!'[]'};
(function(){
	var pr  = [];
	var pn  = [];
	var pl  = [];
	var pinyin  = [];
	var py  = [];
	for(var i=0;i<destinations.length;i++) {
		var d = destinations[i];
		d.value  = d.name;
		if (pr.length>0 && d.lft>pr[pr.length-1]) {
			var c = pr.pop();
			pn.pop();
			pl.pop();
			pinyin.pop();
			py.pop();
			while(c+1<d.lft) {
				c = pr.pop();
				pn.pop();
				pl.pop();
				pinyin.pop();
				py.pop();
			}
		}
		if (pr.length==0) {
			d.fullname = d.name;
			d.label = d.name;
		} else {
			d.fullname = pn[pn.length-1] + d.name;
			d.label = pl[pl.length-1] + '/' + d.name;
			d.pinyin = pinyin[pinyin.length-1] + d.pinyin;
			d.py = py[py.length-1] + d.py;
		}
		if(d.rgt-d.lft>1) {
			pr.push(d.rgt);
			pn.push(d.fullname);
			pl.push(d.label);
			pinyin.push(d.pinyin);
			py.push(d.py);
		}
	}
})();
$('#product-keyword').cqlybestTag({
	hiddenTagListName: 'keywordIds',
	typeahead: true,
	typeaheadAjaxSource: '/data/dict.html?action=dict&type=keyword',
	typeaheadAjaxPolling: true,
	AjaxPush: '/data/dict/add_keyword.html'
});
$('#product-departure-city').cqlybestTag({
	hiddenTagListName: 'departureCityIds',
	typeahead: true,
	typeaheadAjaxSource: '/data/dict.html?action=dict&type=departure-city',
	typeaheadAjaxPolling: true,
	AjaxPush: '/data/dict/add_departure_city.html'
});
$('#product-destination').cqlybestTag({
	hiddenTagListName: 'destIds',
	typeahead: true,
	typeaheadAjaxSource: '/data/dict.html?action=dict&type=destination',
	typeaheadAjaxPolling: true
});
$('select', '#main-content-form').selectBoxIt({autoWidth:false});
UE.delEditor('product-description');
var pdEditor = UE.getEditor('product-description', {
	initialContent: '',
	initialFrameWidth: '100%'
});
$('#add-product-poster').click(function(){
	var controlGroup = $(this).parents('.control-group');
	var html =  ['<idv class="control-group"><label class="control-label"><button type="button" class="btn del">删除</button></label>'];
	html.push('<div class="controls"><input type="file" class="input"></div></div>');
	var newControlGroup = $(html.join(''));
	$('.del', newControlGroup).click(function(){
		$(this).parents('.control-group').remove();
	});
	$('input[type=file]', newControlGroup).change(function(){
		$(this).attr('name', '_posters');
	});
	controlGroup.after(newControlGroup);
});
$('#add-product-photo').click(function(){
	var controlGroup = $(this).parents('.control-group');
	var html =  ['<idv class="control-group"><label class="control-label"><button type="button" class="btn del">删除</button></label>'];
	html.push('<div class="controls"><input type="file" class="input"></div></div>');
	var newControlGroup = $(html.join(''));
	$('.del', newControlGroup).click(function(){
		$(this).parents('.control-group').remove();
	});
	$('input[type=file]', newControlGroup).change(function(){
		$(this).attr('name', '_photos');
	});
	controlGroup.after(newControlGroup);
});
$('input,textarea,select', '#main-content-form').jqBootstrapValidation({
	submitSuccess : function($form, event) {
		event.preventDefault();
		event.stopPropagation();
		pdEditor.sync();
		// 天数
		if (isNaN(parseFloat($('#product-days').val()))) {
			$('#product-days').attr('name', null);
		} else {
			$('#product-days').attr('name', 'days');
		}
		// 价格
		var price = parseFloat($('#product-price').val());
		if (isNaN(price)) {
			$('#product-price').next().attr('name', null);
		} else {
			$('#product-price').next().attr('name', 'price').val(price*100);
		}
		$form.ajaxSubmit({
			success : function(response) {
				cqlybest.success();
			},
			error : function() {
				cqlybest.error();
			}
		});
	}
});
</script>