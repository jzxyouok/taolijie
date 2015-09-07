<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: wynfrith
  Date: 15-5-17
  Time: 下午8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>

<!doctype html>
<html class="no-js" ng-app="tljApp">
<head>
    <meta charset="utf-8">
    <title>兼职发布</title>
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
<p class="browsehappy">您的浏览器太老了, 升级下您的浏览器吧</p>
<![endif]-->


<jsp:include page="../block/top-bar-reverse.jsp"/>


<div class="container user">
    <jsp:include page="../block/post-nav.jsp">
        <jsp:param name="navShow" value="job"/>
    </jsp:include>
    <style>
        .form-control {
            height: 40px;
            font-size: 16px;
            position:relative;
        }

        .form-group.division select {
            width: 110px !important;
        }

        .form-group.wage select {
            width: 90px !important;
            margin-left: 15px !important;
        }
        .form-group span.err-span{
            position: absolute;top:50px;left:120px; font-size:14px; color: #EF6F66;
        }
       .ng-cloak{display: none}

    </style>

    <div class="segment end-segment" ng-controller="jobPostCtrl">
        <p class="pin-title red-bg ">基本信息
            <i class="pin-arrow  dark-red-arrow"></i>
        </p>

        <form name="JobPostForm" id="JobPostForm" class="form-horizontal" novalidate ng-submit="submit(JobPostForm.$valid)">
            <div class="form-group" style="position: relative">
                <label for="">兼职标题<span class="asterisk-red">*</span></label>
                <input type="text" class="form-control large-input" name="title" placeholder="20字以内"
                       value="${job.title}" ng-model="job.title" ng-maxlength="20"
                       required ng-focus>
                <span class="err-span ng-cloak" ng-cloak ng-show="(checkAll && JobPostForm.title.$error.required) || (!JobPostForm.title.$focused && JobPostForm.title.$dirty && JobPostForm.title.$error.required)">请输入兼职标题</span>
                <span class="err-span ng-cloak" ng-cloak ng-show="(checkAll && JobPostForm.title.$error.maxlength) || (!JobPostForm.title.$focused && JobPostForm.title.$dirty && JobPostForm.title.$error.maxlength)">长度要在20字以内</span>
            </div>
            <div class="form-group">
                <label for="">选择分类<span class="asterisk-red">*</span></label>
                <select name="jobPostCategoryId" class="form-control"  required>
                    <option value="" style="display: none">选择分类</option>
                    <c:forEach items="${cates}" var="cate">
                        <option value="${cate.id}" ${job.jobPostCategoryId == cate.id ? 'selected="selected"' : ''}>${cate.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group wage">
                <label for="">工资待遇<span class="asterisk-red">*</span></label>
                <input type="number" class="form-control short-input fl" name="wage" placeholder="输入有效数字"
                       value="${job.wage.intValue()}" required>
                <select ng-model="job.salaryUnit" name="salaryUnit" class="form-control fl" required>
                    <option value="时">元/时</option>
                    <option value="天">元/天</option>
                    <option value="周">元/周</option>
                    <option value="月">元/月</option>
                </select>
                <%--<span class="input-unit">元</span>--%>
            </div>
            <div class="form-group">
                <label for="">结算方式<span class="asterisk-red">*</span></label>
                <select name="timeToPay" class="form-control" required>
                    <option value="" style="display: none" ${job.timeToPay == '-1' ? 'selected="selected"' : ''}>选择分类
                    </option>
                    <option value="日结" ${job.timeToPay == '日结' ? 'selected="selected"' : ''}>日结</option>
                    <option value="周结" ${job.timeToPay == '周结' ? 'selected="selected"' : ''}>周结</option>
                    <option value="月结" ${job.timeToPay == '月结' ? 'selected="selected"' : ''}>月结</option>
                    <option value="完工结算" ${job.timeToPay == '完工结算' ? 'selected="selected"' : ''}>完工结算</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">截止时间<span class="asterisk-red">*</span></label>
                <input type="text" class="form-control datepicker medium-input" name="expiredTime" id="expiredTime"
                        required
                       placeholder="保留该信息的截止日期"
                       value="<fmt:formatDate value='${job.expiredTime}' pattern='yyyy-MM-dd' />">
            </div>

            <p class="pin-title red-bg ">兼职详情
                <i class="pin-arrow  dark-red-arrow"></i>
            </p>

            <div class="form-group">
                <label for="">工作时间<span class="asterisk-red">*</span></label>
                <input type="text" class="form-control large-input" name="workTime" placeholder="请填写具体的工作时间"
                       value="${job.workTime}"
                        required>
            </div>
            <div class="form-group division">
                <label for="" class="">工作区域<span class="asterisk-red">*</span></label>
                <select name="province" id="province" class="form-control fl" required>
                    <option value="山东省">山东省</option>
                </select>
                <select name="city" id="city" class="form-control fl" required>
                    <option value="淄博市">淄博市</option>
                </select>
                <select name="region" id="region" class="form-control fl" required>
                    <option value="张店区">张店区</option>
                    <option value="周村区">周村区</option>
                    <option value="淄川区">淄川区</option>
                    <option value="临淄区">临淄区</option>
                    <option value="博山区">博山区</option>
                    <option value="桓台县">桓台县</option>
                    <option value="沂源县">沂源县</option>
                    <option value="高青县">高青县</option>
                </select>
            </div>
            <div class="form-group">
                <label for="">工作地点<span class="asterisk-red">*</span></label>
                <input type="text" class="form-control large-input" name="workPlace" placeholder="填写一定要详细哟"
                       value="${job.workPlace}"
                        required>
            </div>
            <div class="form-group">
                <label for="">工作内容<span class="asterisk-red">*</span></label>
                <textarea name="jobDetail" id="" cols="30" class="form-control large-input"
                          placeholder="填写工作具体内容(10字以上)"
                           required>${job.jobDetail}</textarea>
            </div>
            <div class="form-group">
                <label for="">工作要求<span class="asterisk-red">*</span></label>
                <textarea name="jobDescription" class="form-control large-input" required
                          placeholder="请填写工作具体要求(10字以上)">${job.jobDescription}</textarea>
            </div>

            <p class="pin-title red-bg ">兼职详情
                <i class="pin-arrow  dark-red-arrow"></i>
            </p>

            <div class="form-group">
                <label for="">联系人<span class="asterisk-red">*</span></label>
                <input type="text" class="form-control medium-input" name="contact" value="${job.contact}"
                       placeholder="请输入联系人的姓名"
                       required>
            </div>
            <div class="form-group">
                <label for="">手机号<span class="asterisk-red">*</span></label>
                <input type="text" class="form-control medium-input" name="contactPhone" value="${job.contactPhone}"
                       placeholder="请输入联系人的手机号" required
                       pattern="^1\d{10}$|^(0\d{2,3}-?|\(0\d{2,3}\))?[1-9]\d{4,7}(-\d{1,8})?$">
            </div>
            <div class="form-group">
                <label for="">QQ号</label>
                <input type="text" class="form-control medium-input" name="contactQq" value="${job.contactQq}"
                       placeholder="请输入您的qq号">
            </div>

            <div class="segment">
                <div class="submit-btn big-btn red-bg">
                    <span>提交</span>
                </div>
                <input type="submit" value="发布兼职" />
            </div>
        </form>

        <div class="jqmWindow dialog">
            <div class="tlj_modal_header">桃李街提示</div>
            <div class="tlj_modal_content"></div>
        </div>
    </div>
</div>

<jsp:include page="../block/user-footer.jsp"></jsp:include>
<script src="/scripts/jobpost.js"></script>
</body>
</html>
