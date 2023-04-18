package demo;

import demo.serviceb.BServiceApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {BServiceApi.class})
public class ApplicationA {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationA.class,args);
    }
}
