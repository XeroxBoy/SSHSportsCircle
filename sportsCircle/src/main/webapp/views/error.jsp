<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'error.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    <script type="text/javascript">
        var cnt = 2;

        function load() {
            if (cnt < 0) {
                window.location.href = "http://localhost:8888";
                window.event.returnValue = false;
            } else {
                document.getElementById("content").innerHTML = "页面<font color=red>" + cnt + "</font>秒后跳转";
                cnt--;
            }
            setTimeout("load()", 1000);
        }
    </script>
</head>

<body onload="load()">
你也许输错了信息.或是您的账号已被注册 <a src="Login.jsp" id="content">页面将在3秒后跳转</a><br>
</body>
</html>
