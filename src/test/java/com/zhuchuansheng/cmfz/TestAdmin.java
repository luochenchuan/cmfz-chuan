package com.zhuchuansheng.cmfz;

import com.zhuchuansheng.cmfz.dao.AdminDao;
import com.zhuchuansheng.cmfz.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class TestAdmin {

    @Autowired
    private AdminDao adminDao;

    /**
     * 根据名字查询管理员信息
     */
    @Test
    public void testSelectAdminByUserName() {

        Admin admin = adminDao.selectAdminByUserName("admin");

        System.out.println(admin);
    }

}
