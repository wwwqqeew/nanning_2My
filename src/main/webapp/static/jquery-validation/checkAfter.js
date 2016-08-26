(function($, exports) {
    exports.checkIframe = function(formid,args) {
    	this.$formid = $("#"+formid);
        this.empty = this.$formid.find("." + (args.empty || "required"));//空值
        this.lengthed = this.$formid.find("." + (args.lengthed || "lengthed"));//长度
        this.matched = this.$formid.find("." + (args.matched || "matched"));//数字
        this.bstartDated = this.$formid.find("." + (args.bstartDated || "bstartDated"));//起始日期
        this.bendDated = this.$formid.find("." + (args.bendDated || "bendDated"));//终止日期
        this.result = false;
        var _this = this;
        this.init = function() {
            this.empty.focusout(function(e) {_this.checkEmpty($(this));});
            this.matched.focusout(function(e) {_this.doCheck($(this));});
        };
        
        this.postCheck = function(){
            this.result = true;
            this.empty.each(function(e) {_this.checkEmpty($(this));});
            this.doFunction();
            this.checkStartDatedAndEndDated(this.bstartDated,this.bendDated,"结束日期错误！");
        };
        
        this.doFunction =  function(){
        	this.matched.each(function(e) {
            	_this.doCheck(this.matched);
            	});
        	this.lengthed.each(function(e) {
            	_this.doLong($(this));
            	});
            };
        
        this.doCheck = function(theClass){
            var varreg=/^\d+(\.{0,1}\d+){0,1}$/;
            this.check(varreg,theClass,"只能输入数字！");
        };
        
        this.doLong = function(theClass){
        	var erLong = /^\s*[\s\S]{1,18}\s*$/;
        	this.check(erLong,theClass,"最多18个字！");
        };
        
        this.checkStartDatedAndEndDated = function(startDatedClass,endDatedClass,tis){
        	  var regEx = new RegExp("\\-", "gi");
        	  var startDated = (startDatedClass.val()?startDatedClass.val().replace(regEx,""):"");
        	  var endDated = (endDatedClass.val()?endDatedClass.val().replace(regEx,""):"");
            if (endDated<startDated) {
                if (endDatedClass.next("span[name='" + endDatedClass.attr("name")+ "']").length == 0) {
                    endDatedClass.after("<span class=\"error\" name='" + endDatedClass.attr("name")+ "'>"+tis+"</span>");
                };
                this.result = false;

            } else {
                if (endDatedClass.next("span[name='"+endDatedClass.attr("name")+"']").length != 0) {
                    endDatedClass.next("span[name='"+endDatedClass.attr("name")+"']").remove();
                }
            }
        };
        
        
         this.check = function(varreg,theClass,tis){
                if (!varreg.test(theClass.val())) {
                    if (theClass.next("span[name='" + theClass.attr("name")+ "']").length == 0) {
                        theClass.after("<span class=\"error\" name='" + theClass.attr("name")+ "'>"+tis+"</span>");
                    };
                    this.result = false;

                } else {
                    if (theClass.next("span[name='"+theClass.attr("name")+"']").length != 0) {
                        theClass.next("span[name='"+theClass.attr("name")+"']").remove();
                    }
                }
            };
        
        this.checkEmpty = function( theClass ){
            if (theClass.val() == "") {
//            	//输入框后加提示框
//                if (theClass.next("span[name='" + theClass.attr("name")+ "']").length == 0) {
//                    theClass.after("<span class=\"error\" name='" + theClass.attr("name")+ "'>不能为空</span>");
//                };
            	//输入框边框变红
            	//alert(theClass.attr("class").indexOf("error2"));
            	if(theClass.attr("class").indexOf("error2") < 0){
            		theClass.addClass("error2");
            	}
                this.result = false;

            } else {
//            	//输入框后移除提示框
//                if (theClass.next("span[name='"+theClass.attr("name")+"']").length != 0) {
//                    theClass.next("span[name='"+theClass.attr("name")+"']").remove();
//                }
            	//输入框边框变红
            	if(theClass.attr("class").indexOf("error2") >= 0){
            		theClass.removeClass("error2");
            	}

            }
        };
        
        this.emptyErroy = function(){
        	this.$formid.find(".error").empty().remove();
        };
    };
})(jQuery, window);