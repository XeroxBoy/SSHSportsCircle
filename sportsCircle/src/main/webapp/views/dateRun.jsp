<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>发出你的邀请</title>
    <!---css--->
    <link href="../css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../css/style01.css" rel='stylesheet' type='text/css'/>
    <!---css--->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!---js--->
    <script src="../js/jquery-latest.js"></script>
    <script src="../js/bootstrap.js"></script>
    <!---js--->
    <!--web-fonts-->
    <link href='../css/font1.css' rel='stylesheet' type='text/css'>
    <link href='../css/font2.css' rel='stylesheet' type='text/css'>
    <link href='../css/font3.css' rel='stylesheet' type='text/css'>
    <!--//web-fonts-->
    <script src="../js/responsiveslides.min.js"></script>
    <script>
        $(function () {
            $("#slider").responsiveSlides({
                auto: true,
                nav: true,
                speed: 500,
                namespace: "callbacks",
                pager: true,
            });
        });
        $(document).ready(function () {
            $(".nav-in").mouseover(function () {
                $(this).next(".subnav").slideDown(500);
            });

            $("li").mouseleave(function () {
                $(this).children(".subnav").slideUp(500);
            });
        });
    </script>
    <!--JS for animate-->
    <link href="../css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <style type="text/css">
        .form-inline {
            left: 35%;
            top: 150px;
            bottom: 40%;
            height: 450px;
            width: 400px;
            position: relative;
            background-image: url("../images/fbg.jpg");
        }

        body {
            background-image: url("../images/datebg.jpg");
        }

        label {
            /*  color:#FF5; */
        }
    </style>
</head>
<body>
<!---header--->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header wow fadeInLeft animated animated"
             data-wow-delay="0.4s">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <h1>
                <a class="navbar-brand" href="message-findArea.action?area=${sessionScope.area }">
                    <img src="../images/e.png"/>跑步圈</a>
            </h1>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul
                    class="nav navbar-nav navbar-right wow fadeInRight animated animated"
                    data-wow-delay="0.4s">
                <li style="display:inline;"><a class="nav-in"
                                               href='message-findArea.action?area=跑步圈'><span class="flip"
                                                                                             data-letters="跑步圈">跑步圈</span></a>

                    <ul class="subnav" style="display:none;float:left;left:20px;position:relative;">
                        <li><a href="message-findArea.action?area=打球圈"><span class="nav-in"
                                                                             data-letters="打球圈"
                                                                             style="font-size:15px;color:#FFF">打球圈</span></a>
                        </li>
                        <li><a href="message-findArea.action?area=健身圈"><span class="nav-in"
                                                                             data-letters="健身圈"
                                                                             style="font-size:15px;color:#FFF">健身圈</span></a>
                        </li>
                        <li><a href="http://118.25.222.77:8080/game/index.jsp" target="_blank"><span class="nav-in"
                                                                                                     data-letters="健身圈"
                                                                                                     style="font-size:15px;color:#FFF">游戏圈</span></a>
                        </li>
                    </ul>
                </li>

                <%--<li><a class="nav-in" href="friends-friendList.action"><span
                        data-letters="我的好友">我的好友</span></a></li>--%>
                <li><a class="nav-in" href="zhuce-findMyInfo.action"><span
                        data-letters="我的信息">我的信息</span></a></li>
                <li><a class="nav-in" href="dateRun.jsp"><span data-letters="我要约啦">我要约啦</span></a></li>
                <li><a class="nav-in" href="daka.jsp"><span
                        data-letters="每日打卡">每日打卡</span></a></li>
                <li><a class="nav-in" href="Login.jsp"><span
                        data-letters="注销">注销</span></a></li>

            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
<form action="message.action" method="post" class="form-inline" role="form">
    <div class="form-group">
        <h3 style="font-family: 'Arial';left:30% ;top:0px;position:relative;">发出你的邀请</h3>
        <label for="contents">状态内容:</label><textarea name="content" cols="45" rows="3" class="form-control"
                                                     style="vertical-align:top;position: relative;width:400px"
                                                     required></textarea><br><br>
        <label for="location">集合地点:</label><input type="text" name="location" class="form-control"
                                                  style="width: 302px; " required><br>
        <label for="assignTime"><br>约定时间:</label>
        <p><label for="outDate">日期：</label><input type="date" class="form-control" id="pickdate" name="outDate"
                                                  required/></p><br/>
        <p><label for="assignTime">时间：</label><input type="text" class="input" id="picktime" name="assignTime"
                                                     list="timedata" required/></p>
        <datalist id="timedata">
            <option value="早上6点以前"/>
            <option value="早上6-8点"/>
            <option value="早上9-11点"/>
            <option value="中午11-下午1点"/>
            <option value="下午1-4点"/>
            <option value="下午4-6点"/>
            <option value="下午6-晚上8点"/>
            <option value="晚上8-晚上10点"/>
            <option value="晚上10点以后"/>
        </datalist>
        <br>
        <label for="lsex">对方性别要求:</label>
        <select class="form-control" name="lsex" required>
            <option value="不限">不限</option>
            <option value="男">男</option>
            <option value="女">女</option>
        </select><br>
        <label for="belongTo">所属板块：</label>
        <select class="form-control" name="belongTo" required>
            <option value="跑步圈">跑步圈</option>
            <option value="健身圈">健身圈</option>
            <option value="打球圈">打球圈</option>
        </select><br>
        <input type="submit" class="btn btn-info" style=" left:40%;bottom:10%; position: relative" value="发表状态">

    </div>

</form>

</html>