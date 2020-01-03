package com.zhuchuansheng.cmfz;

import com.zhuchuansheng.cmfz.dao.BannerDao;
import com.zhuchuansheng.cmfz.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhuchuansheng
 * @date: 2019/12/23
 * @time: 15:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class TestBanner {

    @Autowired
    private BannerDao bannerDao;

    @Test
    public void testSelectByPaging(){

        List<Banner> banners = bannerDao.selectByPaging(1, 1);
        System.out.println(banners);
    }

}

