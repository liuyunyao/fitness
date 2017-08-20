$(function() {
	configEchart();
});

function configEchart() {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));

	// 指定图表的配置项和数据
	option = {
		tooltip: {
			trigger: 'axis'
		},
		calculable: true,
		color: ['#33caff', '#ea5e59', '#9b9b9b'],
		legend: {
			x: 'left',
			itemWidth: 10, //图例标记的图形宽度
			itemHeight: 10, //图例标记的图形高度
			data: [{
					name: '速度',
					textStyle: {
						fontSize: '0.9rem',
						color: '#9b9b9b'
					}
				},
				{
					name: '坡度',
					textStyle: {
						fontSize: '0.9rem',
						color: '#9b9b9b'
					}
				},
				{
					name: '时间（分钟）',
					icon: 'line',
					textStyle: {
						fontSize: '0.9rem',
						color: '#9b9b9b'
					}
				},
				'速度', '坡度', '时间（分钟）'
			]
		},
		grid: {
			left: '0%',
			right: '0%',
			bottom: '5%',
			containLabel: true
		},
		xAxis: {
			show: false,
			type: 'category',
			boundaryGap: true,
			data: ['课时1', '课时2', '课时3', '课时4', '课时5', '课时6', '课时7', '课时8', '课时9', '课时10','课时1', '课时2', '课时3']
		},
		yAxis: [{
				show: false,
				type: 'value'
			},
			{
				show: false,
				type: 'value'
			}
		],
		series: [{
			name: '速度',
			type: 'bar',
			//stack: '总量',
			data: ["12", "13", "10", "13", "9", "8", "21","12", "13","12", "13", "10", "13"]
		}, {
			name: '坡度',
			type: 'line',
			//stack: '总量',
			//yAxisIndex: 1,
			data: [21, 21, 21, 11, 21, 21, 21, 21, 21, 2,5, 6, 5]
		}, {
			name: '时间（分钟）',
			type: 'bar',
			//yAxisIndex: 1,
			data: [3, 2, 3, 2, 3, 2, 3, 2, 2, 3,3, 2, 3]
		}]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}