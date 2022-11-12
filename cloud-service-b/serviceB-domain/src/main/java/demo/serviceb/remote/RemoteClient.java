package demo.serviceb.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="serviceB",url = "${remote.test.url}")
public interface RemoteClient {

	@GetMapping(value = "form/enum",headers = "token=${server.port}")
	String get(@RequestParam Integer status,@RequestHeader String appId);
}

