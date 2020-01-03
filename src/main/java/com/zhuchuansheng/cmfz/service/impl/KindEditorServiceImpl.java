package com.zhuchuansheng.cmfz.service.impl;

import com.zhuchuansheng.cmfz.commons.ImageUploadUtil;
import com.zhuchuansheng.cmfz.service.KindEditorService;
import com.zhuchuansheng.cmfz.util.RequestPath;
import org.apache.commons.io.FilenameUtils;
import org.apache.coyote.ErrorState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhuchuansheng
 * @date: 2019/12/26
 * @time: 14:18
 */
@Service
public class KindEditorServiceImpl implements KindEditorService {


    @Override
    public Map<String, Object> updateImage(MultipartFile img,
                                           HttpSession session, HttpServletRequest request) {
        /**
         * 图片上传
         *     返回图片上传后的图片名
         */
        String fileName = ImageUploadUtil.uploadImage(session, "/image/kindEditor", img, null);

        Map<String, Object> result = new HashMap<>();

        result.put("error", 0);
        String requestPath = RequestPath.getRequestPath(request);
        result.put("url", requestPath + "/image/kindEditor/" + fileName);

        return result;
    }

    @Override
    public Map<String, Object> imageSpace(HttpServletRequest request) {

        //获取路径
        String realPath = request.getSession().getServletContext().getRealPath("/image/kindEditor");
        //获取文件夹中所以图片
        File images = new File(realPath);
        String[] names = images.list();
        if (names == null || names.length < 1) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (String name : names) {

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("dir_path", "");
            hashMap.put("is_dir", false);
            hashMap.put("has_file", false);
            hashMap.put("is_photo", true);
            hashMap.put("filename", name);
            hashMap.put("filesize", new File(realPath, name).length());
            hashMap.put("filetype", FilenameUtils.getExtension(name));
            Long aLong = Long.valueOf(name.split("-")[0]);
            Date date = new Date(aLong);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            hashMap.put("datetime", simpleDateFormat.format(date));

            list.add(hashMap);
        }

        result.put("file_list", list);
        result.put("current_url", RequestPath.getRequestPath(request) + "/image/kindEditor/");
        result.put("total_count", names.length);
        result.put("moveup_dir_path", "");
        result.put("current_dir_path", "");

        return result;
    }
}
