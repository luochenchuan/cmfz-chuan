package com.zhuchuansheng.cmfz.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 18:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapDto implements Serializable {

    /**
     * 月份
     */
    private Integer month;

    /**
     * 天数
     */
    private Integer day;

    /**
     * 目标天数
     */
    private Integer targetDay;

    /**
     * 地址
     */
    private String address;

    /**
     * 数量
     */
    private Integer count;
}
