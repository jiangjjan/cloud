package demo.serviceb.remote;

import demo.serviceB.DateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

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
}

