<!DOCTYPE html>
<html lang="zh">
	<#assign ContextPath=springMacroRequestContext.getContextPath() />
	<head>
		<meta charset="utf-8">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<link href="${ContextPath}/assets/v1/css/bootstrap.css" rel="stylesheet">
		<link href="${ContextPath}/assets/v1/css/jquery.plupload.queue.css" rel="stylesheet">
	</head>
	<body>
		<div id="uploader"></div>
		<div class="text-center"><button id="callback-btn" type="button" class="btn btn-primary">确定</button></div>
		<script type="text/javascript" src="${ContextPath}/assets/v1/js/jquery.js"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v1/js/plupload.js"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v1/js/plupload.html4.js"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v1/js/jquery.plupload.queue.js"></script>
		<script type="text/javascript">
		var files = [];
		plupload.addI18n({
			'Select files' : '选择文件',
			'Add files to the upload queue and click the start button.' : '添加文件到上传队列然后点击开始按钮',
			'Filename' : '文件名',
			'Status' : '状态',
			'Size' : '尺寸',
			'Add Files' : '添加文件',
			'Stop Upload': '停止上传',
			'Start Upload': '开始上传',
			'Add files': '添加文件',
			'Stop current upload': '停止当前上传',
			'Start uploading queue': '开始上传队列',
			'Stop upload': '停止上传',
			'Start upload': '上传',
			'Uploaded % d / % d files': '已上传 % d / % d 文件',
			'N / A': 'N / A',
			'Drag files here': '拖拽文件到这儿',
			'File extension error': '文件扩展名错误',
			'File size error': '文件尺寸错误',
			'File count error': '文件数量错误',
			'Init error': '初始化错误',
			'HTTP Error': '网络错误',
			'Security error': '安全错误',
			'Generic error': '一般错误',
			'IO error': 'IO 错误',
			'File : % s': '文件 % s',
			'Close': '关闭',
			'% d files queued': '已排队 % d 文件',
			'Using runtime :': '运行时',
			'File : % f, size : % s, max file size : % m': '文件 : % f, 尺寸 : % s, 最大文件尺寸 : % m',
			'Upload element accepts only % d file (s) at a time. Extra files were stripped': '一次接受 % d 个文件。多余文件将被忽略。',
			'Upload URL might be wrong or doesn \'t exist ':'上传地址错误或不存在',
			'Error : File too large :': '错误 : 文件太大',
			'Error: Invalid file extension:': '错误：不允许地文件类型：'
		});
		$('#uploader').pluploadQueue({
			multipart: true,
			runtimes : 'html4',
			url : '${ContextPath}/image/upload',
			filters : [{title : '图片', extensions : 'jpg,gif,png'}],
			init : {
				FileUploaded: function(up, file, info) {
					files.push($.parseJSON(info.response));
				}
			}
		});
		$('#callback-btn').click(function(){
			window.returnValue = files;
			window.close();
		});
		</script>
	</body>
</html>
