<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>${sessionScope.name}的主页</title>
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
    <%-- <script src="js/changebg.js"></script> --%>
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
        //把背景图片和文字换成XX圈的

        var first = "${pageContext.request.contextPath }/findArea?currPage=1&area=${sessionScope.area }";
        var last = "${pageContext.request.contextPath }/findArea?currPage=${sessionScope.totalPage}&area=${sessionScope.area }";
        var before = "${pageContext.request.contextPath }/findArea?currPage=${sessionScope.currPage}&area=${sessionScope.area }";
        var next = "${pageContext.request.contextPath }/findArea?currPage=${sessionScope.currPage+2}&area=${sessionScope.area }";
        //document.querySelector(".banner-section").style.backgroundImage = "url('../images/${sessionScope.bgNum }.jpg')";
        document.querySelector("#nar").innerHTML = "${sessionScope.area }";
        document.getElementById("bg").src = "../images/e2.png";
        document.getElementById("firstp").setAttribute("href", first);
        document.getElementById("nextp").setAttribute("href", next);
        document.getElementById("beforep").setAttribute("href", before);
        document.getElementById("lastp").setAttribute("href", last);
    });</script>


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
                        src="../images/e.png" id="bg"/><font id="nar">${sessionScope.area }</font></a>
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

<!---banner--->
<div class="banner-section">
    <div class="container">
        <div class="slider">
            <div class="callbacks_container">
                <ul class="rslides" id="slider">
                    <li>
                        <div class="caption">
                            <div class="col-md-6 cap-left wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                                <p>Life is contradictory， is movement， once the contradictory eliminate motion stops，
                                    life will be over</p>

                            </div>
                            <div class="col-md-6 cap-right wow fadeInUpBig animated animated" data-wow-delay="0.4s">
                                <h3>Pain is temporary. quitting lasts forever.</h3>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </li>
                    <li>
                        <div class="caption">
                            <div class="col-md-6 cap-left wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                                <p>Pain may last a minute, an hour, or a day, but it will eventually subside & something
                                    else will take its place. But quitting, however, lastsFusce porta porta</p>

                            </div>
                            <div class="col-md-6 cap-right wow fadeInUpBig animated animated" data-wow-delay="0.4s">
                                <h3>Work hard everyday No guts no glory</h3>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                    </li>
                    <li>
                        <div class="caption">
                            <div class="col-md-6 cap-left wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                                <p>Only sports can remove a variety of concerns</p>

                            </div>
                            <div class="col-md-6 cap-right wow fadeInUpBig animated animated" data-wow-delay="0.4s">
                                <h3>Pain is temporary. quitting lasts forever.</h3>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!---banner--->
<!---content--->
<div class="content">
    <!---train--->
    <div class="train w3-agile">
        <div class="container">
            <h2>运动小百科</h2>

            <h4> 一些你需要知道的运动知识</h4>
            <div class="train-grids">
                <div class="col-md-3 train-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
                    <div class="train-top hvr-bounce-to-right">
                        <div class="train-img">
                            <img src="../images/e1.png"/>
                        </div>
                        <h4>深蹲</h4>
                        <p>健身房里总有这样一句话：不做深蹲的健身者，算不上是真正的健身者。当你走进健身房的时候，在不予余力的练习深蹲的人才是高手</p>
                    </div>
                </div>
                <div class="col-md-3 train-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                    <div class="train-top hvr-bounce-to-right">
                        <div class="train-img">
                            <img src="../images/e2.png"/>
                        </div>
                        <h4>哑铃</h4>
                        <p>　哑铃健身不仅能消耗人体过多的热量，减肥瘦身，还可以强壮身体，训练肌肉，但很多健身人士只知道举哑铃的好处，不知道什么才是正确的哑铃锻炼方法</p>
                    </div>
                </div>
                <div class="col-md-3 train-grid wow fadeInUpBig animated animated" data-wow-delay="0.4s">
                    <div class="train-top hvr-bounce-to-right">
                        <div class="train-img">
                            <img src="../images/e3.png"/>
                        </div>
                        <h4>杠铃 </h4>
                        <p>杠铃是健身界不可缺少的锻炼工具，只有通过杠铃才能上大重量，迅速提高肌肉力量和围度。但你知道如何正确有效的通过杠铃进行锻炼吗</p>
                    </div>
                </div>
                <div class="col-md-3 train-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
                    <div class="train-top hvr-bounce-to-right">
                        <div class="train-img">
                            <img src="../images/e4.png"/>
                        </div>
                        <h4>拉伸</h4>
                        <p>关于健身，我们听得最多的忠告就是：健身之后一定要进行拉伸，让肌肉充分放松。拉伸运动有很多种，你知道哪些有效正确的拉伸方法呢</p>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="train-grids">
                <div class="col-md-3 train-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
                    <div class="train-top hvr-bounce-to-right">
                        <div class="train-img">
                            <img src="../images/e.png"/>
                        </div>
                        <h4>跑步</h4>
                        <p>现在选择自主健身的男女越来越多了,跑步机是很普遍的一种健身选择。那么,跑步机怎么使用?如何正确使用跑步机对减肥及人身安全等效果才是最好的?</p>
                    </div>
                </div>
                <div class="col-md-3 train-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                    <div class="train-top hvr-bounce-to-right">
                        <div class="train-img">
                            <img src="../images/e6.png"/>
                        </div>
                        <h4>引体向上</h4>
                        <p>要说提高上半身力量的最佳方法，非以正确的姿势完成引体向上莫属。引体向上可谓是最具挑战性的自重训练之一</p>
                    </div>
                </div>
                <div class="col-md-3 train-grid wow fadeInUpBig animated animated" data-wow-delay="0.4s">
                    <div class="train-top hvr-bounce-to-right">
                        <div class="train-img">
                            <img src="../images/e7.png"/>
                        </div>
                        <h4>跳绳 </h4>
                        <p>跳绳是一类无论何时何地都可以自己完成的运动。与走步、跑步等有氧运动相比,同样的时间内,它的运动量更多</p>
                    </div>
                </div>
                <div class="col-md-3 train-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
                    <div class="train-top hvr-bounce-to-right">
                        <div class="train-img">
                            <img src="../images/e8.png"/>
                        </div>
                        <h4>卧推 </h4>
                        <p>卧推是最好锻炼你上半身的锻炼方式之一,但是一些技术上的小问题可能会导致你肌肉酸痛,不能继续坚持锻炼而影响你的健身效果</p>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>


    <!---content--->
    <br>
    <br>

    <c:forEach var="page" begin="0" end="${PageBean.list.size()}" step="1" items="${PageBean.list }">
    <div class="fitcircle-comment fuck-layout">
        <div class="fitcircle-comment-meta">
            <span>用户： ${sessionScope.name }</span>
            <span>评论数：${page.comments.size() }</span>
            <span>所属模块：${sessionScope.area }</span>
        </div>
        <div class="fitcircle-comment-meta">
            <span>约定时间：${page.assignTime}</span>
            <span>约定地点：${page.location}</span>
            <span>性别要求：${page.lsex}</span>
            <span>发布时间:${page.outDate.toLocaleString()}</span>
        </div>
        <a href="/deleteMessage?messageid=${page.messageid }" value="删除消息"></a>
        <div class="fitcircle-comment-meta-special">
            <span>内容：</span>
            <p class="content">${page.content}</p>
        </div>
        <c:if test="${page.comments.size()!=0}">
            <c:forEach var="l" begin="0" end="${page.comments.size()}" step="1" items="${page.comments }">
                <div class="fitcircle-comment-meta">
                    <span>用户:${sessionScope.name }</span>
                    <span>评论：${l.contents }</span>
                    <span>发布时间:${l.outTime}</span>
                </div>
                <!--<a href="/makeFriend?friendsTo=${l.userId}"></a>-->
                <a href="/commentDelete?commentid=${l.commentId }" value="删除评论"></a>
                </tr>
            </c:forEach>
        </c:if>
    </div>
    <form action="/comment" name="commentForm" class="fuck-layout">
        <label for="contents">我要评论:</label>
        <textarea name="contents" cols="30" rows="5" class="form-control"
                  required></textarea>
        <input type="submit" class="btn btn-default form-submit" value="发表评论"/> <input
            type="hidden" name="userId" value="${sessionScope.name }"/>
        <input type="hidden" name="messagebelongTo"
               value=
                       "${page.messageid }"/>
    </form>
    </c:forEach>

    <div class="fuck-layout fitcircle-pagination">
        <p>${PageBean.currPage }/ ${PageBean.totalPage } 页</p>
        <p>总记录数: ${PageBean.totalCount } <span class="pagination-extra">每页显示 ${PageBean.pageSize }</span></p>
        <p>
            <a class="pagination-link" id="firstp"
               href="${pageContext.request.contextPath }/findArea?currPage=1&area=${sessionScope.area}">[首页]</a>
            <c:if test="${PageBean.currPage!=1 }">
                <a class="pagination-link" id="beforep"
                   href="${pageContext.request.contextPath }/findArea?currPage=${PageBean.currPage }&area=${sessionScope.area }">[上一页]</a>
            </c:if>
            <c:if test="${PageBean.currPage!= PageBean.totalPage }">
                <a class="pagination-link" id="nextp"
                   href="${pageContext.request.contextPath }/findArea?currPage=${PageBean.currPage+2 }&area=${sessionScope.area }">[下一页]</a>
                <a class="pagination-link" id="lastp"
                   href="${pageContext.request.contextPath }/findArea?currPage=${PageBean.totalPage }&area=${sessionScope.area }">[尾页]</a>
            </c:if>
        </p>
    </div>

    <!-- content -->
    <!---footer--->
    <div class="footer-section">
        <div class="container">
            <div class="footer-grids">
                <div class="col-md-3 footer-grid wow fadeInRight animated animated"
                     data-wow-delay="0.4s">
                    <h4>与我们联系</h4>
                    <p>电话：13688056393</p>
                    <a href="mailto:827312773@qq.com"> 827312773@qq.com</a>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <!---footer--->
    <!--copy-->
    <div class="copy-section wow fadeInDownBig animated animated"
         data-wow-delay="0.4s">
        <div class="container">
            <div class="social-icons">
                <a href="#"> <i class="icon"></i>
                </a> <a href="#"> <i class="icon1"></i>
            </a> <a href="#"> <i class="icon2"></i>
            </a> <a href="#"> <i class="icon3"></i>
            </a>
            </div>
            <p>
                成都理工大学软件工程三班 <a target="_blank" href="http://sc.chinaz.com/moban/"></a>
            </p>
        </div>
    </div>
    <!--copy-->
</body>
</html>