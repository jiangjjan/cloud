package demo.serviceb.example;

import demo.serviceb.BServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("demoB")
@FeignClient(value = BServiceApi.application, contextId = "demo.example.BServiceTestApi")
public interface BServiceTestApi {

    @GetMapping("test")
    RequestParam get(@SpringQueryMap RequestParam param, @org.springframework.web.bind.annotation.RequestParam String aa);

    @GetMapping("local")
    LocalDateReq local(@SpringQueryMap LocalDateReq param, @org.springframework.web.bind.annotation.RequestParam String aa);

    @GetMapping("strTest")
    String strTest();


}
