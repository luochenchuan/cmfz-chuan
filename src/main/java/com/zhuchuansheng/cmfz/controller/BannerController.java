package com.zhuchuansheng.cmfz.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.zhuchuansheng.cmfz.commons.Result;
import com.zhuchuansheng.cmfz.entity.Banner;
import com.zhuchuansheng.cmfz.service.BannerService;
import com.zhuchuansheng.cmfz.util.RequestPath;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.metadata.ManagedOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhuchuansheng
 * @date: 2019/12/23
 * @time: 15:37
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     *      分页查询轮播图
     *
     * @param page          当前页
     * @param pageNum       每一页显示数量
     * @return
     */
    @RequestMapping("/queryBanner")
    public Map<String, Object> queryBannerByPaging(Integer page, Integer pageNum){

        return bannerService.queryBannerByPaging(page, pageNum);
    }


    /**
     *      编辑操作
     *
     * @param oper              操作选项
     * @param banner            操作参数
     * @return
     */
    @RequestMapping("/editBanner")
    public Map<String, Object> editBanner(String oper, Banner banner){

        Map<String, Object> map = null;
        //添加轮播图
        if("add".equals(oper)){

            map = bannerService.addBannerInfo(banner);
        }else if("edit".equals(oper)){

            //判断是的需要更新图片
            boolean update = this.isUpdate(banner);
            //更新数据
            map = bannerService.updateBannerInfo(banner);
            map.put("updateImage", update);
        }else if("del".equals(oper)){

            //获取ID
            String id = banner.getId();
            map = bannerService.deleteAll(id);
        }
        return map;
    }


    /**
     *      更新图片
     *
     * @param img           图片
     * @param id            修改的ID
     * @param request       请求对象
     * @return
     */
    @PostMapping("/upload")
    public Map<String, Object> uploadImage(MultipartFile img, String id, HttpServletRequest request){

        return bannerService.uploadImageAndUpdateInfo(img, id, request.getSession());
    }


    /**
     *      修改状态
     *
     * @param id            修改ID
     * @param status        状态值
     */
    @PostMapping("/updateStatus")
    public void updateStatus(String id, String status){

        bannerService.updateBannerInfo(new Banner(id, null, null, null, status));
    }



    /**
     *      判断是否为修改操作
     *
     * @param banner       数据
     * @return
     */
    private boolean isUpdate(Banner banner) {

        if (banner == null) {
            banner.setImg(null);
            return false;
        }

        if (banner.getImg() == null){
            banner.setImg(null);
            return false;
        }

        if("".equals(banner.getImg())) {
            banner.setImg(null);
            return false;
        }

        return true;
    }


    /**
     *      将轮播图中的数据以表格文件(excel)导出
     *
     * @param response
     * @return
     */
    @RequestMapping("/deriveFile")
    public void deriveFile(HttpServletRequest request, HttpServletResponse response) {

        // 获取全部数据
        Map<String, Object> map = bannerService.queryBannerByPaging(1, 100);
        List<Banner> banners = (List<Banner>) map.get("rows");
        for (Banner banner : banners) {

            banner.setImg("http://localhost/cmfz" + banner.getImg());
        }
        //设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=banner.xls");
        //创建表格文件对象
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图信息表", "程序打印(chuan)", "轮播图信息"), Banner.class, banners);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @PostMapping("/toLeadFile")
    public void toLeadFile(MultipartFile bannerFile, HttpSession session){

        //获取文件夹路径
        String realPath = session.getServletContext().getRealPath("/image/import-file/");

        //创建导入对象
        ImportParams params = new ImportParams();
        //确定标题行
        params.setTitleRows(2);
        //确定子标题行
        params.setHeadRows(1);
        params.setNeedSave(true);
        params.setSaveUrl(realPath);
        try {
            List<Banner> banners= ExcelImportUtil.importExcel(bannerFile.getInputStream(), Banner.class, params);
            for (Banner banner : banners) {
                //设置路径
                String path = banner.getImg().split("webapp")[1];
                banner.setImg(path);
            }
            Result result = bannerService.insertAllByList(banners);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
