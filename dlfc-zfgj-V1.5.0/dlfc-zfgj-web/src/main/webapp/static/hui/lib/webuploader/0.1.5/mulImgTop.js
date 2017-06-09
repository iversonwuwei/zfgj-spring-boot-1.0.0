/**
	 选择图片并且预览(此方法专为出租信息可选首图准备)
	 @param picker 选择文件按钮的ID
	 @param servURL 图片上传服务
	 @param swfPath 参数swf文件路径
	 @param _fileNumLimit 预览区图片限制
	 @param fn 上传图片后的自定义处理
	 @param fnDel 删除图片后的自定义处理
	*/
	function mulImagesInit(servURL,idx,_fileNumLimit,swfPath,fn,fnDel)
	{
		try {
			var $ = jQuery,
			$wrap = $('#uploader'+idx),
				$list = $('#queueList'+idx),
				// 优化retina, 在retina下这个值是2
				ratio = window.devicePixelRatio || 1,

				// 缩略图大小
				thumbnailWidth = 110 * ratio,
				thumbnailHeight = 110 * ratio,

				// Web Uploader实例
				imgUploader;
			
			var $queue = $wrap.find('#queueList'+idx).find(".filelist");
				if ($queue.length == 0) {
					$queue = $('<ul class="filelist"></ul>').appendTo( $wrap.find('#queueList'+idx) );
				}
				
			// 初始化Web Uploader
			imgUploader = WebUploader.create({
				// 自动上传。
				auto: true,
				// swf文件路径
				swf: swfPath,
				// 文件接收服务端。
				server:  servURL+'/upload/uploadHeadPic/',
				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick: {id: '#filePicker'+idx, innerHTML: '选择图片'},
				fileNumLimit: _fileNumLimit,
				// 只允许选择文件，可选。
				accept: {
		            title: 'Images',
		            extensions: 'gif,jpg,jpeg,bmp,png',
		            mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'
		        },
				fileSingleSizeLimit: 4 * 1024 * 1024
			});

			// 当有文件添加进来的时候
			imgUploader.on( 'fileQueued', function( file ) {
				var exitImgs=$list.find('img');
				var len=exitImgs.size();
				if(_fileNumLimit==len)
				{
					layer.alert("图片最多"+_fileNumLimit+"张!");
					return false;
				}
				var formatId = "\'" + file.id + "\'";
				var $li = $( '<li id="' + file.id + '" class="filelist">' +
						'<p class="moreInfo"><input type="hidden" name="pics'+idx+'"></p>'+
						'<p class="progress"><span></span></p>' +
						'</li>' ),

					$btns = $('<div class="file-panel">' +
						'<span class="cancel">删除</span>' +
						'</div>').appendTo( $li );
				var idFlag = idx + "";
				if (idFlag == "" || idFlag == undefined || idFlag == null) {
					img = $('<img src="" onclick="doTop('+formatId+')">').appendTo( $li );
				} else {
					img = $('<img src="">').appendTo( $li );
				}
				if (len == 0) {
					$('<span class="top"></span>').appendTo( $li );
				}
				var $img = $li.find('img');
			
				$list.append( $li );
				
				var $a=$li.find('span');
				$a.click(function(){
					var f=$(this).parent().parent();
					var id=f.attr('id');
					imgUploader.removeFile( id );
					f.remove();
					if (fnDel) {
						fnDel($list);
					}
				});
				if (fn) {
					fn($list);
				}
				
				$li.on( 'mouseenter', function() {
					$btns.stop().animate({height: 30});
				});

				$li.on( 'mouseleave', function() {
					$btns.stop().animate({height: 0});
				});
				
				
				
				// 创建缩略图
				imgUploader.makeThumb( file, function( error, src ) {
					if ( error ) 
					{
						$img.replaceWith('<span>不能预览</span>');
						return;
					}

					$img.attr( 'src', src );
				}, thumbnailWidth, thumbnailHeight );
				
				$li.appendTo( $queue );
			});

			// 文件上传过程中创建进度条实时显示。
			imgUploader.on( 'uploadProgress', function( file, percentage ) {
				var $li = $( '#'+file.id ),
					$percent = $li.find('.progress span');

				// 避免重复创建
				if ( !$percent.length ) 
				{
					$percent = $('<p class="progress"><span></span></p>').appendTo( $li ).find('span');
				}

				$percent.css( 'width', percentage * 100 + '%' );
			});

		
			//当validate不通过时，会以派送错误事件的形式通知调用者
			imgUploader.on('error', function(type){
				if(type=='Q_EXCEED_SIZE_LIMIT')
				{
					layer.alert("上传文件尺寸过大");
				}
				if(type=='Q_EXCEED_NUM_LIMIT ')
				{
					layer.alert("上传文件最多"+_fileNumLimit+"个");
				}
			});

			// 文件上传失败，现实上传出错。
			imgUploader.on( 'uploadError', function( file ) {
				var $li = $( '#'+file.id ),
					$error = $li.find('div.error');

				// 避免重复创建
				if ( !$error.length ) 
				{
					$error = $('<div class="error"></div>').appendTo( $li );
				}

				$error.text('上传失败');
			});

			// 完成上传完了，成功或者失败，先删除进度条。
			imgUploader.on( 'uploadComplete', function( file ) {
				$( '#'+file.id ).find('.progress').remove();
			});
			
			imgUploader.on( 'uploadSuccess', function( file ) {
				if(arguments[1].success == 0){
					layer.alert(arguments[1].message);
				}else if(arguments[1].success == 1){//上传临时文件夹成功后的操作写在这里 返回filepath和filename
					//layer.alert(arguments[1].data.filepath);
					$("#"+file.id).find("input[name='pics"+idx+"']").val(arguments[1].data.filepath);
					if ($("#"+file.id).find("span.top").length == 1) {
						$("#mainimgId_path").val(arguments[1].data.filepath);
					}
				}
			});
		} catch(e) {
			$("#filePicker").on("click", function(){
				try{
					var swf1 = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
				} catch(e) {
					layer.alert("未找到 Flash 播放器，会导致选择图片功能失效！请升级或重新安装。3秒后自动打开Adobe Flash Player官方下载页面。");
					setTimeout(function(){
						window.open("https://get.adobe.com/flashplayer/?loc=cn");
					}, 3000);
				}
			})
		}
		
		return imgUploader;
	}
	
	function doTop(ss) {
		$("ul.filelist").find("span.top").remove();
		$("#"+ss).append('<span class="top"></span>');
		//$("#"+ss).find("[name=pics]").addClass("lol");
		$("#mainimgId_path").val($("#"+ss).find("[name=pics]").val());
	}
