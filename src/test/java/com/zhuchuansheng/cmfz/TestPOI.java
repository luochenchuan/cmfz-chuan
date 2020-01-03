package com.zhuchuansheng.cmfz;

import com.zhuchuansheng.cmfz.dao.EmployeeMapper;
import com.zhuchuansheng.cmfz.entity.Employee;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zhuchuansheng
 * @date: 2019/12/27
 * @time: 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class TestPOI {

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     *      创建一个简单的表格文件
     *
     * @throws IOException
     */
    @Test
    public void testHSSFWorkbook1() throws IOException {

        // 创建一个表格文件
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建一个工作簿
        HSSFSheet sheet = workbook.createSheet("自动创建");

        // 创建行   0: 表示第一行
        HSSFRow row = sheet.createRow(0);

        // 创建单元格  0: 表示第一个单元格
        HSSFCell cell = row.createCell(0);
        //为单元格赋值
        cell.setCellValue("测试");

        //导出
        workbook.write(new File("F:\\test\\text.xls"));

        //关闭流
        workbook.close();
    }


    /**
     *      为单元格添加一些样式
     *
     * @throws IOException
     */
    @Test
    public void testHSSFWorkbook2() throws IOException {

        // 创建一个表格文件
        HSSFWorkbook workbook = new HSSFWorkbook();
            //创建字体样式
        HSSFFont font = workbook.createFont();
        font.setBold(true);                 //  加粗
        font.setColor(Font.COLOR_RED);      //  设置颜色
        font.setCharSet(20);
            //创建表格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
            //添加字体样式
        cellStyle.setFont(font);

        // 创建一个工作簿
        HSSFSheet sheet = workbook.createSheet();

        // 创建行
        HSSFRow row = sheet.createRow(0);

        //数据
        String[] titles = {"编号", "姓名", "性别", "年龄", "生日"};

        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            // 创建单元格
            HSSFCell cell = row.createCell(i);
            //添加数据
            cell.setCellValue(title);
            // 设置样式
            cell.setCellStyle(cellStyle);
        }

        // 导出
        workbook.write(new File("F:\\test\\title.xls"));

        // 关闭流
        workbook.close();
    }



    @Test
    public void testHSSFWorkbook3() throws IOException {

        // 创建表格文件
        HSSFWorkbook workbook = new HSSFWorkbook();
            //设置时间转换
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
            //创建样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        // 创建工作簿
        HSSFSheet sheet = workbook.createSheet();
        // 创建行
        HSSFRow row = sheet.createRow(0);
        // 标题数据
        String[] titles = {"编号", "姓名", "邮件", "电话号码", "工作ID", "工资", "任职时间"};
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            //为每一行设置宽度
            sheet.setColumnWidth(i, 20*256);
            // 创建 单元格
            HSSFCell cell = row.createCell(i);
            // 添加数据
            cell.setCellValue(title);
        }

        // 获取数据
        List<Employee> employees = employeeMapper.selectAll();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            // 创建行
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(employee.getEmployeeId());
            row1.createCell(1).setCellValue(employee.getFirstName());
            row1.createCell(2).setCellValue(employee.getEmail());
            row1.createCell(3).setCellValue(employee.getPhoneNumber());
            row1.createCell(4).setCellValue(employee.getJobId());
            row1.createCell(5).setCellValue(employee.getSalary());
            HSSFCell cell = row1.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(employee.getHireDate());
        }

        workbook.write(new File("F:\\test\\employee.xls"));
        workbook.close();
    }

}