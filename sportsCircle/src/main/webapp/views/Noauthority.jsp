<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'Noauthority.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <script type="text/javascript">
        var cnt = 2;

        function load() {
            if (cnt < 0) {
                window.location.href = "http://localhost:8080";
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
<p>您还没有登录！</p>
<a src="views/Login.jsp" id="content">页面将在3秒后跳转</a>
</body>
</html>
