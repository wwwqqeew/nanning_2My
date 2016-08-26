	addErroy = function(theClass,className,erroyTxt){
		if(theClass.next("span[name='"+className+"']").length == 0){
			theClass.after("<span class=\"error\" name='"+className+"'>"+erroyTxt+"</span>");
			theClass.next("span[name='"+className+"']").show();
		}
	};
	
	delErroy = function(theClass,className){
		if(theClass.next("span[name='"+className+"']").length != 0){
			theClass.next("span[name='"+className+"']").remove();
		}
	};
	
	emptyCheck = function(date,theClass,className,erroyTxt){
		if($.trim(date) == "" || $.trim(date) == null){
			addErroy(theClass,className,erroyTxt);
			return false;
		}else{
			delErroy(theClass,className);
			return true;
		}
	};
	
	checkStartDatedAndEndDated = function(startDatedClass,endDatedClass,tis){
  	  var regEx = new RegExp("\\-", "gi");
  	  var startDated = (startDatedClass.val()?startDatedClass.val().replace(regEx,""):"");
  	  var endDated = (endDatedClass.val()?endDatedClass.val().replace(regEx,""):"");
      if (endDated<startDated) {
          if (endDatedClass.next("span[name='" + endDatedClass.attr("name")+ "']").length == 0) {
              endDatedClass.after("<span class=\"error\" name='" + endDatedClass.attr("name")+ "'>"+tis+"</span>");
          };
          return false;
      } else {
          if (endDatedClass.next("span[name='"+endDatedClass.attr("name")+"']").length != 0) {
              endDatedClass.next("span[name='"+endDatedClass.attr("name")+"']").remove();
          }
          return true;
      }
  };
	
	checkNameOrNuber = function(theUrl,theClass,className,str,name,date){
		var rt = true;
		$.ajax({
			type : "POST",
			url : theUrl+"/check"+name,
			async:false, 
			dataType : "json",
			data : name+"=" + date,
			success : $.proxy(function(obj) {
				if(obj.data==true || obj.data=="true"){
					delErroy(theClass,className);
					rt = true;
				}else{
					addErroy(theClass,className,str);
					rt = false;
				}
			}, this),
			error : function(msg) {
				alert("由于网络或服务器问题，暂时无法进行操作！\n若一直出现这个提示，请联系管理员！\n" + theUrl+"/check"+name
						);
			}
		});
	return rt;
	};