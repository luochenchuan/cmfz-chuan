package com.zhuchuansheng.cmfz.service.impl;

import com.zhuchuansheng.cmfz.commons.ImageUploadUtil;
import com.zhuchuansheng.cmfz.dao.AlbumDao;
import com.zhuchuansheng.cmfz.entity.Album;
import com.zhuchuansheng.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:29
 */
@Transactional
@Service
public class AlbumServiceImpl implements AlbumService {


    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map<String, Object> queryAllByPaging(Integer page, Integer pageNum) {

        if(page == null)
            page = 1;

        if(pageNum == null)
            pageNum = 3;

        //计算开始下标
        int start = (page - 1) * pageNum;
        //查询分页数据
        List<Album> albums = albumDao.selectAllByPaging(start, pageNum);
        //查询总记录数
        int count = albumDao.selectCountById();
        //计算总页数
        int total = count % pageNum == 0 ? count / pageNum : count / pageNum + 1;

        //定义返回值
        Map<String, Object> result = new HashMap<>();
        result.put("page", page);
        result.put("records", count);
        result.put("total", total);
        result.put("rows", albums);

        return result;
    }



    @Override
    public Map<String, Object> addAlbum(Album album) {

        //设置ID
        String uuid = UUID.randomUUID().toString();
        album.setId(uuid);
        //设置封面图片
        album.setImg("");
        //设置添加时间
        album.setCreateDate(new Date());
        //设置专辑数量   默认值为0
        album.setCount(0);

        //定义结果
        Map<String, Object> result = new HashMap<>();
        result.put("uuid", uuid);
        //保存数据
        int i = albumDao.insertAlbum(album);
        if(i == 1)
            result.put("success", i);

        return result;
    }


    @Override
    public Map<String, Object> updateAlbum(Album album) {

        Map<String, Object> result = new HashMap<>();
        int i = albumDao.updateAlbum(album);

        result.put("success", i);

        return result;
    }


    @Override
    public List<Album> queryAllAlbum() {

        return albumDao.selectAllAlbum();
    }


    @Override
    public Map<String, Object> deleteAlbum(String id) {

        Map<String, Object> result = new HashMap<>();

        int i = albumDao.deleteAlbum(id);
        result.put("success", i);

        return result;
    }


    @Override
    public Map<String, Object> uploadImageAndUpdateInfo(MultipartFile img, String id, HttpSession session) {

        //图片上传
        String fileName = ImageUploadUtil.uploadImage(session, "/image/album/", img, null);

        //修改图片地址
        int i = albumDao.updateAlbum(new Album(id, null, "/image/album/" + fileName, null, null, null, null, null, null, null));

        Map<String, Object> result = new HashMap<>();
        result.put("success", i);
        result.put("fileName", fileName);

        return result;
    }


    @Override
    public Album queryAlbumById(String id) {

        Album album = albumDao.selectAlbumById(id);
        return album;
    }
}
