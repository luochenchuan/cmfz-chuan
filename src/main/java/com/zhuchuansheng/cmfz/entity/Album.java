package com.zhuchuansheng.cmfz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *      专辑实体类
 *
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
                        public class Album implements Serializable {

    /**
     * ID标识
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面路径
     */
    private String img;

    /**
     *  评分
     */
    private String score;

    /**
     * 作者
     */
    private String author;

    /**
     *  播音人员姓名
     */
    private String broadcaster;

    /**
     * 章节数
     */
    private Integer count;

    /**
     * 简介
     */
    private String brief;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 状态
     */
    private String status;
}
