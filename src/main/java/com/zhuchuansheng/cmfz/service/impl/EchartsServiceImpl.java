package com.zhuchuansheng.cmfz.service.impl;

import com.zhuchuansheng.cmfz.commons.MapDto;
import com.zhuchuansheng.cmfz.dao.TUserDao;
import com.zhuchuansheng.cmfz.service.EchartsService;
import com.zhuchuansheng.cmfz.util.EchartsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/30
 * @time: 19:13
 */
@Service
@Transactional
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    private TUserDao tUserDao;

    @Override
    public List<Integer> queryMonthCountByRegister() {

        //查询数据
        List<MapDto> mapDtos = tUserDao.selectMonthCountByRegister();

        //数据转换
        List<Integer> integers = EchartsUtil.resultToList(mapDtos);

        return integers;
    }

    @Override
    public Map<String, List<Object>> querySevenDaysByRegister() {

        //查询数据
        List<MapDto> mapDtos = tUserDao.selectSevenDaysByRegister();

        //数据转换
        Map<String, List<Object>> stringListMap = EchartsUtil.resultToListSeven(mapDtos);

        return stringListMap;
    }


    @Override
    public List<Map<String, Object>> queryRegisterByRegion() {

        List<MapDto> mapDtos = tUserDao.selectEachProvinceCountByRegister();

        List<Map<String, Object>> maps = EchartsUtil.resultToListEachProvince(mapDtos);

        return maps;
    }
}
