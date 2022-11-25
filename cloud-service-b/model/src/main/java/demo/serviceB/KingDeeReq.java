package demo.serviceB;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Delegate;

import java.util.List;
import java.util.StringJoiner;

@Data
public class KingDeeReq {


	@Delegate
	private KingDeeParam data = new KingDeeParam();


	/**
	 * FMaterialID.FNumber k3值
	 * FMaterialID.F_Manufacturer 供应商
	 * FMaterialName 试剂名称
	 * FExpiryDate  失效日期
	 * FAPPROVEDATE 实验领用日期
	 * <p>
	 * 不同的表设置的字段不一样
	 * <p>
	 * 设置返回结果需要查询的字段
	 */
	public void addSelectField(String key) {
		data.field.add(key);
	}

	/**
	 * FMaterialID.FNumber k3值
	 * FMaterialID.F_Manufacturer 供应商
	 * FMaterialName 试剂名称
	 * FExpiryDate  失效日期
	 * FAPPROVEDATE 实验领用日期
	 * <p>
	 * 不同的表设置的字段不一样
	 * <p>
	 * 设置返回结果需要查询的字段
	 */
	public void addSelectField(List<String> keys) {
		keys.forEach(data.field::add);
	}


	@Data
	public static class KingDeeParam {


		/**
		 * select field
		 */
		@JsonIgnore
		StringJoiner field = new StringJoiner(",");


		/**
		 * 查询KingDee的表格名称
		 * SP_PickMtrl 物料表
		 * <p>
		 * BD_BatchMainFile 批次编号表
		 */
		@JsonProperty("FormId")
		private String tableName;

		/**
		 * 表字段 排序方式
		 * example :FAPPROVEDATE desc
		 */
		@JsonProperty("OrderString")
		private String orderStr="";

		/**
		 * 0: 查询全部
		 */
		@JsonProperty("TopRowCount")
		private Integer topRowCount = 0;

		/**
		 * 0: 开始从第一行查询
		 */
		@JsonProperty("StartRow")
		private Integer startRow = 0;

		/**
		 * 0: 查询全部
		 */
		@JsonProperty("Limit")
		private Integer limit = 0;

		@JsonProperty("SubSystemId")
		private String subSystemId = "";


		/**
		 * 查询条件
		 */
		@JsonProperty("FilterString")
		private String filterStr;


		@JsonProperty("FieldKeys")
		public String getFieldKeys() {
			return field.toString();
		}

	}

}
