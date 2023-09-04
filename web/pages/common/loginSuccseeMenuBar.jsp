<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yan
  Date: 2021/12/15
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<header>
    <a href="https://www.vip.com/"><img src="/ITMS/pages/img/logo.png"></a>
</header>
<div class="navBar">

        <ul class="menu">
            <li> <b>IT项目管理系统</b></li>
            <li style="margin-left: 10%;">消息提醒
                <ul class="submenu">
                    <li><a href="/ITMS/myNewsLookServlet?n_Status=0">未读消息</a> </li>
                    <li><a href="/ITMS/myNewsLookServlet?n_Status=1">已读消息</a> </li>
                </ul>
            </li>
            <c:if test="${sessionScope.user_type==0}">
                <li>任务管理
                    <ul class="submenu">
                        <li><a href="/ITMS/myTaskLookServlet?t_Status=0">新任务</a></li>
                        <li><a href="/ITMS/myTaskLookServlet?t_Status=1">在研任务</a></li>
                        <li><a href="/ITMS/myTaskLookServlet?t_Status=2">已完成任务</a></li>
                    </ul>
                </li>
            </c:if>

            <c:if test="${sessionScope.user_type==1}">
                <li>项目管理
                    <ul class="submenu">
                        <li><a id = "newProject" href="/ITMS/projectLookServlet?p_Status=0" onclick="">新项目</a></li>
                        <li><a href="/ITMS/projectLookServlet?p_Status=1">在研项目</a></li>
                        <li><a href="/ITMS/projectLookServlet?p_Status=2">已完成项目</a></li>


                    </ul>
                </li>
                <li>统计查询
                    <ul class="submenu">
                        <li><a href="/ITMS/singleProjectStatistic">单项目统计</a> </li>
                        <li><a href="/ITMS/multiProjectStatistic">多项目统计</a> </li>
                        <!--
                        1.查询单个项目的任务完成比例
                        2.查询项目经理的项目完成比例
                        3.单项目统计：
                        当前使用的成本占总体预算的比例，用饼图
                        任务完成比例，即将超时的任务列表，
                        任务量最大的前三名成员，完成任务最快/慢的前三名成员
                        4.多项目统计：项目完成度列表

                        -->
                    </ul>
                </li>
            </c:if>


            <li >帮助中心
                <ul class="submenu">
                    <li><a href="http://baidu.com/">搜索</a></li>
                    <li><a href="">关于</a></li>
                </ul>
            </li>
            <li>资源中心
                <ul class="submenu">
                    <li><a href="">OA系统</a></li>
                    <li><a href="">流量监控</a></li>
                </ul>
            </li>
        </ul>
        <span style="float: right;margin-left: 10px;margin-right: 10px;margin-top: 5px;">[<a href="/ITMS/loginOutServlet">退出</a>]</span>
        <span name="username" value=null style="float: right;margin-left: 10px;margin-right: 10px;margin-top: 5px;">
        ${sessionScope.user.u_Name}
        </span>




</div>
