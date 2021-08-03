$(document).ready(
		function() {
			var o, e = function() {
				$("#sparkline1").sparkline(
						[ 0, 23, 43, 35, 44, 45, 56, 37, 40 ], {
							type : "line",
							width : "100%",
							height : 210,
							chartRangeMax : 50,
							lineColor : "#3bafda",
							fillColor : "rgba(59,175,218,0.3)",
							highlightLineColor : "rgba(0,0,0,.1)",
							highlightSpotColor : "rgba(0,0,0,.2)",
							maxSpotColor : !1,
							minSpotColor : !1,
							spotColor : !1,
							lineWidth : 1
						}), $("#sparkline1").sparkline(
						[ 25, 23, 26, 24, 25, 32, 30, 24, 19 ], {
							type : "line",
							width : "100%",
							height : "210",
							chartRangeMax : 40,
							lineColor : "#1abc9c",
							fillColor : "rgba(26, 188, 156, 0.3)",
							composite : !0,
							highlightLineColor : "rgba(0,0,0,.1)",
							highlightSpotColor : "rgba(0,0,0,.2)",
							maxSpotColor : !1,
							minSpotColor : !1,
							spotColor : !1,
							lineWidth : 1
						}), $("#sparkline2").sparkline(
						[ [ 70, 40 ], [ 90, 50 ], [ 100, 150 ], [ 140, 80 ],
								[ 50, 90 ], [ 80, 120 ], [ 130, 80 ],
								[ 90, 70 ], [ 80, 50 ], [ 120, 130 ],
								[ 120, 100 ], [ 140, 110 ] ],
						{
							type : "bar",
							height : "210",
							barWidth : "15",
							barSpacing : "3",
							stackedBarColor : [ "#3bafda",
									"rgba(188, 196, 201, 0.2)" ]
						}), $("#sparkline3").sparkline([ 20, 40, 30 ], {
					type : "pie",
					width : "210",
					height : "210",
					sliceColors : [ "#e3eaef", "#3bafda", "#00b19d" ]
				})
			};
			e(), $(window).resize(function(i) {
				clearTimeout(o), o = setTimeout(function() {
					e()
				}, 300)
			})
		});