package com.zhuchuansheng.cmfz.service;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.entity.Admin;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author zhuchuansheng
 * @date: 2019/12/20
 * @time: 18:06
 */
public interface AdminService {


    /**
     *      管理员登陆
     *          如果登陆成功: 返回{status: 200, msg: Admin}
     *          如果登陆失败: 返回{status: 500, msg: "用户名或密码错误"}
     *
     * @param userName      管理员登陆名
     * @param password      管理员密码
     * @return
     */
    Result AdminLogin(String userName, String password);


    /**
     *      修改管理员的密码
     *
     * @param password      修改的密码
     * @param oldPassword   旧密码
     * @param session       HttpSession
     * @return
     */
    Result updatePassword(String password, String oldPassword, HttpSession session);


    /**
     *      修改管理员的信息
     *
     * @param admin     管理员信息
     * @return
     */
    Result updateAdminInfo(Admin admin, HttpSession session);


    /**
     *      上传管理员头像
     *
     * @param img           头像
     * @param session       HttpSession
     * @return
     */
    Result uploadImage(MultipartFile img, HttpSession session);
}
