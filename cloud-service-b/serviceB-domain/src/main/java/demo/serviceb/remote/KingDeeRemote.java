package demo.serviceb.remote;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import demo.serviceB.KingDeeReq;
import feign.FeignException;
import feign.RequestInterceptor;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpMessageConverterExtractor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.*;


@FeignClient(name = "kingDeeRemote", url = "https://k3cloud.adicon.com.cn:6699/K3Cloud", configuration = KingDeeRemote.KingDeeRemoteConfig.class)
public interface KingDeeRemote {

	@PostMapping(value = "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.ExecuteBillQuery.common.kdsvc")
	List<List<String>> queryList(@RequestBody KingDeeReq body);

	@PostMapping(value = "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.ExecuteBillQuery.common.kdsvc")
	String[][] queryStrArray(@RequestBody KingDeeReq body);

	@Slf4j
	class KingDeeRemoteConfig {

		@Bean
		RequestInterceptor kingHeaderHandler() {
			return template -> {
				template.header("x-api-signheaders", "X-Api-TimeStamp,X-Api-Nonce");
				template.header("X-Kd-Signature", "MWE3ZGQ2MjBhYTI5MTM0YTA2Y2QyNGUzOWIxOWJjNTM4NDNjNmJjNTA5MzQ4MjI4N2YzOGIwNDE3MTg4OGQ0Zg\u003d\u003d");
				template.header("X-Kd-Appkey", "223304_1+cCQYFoyloX7fVFXYQAUZxNVsX9SBOp");
				template.header("X-Kd-Appdata", "NjExZjZkYzg4YWIzOGEsTGVnYW8sMjA1MixudWxs");
				template.header("X-Api-Auth-Version", "2.0");
				template.header("X-Api-ClientID", "223304");
				template.header("X-Api-Signature", "OWE0MDFiNGU2NGZhNGQzMTNlYmJlNjc3NjVkYzJjYTY5NTdjYzc3NDQ0YTc4YmExNDgzMDYxNzQ2M2Y3MzU5MQ\u003d\u003d");
				Date now = new Date();
				template.header("x-api-timestamp", String.valueOf(now.getTime()));
				template.header("x-api-nonce", String.valueOf(now.getTime()));
			};
		}

		@Bean
		Decoder decoder() {
			return new CustomDecoder();

		}
	}

	 class CustomDecoder implements Decoder {

		private static final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

		static {
			ObjectMapper json = new ObjectMapper();
			json.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			json.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(json);
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
			messageConverters.add(converter);
		}


		@Override
		public Object decode(final Response response, Type type)
				throws IOException, FeignException {
			if (type instanceof Class || type instanceof ParameterizedType
					|| type instanceof WildcardType) {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				HttpMessageConverterExtractor<?> extractor = new HttpMessageConverterExtractor(
						type, messageConverters);

				return extractor.extractData(new FeignResponseAdapter(response));
			}
			throw new DecodeException(response.status(),
					"type is not an instance of Class or ParameterizedType: " + type,
					response.request());
		}

		private static final class FeignResponseAdapter implements ClientHttpResponse {

			private final Response response;

			private FeignResponseAdapter(Response response) {
				this.response = response;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.valueOf(this.response.status());
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return this.response.status();
			}

			@Override
			public String getStatusText() throws IOException {
				return this.response.reason();
			}

			@Override
			public void close() {
				try {
					this.response.body().close();
				}
				catch (IOException ex) {
					// Ignore exception on close...
				}
			}

			@Override
			public InputStream getBody() throws IOException {
				return this.response.body().asInputStream();
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders httpHeaders = new HttpHeaders();
				for (Map.Entry<String, Collection<String>> entry : response.headers().entrySet()) {
					httpHeaders.put(entry.getKey(), new ArrayList<>(entry.getValue()));
				}
				return httpHeaders;
			}

		}

	}


}

