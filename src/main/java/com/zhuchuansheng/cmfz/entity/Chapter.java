package com.zhuchuansheng.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *      章节实体类
 *
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 专辑ID
     */
    private String albumId;

    /**
     * 大小
     */
    private String size;

    /**
     * 时长
     */
    private String duration;

    /**
     * 音频路径
     */
    private String src;

    /**
     * 状态
     */
    private String status;
}
