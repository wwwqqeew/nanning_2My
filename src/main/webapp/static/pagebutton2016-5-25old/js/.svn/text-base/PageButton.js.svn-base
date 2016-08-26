/**
 * @author Joe
 * 
 * PageButton
 * @param {} $
 * @param {}
 *            exports
 * @returns {}
 */
(function($, exports) {
	exports.PageButton = function(contentID, page, pageSize, showNum, preText, nexText) {
		this.id = contentID;
		this.page = (page || 1);
		this.pageSize = (pageSize || this.page);
		this.showNum = (showNum || 5);
		this.preText = (preText || "<<");
		this.nexText = (nexText || ">>");
		this.$pageBtn;
		this.clickEvent;

		this.init = function() {
			var pbText = "<div id='page-btn' class='page-btns'></div>";
			this.$pageBtn = $(pbText);
			this.setButton(this.page, this.pageSize, this.showNum);
			initOperateListen(this);
			this.id.append(this.$pageBtn);
		};
		
		/**设置button
		page：当前页
		pageSize：总页数
		showNum：每页显示数
		**/
		this.setButton = function(page, pageSize, showNum, preText, nexText) {
			this.page = (page || this.page);
			this.pageSize = (pageSize || this.pageSize);
			this.showNum = (showNum || this.showNum);
			this.preText = (preText || this.preText);
			this.nexText = (nexText || this.nexText);
			this.$pageBtn.html(getButtonText(this.page, this.pageSize, this.showNum, this));
		};

		this.click = function(fn) {
			this.clickEvent = fn;
		};

		var initOperateListen = function(_this) {
			_this.$pageBtn.mouseup(function(e) {
				var $target = $(e.target || e.srcElement);
				if (($target[0].nodeName || " ").toLowerCase() == "input") {
					_this.setButton(($target.attr('alt') || _this.page), _this.pageSize, _this.showNum);
					if(typeof _this.clickEvent == "function"){
						_this.clickEvent(($target.attr('alt') || _this.page));
					}
				}
				;
			});
		};
		
		/**获取页面的html
		page：当前页
		pageSize：总页数
		showNum：每页显示数
		_this：对象
		**/
		var getButtonText = function(page, pageSize, showNum, _this) {
			page = +page;
			pageSize = +pageSize;
			showNum = +showNum;
			if (pageSize < 1)
				return "";
			var pageButtonText = getSingleButtonText(page, page, true);
			var n = 1;
			var r = 0;
			var l = 0;
			for ( var i = 1; i <= showNum; i++) {
				if (page + i <= pageSize && n < showNum) {
					pageButtonText = pageButtonText + getSingleButtonText(page + i, page + i, false);
					n++;
					r++;
				}
				if (page - i >= 1 && n < showNum) {
					pageButtonText = getSingleButtonText(page - i, page - i, false) + pageButtonText;
					n++;
					l++;
				}
				if ((page - i < 1 && page + i > pageSize) || n >= showNum) {
					break;
				}
				;
			}

			if (page - l > 1)
				pageButtonText = getSingleButtonText((((page - showNum) >= 1) ? (page - showNum) : 1), _this.preText,
						false, "pre-page-btn")
						+ pageButtonText;
			if (page + r < pageSize)
				pageButtonText = pageButtonText
						+ getSingleButtonText((((page + showNum) <= pageSize) ? (page + showNum) : pageSize),
								_this.nexText, false, "next-page-btn");
			return pageButtonText;
		};

		var getSingleButtonText = function(page, value, current, className) {
			if (current)
				return "<input type='hidden' class='page' value='" + page
						+ "' name='page'/><input type='button' class='" + (className || "current-page-btn")
						+ "' disabled='disabled' alt='" + page + "' value='" + value + "' style='width:"+(19+(page+"").length*4)+"px;'/>";
			return "<input class='" + (className || "page-btn") + "' type='button' alt='" + page + "' value='" + value
					+ "' style='width:"+(19+(page+"").length*4)+"px;'/>";
		};
	};

})(jQuery, window);
