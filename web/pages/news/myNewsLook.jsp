<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/24
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <base href="http:localhost:8080/ITMS">
    <title>查看消息</title>
    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/loginSuccessful.css" />
    <script type="text/javascript"
            src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        var isChechAll = false;
        function swapCheck(){
            if(isChechAll){
                $("input[type='checkbox']").each(function (){
                    this.checked=false;
                });
                isChechAll=false;
            }
            else {
                $("input[type='checkbox']").each(function (){
                    this.checked=true;
                });
                isChechAll=true;
            }
        }
        function btnType_0(){
            document.getElementById("btnType").value=0;
        }
        function btnType_1(){
            document.getElementById("btnType").value=1;
        }
    </script>
</head>
<body>
    <div id="container">
    <%@include file="/pages/common/loginSuccseeMenuBar.jsp"%>
    <section class="main">
        <form action="/ITMS/editNewsServlet" method="get">

            <input type="hidden" name="n_Status" value="${requestScope.n_Status}">
            <input type="hidden" id="btnType" name="btnType">


            <c:if test="${requestScope.n_Status==0}">
                <c:if test="${requestScope.newsList.size()==0}">
                    <div class="num_title"><p>您目前没有未读消息！</p></div>
                </c:if>
                <c:if test="${requestScope.newsList.size()!=0}">
                    <div class="num_title"><p>您有${requestScope.newsList.size()}条未读消息，请查收！</p></div>
                    <!--该界面可以直接删除，也可以标记为已读，链接到两个servlet
                    或者一个servlet的if-else
                    -->

                    <table class="table_norm">
                        <tr class="first-tr">
                            <td style="width: 10%;"><input type="checkbox" value="all" onclick="swapCheck()"></td>
                            <td style="width: 90%" colspan="2">消息摘要</td>
                        </tr>
                        <c:forEach items="${requestScope.newsList}" var="news">
                            <tr>
                                <td><input type="checkbox" name="selectedNews" value="${news.n_ID}"></td>
                                <td colspan="2">
                                        ${news.n_Text}
                                    <c:if test="${fn:length(news.n_ObjID)==11}">
                                        <a href="/ITMS/doingProjectDetailsServlet?p_ID=${news.n_ObjID}">
                                            查看详情
                                        </a>
                                    </c:if>
                                    <c:if test="${fn:length(news.n_ObjID)==14}">
                                        <a href="/ITMS/taskDetailsServlet?t_ID=${news.n_ObjID}">
                                            查看详情
                                        </a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div align="center">
                        <span><button onclick="btnType_0()">标记为已读</button></span>
                        <span><button onclick="btnType_1()">删除</button></span>
                    </div>

                </c:if>
            </c:if>
            <c:if test="${requestScope.n_Status==1}">
                <c:if test="${requestScope.newsList.size()==0}">
                    <div class="num_title"><p>您目前没有已读消息！</p></div>
                </c:if>
                <c:if test="${requestScope.newsList.size()!=0}">
                    <div class="num_title"><p>您有${requestScope.newsList.size()}条已读消息。</p></div>
                    <!--该界面可以直接删除，也可以标记为已读，链接到两个servlet
                    或者一个servlet的if-else
                    -->

                    <table class="table_norm">
                        <tr>
                            <td><input type="checkbox"  value="all" onclick="swapCheck()"></td>
                            <td colspan="2">消息摘要</td>
                        </tr>
                        <c:forEach items="${requestScope.newsList}" var="news">
                            <tr>
                                <td><input type="checkbox" name="selectedNews" value="${news.n_ID}"></td>
                                <td colspan="2">
                                        ${news.n_Text}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div align="center">
                        <span><button onclick="btnType_1()">删除</button></span>
                    </div>



                </c:if>
            </c:if>
        </form>
    </section>
    <footer>
        <a href="https://www.vip.com/">© VIP.COM 版权所有</a>
    </footer>
</div>
</body>
</html>
