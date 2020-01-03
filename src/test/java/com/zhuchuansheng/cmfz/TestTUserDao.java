package com.zhuchuansheng.cmfz;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.dao.TUserDao;
import com.zhuchuansheng.cmfz.entity.TUser;
import com.zhuchuansheng.cmfz.service.TUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 22:12
 */
@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class TestTUserDao {


    @Autowired
    private TUserService tUserService;


    @Test
    public void testAddTUserInfo(){

        Result result = tUserService.addTUserInfo(new TUser(UUID.randomUUID().toString(), null, null, null, null, null, null, "添加", null, null, null, new Date(), null, null, null));

        System.out.println(result);
    }

}
