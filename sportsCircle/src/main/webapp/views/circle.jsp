<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>

<link href="../css/bootstrap.css" rel='stylesheet' type='text/css'/>
<link href="../css/style01.css" rel='stylesheet' type='text/css'/>
<link href='../css/fileinput.css' rel='stylesheet' type='text/css'>

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
<script src="../js/fileinput.js"></script>
<script src="../js/fileinput-zh.js"></script>
<!---js--->
<!--web-fonts-->
<link href='../css/font1.css' rel='stylesheet' type='text/css'>
<link href='../css/font2.css' rel='stylesheet' type='text/css'>
<link href='../css/font3.css' rel='stylesheet' type='text/css'>

<!--//web-fonts-->
<script src="../js/responsiveslides.min.js"></script>

<!--JS for animate-->
<link href="../css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="../js/wow.min.js"></script>
<script>
    new WOW().init();
    $(document).ready(function () {
        $(".nav-in").mouseover(function () {
            $(this).next(".subnav").slideDown(500);
        });

        $("li").mouseleave(function () {
            $(this).children(".subnav").slideUp(500);
        });
        /*   $(this).fileinput({
         showUpload : false,
         showRemove : false,
         language : 'zh',
         allowedPreviewTypes: ['image'],
         allowedFileTypes: ['image'],
         allowedFileExtensions:  ['jpg', 'png'],
         maxFileSize : 20000,

         }); */
        /*    projectfileoptions : {
         showUpload : false;
         showRemove : false;
         language : 'zh';
         allowedPreviewTypes : [ 'image' ];
         allowedFileExtensions : [ 'jpg', 'png', 'gif' ];
         maxFileSize : 2000;
         }; */
        $("#form2").on("click", function () {
            $("#form2").submit();
        });
// 文件上传框
        $('input[class=projectfile]').each(function () {
            var imageurl = $(this).attr("value");

            if (imageurl) {
                var op = $.extend({
                    initialPreview: [ // 预览图片的设置
                        "<img src='" + imageurl + "' class='file-preview-image'>",]
                }, {
                    showUpload: false,
                    showRemove: false,
                    language: 'zh',
                    allowedPreviewTypes: ['image'],
                    allowedFileTypes: ['image'],
                    allowedFileExtensions: ['jpg', 'png', 'gif'],
                    maxFileSize: 20000,

                });

                $(this).fileinput(op);
            } else {
                $(this).fileinput({
                    showUpload: false,
                    showRemove: false,
                    language: 'zh',
                    allowedPreviewTypes: ['image'],
                    allowedFileTypes: ['image'],
                    allowedFileExtensions: ['jpg', 'png', 'gif'],
                    maxFileSize: 20000,

                });
            }
        });
    });

    $(function () {
        var area = "${sessionScope.area }";//获取用户的爱好领域
        if (area == null || area == "") {
            window.location.href = "/toError";
        }
        if (area == "健身圈") //把背景图片和文字换成健身圈的
        {
            document.querySelector("#nar").innerHTML = "  健身圈";
            document.getElementById("bg").src = "../images/e2.png";
        }

        if (area == "打球圈") {
            document.querySelector("#nar").innerHTML = "  打球圈";
            document.getElementById("bg").src = "../images/ball.png";
        }
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
                                               href='/findArea?area=跑步圈'><span class="flip"
                                                                               data-letters="跑步圈">跑步圈</span></a>

                    <ul class="subnav" style="display:none;float:left;left:20px;position:relative;">
                        <li><a href="/findArea?area=打球圈"><span class="nav-in"
                                                               data-letters="打球圈"
                                                               style="font-size:15px;color:#FFF">打球圈</span></a>
                        </li>
                        <li><a href="/findArea?area=健身圈"><span class="nav-in"
                                                               data-letters="健身圈"
                                                               style="font-size:15px;color:#FFF">健身圈</span></a>
                        </li>
                    </ul>
                </li>

                <li><a class="nav-in" href="/findMyInfo"><span
                        data-letters="我的信息">我的信息</span></a></li>

                <li><a class="nav-in" href="/friendList"><span
                        data-letters="我的好友">我的好友</span></a></li>
                <li><a class="nav-in" href="/date"><span data-letters="我要约啦">我要约啦</span></a></li>
                <li><a class="nav-in" href="/toDaka"><span
                        data-letters="每日打卡">每日打卡</span></a></li>
                <li><a class="nav-in" href="/toCircle"><span
                        data-letters="圈子">圈子</span></a></li>
                <li><a class="nav-in" href="/logout"><span
                        data-letters="注销">注销</span></a></li>
                <div> <form action="searchCircle" method="post">
                    <li><input type="text" name="key" style="position:relative;top:-10px;height: 35px;width: 180px;" placeholder="请输入您要搜索的圈子..."></li>
                    <li><button type="submit" class="btn btn-default" style="position:relative;top:-20px;left:10px;height: 35px;width: 75px;vertical-align: text-top">搜索</button> </li></form>
                </div>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
<%--展示推荐的圈子/热帖--%>


<form action="/circle" method="post" class="form-inline" role="form">
    <div class="form-group">
        <%--TODO--%>
        <%--@declare id="lsex"--%><h3 style="font-family: 'Arial';left:30% ;top:0px;position:relative;">创建你的圈子</h3>
        <label for="circleName">圈名:</label><input type="text" name="circleName" class="form-control"
                                                  style="width: 302px; " required><br>
        <label>选择该圈子的代表图片:</label><input type="file" name="file"/> <br><img src="../pic/${sessionScope.name}.jpg"
                                                                            onerror="javascript:this.src='../pic/moren.jpg';alt='pic'"/><br>
        <input value="上传" type="submit">
        <input type="submit" class="btn btn-info" style=" left:40%;bottom:10%; position: relative" value="创建圈子">
    </div>
</form>

</body>

</html>