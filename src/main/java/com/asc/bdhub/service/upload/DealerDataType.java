package com.asc.bdhub.service.upload;


public class DealerDataType extends BaseDataType{

	public DealerDataType() {
		this.name = "经销商信息";
		
		this.dataProps.add(new DataProperty("dealerName", "经销商名称"));
		this.dataProps.add(new DataProperty("dealerCode", "经销商CODE"));
		this.dataProps.add(new DataProperty("orgId", "所属药厂ID"));
		this.dataProps.add(new DataProperty("orgCode", "所属药厂CODE"));
		this.dataProps.add(new DataProperty("orgName", "所属药厂名称"));
		this.dataProps.add(new DataProperty("province", "省份"));
		this.dataProps.add(new DataProperty("city", "城市"));
	}
}
