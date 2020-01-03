<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<script>

    $(function () {

        $("#banner-list").jqGrid({
            url: "${app}/banner/queryBanner",
            styleUI : 'Bootstrap',
            colNames: ["编号", "标题", "图片", "状态", "操作"],
            datatype: "json",
            autowidth: true,
            colModel: [
                {name: "id", align: "center", hidden: true},
                {name: "title", align: "center", editable: true},
                {name: "img", align: "center", editable: true, edittype: "file", formatter: function (cellvalue, options, rowObject) {

                        return "<img src='${app}/"+cellvalue+"' style='width: 32px; height: 32px'/>";
                    }
                },
                {name: "status", align: "center", editable: true, edittype: "select", editoptions: {value: "0:不激活;1:激活"}, formatter: function (cellvalue, options, rowObject) {

                        if(cellvalue == 1){
                            return "<span class='activate success'>正 &nbsp; 常</span>";
                        }else{
                            return "<span class='activate error'>冻 &nbsp; 结</span>";
                        }
                    }
                },
                {name: "status", align: "center", formatter: function (cellvalue, options, rowObject) {

                        if(cellvalue == 1){
                            return "<button class='btn btn-danger' id='"+options["rowId"]+"' onclick='updateStatus(this, 0)'>不激活</button>";
                        }else{
                            return "<button class='btn btn-primary' id='"+options["rowId"]+"' onclick='updateStatus(this, 1)'>激活</button>";
                        }
                    }
                }
            ],
            pager: "#jqGridPager",
            page: 1,
            rownumbers: true,
            multiselect: true,     //定义是否可以多选
            viewrecords: true,      //是否要显示总记录数
            editurl: "${app}/banner/editBanner"
        }).navGrid('#jqGridPager',
            {search:false},
            {
                /*修改*/
                closeAfterEdit:true,
                afterSubmit: function (response) {
                    //获取ID
                    var uuid = response["responseJSON"]["uuid"];
                    //判断是否修改了图片
                    var element = response["responseJSON"]["updateImage"];
                    if(element)
                        fileUpload("${app}/banner/upload", {id: uuid}, "修改成功", "修改失败");

                    return response;
                },
            },
            {
                /* 添加 */
                closeAfterAdd:true,
                afterSubmit: function (response) {
                    var uuid = response["responseJSON"]["uuid"];
                    fileUpload("${app}/banner/upload", {id: uuid}, "添加成功", "添加失败");

                    return response;
                }
            },
            {
                /*删除*/
                closeAfterdDel:true,
                afterSubmit: function (response) {

                    var count = response["responseJSON"]["success"];
                    $("#hint").show();
                    $("#hint-content").text("成功删除 "+count+" 条记录");
                    setTimeout(function () {
                        $("#hint").hide();
                    }, 2000);
                    return response;
                }
            }
        );

    });

    function fileUpload(url, data, successText, errorText) {

        $.ajaxFileUpload({
            url: url,
            type: "POST",
            fileElementId: "img",
            data: data,
            success: function () {
                $("#banner-list").trigger("reloadGrid");
                $("#hint").show();
                $("#hint-content").text(successText);
                setTimeout(function () {
                    $("#hint").hide();
                }, 2000);
            },
            error: function () {
                $("#hint").show();
                $("#hint-content").text(errorText);
                setTimeout(function () {
                    $("#hint").hide();
                }, 2000);
            }
        });
    }

    function updateStatus(obj, status) {

        var id = $(obj).attr("id");

        $.ajax({
            url: "${app}/banner/updateStatus",
            type: "POST",
            data: {id: id, status: status},
            success: function () {
                $("#banner-list").trigger("reloadGrid");
                layer.open({
                    title: "",
                    content: "修改成功!!!",
                    time: 1000,
                    skin: "demo-class",
                    anim: 4
                });
            }
        })
    }


    /* 导出文件 */
    function deriveFile() {

        location.href = "${app}/banner/deriveFile";
    }


    /* 导入文件 */
    function toLeadFile() {

        $("#fileModal").modal("show");
    }
    
    
    function importFile() {
        //上传文件
        $.ajaxFileUpload({
            url: "${app}/banner/toLeadFile",
            type: "POST",
            fileElementId: "bannerFile",
            success: function () {
                $("#fileModal").modal("hide");
            }
        });

    }


</script>
<div>

    <div class="page-header">
        <h2 class="head-h2">轮播图列表</h2>
    </div>


    <div class="alert alert-info alert-dismissible" id="hint" style="display: none">
        <strong>提示:</strong> <span id="hint-content"></span>
    </div>

    <div class="panel panel-default">

        <div class="panel-heading">
            <div class="text-right">
                <button type="button" class="btn btn-info" onclick="toLeadFile()">导入文件</button>
                <button type="button" class="btn btn-info" onclick="deriveFile()">导出文件</button>
            </div>
        </div>

        <table id="banner-list"></table>

        <div id="jqGridPager" style="height: 50px"></div>

    </div>


</div>


<%-- 图片展示 --%>
<div class="modal fade" id="image-Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="fileModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">导入的文件</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">选择文件</label>
                        <div class="col-sm-8">
                            <input type="file" name="bannerFile" id="bannerFile" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">说明</label>
                        <div class="col-sm-10">
                            <img src="${app}/image/format.png" class="img-thumbnail" style="">
                            <small style="color: red">格式要相同,否则导入失败</small>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="importFile()">导入</button>
            </div>
        </div>
    </div>
</div>