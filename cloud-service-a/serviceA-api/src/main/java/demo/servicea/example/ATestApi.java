package demo.servicea.example;

import demo.servicea.AServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * value 对应微服务的应用名称, contextId 对应bean Name 否则会使用value 作为beanName 导致名字重复
 */
@FeignClient(value= AServiceApi.application,contextId = "demo.example.ATestApi")
@RequestMapping("example")
public interface ATestApi {

	@GetMapping("ss")
	String  getSS();
}
