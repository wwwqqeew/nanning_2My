
	$(".export-main,.export-main2").draggable();

	//绑定事件,这里对mousewheel做了判断,注册时统一使用mousewheel 
	function addEventMy(obj, type, fn) {
		var isFirefox = typeof document.body.style.MozUserSelect != 'undefined';
		if (obj.addEventListener)
			obj.addEventListener(isFirefox ? 'DOMMouseScroll' : type, fn,false);
		else
			obj.attachEvent('on' + type, fn);
		return fn;
	}

	function stopEvent(e) {
		e = e || event;
		if (e.preventDefault)
			e.preventDefault();
		e.returnValue = false;
	}

