package com.utils.tools.result;


public enum ResultEnum {

	//成功
	SUCCESS(200),
	//失败
	FAIL(-200);

	private Integer code;

	ResultEnum(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
