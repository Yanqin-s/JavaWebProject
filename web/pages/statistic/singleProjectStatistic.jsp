<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/23
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <base href="http:localhost:8080/ITMS">
    <title>成本统计</title>
    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/loginSuccessful.css" />
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5.2.2/dist/echarts.min.js"></script>
    <script type="text/javascript">
        function refreshLook(){
            var obj = document.getElementById("selectedProject");
            var PID = obj.value;
            window.location.href=
                "${pageContext.request.contextPath}/singleProjectStatistic?selectedPID="+PID;
        }

    </script>
</head>
<body>
<div id="container">
    <%@include file="/pages/common/loginSuccseeMenuBar.jsp"%>
    <div class="main grid-container" style="padding-top: 40px;">
        <div class="item1" style="width: 240px;height:180px; " >
            <div style="margin-top: 10px;margin-bottom: 10px;">
                <select id="selectedProject"
                        style="text-align:center;
                            font-size: 40px;font-weight: bold;color: #E4007F;
                            border: none;width: 240px"
                        onchange="refreshLook()">

                    <c:if test="${requestScope.selectedPID!=null}">
                        <option value="${requestScope.selectedPID}" selected>${requestScope.project.p_Name}</option>
                    </c:if>
                    <c:if test="${requestScope.selectedPID==null}">
                        <option value="none" selected disabled hidden>请选择项目</option>
                    </c:if>

                    <c:forEach items="${sessionScope.inProjects}" var="project">
                        <option value=${project.p_ID}>${project.p_Name}</option>
                    </c:forEach>
                </select>
            </div>
            <p style="font-size: 21px">距离截止还有</p>
            <p align="center">
                <span style="font-weight: bold;font-size: 60px;color:#E4007F">${requestScope.project.p_DiffDay}</span>
                <span style="font-size: 15px">天</span>
            </p>
        </div>
        <c:if test="${requestScope.selectedPID==null}">
            <script type="text/javascript">alert("请选择项目！")</script>
        </c:if>
        <c:if test="${requestScope.selectedPID!=null}">

            <div class="item2" style="width: 740px;height: 500px;" >

                <div>
                    <table class="table_Statistic">
                        <tr style="font-size: 30px;color: #e4007f">
                            <td>项目总预算</td>
                            <td>当前花销</td>
                            <td>超预算任务数</td>
                        </tr>
                        <tr style="font-size: 25px;color: #e4007f">
                            <td>${requestScope.project.p_Budget}</td>
                            <td>${requestScope.project.p_AC}</td>
                            <td>${requestScope.OverBudgetNum}</td>
                        </tr>
                    </table>
                </div>
                <div style="display: flex;justify-content: space-around;width: 100%;height:400px;margin-top: 20px">
                    <div id="pic" style="height: 350px;width: 50%;">
                        <script type="text/javascript">
                            var chartDom = document.getElementById("pic");

                            var myChart = echarts.init(chartDom);
                            window.onresize = function() {
                                myChart.resize();
                            };
                            var option;

                            option = {
                                title: {
                                    text:"各任务预算",
                                    textStyle: {
                                        color:"#E4007F",
                                        fontSize:"15",
                                        fontWeight:"bold"
                                    },
                                    right:"40%",
                                    bottom:"10"
                                },
                                tooltip:{
                                    trigger:"item",
                                    formatter:'{a}<br>{b}:{c}({d}%)',
                                    backgroundColor: 'rgba(218,186,227,0.8)',
                                    text:{
                                        fontSize:"10"
                                    }
                                },

                                series: [
                                    {
                                        name:'各任务预算占比',
                                        center:['50%','50%'],
                                        type: "pie",
                                        data:[
                                            <c:forEach items="${requestScope.tasks}" var="task">
                                            {value: ${task.t_Budget},name: "${task.t_Name}"},
                                            </c:forEach>
                                        ],
                                        radius:["30%", "50%"],
                                        avoidLabelOverlap: false,
                                        label:{
                                            show:false,
                                            position: 'center',
                                            emphasis: {show:true,textStyle:{fontSize:'15',fontWeight:'bold'}}
                                        },
                                        labelLine: {show:false},
                                        emphasis:{label:{show:true,fontSize: '15',fontWeight: 'bold'}},
                                    }
                                ],
                                legend: {
                                    orient:"vertical",
                                    x:"left",
                                    data:[<c:forEach items="${requestScope.tasks}" var="task">
                                        "${task.t_Name}",
                                        </c:forEach>]
                                }
                            };

                            option && myChart.setOption(option);

                        </script>
                    </div>
                    <div id="pic2" style="height: 100%;width: 50%;">
                        <script type="text/javascript">
                            var chartDom = document.getElementById("pic2");
                            var myChart = echarts.init(chartDom);
                            window.onresize = function() {
                                myChart.resize();
                            };
                            var option;

                            option = {

                                title: {
                                    text:"各任务当前花销",
                                    textStyle: {
                                        color:"#E4007F",
                                        fontSize:"15",
                                        fontWeight:"bold"
                                    },
                                    right:"35%",
                                    bottom:"10"
                                },
                                tooltip:{
                                    trigger:"item",
                                    formatter:'{a}<br>{b}:{c}({d})%',
                                    backgroundColor: 'rgba(218,186,227,0.8)',
                                    text:{
                                        fontSize:"10"
                                    }
                                },
                                series: [
                                    {
                                        name:"各任务实际花销",
                                        type: "pie",
                                        data:[
                                            <c:forEach items="${requestScope.tasks}" var="task">
                                            {value: ${task.t_AC},name: "${task.t_Name}"},
                                            </c:forEach>
                                        ],
                                        radius:["30%", "50%"],
                                        avoidLabelOverlap: false,
                                        label:{
                                            show:false,
                                            position: 'center',
                                            emphasis: {show:true,fontSize: '15',fontWeight: 'bold'}
                                        },
                                        labelLine: {show:false},
                                        emphasis:{label:{show:true,fontSize: '15',fontWeight: 'bold'}},
                                    }
                                ],
                                legend: {
                                    orient:"vertical",
                                    x:"left",
                                    data:[<c:forEach items="${requestScope.tasks}" var="task">
                                        "${task.t_Name}",
                                        </c:forEach>]
                                },
                            };

                            option && myChart.setOption(option);


                        </script>
                    </div>
                </div>



            </div>
            <div class="item3" style="width: 370px" >
                <div id="pic3" style="height: 50px;width: 100%;margin-top: 15px;">
                </div>
                <script type="text/javascript">
                    var myChart = echarts.init(document.getElementById('pic3'));

                    var option;
                    var per_data = (${requestScope.tasks_2.size()*100/requestScope.tasks.size()}).toFixed(2);
                    option = {
                        grid: {   // 直角坐标系内绘图网格
                            left: '10',  //grid 组件离容器左侧的距离,
                                         //left的值可以是80这样具体像素值，
                            //也可以是'80%'这样相对于容器高度的百分比
                            top: '10',
                            right: '10',
                            bottom: '0',
                            containLabel: true   //gid区域是否包含坐标轴的刻度标签。为true的时候，
                            // left/right/top/bottom/width/height决定的是包括了坐标轴标签在内的
                            //所有内容所形成的矩形的位置.常用于【防止标签溢出】的场景
                        },
                        xAxis: {
                            //直角坐标系grid中的x轴,
                            //一般情况下单个grid组件最多只能放上下两个x轴,
                            //多于两个x轴需要通过配置offset属性防止同个位置多个x轴的重叠。
                            type: 'value',//坐标轴类型,分别有：
                                          //'value'-数值轴；'category'-类目轴;
                                          //'time'-时间轴;'log'-对数轴
                            splitLine: {show: false},//坐标轴在 grid 区域中的分隔线
                            axisLabel: {show: false},//坐标轴刻度标签
                            axisTick: {show: false},//坐标轴刻度
                            axisLine: {show: false},//坐标轴轴线
                        },
                        yAxis: {
                            type: 'category',
                            axisTick: {show: false},
                            axisLine: {show: false},
                            axisLabel: {
                                color: 'black',
                                fontSize: 15
                            },
                            data: ['']//类目数据，在类目轴（type: 'category'）中有效。
                            //如果没有设置 type，但是设置了axis.data,则认为type 是 'category'。
                        },
                        series: [//系列列表。每个系列通过 type 决定自己的图表类型
                            {
                                name: '%',//系列名称
                                type: 'bar',//柱状、条形图
                                barWidth: 19,//柱条的宽度,默认自适应
                                data: [per_data],//系列中数据内容数组
                                label: { //图形上的文本标签
                                    show: true,
                                    position: 'right',//标签的位置
                                    offset: [0,-20],  //标签文字的偏移，此处表示向上偏移40
                                    formatter: '{c}{a}',//标签内容格式器 {a}-系列名,{b}-数据名,{c}-数据值
                                    color: 'black',//标签字体颜色
                                    fontSize: 10  //标签字号
                                },
                                itemStyle: {//图形样式
                                    normal: {
                                        //normal 图形在默认状态下的样式;
                                        //emphasis图形在高亮状态下的样式
                                        barBorderRadius: 10,//柱条圆角半径,单位px.
                                                            //此处统一设置4个角的圆角大小;
                                        //也可以分开设置[10,10,10,10]顺时针左上、右上、右下、左下
                                        color: new echarts.graphic.LinearGradient(
                                            0, 0, 1, 0,//柱图渐变色起点颜色
                                            [{offset: 0, color: 'rgba(139,34,237,0.45)'}, {offset: 1, color: '#9127ce'}]
                                        )
                                    }
                                },
                                zlevel:1//柱状图所有图形的 zlevel 值,
                                //zlevel 大的 Canvas 会放在 zlevel 小的 Canvas 的上面
                            },
                            {
                                name: '进度条背景',
                                type: 'bar',
                                barGap: '-100%',//不同系列的柱间距离，为百分比。
                                // 在同一坐标系上，此属性会被多个 'bar' 系列共享。
                                // 此属性应设置于此坐标系中最后一个 'bar' 系列上才会生效，
                                //并且是对此坐标系中所有 'bar' 系列生效。
                                barWidth: 19,
                                data: [100],
                                color: 'rgba(228,0,127,0.51)',//柱条颜色
                                itemStyle: {normal: {barBorderRadius: 10}}
                            }
                        ]
                    }


                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                </script>
                <div style="height: 50px">
                    <table class="table_Statistic" >
                        <tr style="font-size: 20px;color: #e4007f">
                            <td>已完成</td>
                            <td>未完成</td>
                        </tr>
                        <tr style="font-size: 20px;color: #e4007f">
                            <td>${requestScope.tasks_2.size()}</td>
                            <td>${requestScope.tasks.size()-requestScope.tasks_2.size()}</td>
                        </tr>
                    </table>
                </div>

            </div>
            <div class="item4" style="width: 240px;">
                <p style="color:#E4007F;font-weight: bold;font-size: 15px;margin-top: 35px">Top3超前完成任务</p>
                <table class="table_min">
                    <tr style="background-color: rgba(218, 186, 227, 0.9);font-weight: bold">
                        <td>任务名称</td>
                        <td>负责人</td>
                        <td>提前天数</td>
                    </tr>
                    <c:if test="${requestScope.tasks_2.size()<3}">
                        <c:forEach items="${requestScope.tasks_2}" var="task">
                            <tr>
                                <td>${task.t_Name}</td>
                                <td>${task.t_OwnerName}</td>
                                <td>${task.t_EarlierDays}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${requestScope.tasks_2.size()>=3}">
                        <c:forEach var="i" begin="0" end="3">
                            <tr>
                                <td>${requestScope.tasks_2[i].t_Name}</td>
                                <td>${requestScope.tasks_2[i].t_OwnerNe}</td>
                                <td>${requestScope.tasks_2[i].t_EarlierDays}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
            <div class="item5" style="width: 370px;">
                <div id="pic4" style="height:300px;width: 100%;margin-top: 20px "></div>
                <script type="text/javascript">
                    //每个成员要完成的任务，以及实际完成的任务数量
                    var myChart = echarts.init(document.getElementById('pic4'));
                    var option;
                    option={
                        title: {
                            text:"各成员任务完成情况",
                            textStyle: {
                                color:"#E4007F",
                                fontSize:"15",
                                fontWeight:"bold"
                            },
                            left:"0%",
                            top:'0'
                        },
                        tooltip:{
                            trigger: 'axis',
                            axisPointer: {
                                // Use axis to trigger tooltip
                                type: 'shadow' // 'shadow' as default; can also be 'line' or 'shadow'
                            }
                        },
                        legend:{
                            show:true,
                            top:"8%",
                        },
                        grid:{
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'value'
                        },
                        yAxis: {
                            type: 'category',
                            axisTick: {show: false},
                            data: [<c:forEach items="${requestScope.statisticTNumOfMemberList}" var="statisticTNumOfMember">
                                "${statisticTNumOfMember.t_OwnerName}",
                                </c:forEach>
                            ]
                        },
                        series:[
                            {
                                name:'已完成',
                                type:'bar',
                                barWidth: 15,//柱条的宽度,默认自适应
                                // barCategoryGap:"15",
                                stack:"total",
                                label:{show:true},
                                emphasis:{focus:"series"},
                                data:[
                                    <c:forEach items="${requestScope.statisticTNumOfMemberList}" var="statisticTNumOfMember">
                                    ${statisticTNumOfMember.doneTaskNum},
                                    </c:forEach>
                                ],
                                color: 'rgba(139,34,237,0.45)',//柱条颜色
                                itemStyle: {//图形样式
                                    normal: {
                                        //normal 图形在默认状态下的样式;
                                        //emphasis图形在高亮状态下的样式
                                        barBorderRadius: 10,//柱条圆角半径,单位px.
                                                            //此处统一设置4个角的圆角大小;
                                        //也可以分开设置[10,10,10,10]顺时针左上、右上、右下、左下
                                        // color: new echarts.graphic.LinearGradient(
                                        //     0, 0, 1, 0,//柱图渐变色起点颜色
                                        //     [{offset: 0, color: 'rgba(139,34,237,0.45)'}, {offset: 1, color: '#9127ce'}]
                                        // )
                                    }
                                },
                                zlevel:1,//柱状图所有图形的 zlevel 值,
                                //zlevel 大的 Canvas 会放在 zlevel 小的 Canvas 的上面
                                // itemStyle: {normal: {barBorderRadius: 10}}
                            },
                            {
                                name:'总任务数量',
                                type: 'bar',
                                barGap: '-100%',//不同系列的柱间距离，为百分比。
                                barWidth:"15",
                                label:{show:true},
                                emphasis:{focus:"series"},
                                // barGap: '-100%',//不同系列的柱间距离，为百分比。
                                // 在同一坐标系上，此属性会被多个 'bar' 系列共享。
                                // 此属性应设置于此坐标系中最后一个 'bar' 系列上才会生效，
                                //并且是对此坐标系中所有 'bar' 系列生效。
                                barWidth: 15,
                                data:[
                                    <c:forEach items="${requestScope.statisticTNumOfMemberList}" var="statisticTNumOfMember">
                                    ${statisticTNumOfMember.taskNum},
                                    </c:forEach>
                                ],
                                color: 'rgba(228,0,127,0.45)',//柱条颜色
                                itemStyle: {normal: {barBorderRadius: 10}}
                            }
                            // {
                            //     name:'总任务数量',
                            //     type:'bar',
                            //     stack:"total",
                            //
                            //
                            //     color: 'rgba(228,0,127,0.8)',//柱条颜色
                            //     itemStyle: {normal: {barBorderRadius: 10}}
                            // }
                        ]
                    }
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                </script>
            </div>
            <div class="item6" style="width: 100%">
                <!--各个任务距离截止时间-->

                <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
                <div id="pic_leftDown" style="width: 100%;height:300px;"></div>
                <script type="text/javascript">
                    var myChart = echarts.init(document.getElementById('pic_leftDown'));
                    var option;
                    // 基于准备好的dom，初始化echarts实例
                    let dataAxis = [
                        <c:forEach items="${requestScope.tasks_1}" var="task">
                        "${task.t_Name}",
                        </c:forEach>
                    ];
                    let data = [
                        <c:forEach items="${requestScope.tasks_1}" var="task">
                        ${task.t_DiffDay},
                        </c:forEach>
                    ];
                    let yMax=${requestScope.project.p_DiffDay};
                    let dataShadow = [];
                    for(let i=0;i<data.length;i++){
                        dataShadow.push(yMax);
                    }

                    option = {
                        title:{
                            text:"各任务期限",
                            textStyle: {
                                color:"#E4007F",
                                fontSize:"15",
                                fontWeight:"bold"
                            }
                        },
                        tooltip:{
                            trigger: 'axis',
                            axisPointer: {
                                // Use axis to trigger tooltip
                                type: 'shadow' // 'shadow' as default; can also be 'line' or 'shadow'
                            }
                        },
                        xAxis: {
                            data: dataAxis,
                            axisLabel: {
                                inside: true,
                                color: '#070707'
                            },
                            axisTick: {
                                show: false
                            },
                            axisLine: {
                                show: false
                            },
                            z: 10
                        },
                        yAxis: {
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            axisLabel: {
                                color: 'rgba(153,153,153,0.44)'
                            }
                        },
                        dataZoom: [
                            {
                                type: 'inside'
                            }
                        ],
                        series:[
                            {
                                type: 'bar',
                                showBackground: true,
                                itemStyle: {
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                        { offset: 0, color: 'rgba(145,39,206,0.25)' },
                                        { offset: 0.5, color: 'rgba(228,0,127,0.34)' },
                                        { offset: 1, color: '#e4007f' }
                                    ])
                                },
                                emphasis: {
                                    itemStyle: {
                                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                            { offset: 0, color: '#e4007f' },
                                            { offset: 0.7, color: 'rgba(145,39,206,0.25)' },
                                            { offset: 1, color: 'rgba(228,0,127,0.51)' }
                                        ])
                                    }
                                },
                                data: data
                            }
                        ]
                    }
                    // Enable data zoom when user click bar.
                    const zoomSize = 6;
                    myChart.on('click', function (params) {
                        console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
                        myChart.dispatchAction({
                            type: 'dataZoom',
                            startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                            endValue:
                                dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
                        });
                    });

                    option && myChart.setOption(option);

                    // 指定图表的配置项和数据

                </script>

            </div>
<%--            <div class="item7" >--%>
<%--                <div style="height: 300px;width: 600px;" id="pic_rightDowm">--%>
<%--                </div>--%>
<%--                <script type="text/javascript">--%>

<%--                </script>--%>
<%--            </div>--%>
        </c:if>



    </div>
    <footer>
        <a href="https://www.vip.com/">© VIP.COM 版权所有</a>
    </footer>
</div>
</body>
</html>
