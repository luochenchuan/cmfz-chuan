<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<c:set value="${pageContext.request.contextPath}" var="app"/>

<script>


    /**
     * 初始化数据
     */
    $(function () {

        $.ajax({
            url: "${app}/album/queryAllAlbum",
            datatype: "json",
            type: "POST",
            success: function (result) {
                if(result == null)
                    return;

                $("#albumId").empty();
                result.forEach(function (item, index) {

                    $("#albumId").append("<option value='"+item["id"]+"'>"+item["title"]+"</option>");
                })

            }
        });

    });


    /**
     * 添加数据
     */
    function addChapter() {

        //初始化数据
        $("#duration").val($("#durationText").val());
        //获取表单数据
        var data = $("#chapterForm").serialize();

        $.ajax({
            url: "${app}/chapter/addChapter",
            data: data,
            type: "POST",
            datatype: "json",
            success: function (result) {
                var id = result["uuid"];
                $.ajaxFileUpload({
                    url: "${app}/chapter/uploadMusic",
                    type: "POST",
                    data: {id: id},
                    fileElementId: "src",
                    success: function () {
                        resetFrom("#chapterForm")
                        layer.open({
                            title: "",
                            content: "新增成功!!!",
                            time: 1000,
                            skin: "demo-class",
                            anim: 3
                        });
                    }
                })

            }
        });

    }
</script>


<div class="page-header">
    <h2 class="head-h2">章节添加</h2>
</div>

<form class="form-horizontal" id="chapterForm">

    <div class="form-group">
        <label class="control-label col-sm-2">标题:</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="title"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">选择专辑ID:</label>
        <div class="col-sm-8">
            <select class="form-control" id="albumId" name="albumId">
                <option value="1">1</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">作者:</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="author"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">描述:</label>
        <div class="col-sm-8">
            <input type="hidden" name="duration" id="duration"/>
            <textarea class="form-control" rows="3" style="resize: none" id="durationText"></textarea>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">音乐:</label>
        <div class="col-sm-8">
            <input type="file" name="src" id="src"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">状态</label>
        <div class="col-sm-8">
            <select class="form-control" name="status">
                <option value="1">正 常</option>
                <option value="0">冻 结</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4 col-sm-offset-3">
            <button type="button" class="btn btn-success" onclick="addChapter()">提 交</button>
        </div>
        <div class="col-sm-4">
            <button type="button" class="btn btn-danger" onclick="resetFrom('#chapterForm')">重 置</button>
        </div>
    </div>

</form>
