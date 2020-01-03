package com.zhuchuansheng.cmfz;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.zhuchuansheng.cmfz.commons.ImageUploadUtil;
import com.zhuchuansheng.cmfz.dao.EmployeeMapper;
import com.zhuchuansheng.cmfz.entity.Banner;
import com.zhuchuansheng.cmfz.entity.Employee;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author zhuchuansheng
 * @date: 2019/12/27
 * @time: 17:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class TestEasyPoi {


    @Autowired
    private EmployeeMapper employeeMapper;


    @Test
    public void testWorkbook() throws Exception {

        List<Employee> employees = employeeMapper.selectAll();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(
                        "员工信息列表", "程序打印", "员工信息表"),
                Employee.class, employees);

        workbook.write(new FileOutputStream("F:\\test\\Easypoi-employee.xls"));
        workbook.close();
    }


    @Test
    public void testImportParams() throws IOException {

        ImportParams params = new ImportParams();
        params.setTitleRows(2);
        params.setHeadRows(1);
        params.setNeedSave(true);
        params.setSaveUrl("/image/import-file");
        List<Banner> list = ExcelImportUtil.importExcel(new File("C:\\Users\\Administrator\\Desktop\\banner.xls"), Banner.class, params);

    }




    @Test
    public void test(){

        //默认保存在根目录 E:盘
        ImportParams params = new ImportParams();
        params.setTitleRows(2);
        params.setHeadRows(1);
        params.setNeedSave(true);
        params.setSaveUrl("E:\\baizhi\\code\\培训\\05--后期项目\\code\\cmfz-chuan\\src\\main\\webapp\\image\\import-file\\");
        List<Banner> result = ExcelImportUtil.importExcel(
                new File("C:\\Users\\Administrator\\Desktop\\banner.xls"),
                Banner.class, params);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(ReflectionToStringBuilder.toString(result.get(i)));
        }
    }



}
