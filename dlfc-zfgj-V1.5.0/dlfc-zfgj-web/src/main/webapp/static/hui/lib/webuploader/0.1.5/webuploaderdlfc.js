function initUploader(uploader, imgW, imgH, location){  
	    	var $btn = $('#ctlBtn');
	    	var $state = "pending";
	    	
	    	uploader.on( 'beforeFileQueued', function( file ) {
	    		uploader.reset();
	    	});
	    	// 当有文件添加进来的时候
	    	uploader.on( 'fileQueued', function( file ) {
	    	    var $li = $(
	    	            '<div id="' + file.id + '" class="file-item thumbnail">' +
	    	                '<img>' +
	    	                '<div class="info" title=" '+ file.name +' ">' + file.name + '</div>' +
	    	            '</div>'
	    	            ),
	    	        $img = $li.find('img');
	    	    $(location).html( $li );
	    	    uploader.makeThumb( file, function( error, src ) {
	    	        if ( error ) {
	    	            $img.replaceWith('<span>不能预览</span>');
	    	            return;
	    	        }

	    	        $img.attr( 'src', src );
	    	    }, imgW, imgH);
	    	    
	    	});

	    	uploader.on('error', function(type){
	    		if(type=='Q_TYPE_DENIED'){
	    			layer.alert("上传类型有错");
	    		}
	    		if(type=='Q_EXCEED_SIZE_LIMIT'){
	    			layer.alert("上传文件尺寸过大");
	    		}
	        });
	    	

	    	uploader.on( 'uploadError', function( file ) {
	    		layer.alert("上传出错");
	    	});
} 