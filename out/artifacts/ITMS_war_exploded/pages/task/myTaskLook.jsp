<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/21
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <base href="http:localhost:8080/ITMS">
    <title>查看任务</title>
    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/loginSuccessful.css" />
    <script type="text/javascript">
        function refreshLook(){
            var obj = document.getElementById("selectedProject");
            var PID = obj.value;
            window.location.href=
                "${pageContext.request.contextPath}/myTaskLookServlet?t_Status=${requestScope.t_Status}&selectedPID="+PID;
        }
    </script>
</head>
<body>
<div id="container">
    <%@include file="/pages/common/loginSuccseeMenuBar.jsp"%>
    <section class="main">
        <c:if test="${sessionScope.inProjects.size()!=0}">
            <span style="float: right;">

                <select id="selectedProject" style="float: right;margin-top: 5px;" onchange="refreshLook()">

                    <!--设置默认选择的项目-->
                    <c:if test="${requestScope.selectedPID!=null}">
                        <option value="${requestScope.selectedPID}" selected></option>
                    </c:if>
                    <c:if test="${requestScope.selectedPID==null}">
                        <option value="none" selected disabled hidden>请选择项目</option>
                    </c:if>

                    <c:forEach items="${sessionScope.inProjects}" var="project">
                            <option value=${project.p_ID}>${project.p_Name}</option>
                    </c:forEach>
                </select>


            </span>
        </c:if>
        <div class="num_title">
            <c:if test="${requestScope.t_Status==0}">
                <c:if test="${requestScope.tasks.size()==0}">
                    <p>您暂时没有新任务</p>
                </c:if>
                <c:if test="${requestScope.tasks.size()!=0}">
                    <p >您有${requestScope.tasks.size()}个新任务未领取！</p>
                </c:if>
            </c:if>
            <c:if test="${requestScope.t_Status==1}">
                <c:if test="${requestScope.tasks.size()==0}">
                    <p>您没有在研任务记录！</p>
                </c:if>
                <c:if test="${requestScope.tasks.size()!=0}">
                    <p style="float: left">您有${requestScope.tasks.size()}条在研任务记录！</p>
                </c:if>
            </c:if>
            <c:if test="${requestScope.t_Status==2}">
                <c:if test="${requestScope.tasks.size()==0}">
                    <p>您没有已完成任务记录！</p>
                </c:if>
                <c:if test="${requestScope.tasks.size()!=0}">
                    <p style="float: left">您已完成${requestScope.tasks.size()}个任务！</p>
                </c:if>
            </c:if>
        </div>
        <c:if test="${requestScope.tasks.size()!=0}">
            <table class="table_norm">
                <tr class="first-tr">
                    <td style="width: 15%;"><label>任务名称</label></td>
                    <td style="width: 35%;"><label>任务描述</label></td>
                    <td style="width: 10%;"><label>任务预算(RMB)</label></td>
                    <td style="width: 15%;"><label>所属项目</label></td>
                    <td style="width: 10%;"><label>截止时间</label></td>
                    <td style="width: 15%;" colspan="2"><label>操作</label></td>
                </tr>
                <c:forEach items="${requestScope.tasks}" var="task">
                    <tr>
                        <td>${task.t_Name}</td>
                        <td>${task.t_Description}</td>
                        <td>${task.t_Budget}</td>
                        <td>${task.t_PName}</td>
                        <td>${task.t_DeadLine}</td>
                        <c:if test="${task.t_Status==0}">
                            <td><a href="/ITMS/getTaskServlet?t_ID=${task.t_ID}&selectedPID=${requestScope.selectedPID}"><button>领取</button></a></td>
                            <td><a href=""><button>拒绝</button></a></td>
                        </c:if>
                        <c:if test="${task.t_Status!=0}">
                            <td colspan="2"><a id="details" href="/ITMS/taskDetailsServlet?t_ID=${task.t_ID}">查看详情</a></td>
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
