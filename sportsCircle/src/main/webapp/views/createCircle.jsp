<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
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
<style type="text/css">
    .form-inline {
        left: 35%;
        top: 210px;
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
        document.querySelector("#nar").innerHTML = "  运动圈";
        document.getElementById("bg").src = "../images/e2.png";
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
            <c:if test="${sessionScope.area} != '' && ${sessionScope.area } != null">
                <h1>

                    <a class="navbar-brand" href="/findArea?area=${sessionScope.area }"><img
                            src="../images/e.png" id="bg"/><font id="nar">${sessionScope.area }</font></a>
                </h1>
            </c:if>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul
                    class="nav navbar-nav navbar-right wow fadeInRight animated animated"
                    data-wow-delay="0.4s">
                <li><a class="nav-in" href="/toCircle"><span
                        data-letters="圈子">圈子</span></a></li>
                <div>
                    <form action="searchCircle" method="post">
                        <li><input type="text" name="key" style="position:relative;top:-10px;height: 35px;width: 180px;"
                                   placeholder="请输入您要搜索的圈子..."></li>
                        <br>
                        <li>
                            <button type="submit" class="btn btn-default"
                                    style="position:relative;top:-20px;left:10px;height: 35px;width: 75px;vertical-align: text-top">
                                搜索
                            </button>
                        </li>
                    </form>
                </div>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
<%--展示推荐的圈子/热帖--%>
<h3 style="font-family: 'Arial';left:15% ;top:190px;position:relative;">还没有圈子？创建属于自己的圈子吧</h3>

<form action="/circle" method="post" class="form-inline" role="form">
    <div class="form-group">
        <%--@declare id="lsex"--%><h3 style="font-family: 'Arial';left:30% ;top:0px;position:relative;">创建你的圈子</h3>
        <label for="circleName">圈名:</label><input type="text" name="circleName" class="form-control"
                                                  style="width: 302px; " required><br>
        <br><br>
        <input type="submit" class="btn btn-info" style=" left:40%;bottom:10%; position: relative" value="创建圈子">
    </div>
</form>

</body>

</html>