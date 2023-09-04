<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/17
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加任务</title>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <base href="http:localhost:8080/ITMS">
    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/loginSuccessful.css" />
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

            <form action="/ITMS/addTaskServlet" method="get">
                <input type="hidden" name="from" value=<%= request.getParameter("from")%>>
                <input type="hidden" name="t_PID"
                       value= <%String t_PID = request.getParameter("t_PID");
                                 out.print(t_PID);
                                %>>
                <table class="table_norm">
                    <tr>
                        <!--
                        1.任务阶段用于任务ID生成
                        2.动态添加时，需要将任务阶段和当前项目的ID作为参数传到servlet中
                        3.servlet中addTask方法，默认t_Status=0
                        4.负责人使用select标签
                        -->

                        <td><label>任务名称:</label></td>
                        <td><input name="t_Name" type="text" placeholder="请填写任务名称"></td>
                        <td><label>任务阶段:</label></td>
                        <td>
                            <select name="t_Period">
                                <option value="none" selected disabled hidden>请选择任务阶段</option>
                                <option value="1">项目立项</option>
                                <option value="2">项目启动会</option>
                                <option value="3">项目设计</option>
                                <option value="4">需求调研</option>
                                <option value="5">项目开发</option>
                                <option value="6">项目测试</option>
                                <option value="7">项目试运行</option>
                                <option value="8">项目验收</option>
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td><label>截止时间:</label></td>
                        <td><input name="t_DeadLine" type="Date" placeholder="任务截止时间"></td>

                        <td><label>任务预算:</label></td>
                        <td><input name="t_Buget" type="number" min="0" placeholder="任务预算"></td>



                    </tr>
                    <tr>

                        <td><label>负责人:</label></td>
                        <td>
                            <select name="t_OwnerID">
                                <option value="none" selected disabled hidden>请选择任务负责人</option>
                                <c:forEach items="${sessionScope.users}" var="user">
                                    <option value=${user.u_ID}>${user.u_Name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>任务描述:</label></td>
                        <td colspan="3">
                            <textarea name="t_Description" id="t_Description" placeholder="请描述任务的要求" cols="58" rows="12"></textarea>
                            <script>
                                document.getElementById("t_Description").value=${requestScope.task.t_txt}
                            </script>
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
