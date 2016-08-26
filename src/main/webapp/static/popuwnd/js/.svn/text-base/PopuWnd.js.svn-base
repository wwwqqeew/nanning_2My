
(function($, exports) {

	exports.PopuWnd = function(args) {
		this.title = (args.title || "");
		this.url = args.url;
		this.x = args.left;
		this.y = args.top;
		this.w = +(args.width || "0");
		this.h = +(args.height || "0");
		this.htmlContent = args.html;
		this.max = args.max;
		this.id = (args.id || "");
		this.alt = (args.alt || "add");

		var $wnd = {};
		var $background = {};
		var isMax = false;

		this.init = function(showOrHidden) {

			$wnd = $("<div id='popuwnd' class='popuwnd shadow'><div class='popuwnd-main'><div class='popuwnd-title'><div class='popuwnd-title-tab'></div><div class='popuwnd-title-show'></div><div class='popuwnd-title-maxsize-btn'></div><div class='popuwnd-title-del-btn'></div></div><div class='popuwnd-container'><iframe frameborder='0' class='iframe-main'></iframe></div></div><div class='shadow-top'></div><div class='shadow-right'></div><div class='shadow-bottom'></div><div class='shadow-left'></div><div class='shadow-top-left'></div><div class='shadow-top-right'></div><div class='shadow-bottom-left'></div><div class='shadow-bottom-right'></div></div>");
			$wnd.find(".popuwnd-title-tab").text(this.title);

			if (this.url) {
				$wnd.find(".popuwnd-container iframe").attr("src", this.url);
				$wnd.find(".popuwnd-container iframe").attr("id", this.id);
				$wnd.find(".popuwnd-container iframe").attr("name", "ifra");
				$wnd.find(".popuwnd-container iframe").attr("alt", this.alt);
			}

			setSize(this.w, this.h);
			setDefaultPosition(this);
			initMaxSize(this);
			ifameRead(this);
			scroll();
			moveTo(this);
			_close();
			initLoad(this);

			$background = $("<OBJECT id='popuwnd-backgroung'><div id='popuwnd-backgroung'></div></OBJECT>");

			$("body").append($background);
			$("body").append($wnd);

			if (showOrHidden != true)
				this.show();

		};

		var initMaxSize = function(_this) {
			if (_this.max == true) {
				maxSize(_this);
			} else {
				$wnd.find(".popuwnd-title-maxsize-btn").css("display", "none");
			}
		};

		this.size = function(w, h) {
			if (w)
				this.w = w;
			if (h)
				this.h = h;
			setSize(this.w, this.h);
		};

		var setDefaultPosition = function(_this) {
			if (_this.x == null) {
				_this.x = (getWndWidth() - _this.w) / 2
						+ $(exports).scrollLeft();
			} else {
				_this.x = _this.x;
			}
			if (_this.y == null) {
				_this.y = (getWndHeight() - _this.h) / 3
						+ $(exports).scrollTop();
			} else {
				_this.y = _this.y;
			}
			setPosition(_this.x, _this.y);
		};

		this.position = function(x, y) {
			if (x)
				this.x = x;
			if (y)
				this.y = y;
			setPosition(this.x, this.y);
		};

		var setPosition = function(x, y) {
			$wnd.css("top", y + "px");
			$wnd.css("left", x + "px");
		};

		var setSize = function(w, h) {
			$wnd.find(".popuwnd-container iframe").css({
				height : h + "px",
				width : w + "px"
			});
			$wnd.find(".popuwnd-main").css({
				height : h + "px",
				width : w + "px"
			});

			$wnd.find(".shadow-left").css({
				height : (+h + 2) + "px"
			});
			$wnd.find(".shadow-right").css({
				height : (+h + 2) + "px"
			});
			$wnd.find(".shadow-top").css({
				width : (+w + 2) + "px"
			});
			$wnd.find(".shadow-bottom").css({
				width : (+w + 2) + "px"
			});

			$wnd.css({
				height : (+h + 12) + "px",
				width : (+w + 12) + "px"
			});
		};

		var getWndWidth = function() {
			var w = $(exports).outerWidth();
			if (typeof w != 'number') {
				if (document.compatMode == 'CSS1Compat') {
					w = document.documentElement.clientWidth;
				} else {
					w = document.body.clientWidth;
				}
				;
			}
			;
			return w;
		};
		var getWndHeight = function() {
			var h = $(exports).outerHeight();
			if (typeof h != 'number') {
				if (document.compatMode == 'CSS1Compat') {
					h = document.documentElement.clientHeight;
				} else {
					h = document.body.clientHeight;
				}
			}
			return h;
		};

		var pX = -1;
		var pY = -1;
		var isDown = false;
		var moveTo = function(_this) {
			$wnd.find(".popuwnd-title").mousedown(function(e) {
				var offset = $wnd.offset();
				pY = e.clientY - offset.top;
				pX = e.clientX - offset.left;
				if (isMax) {
					pX = Math.ceil((pX / $wnd.outerWidth()) * (_this.w + 18));
				}
				isDown = true;
			});
			$("body").mouseup(function() {
				pX = -1;
				pY = -1;
				isDown = false;
			});
			$("body").mousemove(function(handle) {
				if (isDown) {
					if (isMax) {
						isMax = false;
						autoSize(_this, isMax);
					} else {
						_this.y = handle.clientY - pY;
						_this.x = handle.clientX - pX;
						$wnd.offset({
							top : _this.y,
							left : _this.x
						});
					}

				}
			});
		};
		var _close = function() {
			$wnd.find(".popuwnd-title-del-btn").click(function() {
				$("#popuwnd-backgroung").remove();
				$("#popuwnd").remove();
				$wnd = null;
			});
		};

		var ifameRead = function(_this) {
			$wnd.find(".popuwnd-container iframe").load(
					function() {
						if (_this.htmlContent) {
							$wnd.find(".popuwnd-container iframe").contents()
									.find('body').html(_this.htmlContent);
						}
						_this.htmlContent = null;
					});
		};

		var scroll = function() {
			$(exports).scroll(function() {
				$background.css("top", $(exports).scrollTop());
				$background.css("left", $(exports).scrollLeft());
			});
		};

		var maxSize = function(_this) {
			$wnd.find(".popuwnd-title").dblclick(function() {
				if (isMax)
					isMax = false;
				else
					isMax = true;
				autoSize(_this, isMax);
			});
			$(exports).resize(function() {
				autoSize(_this, isMax);
			});
			$wnd.find(".popuwnd-title-maxsize-btn").click(function() {
				if (isMax)
					isMax = false;
				else
					isMax = true;
				autoSize(_this, isMax);
			});
		};

		var autoSize = function(_this, _isMax) {
			if (_isMax) {
				setPosition(0, 0);
				setSize(getWndWidth() - 18, getWndHeight() - 54);
			} else {
				setDefaultPosition(_this);
				_this.size();
			}
		};

		this.show = function() {
			$wnd.css("display", "block");
		};
		this.hidden = function() {
			$wnd.css("display", "none");
		};
		this.html = function(html) {
			if (html) {
				$wnd.find(".popuwnd-container iframe").contents().html(
						this.htmlContent);
			}
			return $wnd.find(".popuwnd-container iframe").contents().html();
		};

		var initLoad = function(_this) {
			$wnd.find(".iframe-main").load(function() {
				_this.load($wnd);
			});
		};

		this.load = function(wnd) {

		};
	};

	exports.PopuWnd.close = function() {
		$wnd = null;
		$("#popuwnd-backgroung").remove();
		$("#popuwnd").remove();
	};
})(jQuery, window);