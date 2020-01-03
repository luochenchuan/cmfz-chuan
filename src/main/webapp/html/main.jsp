<!--
  Created by IntelliJ IDEA.
  User: zhuchuansheng
  Date: 2019/12/22
  Time: 8:47
  Describe:
    To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>主页</title>
        <!-- 自定义样式 -->
        <link rel="stylesheet" type="text/css" href="${app}/css/style.css" />
        <!-- bootstrap的样式 -->
        <link rel="stylesheet" type="text/css" href="${app}/bootstrap/css/bootstrap.min.css" />
        <!-- jqGrid的样式 -->
        <link rel="stylesheet" type="text/css" href="${app}/jqGrid/css/trirand/ui.jqgrid-bootstrap.css">
        <!-- jquery.js -->
        <script src="${app}/js/jquery-1.10.1.min.js"></script>
        <!-- bootstrap的js -->
        <script src="${app}/bootstrap/js/bootstrap.min.js"></script>
        <%-- 国际化文件.js --%>
        <script src="${app}/jqGrid/js/trirand/i18n/grid.locale-cn.js"></script>
        <!-- jqGrid的js -->
        <script src="${app}/jqGrid/js/trirand/jquery.jqGrid.min.js"></script>
        <script src="${app}/js/ajaxfileupload.js"></script>
        <script src="${app}/js/layer.js"></script>
        <script src="${app}/js/audio.js"></script>
        <script src="${app}/js/cmfz-js.js"></script>

        <%-- 富文本编辑器 --%>
        <script src="${app}/kindeditor/kindeditor-all.js"></script>
        <script src="${app}/kindeditor/lang/zh-CN.js"></script>

        <script>
            $(function () {
                <%--layer.open({--%>
                    <%--title: "",--%>
                    <%--content: "欢迎回来${sessionScope.loginAdmin.userName}",--%>
                    <%--time: 1000,--%>
                    <%--offset: "rt",--%>
                    <%--skin: "demo-class",--%>
                    <%--anim: 6--%>
                <%--});--%>
            });

        </script>


    </head>
    <body id="theme-body" style="padding-top: 70px;">

        <!-- 导航条 -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">持名法州</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="${app}/html/main.jsp">首页 <span class="sr-only">(current)</span></a></li>
                        <li><a href="#">Link</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">主题 <span class="caret"></span></a>
                            <ul class="dropdown-menu text-center">
                                <li><a href="javascript:void(0)" onclick="setTheme(0)">主题一(默认) <span class="theme-box-1"></span></a></li>
                                <li><a href="javascript:void(0)" onclick="setTheme(1)">主题二 <span class="theme-box-2"></span></a></li>
                                <li><a href="javascript:void(0)" onclick="setTheme(2)">主题三 <span class="theme-box-3"></span></a></li>
                                <li><a href="javascript:void(0)" onclick="setTheme(3)">主题四 <span class="theme-box-4"></span></a></li>
                            </ul>
                        </li>
                        <li><a href="javascript:$('#main-content').load('./my/data-main.jsp')">数据</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                ${sessionScope.loginAdmin != null ? sessionScope.loginAdmin.userName : "你还没有登陆"}
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">
                                        <img src="${app}/${sessionScope.loginAdmin.headImg}" class="img-thumbnail" style="height: 50px; width: 50px;">
                                        <span><b>${sessionScope.loginAdmin.userName}</b></span>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="javascript:$('#main-content').load('./my/my-info.jsp')">我的主页</a></li>
                                <li><a href="javascript:$('#main-content').load('./my/my-password.jsp')">修改密码</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="${app}/admin/exitAdmin">退出</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="${app}/admin/exitAdmin">
                                退出登录
                                <span class="glyphicon glyphicon-log-out"></span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- 导航条 -->

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2">

                    <!-- 菜单栏 -->
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingOne">
                                <h4 class="panel-title">
                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        用户管理
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                <div class="list-group">
                                    <a href="javascript:void(0)" class="list-group-item">子选项1</a>
                                    <a href="javascript:void(0)" class="list-group-item">子选项2</a>
                                    <a href="javascript:void(0)" class="list-group-item">子选项3</a>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingTwo">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        上师管理
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                <div class="list-group">
                                    <a href="javascript:void(0)" class="list-group-item">子选项1</a>
                                    <a href="javascript:void(0)" class="list-group-item">子选项2</a>
                                    <a href="javascript:void(0)" class="list-group-item">子选项3</a>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingThree">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                        文章管理
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                <div class="list-group">
                                    <a href="javascript:$('#main-content').load('article-list.jsp')" class="list-group-item">文章列表</a>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingFour">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                        专辑管理
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseFour">
                                <div class="list-group">
                                    <a href="javascript:$('#main-content').load('album-list.jsp')" class="list-group-item">专辑列表</a>
                                    <a href="javascript:$('#main-content').load('album-add.jsp')" class="list-group-item">添加专辑</a>
                                    <a href="javascript:$('#main-content').load('chapter-add.jsp')" class="list-group-item">添加章节</a>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingFive">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                        轮播图管理
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                                <div class="list-group">
                                    <a href="javascript:$('#main-content').load('banner-list.jsp')" class="list-group-item">轮播图列表</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 菜单栏 -->

                </div>
                <div class="col-sm-10" id="main-content">



                    <!-- 巨幕 -->
                    <!--<div class="jumbotron">
                        <h4>欢迎来到持名法州后台管理系统</h4>
                    </div>-->
                    <div class="page-header" style="margin-top: -20px">
                        <h1>欢迎来到持名法州后台管理系统</h1>
                    </div>
                    <!-- 巨幕 -->

                    <!-- 提示框 -->
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Warning!</strong> Better check yourself, you're not looking too good.
                    </div>
                    <!-- 提示框 -->

                    <!-- 轮播图 -->
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">

                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                        </ol>

                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <img src="../image/manager/001.jpg" style="width: 100%; height: 400px" alt="...">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>
                            <div class="item">
                                <img src="../image/manager/002.jpg" style="width: 100%; height: 400px" alt="...">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>
                            <div class="item">
                                <img src="../image/manager/003.jpg" style="width: 100%; height: 400px" alt="...">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>
                            <div class="item">
                                <img src="../image/manager/004.jpg" style="width: 100%; height: 400px" alt="...">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                    <!-- 轮播图 -->

                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default" style="margin-top: 30px;">
                        <div class="panel-body text-center">
                            @百知教育 baizhi@zparjhr.com.cn
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </body>
</html>