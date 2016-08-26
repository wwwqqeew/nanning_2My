clearNoNum = function(event,obj){ 
    event = window.event||event; 
    if(event.keyCode == 37 | event.keyCode == 39){ 
        return; 
    } 
    obj.value = obj.value.replace(/[^\d.]/g,""); 
    obj.value = obj.value.replace(/^\./g,""); 
    obj.value = obj.value.replace(/\.{2,}/g,"."); 
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
};
 checkNum = function(obj){ 
    obj.value = obj.value.replace(/\.$/g,""); 
}; 

clearNoName = function(event,obj){ 
    event = window.event||event; 
    if(event.keyCode == 37 | event.keyCode == 39){ 
        return; 
    } 
    obj.value = obj.value.replace(/[^\a-zA-Z0-9_\u4E00-\u9FA5]/g, ""); 
};
 checkName = function(obj){ 
    obj.value = obj.value.replace(/[^\a-zA-Z0-9_\u4E00-\u9FA5]/g, ""); 
};



clearNoMath = function(event,obj){ 
    event = window.event||event; 
    if(event.keyCode == 37 | event.keyCode == 39){ 
        return; 
    } 
    obj.value = obj.value.replace(/[^0-9]+/gi, ""); 
};
 checkMath = function(obj){ 
    obj.value = obj.value.replace(/[^0-9]+/gi, ""); 
};

checkEpByArr = function(data){
	var theRt = true;
	for(var i = 0 ; data.length > i ; i++){
		if(!checkEmpty(data[i].eryAlt,data[i].calssName)){
			theRt = false;
			break;
		}
	}
	return theRt;
};

getDate = function(obj){
	obj.value = new Date();
};

checkEmpty = function(eryAlt,calssName){
	var $classP = $("."+calssName);
	if($.trim($classP.val()) == "" ){
		alert(eryAlt+"不能为空！");
		$classP.focus();
		return false;
	}else{
		return true;
	}
};