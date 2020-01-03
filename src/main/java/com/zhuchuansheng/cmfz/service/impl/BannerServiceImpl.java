package com.zhuchuansheng.cmfz.service.impl;

import com.zhuchuansheng.cmfz.annotation.AddCache;
import com.zhuchuansheng.cmfz.annotation.ClearCache;
import com.zhuchuansheng.cmfz.commons.ImageUploadUtil;
import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.dao.BannerDao;
import com.zhuchuansheng.cmfz.entity.Banner;
import com.zhuchuansheng.cmfz.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author zhuchuansheng
 * @date: 2019/12/23
 * @time: 15:57
 */
@Transactional
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @AddCache
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryBannerByPaging(Integer page, Integer pageNum) {

        if(page == null)
            page = 1;
        if(pageNum == null)
            pageNum = 3;

        //计算下标
        int start = (page - 1) * pageNum;
        //获取数据
        List<Banner> rows = bannerDao.selectByPaging(start, pageNum);

        //查询总记录数
        int count = bannerDao.selectCountById();
        int total = count % pageNum == 0 ? count / pageNum : count / pageNum + 1;
        //定义返回值
        Map<String, Object> result = new HashMap<>();

        result.put("rows", rows);
        result.put("total", total);
        result.put("page", page);
        result.put("records", count);

        return result;
    }


    @ClearCache
    @Override
    public Map<String, Object> addBannerInfo(Banner banner) {

        //获取ID
        String id = UUID.randomUUID().toString();
        banner.setId(id);
        //设置时间
        banner.setCreateDate(new Date());
        //添加轮播图
        int i = bannerDao.insertBanner(banner);

        Map<String, Object> result = new HashMap<>();
        result.put("uuid", id);

        if(i == 1)
            result.put("success", true);

        return result;
    }


    @ClearCache
    @Override
    public Map<String, Object> updateBannerInfo(Banner banner) {

        Map<String, Object> result = new HashMap<>();

        int i = bannerDao.updateBannerById(banner);
        result.put("uuid", banner.getId());
        if(i == 1)
            result.put("success", true);

        return result;
    }


    @ClearCache
    @Override
    public Map<String, Object> deleteAll(String ids) {

        Map<String, Object> result = new HashMap<>();
        List<String> list = this.stringToList(ids);
        int i = bannerDao.deleteAll(list);
        result.put("success", i);

        return result;
    }


    private List<String> stringToList(String ids){

        List<String> result = new ArrayList<>();
        if(!ids.contains(",")){
            result.add(ids);
        }else{

            String[] split = ids.split(",");

            for (String id : split) {
                result.add(id);
            }
        }
        return result;
    }


    @ClearCache
    @Override
    public Map<String, Object> uploadImageAndUpdateInfo(MultipartFile img, String id, HttpSession session) {

        //图片上传
        String fileName = ImageUploadUtil.uploadImage(session, "image/banner/", img, null);

        Map<String, Object> map = this.updateBannerInfo(new Banner(id, null, "/image/banner/" + fileName, null, null));

        map.put("fileName", fileName);

        return map;
    }


    @ClearCache
    @Override
    public Result insertAllByList(List<Banner> banners) {

        int i = bannerDao.insertByList(banners);

        return new Result(200, "添加成功了"+i+"条数据");
    }
}
