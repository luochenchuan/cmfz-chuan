package com.zhuchuansheng.cmfz.controller.front;

import com.zhuchuansheng.cmfz.commons.ResultDto;
import com.zhuchuansheng.cmfz.commons.ResultSubDto;
import com.zhuchuansheng.cmfz.entity.Album;
import com.zhuchuansheng.cmfz.entity.Article;
import com.zhuchuansheng.cmfz.entity.Banner;
import com.zhuchuansheng.cmfz.entity.TUser;
import com.zhuchuansheng.cmfz.service.AlbumService;
import com.zhuchuansheng.cmfz.service.ArticleService;
import com.zhuchuansheng.cmfz.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/31
 * @time: 14:42
 */
@RestController
public class FirstPageController {


    @Autowired
    private BannerService bannerService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArticleService articleService;

    /**
     *      一级页面的数据获取
     *
     * @param uid           用户数据
     * @param type          数据类型
     *                          all: 查询轮播图
     *                          wen: 查询专辑
     *                          si:  查询文章
     * @param sub_type      子类型
     *                          ssyj: 上师言教
     *                          xmfy: 显密法要
     */
    @RequestMapping("/first_page")
    public ResultDto first_page(String uid, String type, String sub_type, HttpSession session){

        //获取上师的ID
        TUser login = (TUser) session.getAttribute("login");
        String guruId = null;
        if(login != null) {
            guruId = login.getGuruId();
        }
        //返回值
        ResultDto resultDto = new ResultDto();

        //查询轮播图
        if("all".equals(type)){
            //轮播图查询
            resultDto = this.getBanner(resultDto);
            //专辑查询
            resultDto = this.getAlbum(resultDto);
            //文章查询
            resultDto = this.getArticleAll(resultDto, null);
        }else if ("wen".equals(type)){
            //专辑查询
            resultDto = this.getAlbum(resultDto);
        }else if("si".equals(type)){
            //文章查询
            if("ssyj".equals(sub_type)){
                resultDto = this.getArticleAll(resultDto, guruId);
            }

            if("xmfy".equals(sub_type)){
                resultDto = this.getArticleAll(resultDto, guruId);
            }
        }

        return resultDto;
    }


    /**
     *      获取专辑数据
     *
     * @param resultDto
     * @return
     */
    private ResultDto getAlbum(ResultDto resultDto){

        List<Album> albums = albumService.queryAllAlbum();
        for (Album album : albums) {

            ResultSubDto resultSubDto = new ResultSubDto();

            resultSubDto.setThumbnail(album.getImg());
            resultSubDto.setAuthor(album.getAuthor());
            resultSubDto.setCreate_date(album.getCreateDate());
            resultSubDto.setTitle(album.getTitle());
            resultSubDto.setSet_count(album.getCount().toString());
            resultSubDto.setType("0");
            resultDto.getBody().add(resultSubDto);
        }

        return resultDto;
    }


    /**
     *      查询轮播图
     *
     * @param resultDto
     * @return
     */
    private ResultDto getBanner(ResultDto resultDto){

        Map<String, Object> map = bannerService.queryBannerByPaging(1, 3);
        List<Banner> rows = (List<Banner>) map.get("rows");
        for (Banner row : rows) {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("thumbnail", row.getImg());
            hashMap.put("desc", row.getTitle());
            hashMap.put("id", row.getId());
            resultDto.getHeader().add(hashMap);
        }

        return resultDto;
    }


    /**
     *      获取文章
     *
     * @param resultDto
     * @return
     */
    private ResultDto getArticleAll(ResultDto resultDto, String guruId){

        Map<String, Object> map =null;
        if(guruId != null){
            //根据上传获取数据数据
            map = articleService.queryByPaging(1, 3, guruId);
        }else{
            //查询全部的数据
            map = articleService.queryAllByPaging(1, 3);
        }
        List<Article> rows = (List<Article>) map.get("rows");
        for (Article row : rows) {
            ResultSubDto resultSubDto = new ResultSubDto();
            resultSubDto.setThumbnail("");
            resultSubDto.setType("1");
            resultSubDto.setSet_count("");
            resultSubDto.setTitle(row.getTitle());
            resultSubDto.setAuthor(row.getAuthor());
            resultSubDto.setCreate_date(row.getCreateDate());
            resultDto.getBody().add(resultSubDto);
        }
        return resultDto;
    }


}
