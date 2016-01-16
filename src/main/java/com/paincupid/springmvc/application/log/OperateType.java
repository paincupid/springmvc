package com.paincupid.springmvc.application.log;

public enum OperateType {
	SAVE("save"),
	UPDATE("update"),
	DELETE("delete");
	
	private OperateType(String desc){
		this.desc = desc;
	}
	
	private String desc;

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
