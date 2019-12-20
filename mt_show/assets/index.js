 var chart;
 $(function() {

 	chart = new Highcharts.Chart({
 		chart: {
 			renderTo: 'morris-area-chart',
 			defaultSeriesType: 'line',
 			backgroundColor: '#f7f8ec',
 			plotBorderWidth: 1,
 			plotBackgroundColor: '#ffffff',
 			shadow: true,
 			borderRadius: 10,
 			plotShadow: true
 		},
 		exporting: {
 			enabled: false
 		},
 		title: {
 			text: '每日订单及环比情况',
 			x: -20
 		},
 		xAxis: {
 			categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
 		},
 		yAxis: {
 			title: {
 				text: ''
 			},
 			plotLines: [{
 				value: 0,
 				width: 1,
 				color: '#808080'
 			}]
 		},
 		tooltip: {
 			formatter: function() {
 				return '<b>' + this.series.name + '</b><br/>' + this.x + ': ' + this.y;
 			}
 		},
 		plotOptions: {
 			line: {
 				dataLabels: {
 					enabled: true
 				},
 				enableMouseTracking: false
 			}
 		},
 		series: []
 	});
 	$.ajax({
 		url: "http://10.0.88.248:8080/listStatsLocation/2018-08-18",
 		cache: false
 	}).success(function(data) {
 		for (i = 0; i < data.length; i++) chart.addSeries(data[i].sessions);
 	});
 })