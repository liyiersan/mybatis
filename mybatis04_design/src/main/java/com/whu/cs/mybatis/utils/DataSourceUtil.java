package com.whu.cs.mybatis.utils;

import com.whu.cs.mybatis.config.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * @author: zhouqian
 * @date: 2020/2/4
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class DataSourceUtil {

    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
