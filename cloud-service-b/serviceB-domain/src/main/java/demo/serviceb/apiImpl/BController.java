package demo.serviceb.apiImpl;

import demo.serviceb.example.BServiceTestApi;
import demo.serviceb.example.LocalDateReq;
import demo.serviceb.example.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BController implements BServiceTestApi {

	@Override
	public RequestParam get(RequestParam param, String aa) {
		log.info("param: {} {}", param, aa);
		return param;
	}

	@Override
	public LocalDateReq local(LocalDateReq param, String aa) {
		param.setNumber(aa);
		return param;
	}

	@Override
	public String strTest() {
		return "/";
	}
}
