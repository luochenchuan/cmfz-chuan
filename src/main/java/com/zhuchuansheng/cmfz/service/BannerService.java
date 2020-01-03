package com.zhuchuansheng.cmfz.service;

import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/23
 * @time: 15:56
 */
public interface BannerService {


    /**
     *      分页查询轮播图信息
     *
     * @param page          当前页
     * @param pageNum       每一页显示数量
     * @return
     */
    Map<String, Object> queryBannerByPaging(Integer page, Integer pageNum);


    /**
     *      添加轮播图信息
     *
     * @param banner        轮播图信息
     * @return
     */
    Map<String, Object> addBannerInfo(Banner banner);


    /**
     *      修改轮播图的信息
     *
     * @param banner        轮播图信息
     */
    Map<String, Object> updateBannerInfo(Banner banner);


    /**
     *      批量删除轮播图信息
     *
     * @param ids       删除ID集合
     * @return
     */
    Map<String, Object> deleteAll(String ids);


    /**
     *      上传图片并更新数据库信息
     *
     * @param img           上传图片
     * @param id            更新的ID
     * @param session
     * @return
     */
    Map<String, Object> uploadImageAndUpdateInfo(MultipartFile img, String id, HttpSession session);


    /**
     *      将一个集合添加到数据库中
     *
     * @param banners       添加的集合
     * @return
     */
    Result insertAllByList(List<Banner> banners);
}
