package com.zhuchuansheng.cmfz.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 *      定义结果
 *
 * @author zhuchuansheng
 * @date: 2019/12/20
 * @time: 18:07
 */
@Data
@AllArgsConstructor
public class Result implements Serializable {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 消息体
     */
    private Object msg;
}
