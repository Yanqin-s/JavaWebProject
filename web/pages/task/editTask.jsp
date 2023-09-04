<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/22
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <%
        String basePath = request.getScheme()
                + "://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()
                + "/";

        pageContext.setAttribute("basePath",basePath);
    %>

    <!--写base标签，永远固定相对路径跳转的结果-->
    <base href="<%=basePath%>">
    <title>修改任务</title>
    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/loginSuccessful.css"/>
    <script type="text/javascript" src="/ITMS/pages/script/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="/ITMS/pages/script/ckeditor/ckeditor.js">
        // window.onload = function()
        // {
        //     //content和textarea的name一致
        //     CKEDITOR.replace('t_Wendang');
        // };
        var myEditor = null;
        window.onload = function(){
            ClassicEditor
                .create(document.querySelector("#t_Wendang"))
                .then(editor => {
                    myEditor = editor;
                })
                .catch(error => {
                    console.error(error);
                });
        }
    </script>

</head>
<body>
    <div id="container">
    <%@include file="/pages/common/loginSuccseeMenuBar.jsp"%>
    <section class="main">
        <form action="/ITMS/saveTaskServlet" method="get">
            <input type="hidden" name="t_ID" value="${requestScope.task.t_ID}">
            <table class="table_norm">
                <tr align="center">
                    <td class="td_label" colspan="4" style="font-weight: bold;font-size: 20px;">
                        ${requestScope.task.t_Name}
                    </td>
                </tr>
                <tr>
                    <td class="td_label"><label>ID</label></td>
                    <td>${requestScope.task.t_ID}</td>
                    <td class="td_label"><label>负责人</label></td>
                    <c:if test="${sessionScope.user.u_ID==requestScope.project.p_MID
                        or sessionScope.user.u_ID==requestScope.task.t_OwnerID}">
                        <td><a href="/ITMS/myTaskLookServlet?id=doingTask&PID=${requestScope.task.t_PID}&OwnerID=${requestScope.task.t_OwnerID}">${requestScope.task.t_OwnerName}</a></td>
                    </c:if>
                    <c:if test="${sessionScope.user.u_ID!=requestScope.project.p_MID
                    and sessionScope.user.u_ID!=requestScope.task.t_OwnerID}">
                        <td>${requestScope.task.t_OwnerName}</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="td_label"><label>前置任务</label></td>
                    <td>
                        <input list="t_PreTID" name="t_PreTID" value="${requestScope.task.t_PreTID}">
                        <datalist  id="t_PreTID">
                            <option>
                                <c:if test="${requestScope.task.t_PreTID==null}">
                                    请选择前置任务
                                </c:if>
                                <c:if test="${requestScope.task.t_PreTID!=null}">
                                    ${requestScope.task.t_PreTID}
                                </c:if>
                            </option>
                            <c:forEach items="${requestScope.tasks}" var="task">
                                <option value="${task.t_ID}">
                                        ${task.t_ID}:${task.t_Name}
                                </option>
                            </c:forEach>
                        </datalist >
                    </td>

                    <td class="td_label"><label>后置任务</label></td>
                    <td>
                        <input list="t_PostTID" name="t_PostTID" value="${requestScope.task.t_PostTID}">
                        <datalist  id="t_PostTID">
                            <option>
                                <c:if test="${requestScope.task.t_PostTID==null}">
                                    请选择后置任务
                                </c:if>
                                <c:if test="${requestScope.task.t_PostTID!=null}">
                                    ${requestScope.task.t_PostTID}
                                </c:if>
                            </option>
                            <c:forEach items="${requestScope.tasks}" var="task">
                                <option value="${task.t_ID}">
                                        ${task.t_ID}:${task.t_Name}</option>
                            </c:forEach>
                        </datalist >
                    </td>
                </tr>
                <tr>
                    <td class="td_label"><label>开始日期</label></td>
                    <td>${requestScope.task.t_StartDate}</td>
                    <td class="td_label"><label>截止日期</label></td>
                    <td>${requestScope.task.t_DeadLine}</td>
                </tr>
                <c:if test="${sessionScope.user.u_ID==requestScope.task.t_OwnerID}">
                    <tr>
                        <td class="td_label">预算</td>
                        <td>${requestScope.task.t_Budget}</td>
                        <td class="td_label">当前花销(RMB)</td>
                        <td>
                            <input type="number" name="t_AC"

                                   value="${requestScope.task.t_AC}">
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td class="td_label"><label>所属项目</label></td>
                        <td colspan="3">${requestScope.task.t_PName}</td>
                </tr>

                <tr>
                    <td class="td_label"><label>任务描述</label></td>
                    <td colspan="3">${requestScope.task.t_Description}</td>
                </tr>

                <tr>
                    <td class="td_label"><label>任务文档</label></td>
                    <!--使用富文本编辑器，后台获取文本内容，并保存在服务器的某个路径下面
                    数据库中t_Wendang保存的是文档在服务器中的路径
                    缺点是：受限于数据库物理模型的设定，暂时认为每个任务只有一个文档-->
                    <td colspan="3">
                        <textarea name="t_Wendang" id="t_Wendang" class="ckeditor">
                            ${requestScope.task.t_txt}
                        </textarea>

                        <script>
                            document.getElementById("t_Wendang").value=${requestScope.task.t_txt}
                        </script>

                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <input name="t_Status" type="checkbox" value="1"
                               style="font-size: 15px;font-weight: bold">已完成
                    </td>
                </tr>

            </table>
            <div align="center">
                <input type="reset" name="reset" value="重置" >
                <input type="submit" name="submit" value="提交">

            </div>
        </form>

    </section>
    <footer>
        <a href="https://www.vip.com/">© VIP.COM 版权所有</a>
    </footer>
</div>
</body>
</html>
