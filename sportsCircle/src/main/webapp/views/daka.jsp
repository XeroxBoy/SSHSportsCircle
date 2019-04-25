<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>${sessionScope.name}的打卡信息</title>
    <!---css--->

    <style type="text/css">
        input {
            border: 10px solid #ffbaad;
            -moz-border-radius: 10px;
        }
    </style>
    <style type="text/css">
        /*    .table{
             height:200px;
             width:200px;
             top:300px;
             left:150px;
             position:relative;
         } */
        .thd {
            top: 300px;
            left: 150px;
            position: relative;
            color: #F5F5F5;
        }

        th, tr {
            color: #F5F5F5;
        }

        .total {
            left: 40%;
            bottom: 30%;
            position: relative;
        }

        .eachDay {
            width: 15px;
            height: 10px;
        }

        .proDay {
            width: 15px;
            height: 10px;
            background: url(../images/yes1.png) no-repeat;
        }

        body {
            background: url(../images/runbg.jpg) no-repeat;
            background-size: 100% auto;
        }
    </style>
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
    <script src="../js/websocket.js"></script>
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


        //使用EL获取后台数据 ，根据打卡天数 来给输出的日历打勾
        //再使用js document.getelementbyid.xxxx=xxxx 来改变属性
        function mGetDate(year, month) { //获取一个月有多少天
            var d = new Date(year, month, 0);
            return d.getDate();
        }

        function table() { //输出签到日期表格
            var tab = "<table class='table  table-bordered' style='height:200px;width:200px;top:300px;left:150px;position:relative;'>";
            var mydate = new Date();
            var days = mGetDate(mydate.getYear(), mydate.getMonth() + 1);//总天数
            var year = mydate.getYear() + 1900;
            var month = mydate.getMonth() + 1;//是几月
            var day = mydate.getDay();//是一个星期的第几天
            var prodays = "${sessionScope.prodays}";//获取连续打卡天数
            var lastproday = "${sessionScope.lastProdays}";//获取上次打卡的时间
            var startProday = calPaintDays(lastproday, prodays);//计算开始打卡的天数
            // alert(lastproday);
            mydate.setDate(1);
            var date = mydate.getDate();//是几号
            tab += "<tr>";
            tab += "<p class='thd'>" + year + "年" + month + "月" + "</p>";
            tab += "</tr>";
            var xingqi = mydate.getDay();//一号是该星期的第几天
            // alert(xingqi);
            tab += "<tr>";
            for (var i = 0; i <= 6; i++) //生成表头
            {
                var j = (xingqi + i) % 7;
                tab += "<th>";
                switch (j) {
                    case 1:
                        tab += "一";
                        break;
                    case 2:
                        tab += "二";
                        break;
                    case 3:
                        tab += "三";
                        break;
                    case 4:
                        tab += "四";
                        break;
                    case 5:
                        tab += "五";
                        break;
                    case 6:
                        tab += "六";
                        break;
                    case 0:
                        tab += "日";
                        break;
                }
                tab += "</th>";
            }
            tab += "</tr>";
            for (var i = 0; i <= days / 7; i++) { //输出日期

                tab += "<tr>"
                if (i < days / 7 - 1) {
                    for (j = 1; j <= 7; j++) {
                        var nowday = j + i * 7;
                        if (nowday >= startProday && nowday <= lastproday) { //打卡的日子换种方式输出 proday的背景图片是勾勾=。=
                            tab += "<td class='proDay'>" + nowday + "</td>";
                            continue;
                        }
                        tab += "<td class='eachDay'>" + nowday + "</td>";
                    }
                }
                if (i == 4) { //最后一行 不足7天
                    for (j = 1; j <= days % 7; j++) {
                        var nowday = j + i * 7;
                        if (nowday >= startProday && nowday <= lastproday) { //打卡的日子换种方式输出
                            tab += "<td class='proDay'>" + nowday + "</td>";
                            continue;
                        }
                        tab += "<td class='eachDay'>" + nowday + "</td>";
                    }
                }
                tab += "</tr>";
            }
            tab += "</table>";
            document.write(tab);
        }

        function calPaintDays(end, days) { //返回应该开始打卡的日子 end为lastproday days为prodays
            var shouldpaint = 0;//应该画的天数
            if (end - days > 0) { //应该进行绘制
                shouldpaint = end - days + 1;//shouldpaint为开始打卡的起点
            } else if (end < days && days != 0)//从上个月开始连续打卡到这个月的XX号,就应该从1号开始打勾
                shouldpaint = 1;
            return shouldpaint;
        }

        table();
        $(document).ready(function () {
            $(".nav-in").mouseover(function () {
                $(this).next(".subnav").slideDown(500);
            });

            $("li").mouseleave(function () {
                $(this).children(".subnav").slideUp(500);
            });
        });
        $(function () {
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
        });
    </script>
    <!--JS for animate-->
    <link href="../css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->

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
                        <c:forEach items="${sessionScope.myCircles }" var="circle" begin="0" end="${circles.size()}">
                            <li><a href="/findArea?area=${circle.getCircleName() }"><span class="nav-in"
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
                <li><a class="nav-in" href="/logout"><span
                        data-letters="注销">注销</span></a></li>

            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
<!--body-->

<form action="/daka" style="width:320px;height:130px;left:12%;top:-120px;position:relative">
    <h3 style="color: #2A4;">运动完了 说点话发泄下吧！</h3>
    <textarea name="message" cols="20" class="form-control" rows="4"></textarea>
    <input type="submit" value="点我打卡" class="btn btn-default ">
</form>
<p style="color: #39b3d7;font-size:20px;" class="total">
    您已连续签到:${sessionScope.prodays }天</p>
<!--显示出该月哪几天打过卡-->
<span id="tongzhi" style="color: #39b3d7;font-size:20px;left:700px;top:-250px;position:relative;"><br><strong
        id="news"></strong></span>


</body>
</html>