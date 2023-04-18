package demo.serviceb.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pathologyRemoteApi", url = "${lis.pathology.url:http://10.0.11.85:6320}")
public interface PathologyRemoteApi {

    @GetMapping("AdiconLeGoExchangeService/GetBLType")
    String pathologyType(@RequestParam String OrganizationCode);
}
