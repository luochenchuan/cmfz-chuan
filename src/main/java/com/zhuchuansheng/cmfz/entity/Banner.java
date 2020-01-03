package com.zhuchuansheng.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *      轮播图的实体类
 * @author zhuchuansheng
 * @date: 2019/12/23
 * @time: 15:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner implements Serializable {

    @Excel(name = "编号", isImportField = "true_st")
    private String id;

    @Excel(name = "标题", isImportField = "true_st")
    private String title;

    @Excel(name = "图片", type = 2, width = 100, height = 60, isImportField = "true_st", savePath = "E:\\baizhi\\code\\培训\\05--后期项目\\code\\cmfz-chuan\\src\\main\\webapp\\image\\import-file\\")
    private String img;

    @Excel(name = "上传时间", format = "yyyy年MM月dd日", isImportField = "true_st")
    private Date createDate;

    @Excel(name = "状态", isImportField = "true_st")
    private String status;

}
