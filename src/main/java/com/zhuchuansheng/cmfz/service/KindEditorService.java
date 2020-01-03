package com.zhuchuansheng.cmfz.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/26
 * @time: 14:16
 */
public interface KindEditorService {


    /**
     *      上传图片
     *
     * @param img           上传图片名称
     * @param session       HttpSession
     * @return
     */
    Map<String, Object> updateImage(MultipartFile img, HttpSession session, HttpServletRequest request);


    /**
     *      获取图片空间
     *
     * @param request
     * @return
     */
    Map<String, Object> imageSpace(HttpServletRequest request);

}
