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

import java.util.HashMap;
import java.util.Map;

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

		Map param = new HashMap();
		param.put("name", "value");
		System.out.println("=================================");
		System.out.println(bean.testForParam(param));

		DataBindEntity p = new DataBindEntity();
		p.setOrgCode("12345");
		System.out.println("++++++++++++++");
		System.out.println(bean.testForParam(p));
		run.close();

	}

}