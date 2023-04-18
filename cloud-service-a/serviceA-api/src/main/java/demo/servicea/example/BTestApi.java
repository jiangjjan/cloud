package demo.servicea.example;

import com.model.User;
import demo.servicea.AServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = AServiceApi.application, contextId = "demo.example.BTestApi")
public interface BTestApi {

    @GetMapping("user")
    com.model.User get(@SpringQueryMap User user);
}
