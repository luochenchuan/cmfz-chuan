package com.zhuchuansheng.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *      管理员的实体类
 * @author zhuchuansheng
 * @date: 2019/12/20
 * @time: 17:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String role;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;
}
