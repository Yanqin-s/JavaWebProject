<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/20
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>

<!--
查看任务详细的三种用户：
1.项目经理：可见该页面的所有链接、所有信息：
    ①前置任务ID（链接）
    ②后置任务ID（链接）
    ③所属项目（链接），链接到DoingProjectDetailsServlet
    ④负责人（链接），点击可查看负责的其他任务,待做
2.任务负责人：
    ①前置任务ID（链接）
    ②后置任务ID（链接）
    ③负责人（链接），点击可查看负责的其他任务,待做
3.非项目经理、非该任务负责人：
    不可见任务的预算、消耗
    ①前置任务ID（链接）
    ②后置任务ID（链接）
-->


<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <base href="http:localhost:8080/ITMS">
    <title>查看在研项目</title>
    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/loginSuccessful.css" />
</head>
<body>
    <div id="container">
        <%@include file="/pages/common/loginSuccseeMenuBar.jsp"%>
        <section class="main">
            <table class="table_norm">
                <tr class="first-tr">
                    <td colspan="4" >${requestScope.task.t_Name}</td>
                </tr>
                <tr>
                    <td class="td_label" > ID</td>
                    <td >${requestScope.task.t_ID}</td>
                    <td class="td_label"><label>任务负责人</label></td>
                    <c:if test="${sessionScope.user.u_ID==requestScope.project.p_MID
                    or sessionScope.user.u_ID==requestScope.task.t_OwnerID}">
                        <td ><a href="/ITMS/myTaskLookServlet?t_Status=1&PID=${requestScope.task.t_PID}&OwnerID=${requestScope.task.t_OwnerID}">${requestScope.task.t_OwnerName}</a></td>
                    </c:if>
                    <c:if test="${sessionScope.user.u_ID!=requestScope.project.p_MID
                    and sessionScope.user.u_ID!=requestScope.task.t_OwnerID}">
                        <td>${requestScope.task.t_OwnerName}</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="td_label">前置任务ID</td>
                    <td><a href="/ITMS/taskDetailsServlet?t_ID=${requestScope.task.t_PreTID}">${requestScope.task.t_PreTID}</a></td>
                    <td class="td_label">后置任务ID</td>
                    <td><a href="/ITMS/taskDetailsServlet?t_ID=${requestScope.task.t_PostTID}">${requestScope.task.t_PostTID}</a>
                </tr>
                <tr>
                    <td class="td_label">开始日期</td>
                    <td>${requestScope.task.t_StartDate}</td>
                    <td class="td_label">截止日期</td>
                    <td>${requestScope.task.t_DeadLine}</td>
                </tr>

                <c:if test="${sessionScope.user.u_ID==requestScope.task.t_OwnerID
                or sessionScope.user.u_ID==requestScope.project.p_MID}">
                    <tr>
                        <td class="td_label">预算</td>
                        <td >${requestScope.task.t_Budget}</td>
                        <td class="td_label">当前花销</td>
                        <td>${requestScope.task.t_AC}</td>
                    </tr>
                </c:if>
                <tr>
                    <td class="td_label"><label>所属项目</label></td>
                    <c:if test="${sessionScope.user.u_ID==requestScope.project.p_MID}">
                        <td><a href="/ITMS/doingProjectDetailsServlet?p_ID=${requestScope.project.p_ID}">${requestScope.task.t_PName}</a></td>
                    </c:if>
                    <c:if test="${sessionScope.user.u_ID!=requestScope.project.p_MID}">
                        <td>${requestScope.task.t_PName}</td>
                    </c:if>
                </tr>

                <tr>
                    <td class="td_label"><label>任务描述</label></td>
                    <td colspan="3">${requestScope.task.t_Description}</td>
                </tr>
                <tr>
                    <td class="td_label"><label>任务文档</label></td>
                    <td colspan="3">${requestScope.task.t_txt}</td>
                </tr>


            </table>
            <div align="center">
                <!--如果此时的登录用户是任务负责人，则显示修改的按钮，点击-->
                <c:if test="${sessionScope.user.u_ID==requestScope.task.t_OwnerID}">

                    <a href="/ITMS/editTaskServlet?t_ID=${requestScope.task.t_ID}"><button>编辑</button></a>

                </c:if>
            </div>
        </section>
        <footer>
            <a href="https://www.vip.com/">© VIP.COM 版权所有</a>
        </footer>
    </div>

</body>
</html>
