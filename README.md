"# cloud" 
服务中心使用nacos, spring cloud 版本:<spring-cloud.version>Hoxton.SR8</spring-cloud.version>
       对应alibabacloud 版本为  <spring-cloud-alibaba.version>2.2.5.RELEASE</spring-cloud-alibaba.version>
       服务注册中心为版本为 2.0.1
OpenFegin:
```java
import demo.serviceb.BServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("demoB")
@FeignClient(value= BServiceApi.application,contextId = "demo.example.BServiceTestApi")
public interface BServiceTestApi {
	@GetMapping("test")
	String get();
}
```
value 与name是同一个值 用于指定的微服务名字作为虚拟主机地址,
contextId 用于beanName 否则多个同一个微服务的api接口,会出现beanName重复的错误;


