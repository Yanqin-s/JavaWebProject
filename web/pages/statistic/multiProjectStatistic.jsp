<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/29
  Time: 0:08
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
    <div class="main grid-container2">
        <div>您有${requestScope.projects.size()}条项目记录</div>
        <div class="item1"></div>
        <div class="item2"></div>

    </div>

    <footer>
        <a href="https://www.vip.com/">© VIP.COM 版权所有</a>
    </footer>
</div>
</body>
</html>
