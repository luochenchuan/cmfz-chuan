package com.zhuchuansheng.cmfz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 19:12
 */
public interface EchartsService {


    /**
     *      查询每月注册的人数
     *
     * @return
     */
    List<Integer> queryMonthCountByRegister();


    /**
     *      查询最近七天的注册的数据
     *
     * @return
     */
    Map<String, List<Object>> querySevenDaysByRegister();


    /**
     *      根据地区查询注册的人数
     *
     * @return
     */
    List<Map<String, Object>> queryRegisterByRegion();
}
