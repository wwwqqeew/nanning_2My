// JavaScript Document
(function($) {
	var printAreaCount = 0;
	$.fn.printArea = function() {
		var ele = $(this);
		var idPrefix = "printArea_";
		removePrintArea(idPrefix + printAreaCount);
		printAreaCount++;
		var iframeId = idPrefix + printAreaCount;
		var iframeStyle = 'position:absolute;width:0px;height:0px;left:-500px;top:-500px;';
		iframe = document.createElement('IFRAME');
		$(iframe).attr({
			style : iframeStyle,
			id : iframeId
		});
		document.body.appendChild(iframe);
		var doc = iframe.contentWindow.document;
		//OpenWindow=window.open("", "newwin", "height=250, width=250,toolbar=no,scrollbars="+scroll+",menubar=no");
		//写成一行
		
		$(document).find("link").filter(function() {
			return $(this).attr("rel").toLowerCase() == "stylesheet";
		}).each(
				function() {
					doc.write('<link type="text/css" rel="stylesheet" href="'+ $(this).attr("href") + '" >');
					//OpenWindow.document.write('<link type="text/css" rel="stylesheet" href="'+ $(this).attr("href") + '" >');
				});
		doc.write('<div class="' + $(ele).attr("class") + '">' + $(ele).html()+ '</div>');
		//OpenWindow.document.write('<div class="' + $(ele).attr("class") + '">' + $(ele).html()+ '</div>');
		doc.close();
		var frameWindow = iframe.contentWindow;
		frameWindow.close();
		frameWindow.focus();
		//OpenWindow.document.write($("table#order-table").html());
		//OpenWindow.document.close();
		frameWindow.print();
	};
	var removePrintArea = function(id) {
		$("iframe#" + id).remove();
	};
})(jQuery);