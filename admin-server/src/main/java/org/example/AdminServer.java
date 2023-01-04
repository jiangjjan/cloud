package org.example;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer //启动adminServer, 后续访问可以查看机器信息
@SpringBootApplication
public class AdminServer {
    public static void main(String[] args) {
        SpringApplication.run(AdminServer.class,args);
    }
}