package com.zhuchuansheng.cmfz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuchuansheng
 * @date: 2020/1/3
 * @time: 17:12
 */
@Target(ElementType.METHOD)          //注解使用的位置
@Retention(RetentionPolicy.RUNTIME)  //注解运行的时机
public @interface AddCache {


}
