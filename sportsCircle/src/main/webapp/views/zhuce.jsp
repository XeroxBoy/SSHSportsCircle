<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>注册你的账号</title>
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
</head>
<body>
<div class="wrapper">
    <h1>SportsCircle</h1>
    <h2>Register Your Account And come into our circle!</h2>
    <div class="content">
        <div id="form_wrapper" class="form_wrapper">
            <form class="register active" action="/zhuce" method="post">
                <h3>Register</h3>
                <div class="column">
                    <div>
                        <label>用户名:</label>
                        <input name="username" type="text" maxlength="15" required/>
                        <span class="error">This is an error</span>
                    </div>
                    <div>
                        <label>邮箱:</label>
                        <input type="email" name="email" required/>
                        <span class="error">This is an error</span>
                    </div>
                    <div>
                        <label>密码:</label>
                        <input type="password" name="password" maxlength="15" required/>
                        <span class="error">This is an error</span>
                    </div>
                </div>
                <div class="column">
                    <div>
                        <label>性别:</label>
                        <input type="text" list="sexdata" name="sex" required/>
                        <datalist id="sexdata">
                            <option value="男"/>
                            <option value="女"/>
                        </datalist>
                    </div>
                    <div>
                        <label>生日:</label>
                        <input type="date" id="birthday" min="1900/01/01" name="birthday" required/>
                        <span class="error">This is an error</span>
                    </div>
                    <div>
                        <label>感兴趣的领域：</label>
                        <input type="text" list="areadata" name="Areabelongto" required/>
                        <%--<datalist id="areadata">--%>
                        <%--<option value="跑步圈"/>--%>
                        <%--<option value="健身圈"/>--%>
                        <%--<option value="打球圈"/>--%>
                        <%--</datalist>--%>

                    </div>

                </div>

                <div class="bottom">
                    <input type="submit" value="注册"/>
                    <a href="Login.jsp">已有账号？点此登录</a>
                    <div class="clear"></div>
                </div>
            </form>

        </div>
        <div class="clear"></div>
    </div>

</div>


<!-- The JavaScript -->
<script type="text/javascript" src="../js/jquery-lastest.js"></script>
</body>
</html>
