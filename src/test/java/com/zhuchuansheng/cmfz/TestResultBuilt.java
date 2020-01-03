package com.zhuchuansheng.cmfz;

import com.sun.org.apache.regexp.internal.RE;
import com.zhuchuansheng.cmfz.util.ResultBuild;
import org.junit.Test;

/**
 * @author zhuchuansheng
 * @date: 2019/12/31
 * @time: 15:12
 */
public class TestResultBuilt {


    @Test
    public void test(){

        ResultBuild resultBuild = new ResultBuild();

        resultBuild.startObject();


        System.out.println(resultBuild.getContent());
    }

}
