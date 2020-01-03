package com.zhuchuansheng.cmfz.dao;

import com.zhuchuansheng.cmfz.commons.MapDto;
import com.zhuchuansheng.cmfz.entity.TUser;

import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 17:34
 */
public interface TUserDao {

    int deleteByPrimaryKey(String id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);


    /**
     *      查询每月注册的人数
     *
     * @return
     */
    List<MapDto> selectMonthCountByRegister();


    /**
     *      查询最近一周(七天)的注册的人数
     *
     * @return
     */
    List<MapDto> selectSevenDaysByRegister();


    /**
     *      查询每一个省的注册人数
     *
     * @return
     */
    List<MapDto> selectEachProvinceCountByRegister();

}
