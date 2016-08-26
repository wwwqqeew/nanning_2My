(function($){  
	 //根据Form来获取内部的数据，返回为JSON个数
        $.fn.serializeJson=function(){  
            var serializeObj={};  
            var array=this.serializeArray();  
            var arrayWj ;//外键加载
            $(array).each(function(){  
            	//装载外键，等等处理
            	if (this.name.indexOf(".") >= 0) {
            		var _value = {};
            		var _name = this.name.split(".");
            		_value[_name[1]] = this.value;
            		this.name = _name[0];
            		this.value = _value;
				}
                if(serializeObj[this.name]){  
                    if($.isArray(serializeObj[this.name])){  
                        serializeObj[this.name].push(this.value);  
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
                    }  
                }else{  
                    serializeObj[this.name]=this.value;   
                }  
            });  
            return serializeObj;  
        };  
})(jQuery);

//设置参数？
(function ($) {
    $.fn.setform = function (jsonValue) {
        var _this = this;
        $.each(jsonValue, function (name, ival) {
            var $oinput = _this.find("input[name=" + name + "]");
            //单选与多选框
            if ($oinput.attr("type")== "radio" || $oinput.attr("type")== "checkbox") {
               $oinput.each(function(){
            	 //是否是复选框
                 if(Object.prototype.toString.apply(ival) == '[object Array]'){//是复选框，并且是数组
                    for(var i=0;i<ival.length;i++){
                        if($(this).val()==ival[i])
                           $(this).attr("checked", "checked");
                    }
                 }else{
                 if($(this).val()==ival)
                    $(this).attr("checked", "checked");
                    }
               });
            }else
            {
            	_this.find("[name="+name+"]").val(ival); 
             }
        });
    };
})(jQuery);


//设置参数？
(function ($) {
    $.fn.myGetList = function (data) {
    	var _this = this;
			$.ajax({
				type : "POST",
				url : data.url,
				async:false, 
				data : $.toJSON(data.property),
				dataType : "json",
				contentType : 'application/json;charset=UTF-8',
				success : $.proxy(function(obj) {
					if (obj.success) {
						_this.attr("class");
						var html = "";
						for ( var int = 0; int < obj.data.length; int++) {
							
						}
						alert($.toJSON(obj.data));
					} else {
						
					}
				}, this),
				error : function(msg) {
					alert(msg.success);
				}
			});
    };
})(jQuery);

//所有的参数
//(function($, exports) {
//	exports.myGetList = function (data) {
//		var _this = this;
//		this.getDate = function(){
//			var dd = {};
//			$.ajax({
//				type : "POST",
//				url : data.url,
//				async:false, 
//				data : $.toJSON(data.property),
//				dataType : "json",
//				contentType : 'application/json;charset=UTF-8',
//				success : $.proxy(function(obj) {
//					if (obj.success) {
//						dd = obj.data;
//					} else {
//						
//					}
//				}, this),
//				error : function(msg) {
//					alert(msg.success);
//				}
//			});
//			return _this.returnDate(dd);
//		};
//		
//		this.returnDate = function(data){
//			return data;
//		};
//		
//		this.getDate();
//    };
//})(jQuery, window);
