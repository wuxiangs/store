package com.aisiono.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuxiang
 * @MapperScan("com.aisiono.store.mapper") 指定当前项目中Mapper接口路径的位置,项目启动的时候会加载所有的接口
 */
@SpringBootApplication
@MapperScan("com.aisiono.store.mapper")
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
