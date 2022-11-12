package demo.web.config;

import demo.util.DateHelper;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@Configuration
public class DateParseConfig implements WebMvcConfigurer {

	/** 用于转换表单参数中的日期 */
	static class DateConverter implements Converter<String, Date> {
		@Override
		public Date convert(String source) {
			return DateHelper.parse(source);
		}
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// 添加日期转换，DateConverter可以猜测传入的多种日期格式
		registry.addConverter(new DateConverter());
	}

}
