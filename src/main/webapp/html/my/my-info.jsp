<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

<script>

    function updateInfo() {
        
        var data = $("#updateInfoForm").serialize();

        $.ajax({
            url: "${app}/admin/updateAdminInfo",
            data: data,
            type: "POST",
            datatyep: "json",
            success: function (result) {
                if(result["status"] == 200){

                    //判断是否需要图片上传
                    var file = $("#img").prop('files');
                    if(file.length == 1) {
                        $.ajaxFileUpload({
                            url: "${app}/admin/uploadImage",
                            type: "POST",
                            fileElementId: "img",
                            success: function () {

                            }
                        });
                    }
                    layer.open({
                        offset: "200px",
                        icon: 3,
                        area: ["400px", "200px"],
                        content: "修改成功,需要重新登陆,修改的信息才能生效,是否需要立即登陆?",
                        btn: ["立即重新登陆", "不用了"],
                        yes: function (index, layero) {
                            //需要重新登陆
                            location.href = "${app}/admin/exitAdmin";
                            layer.close(index);
                        }
                    });

                }else{
                    hint(result["msg"]);
                }
            }
        });


    }


    function hint(content) {

        layer.open({
            title: "",
            content: content,
            time: 1000,
            skin: "demo-class",
            anim: 3
        });
    }


    $(function () {

        var inputBox = document.getElementById("img");
        inputBox.addEventListener("change",function() {
            var reader = new FileReader();
            reader.readAsDataURL(inputBox.files[0]);//发起异步请求
            reader.onload = function () {
                //读取完成后，数据保存在对象的result属性中
                $("#img-show").attr("src", this.result);
            }
        });
    });

</script>


<div class="page-header">
    <h2 class="head-h2">设置我的资料</h2>
</div>

<form class="form-horizontal" id="updateInfoForm">

    <div class="form-group">
        <label class="control-label col-sm-2">我的角色:</label>
        <div class="col-sm-8">
            <select class="form-control" name="role">
                <option value="超级管理员" ${sessionScope.loginAdmin.role == "超级管理员" ? "selected='selected'" : ""}>超级管理员</option>
                <option value="普通管理员" ${sessionScope.loginAdmin.role == "普通管理员" ? "selected='selected'" : ""}>普通管理员</option>
                <option value="审核人员" ${sessionScope.loginAdmin.role == "审核人员" ? "selected='selected'" : ""}>审核人员</option>
                <option value="编辑人员" ${sessionScope.loginAdmin.role == "编辑人员" ? "selected='selected'" : ""}>编辑人员</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">用户名</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="name" value="${sessionScope.loginAdmin.name}"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">昵称</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" placeholder="登陆时使用的名" name="userName" value="${sessionScope.loginAdmin.userName}" />
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">性别</label>
        <div class="col-sm-8">
            <label class="radio-inline">
                <input type="radio" name="sex" value="男" ${sessionScope.loginAdmin.sex == "男" ? "checked='true'" : ""}/> 男
            </label>
            <label class="radio-inline">
                <input type="radio" name="sex" value="女" ${sessionScope.loginAdmin.sex == "女" ? "checked='true'" : ""}/> 女
            </label>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">头像</label>
        <div class="col-sm-8">
            <input type="file" name="img" id="img"/>
            <img style="width: 140px; height: 140px; margin-left: 30%; margin-top: -10%" src="${app}/${sessionScope.loginAdmin.headImg}" class="img-circle" alt="还没有上传头像" id="img-show"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">手机号码</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="phoneNumber" value="${sessionScope.loginAdmin.phoneNumber}" />
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">邮箱</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="email" value="${sessionScope.loginAdmin.email}" />
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">备注</label>
        <div class="col-sm-8">
            <textarea class="form-control" rows="3"></textarea>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-8 col-sm-offset-5">
            <button type="button" class="btn btn-success" onclick="updateInfo()">确认修改</button>
            <button type="reset" class="btn btn-danger">重新填写</button>
        </div>
    </div>

</form>



