package demo.serviceb;

import demo.servicea.AServiceApi;
import demo.serviceb.remote.PathologyRemoteApi;
import demo.serviceb.remote.RemoteClient;
import demo.serviceb.remote.param.DataBindEntity;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {AServiceApi.class, RemoteClient.class, PathologyRemoteApi.class})
public class ApplicationB {

	@Bean
	Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
		return new SpringFormEncoder(new SpringEncoder(converters));
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(ApplicationB.class, args);
		RemoteClient bean = run.getBean(RemoteClient.class);

		DataBindEntity p = new DataBindEntity();
		p.setOrgCode("12345");
		System.out.println("=================================");
		System.out.println(bean.webForm(p));

		p.setOrgCode("12345");
		System.out.println("++++++++++++++");
		System.out.println(bean.webJson(p));
		run.close();

	}

}