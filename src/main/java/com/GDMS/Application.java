package com.GDMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {


    @Bean
    public ExecutorService executorService() {
        // 获取Java虚拟机的可用的处理器数，最佳线程个数，处理器数*2。根据实际情况调整
        int curSystemThreads = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("------------系统可用线程池个数：" + curSystemThreads);
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(curSystemThreads);
        return pool;
    }


    //在pom配置打包方式为war，然后再启动类这里写下面这方法，就能做到tomcat启动项目了，方便热加载
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        System.out.println("启动成功");

    }
}
