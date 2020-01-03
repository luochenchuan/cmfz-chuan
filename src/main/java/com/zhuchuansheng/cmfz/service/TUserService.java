package com.zhuchuansheng.cmfz.service;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.entity.TUser;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 21:36
 */
public interface TUserService {


    /**
     *      添加数据
     *
     * @param user
     * @return
     */
    Result addTUserInfo(TUser user);
}
