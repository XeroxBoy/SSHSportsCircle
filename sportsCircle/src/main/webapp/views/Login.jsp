<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>运动圈</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="Expand, contract, animate forms with jQuery wihtout leaving the page"/>
    <meta name="keywords" content="expand, form, css3, jquery, animate, width, height, adapt, unobtrusive javascript"/>
    <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
    <script src="../js/cufon-yui.js" type="text/javascript"></script>
    <script src="../js/ChunkFive_400.font.js" type="text/javascript"></script>
    <script type="text/javascript">
        Cufon.replace('h1', {textShadow: '1px 1px #fff'});
        Cufon.replace('h2', {textShadow: '1px 1px #fff'});
        Cufon.replace('h3', {textShadow: '1px 1px #000'});
        Cufon.replace('.back');
    </script>
    <script type="text/javascript">
        function refresh(imgurl) {
            imgurl.src = "/randstr" + "?id=" + Math.random();
        }
    </script>
    <%
        Cookie[] cookies = request.getCookies();
        String zh = "";
        String mm = "";
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("zh")) {
                    zh = cookies[i].getValue();
                    break;
                }
            }
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("mm")) {
                    mm = cookies[i].getValue();
                    break;
                }
            }
        }
    %>
</head>
<body>
<div class="wrapper">
    <h1>SportsCircle</h1>
    <h2>Register Your Account And come into our circle!</h2>
    <div class="content">
        <div id="form_wrapper" class="form_wrapper">
            <%--<s:fielderror/><!-- 错误信息 -->--%>
            <form class="login active" action="/login" method="post">
                <h3>Login</h3>
                <div>
                    <label>用户名:</label>
                    <input type="text" name="username" value="<% out.println(zh); %>" required/>
                    <span class="error">This is an error</span>
                </div>
                <div>
                    <label>密码:</label>
                    <input type="password" name="password" value="<% out.println(mm); %>" required/>
                    <span class="error">This is an error</span>
                    <span style="padding-left:25px"> 请输入验证码：</span><br><input type="text" name="code" size="10"
                                                                              required>
                    <img border=0 src="/randstr" onclick="refresh(this)" id="yzm"
                         style="padding-left:30px;padding-top:10px;width:20%;">
                </div>
                <div class="bottom">
                    <div class="remember"><input type="checkbox" name="remember"/><span>记住我</span></div>
                    <input type="submit" value="登录"></input>
                    <a href="/toZhuce">还没有账号？点此注册</a>
                    <!--	rel="register" class="linkform"-->
                    <div class="clear"></div>
                </div>
            </form>
        </div>
        <div class="clear"></div>
    </div>
</div>


<!-- The JavaScript -->
<script type="text/javascript" src="js/jquery-lastest.js"></script>

</body>
</html>