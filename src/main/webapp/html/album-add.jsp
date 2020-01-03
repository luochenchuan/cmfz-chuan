<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<c:set value="${pageContext.request.contextPath}" var="app"/>


<script>


    function addAlbum() {

        //初始化数据
        $("#brief").val($("#briefText").val());
        //获取数据
        var data = $("#album-form").serialize();
        $.ajax({
            url: "${app}/album/addAlbum",
            data: data,
            type: "POST",
            success: function (result) {
                console.log(result)
                var uuid = result["uuid"]
                $.ajaxFileUpload({
                    url: "${app}/album/updateImage",
                    data: {id: uuid},
                    type: "POST",
                    fileElementId: "img",
                    success: function () {
                        //清除表单
                        resetFrom("#album-form");
                        $("#img-show-box").css("display", "none");
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

    $(function () {


        var inputBox = document.getElementById("img");
        inputBox.addEventListener("change",function() {
            var reader = new FileReader();
            reader.readAsDataURL(inputBox.files[0]);//发起异步请求
            reader.onload = function () {
                //读取完成后，数据保存在对象的result属性中
                $("#img-show-box").css("display", "block");
                $("#img-show").attr("src", this.result);
            }
        });

    });


</script>


<div class="page-header">
    <h2 class="head-h2">添加专辑</h2>
</div>

<form class="form-horizontal" id="album-form">

    <div class="form-group">
        <label class="control-label col-sm-2">标题:</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="title"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">封面:</label>
        <div class="col-sm-8">
            <input type="file" id="img" name="img"/>
            <div id="img-show-box" style="display: none;">
                <br/>
                <img id="img-show" style="width: 100px; height: 100px;" class="img-thumbnail" />
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">评分:</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" placeholder="1 - 5分" name="score"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">作者:</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="author"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">播音:</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="broadcaster"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">简介:</label>
        <div class="col-sm-8">
            <input type="hidden" name="brief" id="brief"/>
            <textarea style="resize:none" class="form-control" rows="3" id="briefText"></textarea>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">状态:</label>
        <div class="col-sm-8">
            <select class="form-control" name="status">
                <option value="1">正 常</option>
                <option value="0">冻 结</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4 col-sm-offset-3">
            <button type="button" class="btn btn-success col-sm-4" onclick="addAlbum()">提交</button>
        </div>
        <div class="col-sm-4">
            <button type="button" class="btn btn-danger col-sm-4" onclick="resetFrom('#album-form')">重置</button>
        </div>
    </div>

</form>



