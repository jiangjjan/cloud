package demo.example;

import demo.model.Date2Long;
import demo.serviceb.example.BServiceTestApi;
import demo.serviceb.example.LocalDateReq;
import demo.serviceb.example.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("example")
public class ExampleController {

	@Autowired
	BServiceTestApi bServiceTestApi;

	@GetMapping("test/date")
	public Object parseDate(Date date) {

		RequestParam p = new RequestParam();
		p.setStartDate(new Date2Long());
		p.setNumber("/");
		return bServiceTestApi.get(p, "/");

	}

	@GetMapping("test/strTest")
	public Object strTest(Date date) {

		return bServiceTestApi.strTest();

	}

	@GetMapping("test/localDate")
	public Object parseDate(LocalDateReq date) {

		System.out.println(date);
		return bServiceTestApi.local(date, "asdadad");

	}
}
