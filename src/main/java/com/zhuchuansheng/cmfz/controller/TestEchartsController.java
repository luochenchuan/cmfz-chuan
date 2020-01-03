package com.zhuchuansheng.cmfz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 14:31
 */
@RequestMapping("/echarts")
@RestController
public class TestEchartsController {


    @RequestMapping("/getEchartsAllData")
    public List<Map<String, Object>> getEchartsAllData(){

        List<Map<String, Object>> result = new ArrayList<>();

        String[] titles = {"北京", "天津", "上海", "重庆", "河北", "河南", "云南", "辽宁"};
        for (String title : titles) {

            HashMap<String, Object> data = new HashMap<>();
            data.put("name", title);
            data.put("value", new Random().nextInt(1000));
            result.add(data);
        }

        return result;
    }

}
