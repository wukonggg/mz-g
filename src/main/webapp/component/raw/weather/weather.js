var city = 101190101;
//http://www.weather.com.cn/data/sk/101190101.html
//http://www.weather.com.cn/weather1d/101190101.shtml
window.raw = {};

window.raw.weather = (function (ns, undefined) {
  var show = function (city) {
  };
  var _CITY_NANJING = 101190101;
  return {
    show: show,
    CITY_NANJING: _CITY_NANJING
  };
})(window.raw.weather);

//raw.weather.show(raw.weather.CITY_NANJING);