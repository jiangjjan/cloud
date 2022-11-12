package demo.serviceb;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value=BServiceApi.application,contextId = "demo.BServiceApi")
public interface BServiceApi {
	String application="serviceB";
}

