package com.zhuchuansheng.cmfz.controller;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.entity.Admin;
import com.zhuchuansheng.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zhuchuansheng
 * @date: 2019/12/20
 * @time: 18:22
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     *      管理员登陆
     *
     * @param userName      管理员用户名
     * @param password      管理员密码
     * @param securityCode  验证码
     * @param session       回话对象
     * @return
     */
    @RequestMapping("/login")
    public Result login(String userName, String password, String securityCode, HttpSession session){

        Result result = new Result(500, "验证码错误");
        //判断验证码相等
        String code = (String) session.getAttribute("securityCode");
        if(!securityCode.equalsIgnoreCase(code))
            return result;

        //登陆
        result = adminService.AdminLogin(userName, password);
        if(result.getMsg() instanceof Admin){
            session.setAttribute("loginAdmin", result.getMsg());
            result.setMsg("用户登陆成功");
        }
        return result;
    }


    /**
     *      修改管理员的密码
     *
     * @param password          修改的密码
     * @param oldPassword       旧密码
     * @param session           HttpSession
     * @return
     */
    @RequestMapping("/updatePassword")
    public Result updatePassword(String password, String oldPassword, HttpSession session){

        return adminService.updatePassword(password, oldPassword, session);
    }


    /**
     *      修改管理员信息
     *
     * @param admin         修改信息
     * @param img           图片
     * @param session       HttpSession
     * @return
     */
    @RequestMapping("/updateAdminInfo")
    public Result updateAdminInfo(Admin admin, MultipartFile img, HttpSession session){

        return adminService.updateAdminInfo(admin, session);
    }


    /**
     *      上传头像
     *
     * @param img           头像
     * @param session       HttpSession
     * @return
     */
    @RequestMapping("/uploadImage")
    public Result uploadImage(MultipartFile img, HttpSession session){

        return adminService.uploadImage(img, session);
    }


    /**
     *      管理员退出登陆
     *
     * @param response
     * @param request
     * @param session
     */
    @GetMapping("/exitAdmin")
    public void exitAdmin(HttpServletResponse response, HttpServletRequest request, HttpSession session){
        try {
            session.invalidate();
            //重定向到登陆页面
            response.sendRedirect(request.getContextPath()+"/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
