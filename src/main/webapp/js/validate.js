$(function ($) {
    $.extend($.fn, {

        validate: function( options ) {

            this.rules(options["rules"], options["messages"]);

            return this.result;
        },
        // 记录数
        k: 0,
        // 返回值结果定义
        result: false,
        //检验规则
        rules: function(rules, messages) {

            var i = 0;
            var m = 0;
            for (var prop in rules){

                //获取所用的表单元素
                var oInput = $(this)[0][i];
                //获取所有的表单元素的name值
                var name = $(oInput).attr("name");
                if(name == prop){

                    for(var key in rules[prop]) {

                        if (key == "required") {

                            var n = this.required($(oInput));
                            this.k += n;
                            if(n == 0&& m == 0){
                                m++;
                                $("#index-login-hint-error").css("display", "block");
                                $("#index-login-hint-error-content").text(messages[prop][key]);
                            }
                        }
                    }
                }
                i++;
            }
            if(this.k == i){
                this.result = true;
            }
        },

        required: function (oInput) {
            var val = this.trim(oInput.val());
            if(val == "")
                return 0;
            if(val == undefined)
                return 0;
            if(val.length < 1)
                return 0;

            return 1;
        },

        //去掉首尾空格
        trim: function (str) {
            var string = "";
            for(var i = 0; i < str.length; i++){
                if(str[i] != " " && str[i] != "")
                    string += str[i];
            }
            return string;
        },

        messages: {

        }
        
    });
    
});