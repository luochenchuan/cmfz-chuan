package com.zhuchuansheng.cmfz.dao;

import com.zhuchuansheng.cmfz.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhuchuansheng
 * @date: 2019/12/20
 * @time: 17:33
 */
public interface AdminDao {


    /**
     *      根据管理员名查询管理员信息
     *
     * @param userName      管理员名
     * @return
     */
    Admin selectAdminByUserName(@Param("userName") String userName);


    /**
     *      修改管理员的密码
     *
     * @param admin     修改信息
     * @return
     */
    int updateAdmin(Admin admin);

}
