<!--
 * @Author: Amber.Shuai
 * @Github: https://github.com/Yanqin-s
 * @Date: 2021-12-13 17:41:28
 * @LastEditord: Amber.shuai
 * @LastEditTime: 2021-12-14 16:31:51
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8;">
    <base href="http:localhost:8080/ProjectITMS">
    <title>用户登录</title>
    <script type="text/javascript">
        function checkName(){
            if(document.forms['loginForm']['staff_ID'].value=="")
            {
                alert("ID不能为空┖");

                return false;
            }
            if(document.forms['loginForm']['password'].value==""){
                alert("密码不能为空");
                return false;
            }
            else{
                //document.cookie="zhuangtai=true";
                return true;
            }
        }
        function cleatText(){
            if(document.getElementById("account").innerHTML == ""){
                document.getElementById("account").innerHTML = "";
            }
        }
    </script>


    <link rel="stylesheet"  type = "text/css" href="/ITMS/pages/css/login.css" />
</head>
<body>
<div id = "container">
    <header>
        <a href="https://www.vip.com/"><img src="/ITMS/pages/img/logo.png"></a>
    </header>
    <section class="main">
        <div class="logincontainer">
            <form name="loginForm" method="post" action = "/ITMS/loginServlet" onsubmit="return checkName()">
                <div class = "name">
                    <span  style = "height:23px;margin-top: 20px;">ID:</span>
                    <input type="text" name="userID" class="name" placeholder="请输入ID" required atuofocus><br>
        
                </div>
                <div class = "password">
                    <span  style = "height:23px;">密码:</span>
                    <input type="password" name="password" class="password" placeholder="请输入密码" required><br>
        
                </div>
                <input type="submit" name="login" value="登录"><br><br>
                <div class="remb-forget">
                    <input type="checkbox" name="remember" >记住密码
                    <a href="http://" >忘记密码</a>
                </div>
            </form>
        </div>    
    </section>
    <footer>
        <a href="https://www.vip.com/">© VIP.COM 版权所有</a>
    </footer>

</div>
</body>
</html>