package com.zhuchuansheng.cmfz.controller;

import com.zhuchuansheng.cmfz.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 17:32
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {


    @Autowired
    private EchartsService echartsService;


    /**
     *      查询每月注册的人数
     *
     * @return
     */
    @RequestMapping("/monthCount")
    public List<Integer> getMonthCount(){

        return echartsService.queryMonthCountByRegister();
    }


    /**
     *      查询最近七天的注册的人数
     *
     * @return
     */
    @RequestMapping("/sevenDays")
    public Map<String, List<Object>> getSevenDaysByRegister(){

        return echartsService.querySevenDaysByRegister();
    }


    /**
     *      查询每一个省份注册的人数
     *
     * @return
     */
    @RequestMapping("/registerByRegion")
    public List<Map<String, Object>> getRegisterByRegion(){

        return echartsService.queryRegisterByRegion();
    }

}
