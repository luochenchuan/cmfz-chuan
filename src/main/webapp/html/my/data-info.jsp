<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTDF-8" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${app}/js/jquery-1.10.1.min.js"></script>
    <%-- echarts的js --%>
    <script src="${app}/js/echarts.min.js"></script>
    <script src="${app}/js/china.js"></script>
    <%-- GoEasy的js --%>
    <script src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script>

        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io',  //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BS-84a73d0a7a7c44808401ee67be4bef3b",  //替换为您的应用appkey   这里只需要订阅
        });

        $(function () {

            /* 初始化每月注册的人数 */
            var echarsMonth = echarts.init($("#echars-month").get(0));
            var echarsMonthOption = {
                title: {
                    text: '月注册量',
                    subtext: "每月注册的人数"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {},
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {},
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name:'月注册量',
                        type:'line',
                        stack: '数量',
                        data:[120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 20]
                    }
                ]
            };
            echarsMonth.setOption(echarsMonthOption);

            /* ajax初始化数据 */
            $.ajax({
                url: "${app}/echarts/monthCount",
                datatype: "json",
                success: function (result) {
                    echarsMonth.setOption({
                        series: [
                            {
                                data:result
                            }
                        ]
                    });
                }
            })

            /* GoEasy订阅消息 */
            goEasy.subscribe({
                channel: "month-register", //替换为您自己的channel
                onMessage: function (message) {
                    console.log("Channel:" + message.channel + " content:" + message.content);
                    echarsMonth.setOption({
                        series: [
                            {
                                data:JSON.parse(message["content"])
                            }
                        ]
                    });
                }
            });
            /* 初始化每月注册的人数结束 */



            /* 初始化最近七天注册的人数 */
            var echartsDay = echarts.init($("#echars-day").get(0));
            var echartsDayOption = {
                color: ['#3398DB'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [

                    {
                        name:'直接访问',
                        type:'bar',
                        barWidth: '60%',
                        data:[10, 52, 200, 334, 390, 330, 220]
                    }
                ]
            };
            echartsDay.setOption(echartsDayOption);
            /* ajax初始化数据 */
            $.ajax({
                url: "${app}/echarts/sevenDays",
                datatype: "json",
                success: function (result) {
                    echartsDay.setOption({
                        xAxis : [
                            {
                                data : result["days"]
                            }
                        ],
                        series : [
                            {
                                data:result["count"]
                            }
                        ]
                    })
                }
            })

            /* GoEasy订阅消息 */
            goEasy.subscribe({
                channel: "my_channel", //替换为您自己的channel
                onMessage: function (message) {
                    console.log("Channel:" + message.channel + " content:" + message.content);
                }
            });
            /* 初始化最近七天的注册人数结束 */


            /* 初始化地图的人数 */
            var echarsMap = echarts.init($("#echars-map").get(0));
            var echarsMapOption = {
                title : {
                    text: '省份关注人数',
                    left: 'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data:["人数"]
                },
                visualMap: {
                    min: 0,
                    max: 2500,
                    left: 'left',
                    top: 'bottom',
                    text:['高','低'],
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    left: 'right',
                    top: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name: '人数',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        }
                    }
                ]
            };
            echarsMap.setOption(echarsMapOption);
            /* ajax初始化数据 */
            $.ajax({
                url: "${app}/echarts/registerByRegion",
                datatype: "json",
                success: function (result) {
                    echarsMap.setOption({
                        series : [
                            {
                                data:result
                            }
                        ]
                    })
                }
            })

            /* GoEasy订阅消息 */
            goEasy.subscribe({
                channel: "my_channel", //替换为您自己的channel
                onMessage: function (message) {
                    console.log("Channel:" + message.channel + " content:" + message.content);
                }
            });
            /* 初始化地图的人数结束 */
        })
    </script>
</head>
<body>

    <div id="echars-month" style="height: 400px; width: 900px;"></div>

    <div id="echars-day" style="height: 400px; width: 900px;"></div>

    <div id="echars-map" style="height: 400px; width: 900px;"></div>

</body>
</html>