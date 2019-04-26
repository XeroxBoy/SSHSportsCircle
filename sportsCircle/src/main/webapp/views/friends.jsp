<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>发出你的邀请</title>
    <!---css--->
    <link href="../css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../css/style01.css" rel='stylesheet' type='text/css'/>
    <link href="../css/meihua.css" rel='stylesheet' type='text/css'/>
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
    <%--<script src="../js/websocket.js"></script>--%>
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
                pager: true
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
    <script> $(function () {
        var area = "${sessionScope.area }";//获取用户的爱好领域
        if (area == null || area == "") {
            window.location.href = "/toError";
        }

        document.querySelector("#nar").innerHTML = "  " + area;
        document.getElementById("bg").src = "../images/e2.png";

        //
        // if (area == "打球圈") {
        //     document.querySelector("#nar").innerHTML = "  "+area;
        //     document.getElementById("bg").src = "../images/ball.png";
        //
        // }
    });</script>

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
            /*background-image: url("../images/fbg.jpg");*/
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
                <a class="navbar-brand" href="/findArea?area=${sessionScope.area }"><img
                        src="../images/e.png" id="bg"/><font id="nar">跑步圈</font></a>
            </h1>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul
                    class="nav navbar-nav navbar-right wow fadeInRight animated animated"
                    data-wow-delay="0.4s">
                <li style="display:inline;"><a class="nav-in"
                                               href='/findArea?area=${sessionScope.area }'><span class="flip"
                                                                                                 data-letters="${sessionScope.area }">${sessionScope.area }</span></a>

                    <ul class="subnav" style="display:none;float:left;left:20px;position:relative;">
                        <c:forEach items="${sessionScope.myCircles  }" var="circle" begin="0"
                                   end="${sessionScope.myCircles.size()}">
                            <li><a href="/findArea?area=${circle.circleName }"><span class="nav-in"
                                                                                     data-letters="${circle.getCircleName()}"
                                                                                     style="font-size:15px;color:#FFF">${circle.getCircleName()}</span></a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <%--
                                    <li><a class="nav-in" href="friends-friendList.action"><span
                                            data-letters="我的好友">我的好友</span></a></li>--%>
                <li><a class="nav-in" href="/findMyInfo"><span
                        data-letters="我的信息">我的信息</span></a></li>
                <li><a class="nav-in" href="/friendList"><span
                        data-letters="我的好友">我的好友</span></a></li>
                <li><a class="nav-in" href="/date"><span data-letters="我要约啦">我要约啦</span></a></li>
                <li><a class="nav-in" href="/toDaka"><span
                        data-letters="每日打卡">每日打卡</span></a></li>
                <li><a class="nav-in" href="/toContact"><span
                        data-letters="聊天">聊天</span></a></li>
                <li><a class="nav-in" href="/toCircle"><span
                        data-letters="圈子">圈子</span></a></li>
                <li><a class="nav-in" href="/createCircle"><span
                        data-letters="创建圈子">创建圈子</span></a></li>
                <li><a class="nav-in" href="/logout"><span
                        data-letters="注销">注销</span></a></li>

            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
${info}

<ul class="friends-box">
    <c:forEach var="l" items="${friendsList}">
        <li class="friends-box-item">
            <h4 style="color: #28ff1f;">朋友id:${l.friendsTo}</h4>
            <a href="/tochat/${l.friendsTo}"><p style="color: #28ff1f;">与${l.friendsTo}聊天</p></a>
        </li>
    </c:forEach>
</ul>
<ul class="friends-box">
    <c:forEach var="user" items="${users}">
        <c:if test="${user.username != sessionScope.name}">
            <li class="friends-box-item">
                <form action="/makeFriend" method="get">
                    <input type="hidden" value="${user.username}" name="friendsTo"/>
                    <h4 style="color: #28ff1f;">${user.username}</h4>
                    <button type="submit" class="btn btn-info" style="right: 30%;color: green">结交为好友</button>
                </form>
            </li>
        </c:if>
    </c:forEach>
</ul>
</body>

</html>