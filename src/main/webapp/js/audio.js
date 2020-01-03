var MusicArr = [];
var num = 0;
var oldUuId;
function Music(src) {

    //音乐对象
    this.oMusic = null;
    this.oId = null;
    this.uuId = src,
    this.init(src);

    //初始化值
    this.fig = true;

    //获取ID
    this.getId = function () {
        return this.oId;
    };

    //获取音乐对象
    this.getMusic = function () {
        return this.oMusic;
    };

    this.uuId = function () {
        return this.uuId;
    };
    
    this.playOrPause = function () {

        if(this.fig){
            this.oMusic.get(0).play();
            this.fig = false;
        }else{
            this.oMusic.get(0).pause();
            this.fig = true;
        }
    }

}


Music.prototype = {

    init: function(src){

        if(this.uuId != oldUuId){
            this.oMusic = $(new Audio(src));
            MusicArr[num] = this.oMusic;
            this.oMusic.attr("id", id);
            var id = this.randomString(16);
            this.oId = id;
            oldUuId = this.uuId;
        }else{
            this.oMusic = MusicArr[num];
        }

        //添加到页面中
        $("body").append(this.oMusic);
    },


    /**
     *      生成指定位数的字符串
     *
     * @param size          字符串的长度
     * @returns {string}
     */
    randomString: function (size) {

        size = size || 32;
        var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
        var maxPos = $chars.length;
        var pwd = '';
        for (i = 0; i < size; i++) {
            pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
        }
        return pwd;
    }
    
};


/**
 * 音频初始化
 *      音频路径
 */
function audioInit(src) {



    return id;
}

