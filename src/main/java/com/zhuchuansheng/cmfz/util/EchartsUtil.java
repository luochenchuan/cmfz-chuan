package com.zhuchuansheng.cmfz.util;

import com.zhuchuansheng.cmfz.commons.MapDto;
import org.omg.CORBA.MARSHAL;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;

import javax.swing.*;
import java.util.*;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 18:40
 */
public class EchartsUtil {


    /**
     *      将自定义的结果转换成List(长度12)集合
     *
     * @param list
     * @return
     */
    public static List<Integer> resultToList (List<MapDto> list){

        //将List转换为List
        Map<Integer, Integer> map = new HashMap<>();
        //处理数据
        for (MapDto mapDto : list) {
            map.put(mapDto.getMonth(), mapDto.getCount());
        }

        //在将Map转换为list集合
        List<Integer> result = new ArrayList<>();
        //数据转换
        for(int i = 1; i < 13; i++){

            if(map.containsKey(i))
                result.add(map.get(i));
            else
                result.add(0);
        }
        return result;
    }


    public static Map<String, List<Object>> resultToListSeven (List<MapDto> list){

        //获取开始位置
        Integer targetDay = list.get(0).getTargetDay() + 1;
        //获取结束位置
        int day = new Date().getDate();
        int month = new Date().getMonth() + 1;

        //将List转换为Map集合
        Map<Integer, Integer> map = new HashMap<>();
        for (MapDto mapDto : list) {
            map.put(mapDto.getDay(), mapDto.getCount());
        }

        //将Map转换为List集合
        List<Object> days = new ArrayList<>();         //天数
        List<Object> count = new ArrayList<>();        //注册的人数
        for(int i = targetDay; i <= day; i++){
            if(map.containsKey(i))
                count.add(map.get(i));
            else
                count.add(0);
            days.add(month+"月"+i+"日");
        }

        Map<String, List<Object>> result = new HashMap<>();
        result.put("days", days);
        result.put("count", count);

        return result;
    }


    public static List<Map<String, Object>> resultToListEachProvince (List<MapDto> list){

        List<Map<String, Object>> result = new ArrayList<>();
        for (MapDto mapDto : list) {

            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", mapDto.getAddress());
            hashMap.put("value", mapDto.getCount());
            result.add(hashMap);
        }

        return result;
    }

}
