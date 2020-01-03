<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

<script>
    
    function updatePassword() {

        //判断两次输入的密码是否一致
        if($("#pass2").val() != $("#pass1").val()){
            hint("两次输入的密码不一致");
            return;
        }
        //获取信息
        var data = $("#updatePasswordForm").serialize();
        $.ajax({
            url: "${app}/admin/updatePassword",
            data: data,
            type: "POST",
            success: function (result) {
                if(result["status"] == 200) {
                    $("#updatePasswordForm").get(0).reset();
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
                    hint(result["msg"])
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


</script>


<div class="page-header">
    <h2 class="head-h2">修改密码</h2>
</div>

<form class="form-horizontal" id="updatePasswordForm">

    <div class="form-group">
        <label class="control-label col-sm-2">当前密码</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" name="oldPassword"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">新密码</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="pass1" name="password"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">确认密码</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="pass2" />
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4">
            <button type="button" class="btn btn-info col-sm-offset-8" onclick="updatePassword()">确认修改</button>
        </div>
    </div>

</form>

