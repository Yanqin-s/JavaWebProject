<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/15
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <base href="http:localhost:8080/ITMS">
    <title>查看项目</title>
    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/loginSuccessful.css" />
</head>
<body>
<div id="container">
    <%@include file="/pages/common/loginSuccseeMenuBar.jsp"%>
    <section class="main">
        <div class="num_title">
            <c:if test="${requestScope.p_Status==0}">
                <c:if test="${requestScope.projects.size()==0}">
                    <p>您暂时没有新项目</p>
                </c:if>
                <c:if test="${requestScope.projects.size()!=0}">
                    <p >您有${requestScope.projects.size()}个新项目未领取！</p>
                </c:if>
            </c:if>
            <c:if test="${requestScope.p_Status==1}">
                <c:if test="${requestScope.projects.size()==0}">
                    <p>您没有在研项目记录！</p>
                </c:if>
                <c:if test="${requestScope.projects.size()!=0}">
                    <p style="float: left">您有${requestScope.projects.size()}条在研项目记录！</p>
                </c:if>
            </c:if>
            <c:if test="${requestScope.p_Status==2}">
                <c:if test="${requestScope.projects.size()==0}">
                    <p>您没有已完成项目记录！</p>
                </c:if>
                <c:if test="${requestScope.projects.size()!=0}">
                    <p style="float: left">您已完成${requestScope.projects.size()}个项目！</p>
                </c:if>
            </c:if>
        </div>
        <c:if test="${requestScope.projects.size()!=0}">
            <table class="table_norm">
                <tr class="first-tr">
                    <td style="width: 15%">项目名称</td>
                    <td style="width: 30%">项目描述</td>

                    <td style="width: 15%">项目预算</td>
                    <td style="width: 15%">所属部门</td>
                    <td style="width: 15%">截止时间</td>
                    <td colspan="2"  style="width: 10%"><label>操作</label></td>
                </tr>
                <c:forEach items="${requestScope.projects}" var="project">
                    <tr>
                        <td>${project.p_Name}</td>
                        <td>${project.p_Description}</td>
                        <td>${project.p_Budget}</td>
                        <td>${project.p_Dpmt}</td>
                        <td>${project.p_DeadLine}</td>
                        <c:if test="${requestScope.p_Status==0}">
                            <td><a href="/ITMS/newProjectEditServlet?p_ID=${project.p_ID}&p_MName=${project.p_MName}">领取</a></td>
                            <td><a href="">拒绝</a></td>
                        </c:if>
                        <c:if test="${requestScope.p_Status!=0}">
                            <td colspan="2"><a href="/ITMS/doingProjectDetailsServlet?p_ID=${project.p_ID}">查看详情</a></td>
                        </c:if>

                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </section>
    <footer>
        <a href="https://www.vip.com/">© VIP.COM 版权所有</a>
    </footer>
</div>
</body>
</html>
