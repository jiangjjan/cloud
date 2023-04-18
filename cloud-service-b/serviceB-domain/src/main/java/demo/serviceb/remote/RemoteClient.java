package demo.serviceb.remote;

import demo.serviceB.DateResponse;
import demo.serviceb.remote.param.DataBindEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 使用openFegin调用非注册中心的接口
 * url 有值的时候 name的属性不再起作用
 */
@FeignClient(name = "remoteClient", url = "${remote.test.url}")
public interface RemoteClient {

	/**
	 * Get 请求存在请求体的时候,一定要加@RequestParam 源码内在存在请求体的时候,会转换为Post
	 */
	@GetMapping(value = "form/enum", headers = "token=${server.port}")
	DateResponse get(@RequestParam Integer status, @RequestHeader String appId);

	@GetMapping("compression")
	String compression();

	@PostMapping(value = "bind/web", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	Object testForParam(@RequestBody Map<String, ?> param);

	@PostMapping(value = "bind/json")
	Object testForParam(@RequestBody DataBindEntity param);
}

