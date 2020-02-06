package com.whu.cs.mybatis.io;

import java.io.InputStream;

/**
 * @author: zhouqian
 * @date: 2020/2/4
 * @blog:
 * @version:1.0.0
 * @description: 使用类加载器读取配置文件的类
 */

public class Resources {

    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
