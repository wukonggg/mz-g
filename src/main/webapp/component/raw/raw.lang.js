/*! RawJS | raw.lang.js | 0.1.0 | language相关扩展 | by wukong(wukonggg@139.com) */
/** 依赖：部分方法依赖raw.prototype.js。要用到array的remove方法 */

window.raw = window.raw || {};
window.raw.lang = window.raw.lang || {};
/**
 * 类似java中的Map
 */
window.raw.lang.map = function () {
    /** 存放键的数组(遍历用到) */
    this.keys = new Array();
    /** 存放数据 */
    this.data = new Object();

    /**
     * 放入一个键值对
     * @param {String} key
     * @param {Object} value
     */
    this.put = function (key, value) {
        if (this.data[key] == null) {
            this.keys.push(key);
        }
        this.data[key] = value;
    };

    /**
     * 获取某键对应的值
     * @param {String} key
     * @return {Object} value
     */
    this.get = function (key) {
        return this.data[key];
    };

    /**
     * 删除一个键值对
     * @param {String} key
     */
    this.remove = function (key) {
        this.keys.remove(key);
        this.data[key] = null;
    };

    /**
     * 遍历Map,执行处理函数
     *
     * @param {Function} 回调函数 function(key,value,index){..}
     */
    this.each = function (fn) {
        if (typeof fn != 'function') {
            return;
        }
        var len = this.keys.length;
        for (var i = 0; i < len; i++) {
            var k = this.keys[i];
            fn(k, this.data[k], i);
        }
    };

    /**
     * 获取键值数组(类似Java的entrySet())
     * @return 键值对象{key,value}的数组
     */
    this.entrys = function () {
        var len = this.keys.length;
        var entrys = new Array(len);
        for (var i = 0; i < len; i++) {
            entrys[i] = {
                key: this.keys[i],
                value: this.data[i]
            };
        }
        return entrys;
    };

    /**
     * 判断Map是否为空
     */
    this.isEmpty = function () {
        return this.keys.length == 0;
    };

    /**
     * 获取键值对数量
     */
    this.size = function () {
        return this.keys.length;
    };

    /**
     * 重写toString
     */
    this.toString = function () {
        var s = "{";
        for (var i = 0; i < this.keys.length; i++, s += ',') {
            var key = this.keys[i];
            var data = this.data[key];
            if (raw.util.json.isJson(data)) {
                data = raw.util.json.toString(data);
            }
            s += key + ":" + data;
            //      s += k + "=" + this.data[k];
        }
        s += "}";
        return s;
    };
};


function testRawLangMap() {
    var m = new raw.lang.map();
    m.put('Name', 'Game of Thrones');
    m.put('Country', 'USA');
    m.put('Production', 'HBO');
    alert("First:" + m);

    m.put('Actors', {'Eddard Stark': 'Sean Bean', 'Jon Snow': 'Kit Harington', 'Daenerys Targaryen': 'Emilia Clarke'});
    alert("Then:" + m);

    m.remove("Production");
    alert("And then: " + m);

    var s = "";
    m.each(function (key, value, index) {
        s += index + ":" + key + "=" + value + "/n";
    });
    alert(s);
}