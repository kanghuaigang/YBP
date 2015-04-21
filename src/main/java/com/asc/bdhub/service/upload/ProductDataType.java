package com.asc.bdhub.service.upload;


public class ProductDataType extends BaseDataType{

	public ProductDataType() {
		this.name = "产品信息";
		
		this.dataProps.add(new DataProperty("code", "产品编码"));
		this.dataProps.add(new DataProperty("name", "产品名称"));
		this.dataProps.add(new DataProperty("spec", "产品规格"));
		this.dataProps.add(new DataProperty("unit", "产品单位"));
		
		
	}
}
