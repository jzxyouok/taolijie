<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ju" uri="/WEB-INF/tld/JsonUtils.tld" %>
<%--
Created by IntelliJ IDEA.
User: wynfrith
Date: 15-5-18
Time: 上午10:54
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page session="false" %>
<%request.setCharacterEncoding("UTF-8");%>

<!doctype html>
<html class="no-js" ng-app="tljApp" ng-controller="shCtrl">
<head>
    <meta charset="utf-8">
    <title ng-bind="sh.title"></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

    <!-- build:css /styles/vendor.css -->
    <!-- bower:css -->
    <!-- endbower -->
    <!-- endbuild -->

    <!-- build:css({.tmp,app}) /styles/css/style.css -->
    <link rel="stylesheet" href="/styles/animate.css"/>
    <link rel="stylesheet" href="/styles/style.css">
    <%--图片上传美化--%>
    <link rel="stylesheet" href="/styles/webuploader.css"/>
    <!-- endbuild -->
    <link rel="stylesheet" href="/styles/jquery.bxslider.css">
    <%--<link rel="stylesheet" href="http://libs.useso.com/js/font-awesome/4.2.0/css/font-awesome.min.css">--%>
    <link rel="stylesheet" href="/styles/font-awesome.min.css"/>

    <!-- build:js /scripts/vendor/modernizr.js -->
    <script src="/scripts/modernizr.js"></script>
    <!-- endbuild -->

</head>
<body>
<!--[if lt IE 10]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->


<link rel="stylesheet" href="/styles/responsiveslides.css"/>

<%--顶栏--%>
<jsp:include page="block/top-bar.jsp"/>
<%--页首--%>
<jsp:include page="block/header.jsp"/>

<div class="container">
    <style>
        .sh-fav {
            margin-right: 20px;
            margin-top: 5px;
            color: #4ccda4;
            cursor: pointer;
        }

        .sh-fav :hover {
            color: #4cae4c !important;
        }
    </style>


    <%--轮播--%>
    <jsp:include page="block/silder.jsp"/>
    <%--侧边栏--%>
    <jsp:include page="block/side.jsp"/>

    <!-- 正文 -->
    <div class="detail main">
        <div class="segment sh-main">
            <!-- 二手物品图片轮播 -->
            <div class="pics fl" style="position: relative">
                <ul class="rslides">
                    <li ng-repeat="pid in sh.pids track by $index" on-last-repeat>
                        <img src="/static/images/users/{{ pid }}" alt="good photo">
                        <%--<p class="caption"></p>--%>
                    </li>
                </ul>
            </div>
            <!-- 二手物品详细信息 （右侧）-->
            <div class="fr sh-info">
                <p class="sh-price dark-green-bg">￥<span ng-bind="sh.sellPrice"></span></p>

                <div class="sh-block">
                    <div class="title">
                        <span class="dot"></span>
                        <span>发布人</span>

                        <div class="bubble-arrow"></div>
                        <div class="bubble-arrow-inner"></div>
                    </div>
                    <div class="name">
                        <img src="/static/images/users/{{sh.member.profilePhotoId}}" alt="">

                        <p ng-bind="sh.contactName"></p>
                        <%--<span ng-bind="posterRole.memo"></span>--%>
                    </div>
                </div>
                <div class="sh-block">
                    <div class="title">
                        <span class="dot"></span>
                        <span>交易地点</span>

                        <div class="bubble-arrow"></div>
                        <div class="bubble-arrow-inner"></div>
                    </div>
                    <div class="place">
                        <p ng-bind="sh.tradePlace"></p>
                    </div>
                </div>
                <div class="sh-block">
                    <div class="title">
                        <span class="dot"></span>
                        <span>联系方式</span>

                        <div class="bubble-arrow"></div>
                        <div class="bubble-arrow-inner"></div>
                    </div>
                    <div class="sh-contact">
                        <p><i class="fa fa-phone red"></i> <span ng-bind="sh.contactPhone"></span></p>

                        <p><i class="fa fa-qq blue"></i> <span ng-bind="sh.contactQq"></span></p>
                    </div>
                </div>
            </div>
            <div style="clean:both"></div>
            <span class="sh-title" ng-bind="sh.title"></span>
            <%--<span class="fr">浏览量 ：${sh.likes}</span>--%>
            <%--<span class="fr">发布时间：
                <span ng-bind="sh.postTime | date:'yyyy-MM-dd'"></span>
            </span>--%>

            <p class="sh-des">
                类型：<span ng-bind="sh.category.name"></span>
                新旧程度：<span class="oldnew" ng-bind="sh.depreciationRate"></span>
                发布时间：<span ng-bind="sh.postTime">2</span>
            </p>
            <!-- 分享（暂时不实现） -->
            <div class="share"></div>
        </div>

        <div class="segment sh-description">
            <p class="pin-title dark-green-bg">详情描述
                <i class="pin-arrow dark-green-arrow"></i>
            </p>

            <p class="fr" id="fav" ng-attr-data-id="{{ sh.id }}" data-type="sh">
                <i class="fa" ng-class="{'fa-heart': sh.favStatus, 'fa-heart-o': !sh.favStatus}"></i>
                ${favStatus? '已收藏':'收藏'}
            </p>

            <div>
                <pre style="white-space: pre; word-wrap: break-word; word-break: break-all" ng-bind="sh.description"></pre>
            </div>
        </div>

        <div class="comment clearfix">
            <p class="pin-title">用户评论
                <i class="pin-arrow"></i>
            </p>

            <div class="operates">
                <div class="operate">
                    <span id="like" data-id="${sh.id}" class="fa fa-thumbs-up" style="cursor: pointer"></span>
                    <p ng-bind="sh.likes" ></p>

                </div>
                <%--<div class="operate">--%>
                <%--<span  id="dislike" data-id="${job.id}" class="fa fa-thumbs-down"></span>--%>
                <%--<p >${job.dislikes}</p>--%>
                <%--</div>--%>
                <div class="operate">
                    <span id="toComment" class="fa fa-comment" style="cursor: pointer"></span>
                    <p ng-bind="sh.reviewCount"></p>

                </div>
                <%--                <div class="operate">
                                    <span id="complaint" data-id="${sh.id}" class="text" style="cursor: pointer" >举报</span>
                                </div>--%>
            </div>
            <jsp:include page="block/comment.jsp">
                <jsp:param name="postId" value="${sh.id}"/>
           </jsp:include>

            <div class="content" id="contents">
              <div ng-class="{'no-border-bottom' : $last}" ng-repeat="review in sh.reviews">
                  <img src="/static/images/users/{{ review.member.profilePhotoId }}" alt="user photo">
                  <p >{{review.member.username}}
                  <a class="red delete-review" href="javascript:void(0);"
                     ng-attr-data-id="{{ job.id }}" data-reviewId="{{ review.id }}"
                     ng-show="{{ currentUser.id == review.member.id }}"> 删除 </a>
                 </p>
                  <div class="span"><span ng-bind="review.content"></span></div>
              </div>
            </div>

            <div class="load-more" style="text-align: center;display: none">
              <button id="loadMore">加载更多评论</button>
            </div>

            <%--
            <div class="review-bar">
                <img src="/images/pig.jpg" alt="">
                <input type="text" class="review-input" placeholder="发表评论" id="comment-input">
                <span class="review-span" id="review-btn" data-id="${sh.id}" data-username="${poster.username}">评论</span>
            </div>
            --%>
        </div>
    </div>

</div>

<%--脚部--%>
<jsp:include page="block/footer.jsp"/>
<script src="/scripts/comment.js"></script>
<script src="/scripts/shdetail.js"></script>
<script src="/scripts/responsiveslides.min.js"></script>
<script>
    var sh = JSON.parse(escapeSpecialChars('${ju:toJson(sh)}'));
    var pids = '${pids}';
    pids_arr = pids.substr(1, pids.length - 2).replace(/ /g, '').split(',');
    sh.pids = pids_arr;
    sh.poster = JSON.parse('${ju:toJson(poster)}');
    sh.posterRole = JSON.parse('${ju:toJson(posterRole)}');
    sh.reviewCount = JSON.parse('${ju:toJson(reviewCount)}');
    sh.reviews = JSON.parse('${ju:toJson(reviews)}');
    var currentUser = JSON.parse('${ju:toJson(currUser)}');
    var shownReviewNums = sh.reviews.length || 0;
    var reviewCount = sh.reviewCount;
    var reviewType = 'sh';
    console.log(reviewType);
</script>
</body>
</html>
