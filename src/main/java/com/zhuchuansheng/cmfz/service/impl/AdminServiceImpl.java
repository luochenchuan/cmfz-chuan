package com.zhuchuansheng.cmfz.service.impl;

import com.zhuchuansheng.cmfz.commons.ImageUploadUtil;
import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.dao.AdminDao;
import com.zhuchuansheng.cmfz.entity.Admin;
import com.zhuchuansheng.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuchuansheng
 * @date: 2019/12/20
 * @time: 18:12
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Result AdminLogin(String userName, String password) {

        //定义结果
        Result result = new Result(500, "用户名或密码错误");
        //验证是否为空
        if(userName == null || password == null)
            return result;

        //开始登陆
        Admin admin = adminDao.selectAdminByUserName(userName);
        //验证
        if(admin != null) {
            //验证密码
            if(admin.getPassword().equals(password))
                result = new Result(200, admin);
        }

        return result;
    }


    @Override
    public Result updatePassword(String password, String oldPassword, HttpSession session) {

        //获取登陆信息
        Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
        if(loginAdmin == null)
            return new Result(500, "你还没有登陆了");

        if(!loginAdmin.getPassword().equals(oldPassword))
            return new Result(500, "输入的密码与原密码不服");

        int i = adminDao.updateAdmin(new Admin(loginAdmin.getId(), null, password, null, null, null, null, null, null));
        if(i == 1)
            return new Result(200, "修改成功");
        return new Result(500, "修改失败");
    }

    @Override
    public Result updateAdminInfo(Admin admin, HttpSession session) {

        Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
        if(loginAdmin == null)
            return new Result(500, "你还没有登陆了");

        //修改信息
        admin.setPassword(null);
        admin.setId(loginAdmin.getId());  //设置ID

        int i = adminDao.updateAdmin(admin);
        if(i == 1)
            return new Result(200, "修改成功");

        return new Result(500, "修改失败");
    }

    @Override
    public Result uploadImage(MultipartFile img, HttpSession session) {

        Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
        if(loginAdmin == null)
            return new Result(500, "你还没有登陆了");
        //上传图片
        String fileName = ImageUploadUtil.uploadImage(session, "/image/admin/", img, null);
        int i = adminDao.updateAdmin(new Admin(loginAdmin.getId(), null, null, null, null, null, "/image/admin/"+fileName, null, null));
        if(i == 1)
            return new Result(200, "修改成功");

        return new Result(500, "修改失败");
    }
}
