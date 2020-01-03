<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<c:set value="${pageContext.request.contextPath}" var="app"/>

<style>
    #album-btn-box{
        width: 80%;
        margin: 0 auto;
    }
    #album-btn-box button{
        margin-left: 5%;
    }
</style>

<script>

    $(function () {

        $("#album-list").jqGrid({
            url: "${app}/album/queryAll",
            mtype: "GET",
            styleUI : 'Bootstrap',
            datatype: "json",
            colNames: ["编号", "标题", "评分", "封面", "作者", "章节数", "状态","上传时间"],
            pager: "#album-paging",
            autowidth: true,
            subGrid: true,      //开启子表格
            rowNum: 3,
            viewrecords: true,
            cellEdit: false,
            editurl: "${app}/album/updateAlbum",
            rownumbers: true,
            height: "320px",
            colModel: [
                {name: "id", hidden: true},
                {name: "title", align: "center", editable: true},
                {name: "score", align: "center", editable: true},
                {name: "img", align: "center", /*editable: true,*/ edittype: "file", formatter: function (cellvalue, options, rowObject) {

                        return "<img src='${app}/"+cellvalue+"' style='width: 50px; height: 50px' />";
                    }
                },
                {name: "author", align: "center", editable: true},
                {name: "count", align: "center"},
                {name: "status", align: "center", editable: true, edittype: "select", editoptions: {"value": "1:正 常;0:冻 结"}, formatter: function (cellvalue, options, rowObject) {

                        if(cellvalue == 1)
                            return "<span class='activate success'>正 常</span>";

                        return "<span class='activate error'>冻 结</span>";
                    }
                },
                {name: "createDate", align: "center"}
            ],
            //打开子表格时触发该事件
            subGridRowExpanded: function (subGrid_id, row_id) {
                /**
                 *      subGrid_id   子表格的外层容器的ID
                 *      row_id        当前行的ID
                 */
                createSubJqGridTable(subGrid_id, row_id);
            }

        }).jqGrid("navGrid", "#album-paging",{add: false, search: false},
            {},
            {},
            {}
        );


    });


    /* 创建子表格 */
    function createSubJqGridTable(subGridId, rowId) {

        //自定义ID
        var subTableId = subGridId + "-table";
        var subDivId = subGridId + "-div";

        //添加表格,分页的容器
        $("#"+subGridId).html("<table id='"+subTableId+"'></table>"+
                              "<div id='"+subDivId+"' style='height: 50px'></div>");

        //初始化数据
        $("#"+subTableId).jqGrid({
            url: "${app}/chapter/queryAll?albumId="+rowId,
            mtype: "GET",
            styleUI : 'Bootstrap',
            datatype: "json",
            colNames: ["编号", "标题", "大小", "时长", "音频", "状态", "操作"],
            pager: "#"+subDivId,
            autowidth: true,
            rownumbers: true,
            editurl: "${app}/chapter/updateChapter?albumId="+rowId,
            viewrecords: true,
            height: "100%",
            colModel: [
                {name: "id", hidden: true},
                {name: "title", align: "center", editable: true},
                {name: "size", align: "center"},
                {name: "duration", align: "center"},
                {name: "src", editable: true, hidden: true, editrules: {edithidden: true}, edittype: "file"},
                {name: "status", align: "center", editable: true, edittype: "select", editoptions: {value: "1:正 常;0:冻 结"}, formatter: function (cellvalue, options, rowObject) {

                        if(cellvalue == 1)
                            return "<span class='activate success'>正 常</span>";

                        return "<span class='activate error'>冻 结</span>";
                    }
                },
                {name: "status", align: "center", formatter: function (cellvalue, options, rowObject) {

                        var id = rowObject["id"]
                        if(cellvalue == 1)
                            return "<button class='btn btn-danger' onclick=\"updateAlbumStatus(0, '"+id+"')\">设置为冻结</button>";

                        return "<button class='btn btn-success' onclick=\"updateAlbumStatus(1, '"+id+"')\">设置为正常</button>";
                    }
                }
            ],
            toolbar: [true, "top"]    //构建工具栏

        }).jqGrid("navGrid", "#"+subDivId, {add: false, search: false},
            {
                closeAfterdEdit:true,
                afterSubmit: function (response){

                    var uuid = response["responseJSON"]["msg"]["uuid"];
                    var isUpdateImage = response["responseJSON"]["msg"]["isUpdateImage"];
                    if(isUpdateImage){
                        layer.open({
                            title: "",
                            content: "修改成功!!!",
                            time: 1000,
                            skin: "demo-class",
                            anim: 5
                        });
                        return response;
                    }

                    $.ajaxFileUpload({
                        url: "${app}/chapter/uploadMusic",
                        type: "POST",
                        data: {id: uuid},
                        fileElementId: "src",
                        success: function () {
                            $("#"+subTableId).trigger("reloadGrid");
                            layer.open({
                                title: "",
                                content: "修改成功!!!",
                                time: 1000,
                                skin: "demo-class",
                                anim: 5
                            });
                        }
                    })

                    return response;
                }

            },
            {},
            {}
        );

        //onclick=\"play('"+subGridTableId+"')\"
        $("#t_"+subTableId).html("<div id='album-btn-box'>" +
                "<button type='button' class='btn btn-success' onclick=\" playBtn('"+subTableId+"', this) \">播放 " +
                    "<span class='glyphicon glyphicon-play'></span>" +
                "</button>" +
                "<button type='button' class='btn btn-primary' onclick=\" uploadBtn('"+subTableId+"') \">下载 " +
                    "<span class='glyphicon glyphicon-save'></span>" +
                "</button>" +
            "</div>");
    }


    /* 添加按钮 */
    function playBtn(subTableId, obj) {

        //判断是否选择数据
        var rowId = $("#"+subTableId).jqGrid('getGridParam', 'selrow');
        if(rowId == null){
            return;
        }
        //获取选中的数据
        var data = $("#"+subTableId).jqGrid('getRowData', rowId);
        var music = new Music("${app}"+data["src"]);
        music.playOrPause();
        //改变图标的样式
        // $(obj).children().attr("class", "glyphicon glyphicon-pause");
    }


    /* 下载 */
    function uploadBtn(subTableId) {

        var rowId = $("#"+subTableId).jqGrid('getGridParam', 'selrow');
        if(rowId == null){
            return;
        }
        //获取选中的数据
        var data = $("#"+subTableId).jqGrid('getRowData', rowId);
        location.href = "${app}/chapter/downloadMusic?src="+data["src"];
    }


    /* 修改状态 */
    function updateAlbumStatus(status, id) {

        $.ajax({
            url: "${app}/chapter/updateChapterStatus",
            data: {status: status, id: id},
            type: "POST",
            success: function (result) {
                $("#album-list").trigger("reloadGrid");
                layer.open({
                    title: "",
                    content: "修改成功!!!",
                    time: 1000,
                    skin: "demo-class",
                    anim: 1
                });
            }
        })

    }

</script>


<div class="page-header">
    <h2 class="head-h2">专辑列表</h2>
</div>

<table id="album-list"></table>
<div id="album-paging" style='height: 50px'></div>

