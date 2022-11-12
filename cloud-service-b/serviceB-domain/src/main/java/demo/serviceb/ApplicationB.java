package demo.serviceb;

import demo.servicea.AServiceApi;
import demo.serviceb.remote.RemoteClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {AServiceApi.class,RemoteClient.class})
public class ApplicationB {

	public static void main(String[] args) {
		 SpringApplication.run(ApplicationB.class, args);
	}

}