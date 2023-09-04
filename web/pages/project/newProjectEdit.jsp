<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/16
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <base href="http:localhost:8080/ITMS">
    <title>配置新项目:${requestScope.project.p_Name}</title>
    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/loginSuccessful.css" />
</head>
<body>
    <div id="container">
        <%@include file="/pages/common/loginSuccseeMenuBar.jsp"%>
        <section class="main">
            <table class="table_norm" name = "newProjectDetails">
                <tr class="first-tr">
                    <td>项目名称</td>
                    <td>项目描述</td>
                    <td>项目负责人</td>
                    <td>项目预算(RMB)</td>
                    <td>所属部门</td>
                    <td>截止时间</td>

                </tr>
                <tr>
                    <td>${requestScope.project.p_Name}</td>
                    <td>${requestScope.project.p_Description}</td>
                    <td>${requestScope.project.p_MName}</td>
                    <td>${requestScope.project.p_Budget}</td>
                    <td>${requestScope.project.p_Dpmt}</td>
                    <td>${requestScope.project.p_DeadLine}</td>
                </tr>
            </table>

            <table class="table_norm">
                <tr class="first-tr">
                    <!--
                    1.任务阶段用于任务ID生成
                    2.动态添加时，需要将任务阶段和当前项目的ID作为参数传到servlet中
                    3.servlet中addTask方法，默认t_Status=0
                    4.负责人使用select标签
                    -->
                    <td><label>任务ID</label></td>
                    <td><label>任务名称</label></td>
                    <td><label>任务描述</label></td>
                    <td><label>截止时间</label></td>
                    <td><label>任务预算</label></td>
                    <td><label>负责人</label></td>
                    <td colspan="2"><label>操作</label></td>
                </tr>
                <c:forEach  items="${requestScope.tasks}" var="task">
                    <tr>
                        <td>${task.t_ID}</td>
                        <td>${task.t_Name}</td>
                        <td>${task.t_Description}</td>
                        <td>${task.t_DeadLine}</td>
                        <td>${task.t_Budget}</td>
                        <td>${task.t_OwnerName}</td>
                        <td><a href="">修改</a></td>
                        <td><a href="/ITMS/deleteTaskServlet?t_ID=${task.t_ID}&p_ID=${requestScope.project.p_ID}">删除</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td colspan="2">
                        <a href="/ITMS/pages/task/addTask.jsp?t_PID=${requestScope.project.p_ID}&from=new">
                            <button>添加任务</button>
                        </a>
                    </td>

                </tr>
            </table>



        </section>
        <footer>
            <a href="https://www.vip.com/">© VIP.COM 版权所有</a>
        </footer>
    </div>
</body>
</html>
