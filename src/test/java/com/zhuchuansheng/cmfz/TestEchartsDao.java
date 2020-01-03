package com.zhuchuansheng.cmfz;

import com.zhuchuansheng.cmfz.commons.MapDto;
import com.zhuchuansheng.cmfz.dao.TUserDao;
import com.zhuchuansheng.cmfz.util.EchartsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 18:30
 */
@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class TestEchartsDao {


    @Autowired
    private TUserDao tUserDao;


    @Test
    public void testSelectMonthCountByRegister(){

        List<MapDto> mapDtos = tUserDao.selectMonthCountByRegister();
        for (MapDto mapDto : mapDtos) {

//            System.out.println(mapDto.getMonth()+"---"+mapDto.getCount());
        }
        System.out.println(EchartsUtil.resultToList(mapDtos));
    }


    @Test
    public void testSelectSevenDaysByRegister(){

        List<MapDto> mapDtos = tUserDao.selectSevenDaysByRegister();
        for (MapDto mapDto : mapDtos) {

            System.out.println(mapDto);
        }

        //System.out.println(EchartsUtil.resultToListSeven(mapDtos));
    }


    @Test
    public void testSelectEachProvinceCountByRegister(){

        List<MapDto> mapDtos = tUserDao.selectEachProvinceCountByRegister();
        for (MapDto mapDto : mapDtos) {

//            System.out.println(mapDto);
        }

        List<Map<String, Object>> mapList = EchartsUtil.resultToListEachProvince(mapDtos);
        for (Map<String, Object> map : mapList) {

            for (Map.Entry<String, Object> entry : map.entrySet()) {

                System.out.println(entry.getKey() + "---" + entry.getValue());
            }
        }
    }

}
