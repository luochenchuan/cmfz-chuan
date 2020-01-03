<%@ page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .communication-box{
            width: 40%;
            height: 400px;
            float: left;
            border: black solid 1px;
            margin-left: 4%
        }
        .info{
            width: 100%;
            height: 80%;
        }
        .input{
            width: 100%;
            height: 20%;
        }
        .message{
            width: 40%;
            border: black solid 1px;
            border-radius: 6%;
            margin: 10px;
        }
        .message-left{
            float: left;
        }
        .message-right{
            float: right;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script>

        var first;
        var second;

        $(function () {

            /* 第一个 */
            first = new GoEasy({
                host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                appkey: "BC-00a40ddf77f9434c903e08e8ac73e386", //替换为您的应用appkey
            });
            first.subscribe({
                channel: "second",
                onMessage: function (message) {
                    var message = $("<div class='message message-right'>"+message.content+"</div>")
                    $("#firstDiv").append(message);
                }
            });




            second = new GoEasy({
                host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                appkey: "BC-00a40ddf77f9434c903e08e8ac73e386", //替换为您的应用appkey
            });
            second.subscribe({
                channel: "first",
                onMessage: function (message) {
                    var message = $("<div class='message message-right'>"+message.content+"</div>")
                    $("#secondDiv").append(message);
                }
            });


        })


        function sendFirst() {

            var message = $("<div class='message message-left'>"+$("#firstInfo").val()+"</div>")
            $("#firstDiv").append(message);
            first.publish({
                 channel: "first",
                 message: $("#firstInfo").val()
            });
        }
        
        
        function sendSecond() {
            var message = $("<div class='message message-left'>"+$("#firstInfo").val()+"</div>")
            $("#secondDiv").append(message);
            second.publish({
                channel: "second",
                message: $("#secondInfo").val()
            });
        }

    </script>
</head>
<body>

    <div>

        <div class="communication-box">
            <div class="info" id="firstDiv"></div>
            <textarea class="input" id="firstInfo" rows="3"></textarea>
            <button type="button" onclick="sendFirst()">发送</button>
        </div>

        <div class="communication-box">
            <div class="info" id="secondDiv"></div>
            <textarea class="input" id="secondInfo" rows="3"></textarea>
            <button type="button" onclick="sendSecond()">发送</button>
        </div>

    </div>

</body>
</html>