<#macro renderSections sections location>
	<div class="row-fluid">
		<h5 class="header smaller lighter green" style="margin-top:0;">
			增加内容
			<button type="button" class="btn btn-mini btn-primary pull-right" data-action="expand">展开/收缩</button>
		</h5>
		<form method="post" action="${ContextPath}/www/index/section/add.do" class="form-horizontal hide" data-action="add">
			<input name="in" type="hidden" value="${location}">
			<div class="control-group">
				<label class="control-label">名称:</label>
				<div class="controls">
					<input name="name" type="text" placeholder="如：热门产品">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">类型:</label>
				<div class="controls">
					<select name="type">
						<option value="">请选择类型</value>
						<option value="code">HTML代码</value>
						<option value="img">图片链接</value>
						<option value="product">产品信息</value>
						<option value="maldives">马代海岛信息</value>
					</select>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="code">
				<label class="control-label">代码:</label>
				<div class="controls">
					<textarea name="code"></textarea>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="img">
				<label class="control-label">图片:</label>
				<div class="controls">
					<input name="img" type="text" readonly="readonly" placeholder="">
					<button type="button" class="btn btn-info btn-mini" data-action="upload">上传图片</button>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="img">
				<label class="control-label">图片标题:</label>
				<div class="controls">
					<input name="img.title" type="text" placeholder="">
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="img">
				<label class="control-label">图片描述:</label>
				<div class="controls">
					<textarea name="img.description"></textarea>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="img">
				<label class="control-label">图片链接地址:</label>
				<div class="controls">
					<input name="img.url" type="text" placeholder="">
					<input name="img.target" type="checkbox" value="_blank">
					<label class="lbl"> 新窗口打开链接</label>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="product">
				<label class="control-label">类型:</label>
				<div class="controls">
					<select name="pc.type.value">
						<option value=""></option>
						<option value="maldives">马尔代夫</option>
					</select>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="product">
				<label class="control-label">名称:</label>
				<div class="controls">
					<select name="pc.name.type">
						<option value="1">包含</option>
						<option value="0">等于</option>
					</select>
					<input name="mdc.name.value" type="text" placeholder="">
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="product">
				<label class="control-label">热门:</label>
				<div class="controls">
					<select name="pc.popular.value">
						<option value=""></option>
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="product">
				<label class="control-label">推荐:</label>
				<div class="controls">
					<select name="pc.recommend.value">
						<option value=""></option>
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="product">
				<label class="control-label">特价:</label>
				<div class="controls">
					<select name="pc.special.value">
						<option value=""></option>
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="product">
				<label class="control-label">出发城市:</label>
				<div class="controls">
					<input name="pc.dcity.value" type="text" placeholder="">
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="product">
				<label class="control-label">推荐月份:</label>
				<div class="controls">
					<input name="pc.rmonth.value" type="text" placeholder="">
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="maldives">
				<label class="control-label">岛屿级别:</label>
				<div class="controls">
					<select name="mdc.level.type">
						<option value="0">等于</option>
						<option value="1">包含</option>
					</select>
					<input name="mdc.level.value" type="text" placeholder="">
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="maldives">
				<label class="control-label">酒店星级:</label>
				<div class="controls">
					<select name="mdc.hotelLevel.type">
						<option value="0">等于</option>
						<option value="2">大于</option>
						<option value="3">小于</option>
					</select>
					<select name="mdc.hotelLevel.value">
						<option value="">选择</option>
						<#list 1..10 as i>
						<option value="${i}">${i}</option>
						</#list>
					</select>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="product maldives">
				<label class="control-label">显示数量:</label>
				<div class="controls">
					<input name="number" type="text" value="0">
					<span class="help-inline">正整数，0 表示所有满足条件的数据</span>
				</div>
			</div>
			<div class="control-group hide" data-select="type" data-for="product maldives">
				<label class="control-label">显示［更多］链接:</label>
				<div class="controls">
					<input name="more" type="checkbox" value="true">
					<label class="lbl"> 是</label>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn btn-primary">确定</button>
				</div>
			</div>
		</form>
	</div>
	<#list sections as section>
	<div class="row-fluid">
		<h5 class="header smaller lighter green" style="margin-top:0;">
			${section.name!'未设置名称'}
			<button type="button" class="btn btn-mini btn-primary pull-right" data-action="expand">展开/收缩</button>
		</h5>
		<form method="post" class="form-horizontal hide" data-action="update"
			data-update="${ContextPath}/www/index/section/${section.type}/update.do"
			data-delete="${ContextPath}/www/index/section/delete.do">
			<input name="in" type="hidden" value="${location}">
			<input name="sectionId" type="hidden" value="${section.id}">
			<div class="control-group">
				<label class="control-label">名称:</label>
				<div class="controls">
					<input name="name" type="text" placeholder="如：热门产品" value="${section.name!}">
				</div>
			</div>
			<#if section.type=="code">
			<div class="control-group">
				<label class="control-label">代码:</label>
				<div class="controls">
					<textarea name="code">${section.code!}</textarea>
				</div>
			</div>
			</#if>
			<#if section.type=="img">
			<div class="control-group">
				<label class="control-label">图片:</label>
				<div class="controls">
					<input name="img" type="text" readonly="readonly" placeholder="" value="${(section.img.id)!}.${(section.img.extension)!}">
					<button type="button" class="btn btn-info btn-mini" data-action="upload">上传图片</button>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">图片标题:</label>
				<div class="controls">
					<input name="img.title" type="text" placeholder="" value="${(section.img.title)!}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">图片描述:</label>
				<div class="controls">
					<textarea name="img.description">${(section.img.description)!}</textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">图片链接地址:</label>
				<div class="controls">
					<input name="img.url" type="text" placeholder="" value="${(section.img.url)!}">
					<input name="img.target" type="checkbox" <#if (section.img.target)?has_content && section.img.target=="_blank">checked="checked"</#if> value="_blank">
					<label class="lbl"> 新窗口打开链接</label>
				</div>
			</div>
			</#if>
			<#if section.type=="product">
			<div class="control-group">
				<label class="control-label">类型:</label>
				<div class="controls">
					<select name="pc.type.value">
						<option value=""></option>
						<option <#if (section.pc.type.value)?exists && section.pc.type.value=='maldives'>selected="selected"</#if> value="maldives">马尔代夫</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">名称:</label>
				<div class="controls">
					<select name="pc.name.type">
						<option <#if (section.pc.name.type)?exists && section.pc.name.type==1>selected="selected"</#if> value="1">包含</option>
						<option <#if (section.pc.name.type)?exists && section.pc.name.type==0>selected="selected"</#if> value="0">等于</option>
					</select>
					<input name="mdc.name.value" type="text" value="${(section.pc.name.value)!}" placeholder="">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">热门:</label>
				<div class="controls">
					<select name="pc.popular.value">
						<option value=""></option>
						<option <#if (section.pc.popular.value)?exists && section.pc.popular.value>selected="selected"</#if> value="true">是</option>
						<option <#if (section.pc.popular.value)?exists && !section.pc.popular.value>selected="selected"</#if> value="false">否</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">推荐:</label>
				<div class="controls">
					<select name="pc.recommend.value">
						<option value=""></option>
						<option <#if (section.pc.recommend.value)?exists && section.pc.recommend.value>selected="selected"</#if> value="true">是</option>
						<option <#if (section.pc.recommend.value)?exists && !section.pc.recommend.value>selected="selected"</#if> value="false">否</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">特价:</label>
				<div class="controls">
					<select name="pc.special.value">
						<option value=""></option>
						<option <#if (section.pc.special.value)?exists && section.pc.special.value>selected="selected"</#if> value="true">是</option>
						<option <#if (section.pc.special.value)?exists && !section.pc.special.value>selected="selected"</#if> value="false">否</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">出发城市:</label>
				<div class="controls">
					<input name="pc.dcity.value" type="text" value="${(section.pc.departureCity.value)!}" placeholder="">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">推荐月份:</label>
				<div class="controls">
					<input name="pc.rmonth.value" type="text" value="${(section.pc.recommendedMonth.value)!}" placeholder="">
				</div>
			</div>
			</#if>
			<#if section.type=="maldives">
			<div class="control-group">
				<label class="control-label">岛屿级别:</label>
				<div class="controls">
					<select name="mdc.level.type">
						<option <#if (section.mdc.level.type)?exists && section.mdc.level.type==0>selected="selected"</#if> value="0">等于</option>
						<option <#if (section.mdc.level.type)?exists && section.mdc.level.type==1>selected="selected"</#if> value="1">包含</option>
					</select>
					<input name="mdc.level.value" type="text" value="${(section.mdc.level.value)!}" placeholder="">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">酒店星级:</label>
				<div class="controls">
					<select name="mdc.hotelLevel.type">
						<option <#if (section.mdc.hotelLevel.type)?exists && section.mdc.hotelLevel.type==0>selected="selected"</#if> value="0">等于</option>
						<option <#if (section.mdc.hotelLevel.type)?exists && section.mdc.hotelLevel.type==2>selected="selected"</#if> value="2">大于</option>
						<option <#if (section.mdc.hotelLevel.type)?exists && section.mdc.hotelLevel.type==3>selected="selected"</#if> value="3">小于</option>
					</select>
					<select name="mdc.hotelLevel.value">
						<option value="">选择</option>
						<#list 1..10 as i>
						<option <#if (section.mdc.hotelLevel.value)?exists && section.mdc.hotelLevel.value==i>selected="selected"</#if> value="${i}">${i}</option>
						</#list>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">显示数量:</label>
				<div class="controls">
					<input name="number" type="text" value="${section.number!'0'}">
					<span class="help-inline">正整数，0 表示所有满足条件的数据</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">显示［更多］链接:</label>
				<div class="controls">
					<input name="more" type="checkbox" <#if section.more?exists && section.more>checked="checked"</#if> value="true">
					<label class="lbl"> 是</label>
				</div>
			</div>
			</#if>
			<div class="control-group">
				<div class="controls">
					<button type="button" class="btn btn-primary" data-action="update">确定</button>
					<button type="button" class="btn btn-danger" data-action="delete">删除</button>
				</div>
			</div>
		</form>
	</div>
	</#list>
</#macro>