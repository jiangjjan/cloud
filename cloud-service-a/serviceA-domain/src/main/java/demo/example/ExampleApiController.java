package demo.example;

import demo.servicea.example.ATestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExampleApiController implements ATestApi {

	@Value("${server.port}")
	String port;

	@Override
	public String getSS() {
		return "ss success " + port;
	}
}
