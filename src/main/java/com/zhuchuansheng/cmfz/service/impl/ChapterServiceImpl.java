package com.zhuchuansheng.cmfz.service.impl;

import com.zhuchuansheng.cmfz.dao.AlbumDao;
import com.zhuchuansheng.cmfz.dao.ChapterDao;
import com.zhuchuansheng.cmfz.entity.Album;
import com.zhuchuansheng.cmfz.entity.Chapter;
import com.zhuchuansheng.cmfz.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.spi.CurrencyNameProvider;

/**
 * @author zhuchuansheng
 * @date: 2019/12/24
 * @time: 15:38
 */
@Transactional
@Service
public class ChapterServiceImpl implements ChapterService {


    @Autowired
    private ChapterDao chapterDao;

    @Autowired
    private AlbumDao albumDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryAllByPaging(Integer page, Integer pageNum, String albumId) {

        if(page == null)
            page = 1;

        if(pageNum == null)
            pageNum = 3;

        //计算开始下标
        int start = (page - 1) * pageNum;
        //查询数据
        List<Chapter> chapters = chapterDao.selectAllByPaging(start, pageNum, albumId);
        //查询总记录数
        int count = chapterDao.selectCountById(albumId);
        int total = count % pageNum == 0? count / pageNum : count / pageNum + 1;

        //定义返回值
        Map<String, Object> result = new HashMap<>();
        result.put("page", page);
        result.put("records", count);
        result.put("total", total);
        result.put("rows", chapters);

        return result;
    }


    @Override
    public Map<String, Object> addChapter(Chapter chapter) {

        //设置ID
        String uuid = UUID.randomUUID().toString();
        chapter.setId(uuid);
        //设置音频
        chapter.setSrc("");
        //设置大小
        chapter.setSize("0");
        //设置时长
        chapter.setDuration("0");
        //保存数据
        int i = chapterDao.insertChapter(chapter);
        //专辑总的章节数加一
        Album album = albumDao.selectAlbumById(chapter.getAlbumId());
        Integer count = album.getCount();
        count += 1;
        i += albumDao.updateAlbum(new Album(album.getId(), null, null, null, null, null, count, null, null, null));
        Map<String, Object> result = new HashMap<>();
        result.put("uuid", uuid);
        if(i == 1)
            result.put("success", i);

        return result;
    }

    @Override
    public Map<String, Object> updateChapter(Chapter chapter) {

        boolean aNull = this.isNull(chapter);
        Map<String, Object> result = new HashMap<>();

        int i = chapterDao.updateChapter(chapter);

        result.put("success", i);
        result.put("uuid", chapter.getId());
        result.put("isUpdateImage", aNull);

        return result;
    }


    /**
     *      判断是否为空
     *
     * @param chapter
     * @return
     */
    public boolean isNull(Chapter chapter){

        if(chapter.getSrc() == null)
            return true;
        if("".equals(chapter.getSrc())) {
            chapter.setSrc(null);
            return true;
        }
        if(" ".equals(chapter.getSrc())) {
            chapter.setSrc(null);
            return true;
        }

        return false;
    }


    @Override
    public Map<String, Object> deleteChapter(String id, String albumId) {

        int i = chapterDao.deleteChapter(id);

        //查询总数量
        Album album = albumDao.selectAlbumById(albumId);
        Integer count = album.getCount();
        //修改该专辑中的数量
        Album update = new Album(albumId, null, null, null, null, null, (count - i), null, null, null);
        i += albumDao.updateAlbum(update);
        Map<String, Object> result = new HashMap<>();
        if(i == 1)
            result.put("success", i);

        return result;
    }
}
