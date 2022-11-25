package demo.serviceb.remote;

import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * 文件上传需要单独设置连接时间,默认的连接时间特别短
 */
@FeignClient(name = "uploadFile", url = "${remote.test.url}", configuration = UploadFileClient.UploadConfig.class)
public interface UploadFileClient {

	@PostMapping(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String uploadFile(@RequestPart("file") File file,@RequestParam String token);
	@PostMapping(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String uploadFileMultiPart(@RequestPart MultipartFile file);


	class UploadConfig {
		@Bean
		public Encoder fileFeignUploadBean() {
			return new SpringFormEncoder();
		}
	}
}
