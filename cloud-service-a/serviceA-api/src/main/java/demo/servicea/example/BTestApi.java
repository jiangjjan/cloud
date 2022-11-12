package demo.servicea.example;

import demo.servicea.AServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value= AServiceApi.application,contextId = "demo.example.BTestApi")
public interface BTestApi {

	@GetMapping
	String get();
}
