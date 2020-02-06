package com.whu.cs.mybatis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: zhouqian
 * @date: 2020/2/6
 * @blog:
 * @version:1.0.0
 * @description: 查询的注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {

    //配置SQL语句
    String value();
}
