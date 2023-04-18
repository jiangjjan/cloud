package demo.servicea;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = AServiceApi.application, contextId = "demo.servicea.AServiceApi")
public interface AServiceApi {
    String application="serviceA";
}
