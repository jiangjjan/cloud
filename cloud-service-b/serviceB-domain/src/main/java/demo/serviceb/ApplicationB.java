package demo.serviceb;

import demo.servicea.AServiceApi;
import demo.serviceb.remote.RemoteClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {AServiceApi.class,RemoteClient.class})
public class ApplicationB {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ApplicationB.class, args);
		String property = run.getEnvironment().getProperty("spring.servlet.multipart.max-file-size");
		System.out.println("============================");
		System.out.println(property);
	}

}