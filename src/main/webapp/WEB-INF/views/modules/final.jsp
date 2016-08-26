<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <meta charset="UTF-8">
    <title>监控系统</title>
    <script src="static/jquery/jq.js"></script>
</head>
<body>
<video width="224" height="160" autoplay></video>
<canvas width="224" height="160"></canvas>
<canvas width="224" height="160"></canvas>

<audio src="alarm.wav"></audio>
<script>
    var video = document.querySelector('video');
    var audio = document.querySelector('audio');
    var canvas = document.querySelectorAll('canvas')[0];
    var canvasForDiff = document.querySelectorAll('canvas')[1];

    // video捕获摄像头画面
    		videoObj = { "video": true },
		errBack = function(error) {
			console.log("Video capture error: ", error.code); 
		};

	// Put video listeners into place
	if(navigator.getUserMedia) { // Standard
		navigator.getUserMedia(videoObj, function(stream) {
			video.src = stream;
			video.play();
		}, errBack);
	} else if(navigator.webkitGetUserMedia) { // WebKit-prefixed
		navigator.webkitGetUserMedia(videoObj, function(stream){
			video.src = window.webkitURL.createObjectURL(stream);
			video.play();
		}, errBack);
	}
	else if(navigator.mozGetUserMedia) { // Firefox-prefixed
		navigator.mozGetUserMedia(videoObj, function(stream){
			video.src = window.URL.createObjectURL(stream);
			video.play();
		}, errBack);
	}

    //canvas
    var context = canvas.getContext('2d'),
        diffCtx = canvasForDiff.getContext('2d');
    //将第二个画布混合模式设为“差异”
    diffCtx.globalCompositeOperation = 'difference';

    var preFrame,   //前一帧
        curFrame;   //当前帧

    var diffFrame;  //存放差异帧的imageData

    //捕获并保存帧内容
    function captureAndSaveFrame(){
        preFrame = curFrame;
        context.drawImage(video, 0, 0, 224, 160);
        curFrame = canvas.toDataURL();  //转为base64并保存
    }

    //绘制base64图像到画布上
    function drawImg(src, ctx){
        ctx = ctx || diffCtx;
        var img = new Image();
        img.src = src;
        ctx.drawImage(img, 0, 0, 224, 160);
    }

    //渲染前后两帧差异
    function renderDiff(){
        diffCtx.clearRect(0, 0, 224, 160);
        drawImg(preFrame);
        drawImg(curFrame);
        diffFrame = diffCtx.getImageData( 0, 0, 224, 160 );  //捕获差异帧的imageData对象
    }

    //计算差异
    function calcDiff(){
        if(!diffFrame) return 0;
        var cache = arguments.callee,
            count = 0;
        cache.total = cache.total || 0; //整个画布都是白色时所有像素的值的总和
        for (var i = 0, l = diffFrame.width * diffFrame.height * 4; i < l; i += 4) {
            count += diffFrame.data[i] + diffFrame.data[i + 1] + diffFrame.data[i + 2];
            if(!cache.isLoopEver){  //只需在第一次循环里执行
                cache.total += 255 * 3;   //单个白色像素值
            }
        }
        cache.isLoopEver = true;
        count *= 3;  //亮度放大
        //返回“差异画布高亮部分像素总值”占“画布全亮情况像素总值”的比例
        return Number(count/cache.total).toFixed(2);
    }

    //播放音频
    function fireAlarm(){
        audio.play()
    }


    //定时捕获
    function timer(delta){
        setTimeout(function(){
            captureAndSaveFrame();
            if(preFrame && curFrame){
                renderDiff();
				 //发日记
                    submit();
                    //播放音频告警
                    fireAlarm();
                if(calcDiff() > 0.2){  //监控到异常
                    //发日记
                    submit();
                    //播放音频告警
                    fireAlarm();
                }
            }
            timer(delta)
        }, delta || 500);
    }

    setTimeout(timer, 1000);  //设定打开页面十分钟后才开始监控

	var i = 0;
    //异常图片上传处理
    function submit(){
        var cache = arguments.callee,
            now = Date.now();
        if(cache.reqTime && (now - cache.reqTime < 5000)) return;  //日记创建最小间隔为5秒

        cache.reqTime = now;

        if( i == 3){
        	// console.log(curFrame)
        	 //ajax 提交form
             $.ajax({
                url : '/demomanagement/img',
                type : "POST",
                timeout : 5000,
                data : {
                    'imgId': curFrame,
                },
                success: function(){
                    console.log('submit done')
                },
                error: function(err){
                    cache.reqTime = 0;
                    console.log('error: ' + err)
                }
            }); 
        }
        i++;
       
    }

</script>
</body>
</html>