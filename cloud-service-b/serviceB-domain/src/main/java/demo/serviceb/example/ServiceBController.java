package demo.serviceb.example;

import com.google.common.collect.Lists;
import demo.serviceB.KingDeeReq;
import demo.servicea.example.ATestApi;
import demo.serviceb.remote.KingDeeRemote;
import demo.serviceb.remote.RemoteClient;
import demo.serviceb.remote.UploadFileClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ServiceBController {

	final ATestApi aTestApi;
	final RemoteClient remoteClient;
	final UploadFileClient uploadFileClient;
	final KingDeeRemote kingDeeRemote;

	@GetMapping("queryIp")
	public String queryIp() {
		return aTestApi.getSS();
	}

	//测试fegin 调用指定服务的接口
	@GetMapping("remoteClient")
	public Object remoteClient() throws IOException {
//		uploadFileClient.uploadFile(new File("D:/qq.txt"),"token");
		KingDeeReq req = new KingDeeReq();
		req.setTableName("SP_PickMtrl");
		req.addSelectField(Lists.newArrayList("FMaterialID.F_Manufacturer", "FLot", "FMaterialName", "FExpiryDate", "FAPPROVEDATE", "FSpecification", "FDate", "FActualQty"));
		req.setFilterStr("FMaterialID.FNumber = '" + 11.004432 + "' and FStockOrgId.FNumber = " + 1023 + " and FDate = '" + "2022-11-07" + "'");
		req.setOrderStr("FAPPROVEDATE desc");

		System.out.println(kingDeeRemote.queryList(req));
		System.out.println("===================");
		System.out.println(Arrays.deepToString(kingDeeRemote.queryStrArray(req)));
		return remoteClient.get(2, "appId");
	}

	public static void main(String[] args) {
		System.out.println("sss  sss ss   ss ".replaceAll("\\s+",""));
	}

}
