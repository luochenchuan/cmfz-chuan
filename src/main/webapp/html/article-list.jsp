<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

<script>

    //新增
    window.editorAdd;
    //修改
    window.editorUpdate;

    $(function () {

        $("#article-list").jqGrid({
            url: "${app}/article/queryAll",
            mtype: "GET",
            datatype: "json",
            styleUI : 'Bootstrap',
            autowidth: true,
            pager: "#article-paging",
            rowNum: 3,
            multiselect: true,
            editurl: "${app}/article/deleteArticle",
            rownumbers: true,
            viewrecords: true,
            colNames: ["编号", "标题", "作者", "内容", "上师ID", "添加时间", "状态", "修改状态", "操作"],
            colModel: [
                {name: "id", hidden: true},
                {name: "title", align: "center"},
                {name: "author", align: "center"},
                {name: "content", align: "center", hidden: true},
                {name: "guruId", align: "center"},
                {name: "createDate", align: "center"},
                {name: "status", align: "center",formatter:function(cellvalue, options, rowObject){

                        if(cellvalue == 1)
                            return "<span class='activate success'>正 常</span>";

                        return "<span class='activate error'>冻 结</span>";
                    }
                },
                {name: "", align: "center", formatter: function (cellvalue, options, rowObject) {

                        if(rowObject["status"] == "1")
                            return "<button class='btn btn-danger' onclick=\"updateArticle('"+rowObject["id"]+"', 0)\">冻 结</button>";

                        return "<button class='btn btn-default' onclick=\"updateArticle('"+rowObject["id"]+"', 1)\">正 常</button>";
                    }
                },
                {name: "", formatter: function (cellvalue, options, rowObject) {

                        return "<button class='btn btn-primary' onclick=\"showArticleInfo('"+rowObject["id"]+"')\">查看</button>" +
                               "<button class='btn btn-info' onclick=\"updateArticleInfo('"+rowObject["id"]+"')\">修改</button>";
                    }
                }
            ]
        }).jqGrid("navGrid", "#article-paging", {edit: false, search: false, add: false});


        /* 富文本编辑器初始化 */
        editorAdd = KindEditor.create("#article-add-content",{
            width: "568px",
            minWidth: 568,
            height: "100px",
            resizeType: 1,
            /* 图片上传的路径 */
            uploadJson : '${app}/kindEditor/uploadImage',
            /* 图片空间访问的路径 */
            fileManagerJson : '${app}/kindEditor/imageSpace',
            /* 上传图片的名称 */
            filePostName: "img",
            allowFileManager : true
        });

    });




    /* 展示信息 */
    function showArticleInfo(id){

        var data = $("#article-list").jqGrid('getRowData', id);

        var html = "<div class='list-group'>" +
                        "<div class='list-group-item'>" +
                            "<span>标 &nbsp; &nbsp; 题: </span><a>"+data["title"]+"</a>" +
                        "</div>" +
                        "<div class='list-group-item'>" +
                            "<span>作 &nbsp; &nbsp; 者: </span><a>"+data["author"]+"</a>" +
                        "</div>" +
                        "<div class='list-group-item'>" +
                            "<span>上 师ID: </span><a>"+data["guruId"]+"</a>" +
                        "</div>" +
                        "<div class='list-group-item'>" +
                            "<span>上传时间: </span><a>"+data["createDate"]+"</a>" +
                        "</div>" +
                        "<div class='list-group-item'>" +
                            "<span>状 &nbsp; &nbsp; 态: </span><a>"+data["status"]+"</a>" +
                        "</div>" +
                        "<div class='list-group-item'>" +
                            "<span>内 &nbsp; &nbsp; 容: </span>" +
                            "<div class='list-group-item'>"+data["content"]+"</dvi>" +
                        "</div>" +
                   "</div>";

        $("#showArticleInfo").html(html);
        $("#articleShowModal").modal("show")

        /* 富文本编辑器初始化 */
        var editor = KindEditor.create("#article-show-content",{
            width: "552px",
            minWidth: 552,
            height: "100px",
            resizeType: 1
        });
    }



    /* 修改 */
    function updateArticleInfo(id) {

        var data = $("#article-list").jqGrid('getRowData', id);

        var html = "<form id='article-update-form'>" +
                        "<input type='hidden' name='id' value='"+data["id"]+"'/>" +
                        "<div class='form-group'>" +
                            "<label class='control-label'>标题</label>" +
                            "<input type='text' class='form-control' name='title' value='"+data["title"]+"'>" +
                        "</div>" +
                        "<div class='form-group'>" +
                            "<label class='control-label'>作者</label>" +
                            "<input type='text' class='form-control' name='author' value='"+data["author"]+"'>" +
                        "</div>" +
                        "<div class='form-group'>" +
                            "<label class='control-label'>上师ID</label>" +
                            "<input type='text' class='form-control' name='guruID' value='"+data["guruId"]+"'>" +
                        "</div>" +
                        "<div class='form-group'>" +
                            "<label class='control-label'>状态</label>" +
                            "<select class='form-control' name='status'>" +
                                "<option value='1'>正 常</option>" +
                                "<option value='0'>冻 结</option>" +
                            "</select>" +
                        "</div>" +
                        "<div class='form-group'>" +
                            "<label class='control-label'>内容</label>" +
                            "<input type='hidden' name='content' id='article-update-content-input' />" +
                            "<textarea id='article-update-content'></textarea>" +
                        "</div>" +
                   "<form>";

        $("#articleUpdateInfo").html(html);
        $("#articleUpdateModal").modal("show");

        /* 富文本编辑器初始化 */
        editorUpdate = KindEditor.create("#article-update-content",{
            width: "568px",
            minWidth: 568,
            height: "100px",
            resizeType: 1
        });
        editorUpdate.html(data["content"])
    }



    /* 修改状态 */
    function updateArticle(id, status) {

        $.ajax({
            url: "${app}/article/updateStatus",
            data: {id: id, status: status},
            type: "POST",
            datatype: "json",
            success: function (result) {
                layer.open({
                    title: "",
                    content: "成功修改: "+result["msg"]["success"]+" 条数据",
                    time: 1000,
                    skin: "demo-class",
                    anim: 2
                });
                //刷新表格
                $("#article-list").trigger("reloadGrid");
            }
        })
    }
    
    
    /* 修改文章数据 */
    function articleUpdateInfo() {

        //设置数据
        $("#article-update-content-input").val(editorUpdate.html());
        //获取数据
        var data = $("#article-update-form").serialize();

        //进行修改
        $.ajax({
            url: "${app}/article/updateArticle",
            type: "POST",
            data: data,
            datatype: "json",
            success: function (result) {

                if(result["msg"]["success"] > 0){

                    window.editorUpdate.html("");                  //清空富文本
                    resetFrom("#article-update-form");             //清空表单

                    $('#articleUpdateModal').modal('hide')         //关闭模态框
                    //刷新表格
                    $("#article-list").trigger("reloadGrid");

                    layer.open({
                        title: "",
                        content: "成功修改: "+result["msg"]["success"]+" 条数据",
                        time: 1000,
                        skin: "demo-class",
                        anim: 2
                    });
                }
            }
        });
    }



    /* 添加信息 */
    function articleAddBtn() {

        //获取文本域中的数据
        $("#article-add-content-input").val(window.editorAdd.html());
        //获取表单中的数据
        var data = $("#article-add-form").serialize();
        //进行添加
        $.ajax({
            url: "${app}/article/addArticle",
            type: "POST",
            data: data,
            datatype: "json",
            success: function (result) {

                if(result["msg"]["success"] > 0){

                    window.editorAdd.html("");                  //清空富文本
                    resetFrom("#article-add-form");             //清空表单
                    $('#articleAddModal').modal('hide');        //关闭模态框
                    //刷新表格
                    $("#article-list").trigger("reloadGrid");

                    layer.open({
                        title: "",
                        content: "添加成功,添加: "+result["msg"]["success"]+" 条数据",
                        time: 1000,
                        skin: "demo-class",
                        anim: 2
                    });
                }
            }
        });

    }
    


</script>



<div class="page-header">
    <h2 class="head-h2">文章列表</h2>
</div>


<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">文章列表</a></li>
    <li role="presentation">
        <a href="#articleAddModal" data-toggle="modal" data-target="#articleAddModal">添加文章</a>
    </li>
</ul>

<div class="tab-content">

    <table id="article-list"></table>
    <div id="article-paging" style='height: 50px'></div>

</div>



<%-- 修改的模态框 --%>
<div class="modal fade" id="articleUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改文章信息</h4>
            </div>
            <div class="modal-body" id="articleUpdateInfo">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-success" onclick="articleUpdateInfo()">修改</button>
            </div>
        </div>
    </div>
</div>
<%-- 修改的模态框 --%>

<%-- 查询信息模态框 --%>
<div class="modal fade" id="articleShowModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">查看文章信息</h4>
            </div>
            <div class="modal-body" id="showArticleInfo">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<%-- 查询信息模态框 --%>

<%-- 添加信息 --%>
<div class="modal fade" id="articleAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增文章信息</h4>
            </div>
            <div class="modal-body">
                <form id="article-add-form">
                    <div class="form-group">
                        <label>标题:</label>
                        <input class="form-control" type="text" name="title" />
                    </div>
                    <div class="form-group">
                        <label>作者:</label>
                        <input class="form-control" type="text" name="author" />
                    </div>
                    <div class="form-group">
                        <label>上师ID:</label>
                        <select class="form-control" name="guruId">
                            <option>1</option>
                            <option>2</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>状态:</label>
                        <select class="form-control" name="status">
                            <option value="1">正 常</option>
                            <option value="0">冻 结</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>内容:</label>
                        <input type="hidden" id="article-add-content-input" name="content">
                        <textarea id="article-add-content"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-success" onclick="articleAddBtn()">添加</button>
            </div>
        </div>
    </div>
</div>
<%-- 添加信息 --%>
