package demo.serviceb.example;

import demo.servicea.example.ATestApi;
import demo.serviceb.remote.RemoteClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ServiceBController {

	final ATestApi aTestApi;
	final RemoteClient remoteClient;

	@GetMapping("queryIp")
	public String queryIp() {

		return aTestApi.getSS();
	}

	//测试fegin 调用指定服务的接口
	@GetMapping("remoteClient")
	public String remoteClient() {

		return remoteClient.get(2, "appId");
	}

}
