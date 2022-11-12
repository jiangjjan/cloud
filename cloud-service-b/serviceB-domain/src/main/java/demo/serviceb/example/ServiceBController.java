package demo.serviceb.example;

import demo.servicea.example.ATestApi;
import demo.serviceb.remote.RemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController {

	@Autowired
	ATestApi aTestApi;

	@Autowired
	RemoteClient remoteClient;

	@GetMapping("queryIp")
	public String queryIp(){

		return aTestApi.getSS();
	}

	@GetMapping("remoteClient")
	public String remoteClient(){

		return remoteClient.get(2,"appId");
	}
}
