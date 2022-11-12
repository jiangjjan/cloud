package demo.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("example")
public class ExampleController {

	@GetMapping("test/date")
	public Date parseDate(Date date){

		return date;

	}
}
