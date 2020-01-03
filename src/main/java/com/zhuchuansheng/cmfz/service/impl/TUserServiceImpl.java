package com.zhuchuansheng.cmfz.service.impl;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.dao.TUserDao;
import com.zhuchuansheng.cmfz.entity.TUser;
import com.zhuchuansheng.cmfz.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 21:37
 */
@Service
@Transactional
public class TUserServiceImpl implements TUserService {

    @Autowired
    private TUserDao tUserDao;

    @Override
    public Result addTUserInfo(TUser user) {

        int i = tUserDao.insertSelective(user);
        if(i == 1)
            return new Result(200, "添加成功");

        return new Result(500, "添加失败");
    }


}
