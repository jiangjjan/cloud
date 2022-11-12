package demo.serviceb.apiImpl;

import demo.serviceb.example.BServiceTestApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class A implements BServiceTestApi {

	@Override
	public String get() {
		return null;
	}
}
