/*
* jQuery.autocomplete.js (v1.1.2)
* authored by nswish (nswish@gmail.com)
* jQuery 1.7.1+ support
* compatible: ie/chrome/firefox/opera/safari
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
(function($){
    $.fn.extend({
        'AutoComplete': function(option){
            return this.each(function(){
                if (!(this && this.tagName === 'INPUT' && this.type === 'text')) return;

                if(this.controller)
                    this.controller.setOption(option);
                else {
                    if($.isPlainObject(option))
                        this.controller = new Controller(this, option);
                }
            });
        }
    });

    // ------- Construct Method Here -------------
    var Controller = function(input, option){
        this.option = $.extend(false, {
            // style
            'width': 320,                                   // number, string 'auto'
            'maxHeight': null,                              // number
            'itemHeight': null,                             // number
            'listStyle': 'normal',                          // string 'normal', 'iconList', 'custom'
            'listDirection': 'down',                        // string 'down' or 'up'
            // data
            'data': [],                                     // array, string, function
            'ajaxDataType': 'json',                         // string 'json' or 'xml'
            'ajaxParams': {},                               // function, string, object
            'ajaxTimeout': 3000,                            // number
            'ajaxType': 'GET',                              // string 'GET' or 'POST'
            'maxItems': 20,                                 // number
            // event
            'matchHandler': defaultMatchHandler,            // function
            'emphasisHandler': defaultEmphasisHandler,      // function
            'createItemHandler': null,                      // function
            'beforeLoadDataHandler': null,                  // function
            'afterSelectedHandler': null,                   // function
            // behavior
            'async': false,                                 // bool
            'emphasis': true,                               // bool
            // debug
            'onerror': null                                 // function
        }, option);    

        _setupInputView.apply(this, [input]);
        _setupSearchView.apply(this);
    };

    // ------- Private Method Here -------------
    var _setupInputView = function(input){
        var that = this;

        this.inputView = $(input);

        this.inputView
        .attr('autocomplete', 'off') // disable IE's autocomplete feature
        .keyup(this._keyup = function(event){
            switch(event.keyCode){
                case 13: // enter
                case 16: // shift
                case 17: // ctrl
                case 37: // left
                case 38: // up
                case 39: // right
                case 40: // down
                    break;
                case 27: // esc
                    _emptySearchView.apply(that);
                    break;
                default:
                    if(that.option.async){
                        setTimeout(function(){
                            _search.apply(that);
                        }, 0);
                    } else {
                        _search.apply(that);
                    }
            }
        })
        .keydown(this._keydown = function(event){
            switch(event.keyCode){
                case 38: // up
                    _move.apply(that, ['up']);
                    break;
                case 40: // down
                    _move.apply(that, ['down']);
                    break;
                case 13: // enter
                    var isSearchViewVisible = that.searchView.is(':visible');
                    _select.apply(that);
                    if(isSearchViewVisible)
                        return false;
                    break;
            }
        })
        .blur(this._blur = function(){
            $(document).one('click', function(){
                _emptySearchView.apply(that);
            });
        });
    };

    var _setupSearchView = function(){
        var that = this;

        this.searchView = $("<div class='ac'><ul></ul></div>")
        .appendTo(document.body)
        .on('mouseenter', 'li', function(){
            that.searchView.find("li.selected").removeClass("selected");
            $(this).addClass('selected');
        })
        .on('mouseleave', 'li', function(){
            $(this).removeClass('selected');
        })
        .on('click', 'li', function(){
            _select.apply(that);
            _emptySearchView.apply(that);
        })
        .css('font-size', this.inputView.css('font-size'));

        $(window).resize(function(){
            _locateSearchView.apply(that);
        });
    };

    var _createItems = function(result){
        var that = this,
            container = this.searchView.find('ul').empty();

        if ($.inArray(this.option.listStyle, ['normal', 'iconList', 'custom']) == -1) {
            throw ['遇到未知的listStyle参数！'];
        };

        $.each(result, function(index, data){
            var item = $("<li><div></div></li>").appendTo(container).addClass(that.option.listStyle).data("data", data).find("div");

            switch(that.option.listStyle){
                case 'normal':
                    item.append("<span>"+data.label+"</span>");
                    break;
                case 'iconList':
                    var img = $("<img></img>").attr('src', data.image);
                    item.append($("<div></div>").append(img)).append("<span>"+data.label+"</span>");
                    break;
                case 'custom':
                    item.append(that.option.createItemHandler.apply(that, [index, data]));
                case 'default':
                    break;
            }

            if(that.option.itemHeight > 0){
                item.height(that.option.itemHeight).css('max-height', that.option.itemHeight);
            }
        });
    };

    var _locateSearchView = function(){
        if(this.option.listDirection === 'down'){
            var top = this.inputView.offset().top + this.inputView.outerHeight();
        } else if(this.option.listDirection === 'up'){
            var top = this.inputView.offset().top - this.searchView.outerHeight();
        } else {
            throw '遇到未知的listDirection参数！';
        }

        var left = this.inputView.offset().left;
        this.searchView.css("top", top+"px").css("left", left+"px");
    };

    var _calcWidth = function(){
        if(typeof(this.option.width) === 'string' && this.option.width.toLowerCase() === 'auto'){
            return this.inputView.outerWidth() - 2;
        } else if(typeof(this.option.width) === 'number'){
            return this.option.width;
        } else {
            throw '遇到未知的width参数！';
        }
    }

    var _showSearchView = function(result){
        var that = this;

        if(this.option.listDirection === 'up')
            result = result.reverse();
    
        try{
            _createItems.apply(that, [result]);//console.log("length="+this.searchView.find('li').size());

            if(this.option.maxHeight > 0){
                this.searchView.css('max-height', this.option.maxHeight+"px");
                if($.browser.msie){
                    this.searchView.css("height", this.searchView.height() > this.option.maxHeight ? this.option.maxHeight+"px" : "auto");
                }
            }

            // 定位补全列表
            _locateSearchView.apply(this);

            // 计算并设定补全列表的宽度
            this.searchView.css("width", _calcWidth.apply(this)+'px');

        } catch(ex) {
            _error.apply(this, [ex+'']);
            return;
        }

        this.searchView.show();

        _move.apply(this, [this.option.listDirection]);
    };

    var _emptySearchView = function(){
        this.searchView.find('ul').empty();
        this.searchView.hide();
    };

    var _move = function(dir){
        var selected = this.searchView.find('li.selected');

        if (selected.size()) 
            var nextSelected = dir === 'up' ? selected.prev() : selected.next();
        else
            var nextSelected = dir === 'up' ? this.searchView.find('li').last() : this.searchView.find('li').first();
        
        if(nextSelected.size()){
            this.searchView.find('li').removeClass('selected');
            nextSelected.addClass("selected");

            var itemHeight = nextSelected.outerHeight();
            var itemTop = nextSelected.position().top;
            if(itemHeight + itemTop > this.searchView.height())
                this.searchView.scrollTop(this.searchView.scrollTop() + itemTop + itemHeight - this.searchView.height());
            else if(itemTop < 0)
                this.searchView.scrollTop(this.searchView.scrollTop() + itemTop);
        }
    };

    var _select = function(){
        var that = this,
            selected = this.searchView.find('li.selected');
        
        if (selected.size()) {
            var data = selected.data('data');

            this.inputView.val(data.value);

            if ($.isFunction(this.option.afterSelectedHandler)) {
                try{
                    this.option.afterSelectedHandler.apply(that, [data]);
                } catch(e) {
                    _error.apply(this, ['调用afterSelectedHandler错误:'+e]);
                    return;
                }
            }

            _emptySearchView.apply(this);
        }
    };

    var _ajaxSend = function(keyword){
        jQuery.support.cors = true;
        var that = this,
            data = [],
            ajaxOption = {
                'async': false,
                'dataType': that.option.ajaxDataType,
                'type': that.option.ajaxType,
                'timeout': this.option.ajaxTimeout,
                'success': function(theData, textStatus, jqXHR){
                    if (that.option.ajaxDataType === 'xml') {
                        $(theData).find('item').each(function(){
                            var item = {
                                'value': $(this).text(),
                                'label': $(this).text()
                            };

                            for (var i=0; i<this.attributes.length; i++) {
                                var key = this.attributes[i].nodeName,
                                    value = this.attributes[i].nodeValue;

                                item[key] = value;
                            };

                            data.push(item);
                        });
                    } else if(that.option.ajaxDataType === 'json') {
                        data = theData;
                    } else {
                        throw '遇到未知的ajaxDataType参数！';
                    }
                },
                'error': function(jqXHR, textStatus, errorThrown){
                    throw errorThrown;
                }
            };

        if ($.isPlainObject(this.option.ajaxParams)) {
            ajaxOption['data'] = $.extend(false, {'keyword': keyword}, this.option.ajaxParams);
        } else if ($.isFunction(this.option.ajaxParams)) {
            ajaxOption['data'] = $.extend(false, {'keyword': keyword}, this.option.ajaxParams.apply(this, [keyword]));
        } else if (typeof(this.option.ajaxParams) === 'string') {
            ajaxOption['data'] = "keyword=" + keyword + "&" + this.option.ajaxParams;
        } else {
            throw '遇到未知的ajaxParams参数！';
        }

        $.ajax(this.option.data, ajaxOption);

        return data;
    };

    var _search = function(){
        var that = this,
            keyword = this.inputView.val(),
            data = [],
            loadDataFlag = true,
            result = [];

        if($.trim(keyword).length == 0){
            _emptySearchView.apply(that);
            return;
        }

        // invoke beforeLoadDataHandler if exists
        if ($.isFunction(this.option.beforeLoadDataHandler)) {
            try{
                loadDataFlag = this.option.beforeLoadDataHandler.apply(this, [keyword]);
            } catch(e) {
                _error.apply(this, ['调用beforeLoadDataHandler错误:'+e]);
                return;
            }
        }

        if (loadDataFlag){
            if ($.isArray(this.option.data)) {
                data = this.option.data;
            } else if ($.isFunction(this.option.data)) {
                try{
                    data = this.option.data.apply(this, [keyword]);
                } catch(e) {
                    _error.apply(this, ['调用data错误:'+e]);
                    return;
                }
            } else if (typeof(this.option.data) === 'string') {
                try{
                    data = _ajaxSend.apply(this, [keyword]);
                } catch(e) {
                    _error.apply(this, ['Ajax错误:'+e]);
                    return;
                }
            } else {
                _error.apply(this, ['遇到未知的data参数！']);
                return;
            }
        }

        $.each(data, function(index, value){
            if(that.option.maxItems > 0 && result.length >= that.option.maxItems)
                return false;

            if($.isPlainObject(value)){
                var item = $.extend(false, {}, value);
            } else if(typeof(value) === 'string') {
                var item = {'label': value, 'value': value, 'image': value};
            } else {
                _error.apply(that, ['数据源Item类型错误！']);
                return false;
            }

            if(that.option.matchHandler.apply(that, [keyword, item])){
                result.push(item);
            }
        });

        if(keyword == this.inputView.val()){
            if(result.length > 0)
                _showSearchView.apply(this, [result]);
            else
                _emptySearchView.apply(this);
        }
    };

    var _error = function(msg){
        if($.isFunction(this.option.onerror)){
            this.option.onerror.apply(this, [msg]);
        }
    };

    // ------- Public Method Here -------------
    Controller.prototype.setOption = function(option){
        if ($.isPlainObject(option)) {
            this.option = $.extend(false, this.option, option);
        } else if(typeof(option) === 'string'){
            switch(option){
                case 'destroy':
                    this.destroy();
                    break;
                case 'show':
                    this.show();
                    break;
                default:
                    _error.apply(this, ['未知的AutoComplete参数！']);
                    return;
            }
        } else {
            _error.apply(this, ['未知的AutoComplete参数类型！']);
            return;
        }
    };

    Controller.prototype.destroy = function(){
        this.searchView.remove();
        this.inputView.unbind('keyup', this._keyup).unbind('keydown', this._keydown).unbind('blur', this._blur);
        delete this.inputView.get(0).controller;
    };

    Controller.prototype.show = function(){
        if(this.option.async){
            setTimeout(function(){
                _search.apply(this);
            }, 0);
        } else {
            _search.apply(this);
        }    
    };

    // ------- Default Handler Function Here -------------
    var defaultMatchHandler = function(keyword, data){
        var regex = RegExp(keyword.replace(/([.?*+^$[\]\\(){}|-])/g, "\\$1"), 'i');
        if(this.option.emphasis && $.isFunction(this.option.emphasisHandler))
            this.option.emphasisHandler.apply(this, [keyword, data]);
        return regex.test(data.value);
    };

    var defaultEmphasisHandler = function(keyword, data){
        var regex = RegExp("("+keyword.replace(/([.?*+^$[\]\\(){}|-])/g, "\\$1")+")", 'ig');
        data.label = data.label.replace(regex, "<em>$1</em>");
    };

})(jQuery);
