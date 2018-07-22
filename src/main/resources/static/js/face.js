/**
 * Created by linxins
 * node sdk 浜鸿劯璇嗗埆澶ф鎬濊矾
 *
 * 1.鍓嶇椤甸潰
 *    1.js璋冪敤鏈湴鎽勫儚澶达紝鑾峰彇瑙嗛娴�  video
 *       getUserMedia
 *    2.瑙嗛娴佽В鐮侊紝鐢╲ideo灞曠ず鍑烘潵 file  blob
 *       window.URL.createObjectURL(buffer)
 *    3.canvas鎴彇video瑙嗛娴佺敓鎴愬浘鐗�
 *       鍒涘缓canvas瀵硅薄锛岀粯鍒舵埅鍙栧浘鐗�
 *    4.鍥剧墖鐢熸垚base64缂栫爜
 *       cxt.drawImage    canvas.toDataURL
 *    5.base64浼犺緭鍒板悗鍙�
 *    6.鍙嶉淇℃伅浜や簰
 *
 * 2.鍚庣瑙ｆ瀽
 *    1.鑾峰彇鍓嶇浼犲叆鐨勫浘鍍忔暟鎹�
 *       faceData:宸︿晶鍥剧墖鏁版嵁  scanData:鍙充晶鎵弿鏁版嵁
 *    2.璋冪敤 baidu-ai 澶勭悊浜鸿劯瀵规瘮鏁版嵁
 *    3.杩斿洖 score 缁欏墠绔�
 *       {"result":[{"index_i":"0","index_j":"1","score":92.184844970703}],"result_num":1,"log_id":2309353456092517}
 */

var Util = {
    _$: function (id) {
        return document.getElementById(id);
    },
    ajax: function (options) {
        if (!options || !options.url) {
            throw new Error('request url not empty');
        }
        options.method = options.method || 'POST';
        // 鍒涘缓璇锋眰xhr瀵硅薄url, success
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState === 4) {
                if (this.status === 200) {
                    // 鎴愬姛鍥炶皟
                    typeof options.success === 'function' && options.success(JSON.parse(this.responseText))
                } else {
                    typeof options.error === 'function' && options.error(this.responseText)
                }
            }
        }
        // 鎵撳紑杩炴帴 true: 寮傛
        xhr.open(options.method, options.url, true);
        // 寮€濮嬪彂閫�
        console.log(options)
        xhr.send(options.data);
    }
}

var video = document.querySelector('video'); // 瑙嗛缁勪欢
var buffer; // 瑙嗛娴佺紦鍐插尯

/**
 * 鍩轰簬 querySelectorAll鐨勪簨浠剁粦瀹氾紝浠� Array.prototype涓壗绐冧簡 forEach 鏂规硶鏉ュ畬鎴愰亶鍘�
 * 鎸夐挳鐩戝惉锛岃缃簨浠朵唬鐞�
 */
Array.prototype.forEach.call(document.querySelectorAll('.control-btn'), function (el) {
    el.addEventListener('click', function (e) {
        e = e || window.event;
        var id = e.target.id;
        switch (id) {
            case 'open': // 寮€鍚憚鍍忓ご
                openCamera();
                break;
            case 'close': // 鍏抽棴鎽勫儚澶�
                closeCamera();
                break;
            case 'scanface': // 浜鸿劯璇嗗埆
                scanFace();
                break;
            default:
                break;
        }
    })
});

/**
 * 寮€鍚憚鍍忓ご
 * 鐩稿簲h5 API 鏌ョ湅鍦板潃
 * https://developer.mozilla.org/en-US/docs/Web/API/Navigator/getUserMedia
 */
function openCamera() {
    console.log("打开摄像头");
    // 鍏煎鎬у鐞�
    navigator.getUserMedia = navigator.getUserMedia ||
        navigator.webkitGetUserMedia ||
        navigator.mozGetUserMedia;
    if (navigator.getUserMedia) {
        navigator.getUserMedia({ audio: true, video: { width: 300, height: 300 } },
            function(stream) {
                buffer = stream;
                var src = window.URL && window.URL.createObjectURL(buffer) || stream;
                video.muted = true; // 璁剧疆瑙嗛闈欓煶
                video.src = src;
                video.onloadedmetadata = function(e) {
                    video.play();
                };
            },
            function(err) {
                alert("出错");
                console.log("出错" + err.name);
            }
        );
    } else {
        console.log('getUserMedia not supported');
        alert('运行环境不支持');
    }
}

/**
 * 寮€濮嬩汉鑴歌瘑鍒�
 * 灏嗚棰戞埅灞忥紝鐢╟anvas鏄剧ず锛岀劧鍚庡啀杞负base64鐮佷紶缁欏悗鍙�
 * ps: 杩欓噷鐩镐技搴﹂粯璁ゅ湪 90% 浠ヤ笂灏辩畻鐫€璇嗗埆鎴愬姛锛屽彲璁剧疆绮惧噯搴︺€傛渶楂� 100% 鏈€浣� 80%
 */
function scanFace() {
    console.log('U包人脸识别');
    if (!buffer) {
        alert('您的摄像头未打开');
        console.log('您的摄像头未打开');
        return
    }

    // 绮惧噯搴�
    var accuracy = Util._$('accuracy').value;

    // 瑙嗛娴佽В鐮侊紝鐢╲ideo灞曠ず鍑烘潵 file  blob
    var oCanvas = Util._$('canvas_l');
   /* var oCanvas2 = Util._$('canvas_2');*/
    var ctx = oCanvas.getContext('2d');
    ctx.drawImage(video, 0, 0, 300, 230); // 瀹為檯涓婃槸瀵硅棰戞埅灞�
    var faceData = Util._$('facedata').src;
    console.log("faceData:" + faceData);
    var load = new Loading();
    load.show();
    $.ajax({
        method: 'POST',
        url: 'http://aiface.linxins.com/scanface',
        data: {
            faceData: /^data:image\/(\w*);base64,/.test(faceData) ? faceData : null,
            /*faceData: oCanvas2.toDataURL(),*/
            scanData: oCanvas.toDataURL()
        },
        success: function (res) {
            console.log(res);

            load.hide();
            res.score > (!isNaN(Number(accuracy)) ? accuracy : 70) ? changeStatus('success') : changeStatus('error');
            console.log("相似度:" + res.score);
            alert(res.score);
            window.location.href="http://115.159.71.92/hongruan/web/faceCheck?checkResult=" + res.score + "&accuracy=" + accuracy;
        },
        error: function (res) {
            console.log(res);
            load.hide();
        }
    })
}

/**
 * 鏍规嵁鐘舵€佸垽鏂娴嬪浘鐗囨槸鍚︽垚鍔�
 * @param status
 */
function changeStatus (status) {
    var iconDom = document.querySelector('.icon');
    iconDom.src = "http://aiface.linxins.com/static/images/" + (!status && 'prompt' || status) + ".png";
    /*iconDom.src = "http://aiface.linxins.com/static/images/${!status && 'prompt' || status}.png";*/
    iconDom.classList.remove('animation');
    setTimeout(function () {
        iconDom.classList.add('animation');
    }, 200)
}

/**
 * 关闭摄像头
 */
function closeCamera() {
    console.log("关闭摄像头");
    buffer && buffer.getVideoTracks()[0].stop(); // 鏆傚仠褰撳墠鎾斁鐨勯煶棰�/瑙嗛
    buffer = null;
    changeStatus();
}

/**
 * 閫夋嫨涓婁紶鍥剧墖銆�
 * 鍏跺疄骞舵病鏈夊悜鏈嶅姟鍣ㄤ笂浼犱换浣曟枃浠讹紝鍙槸鍒╃敤浜咹5鐨刟pi灏嗗浘鐗囪浆涓篵ase64锛岀劧鍚庣粰瑕佹樉绀虹殑image鐨剆rc璧嬪€间负杞寲鍚庣殑base64鐮�
 * @returns {boolean}
 */
Util._$('uploadfile').onchange = function () {
    var file = this.files && this.files[0]/*document.getElementById("uploadfile")*/;
    //鍒ゆ柇鏄惁鏄浘鐗囩被鍨�
    if(!/image\/(png|jpg|jpeg)/.test(file.type)){
        this.value = null;
        alert("请选择图片格式文件");
        return false;
    }
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e){
        Util._$('facedata').src = this.result;
    }
}

/*window.onload = function(){
   /!* var url="http://localhost/hongruan/images/787015807@qq.com.png";
    getBase64(url)
        .then(function(base64){
            console.log(base64);//处理成功打印在控制台
            Util._$('facedata').src = base64;
        },function(err){
            console.log(err);//打印异常信息
        });*!/
    var oCanvas2 = Util._$('canvas_2');
    var ctx = oCanvas2.getContext('2d');
    var pic = new Image();
    pic.src = "http://localhost/hongruan/images/787015807@qq.com.jpg";
    ctx.drawImage(pic, 100, 100, 300, 300); // 瀹為檯涓婃槸瀵硅棰戞埅灞�
}*/


//实现将项目的图片转化成base64
function getBase64(img){
    function getBase64Image(img,width,height) {//width、height调用时传入具体像素值，控制大小 ,不传则默认图像大小
        var canvas = document.createElement("canvas");
        canvas.width = width ? width : img.width;
        canvas.height = height ? height : img.height;

        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
        var dataURL = canvas.toDataURL();
        return dataURL;
    }
    var image = new Image();
    image.crossOrigin = '';
    image.src = img;
    var deferred=$.Deferred();
    if(img){
        image.onload =function (){
            deferred.resolve(getBase64Image(image));//将base64传给done上传处理
        }
        return deferred.promise();//问题要让onload完成后再return sessionStorage['imgTest']
    }
}

/**
 * 璇锋眰姝ｅ湪鍔犺浇涓€傘€傘€�
 * @constructor
 */
function Loading() {
    var loadingDiv, queryDom = document.querySelector('.loading');
    if (queryDom) {
        loadingDiv = queryDom;
    } else {
        loadingDiv = document.createElement('div');
        loadingDiv.classList.add('loading');
        var loadingImg = document.createElement('img');
        loadingImg.setAttribute("src", "http://aiface.linxins.com/static/images/loading.gif");
        loadingDiv.appendChild(loadingImg);
        document.querySelector('body').appendChild(loadingDiv);
    }
    this.show = function () {
        loadingDiv.style.display = 'flex';
    }
    this.hide = function () {
        loadingDiv.style.display = 'none';
    }
}
