<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/19
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <base href="http:localhost:8080/ITMS">
    <title>查看在研项目</title>
    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/loginSuccessful.css" />
    <script type="text/javascript">

    </script>
</head>
<body>
    <div id="container">
        <%@include file="/pages/common/loginSuccseeMenuBar.jsp"%>
        <section class="main">

            <%
                String getInfo = (String)request.getAttribute("getInfo");         // 获取错误属性
                if(getInfo != null) {
            %>
            <script type="text/javascript" language="javascript">
                alert("<%=getInfo%>");                                            // 弹出错误信息
            </script>
            <%
                }
            %>
            <h3 style="margin-left: 5%;">项目详情</h3>
            <table class="table_norm" name="projectDetails">
                <tr class="first-tr">
                    <td style="width: 15%"><label>项目名称</label></td>
                    <td style="width: 25%"><label>项目描述</label></td>
                    <td style="width: 15%"><label>项目预算(RMB)</label></td>
                    <td style="width: 15%"><label>所属部门</label></td>
                    <td style="width: 15%"><label>截止时间</label></td>
                    <td style="width: 15%"><label>当前成本(RMB)</label></td>
                </tr>
                <tr>
                    <td>${requestScope.project.p_Name}</td>
                    <td>${requestScope.project.p_Description}</td>
                    <td>${requestScope.project.p_Budget}</td>
                    <td>${requestScope.project.p_Dpmt}</td>
                    <td>${requestScope.project.p_DeadLine}</td>
                    <td>${requestScope.project.p_AC}</td>
                </tr>

            </table>
            <h3 class="h_">任务列表</h3>
            <h4 class="h_">待领取任务</h4>
            <c:if test="${requestScope.tasks_0.size()!=0}">
                <table class="table_norm" name = "newTaskList">
                    <tr class="first-tr">

                        <td style="width: 15%"><label>任务名称</label></td>
                        <td style="width: 30%"><label>任务描述</label></td>
                        <td style="width: 15%"><label>截止时间</label></td>
                        <td style="width: 15%"><label>任务预算(RMB)</label></td>
                        <td style="width: 15%"><label>负责人</label></td>
                        <td style="width: 10%" ><label>操作</label></td>
                    </tr>

                    <c:forEach  items="${requestScope.tasks_0}" var="task">
                        <tr>
                            <td>${task.t_Name}</td>
                            <td>${task.t_Description}</td>
                            <td>${task.t_DeadLine}</td>
                            <td>${task.t_Budget}</td>
                            <td>${task.t_OwnerName}</td>
                            <td>
                                <a href="/ITMS/newsGenerateServlet?t_ID=${task.t_ID}&btnType=getTask&p_ID=${task.t_PID}"><button>提醒领取</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="7" style="text-align: right;">
                            <a href="/ITMS/pages/task/addTask.jsp?t_PID=${requestScope.project.p_ID}&from=doing">
                                <button style="margin-right: 30px;">添加任务</button>
                            </a>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${requestScope.tasks_0.size()==0}">
                <div align="center">
                    <span>任务已全部被领取!</span>
                    <span style="float: right;margin-right: 10%"><a href="/ITMS/pages/task/addTask.jsp?t_PID=${requestScope.project.p_ID}&from=doing">
                        <button>添加任务</button>
                    </a></span>
                </div>
            </c:if>
            <h4 class="h_">在研任务</h4>
            <c:if test="${requestScope.tasks_1.size()!=0}">
                <table name = "doingTaskList" class="table_norm">
                <tr class="first-tr">
                    <td style="width: 15%"><label>任务名称</label></td>
                    <td style="width: 25%;overflow: hidden"><label>任务描述</label></td>
                    <td style="width: 10%"><label>开始时间</label></td>
                    <td style="width: 10%"><label>截止时间</label></td>
                    <td style="width: 10%"><label>任务预算(RMB)</label></td>
                    <td style="width: 10%"><label>当前花销(RMB)</label></td>
                    <td style="width: 10%"><label>负责人</label></td>
                    <td style="width: 10%" colspan="2"><label>操作</label></td>

                </tr>
                <c:forEach  items="${requestScope.tasks_1}" var="task">
                    <tr>
                        <td>${task.t_Name}</td>
                        <td>${task.t_Description}</td>
                        <td>${task.t_StartDate}</td>
                        <td>${task.t_DeadLine}</td>
                        <td>${task.t_Budget}</td>
                        <td>${task.t_AC}</td>
                        <td>${task.t_OwnerName}</td>
                        <td>
                            <a href="/ITMS/newsGenerateServlet?t_ID=${task.t_ID}&btnType=doTask&p_ID=${task.t_PID}"><button>催促</button></a>
                        </td>
                        <td>
                            <a href="/ITMS/taskDetailsServlet?t_ID=${task.t_ID}"><button>查看</button></a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
            </table>
            </c:if>
            <c:if test="${requestScope.tasks_1.size()==0}">
                <p align="center">暂无！</p>
            </c:if>

            <h4 class="h_">已完成任务</h4>
            <c:if test="${requestScope.tasks_2.size()!=0}">
                <table class="table_norm" name = "doneTaskList">
                    <tr class="first-tr">
                        <td style="width: 15%"><label>任务名称</label></td>
                        <td style="width: 20%;overflow: hidden"><label>任务描述</label></td>
                        <td style="width: 10%"><label>开始时间</label></td>
                        <td style="width: 10%"><label>截止时间</label></td>
                        <td style="width: 10%"><label>任务预算(RMB)</label></td>
                        <td style="width: 10%"><label>实际花销(RMB)</label></td>
                        <td style="width: 10%"><label>负责人</label></td>
                        <td style="width: 10%"><label>评价</label></td>
                        <td style="width: 5%"></td>
                    </tr>

                    <c:forEach  items="${requestScope.tasks_2}" var="task">
                        <tr>

                            <td>${task.t_Name}</td>
                            <td>${task.t_Description}</td>
                            <td>${task.t_StartDate}</td>
                            <td>${task.t_DeadLine}</td>
                            <td>${task.t_Budget}</td>
                            <td>${task.t_AC}</td>
                            <td>${task.t_OwnerName}</td>

                            <c:if test="${task.t_Evaluation==null}">
                                <td><a href="/ITMS/newProjectEditServlet?id=${project.p_ID}&p_MName=${project.p_MName}">写评价</a></td>
                            </c:if>
                            <c:if test="${task.t_Evaluation!=null}">
                                <td>${task.t_Evaluation}</td>
                            </c:if>
                            <td>
                                <a href="/ITMS/taskDetailsServlet?t_ID=${task.t_ID}"><button>查看</button></a>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </c:if>
            <c:if test="${requestScope.tasks_2.size()==0}">
                <p align="center">暂无！</p>
            </c:if>


        </section>
        <footer>
            <a href="https://www.vip.com/">© VIP.COM 版权所有</a>
        </footer>
    </div>

</body>
</html>
