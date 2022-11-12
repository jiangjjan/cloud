package demo.serviceb.example;

import demo.serviceb.BServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("demoB")
@FeignClient(value= BServiceApi.application,contextId = "demo.example.BServiceTestApi")
public interface BServiceTestApi {

	@GetMapping("test")
	String get();
}
