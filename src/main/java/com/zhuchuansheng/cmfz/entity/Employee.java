package com.zhuchuansheng.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    @Excel(name = "编号")
    private String employeeId;

    @Excel(name = "用户名")
    private String firstName;

    @ExcelIgnore
    private String lastName;

    @Excel(name = "邮箱号")
    private String email;

    @Excel(name = "电话号码")
    private String phoneNumber;

    @Excel(name = "工作ID")
    private String jobId;

    @Excel(name = "工资")
    private Integer salary;

    @ExcelIgnore
    private Integer commissonPct;

    @ExcelIgnore
    private String managerId;

    @ExcelIgnore
    private String departmentId;

    @Excel(name = "任职时间", format = "yyyy年MM月dd日")
    private Date hireDate;

    @Excel(name = "状态")
    private String status;

}