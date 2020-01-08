package com.utils.tools.result;

public class Result {

	public static final ResultEnum SUCCESS = ResultEnum.SUCCESS;
	public static final ResultEnum FAIL = ResultEnum.FAIL;

    //请求状态
	private Integer code;
    //返回消息
	private String message;
    //返回结果
	private Object data;

	private Result() {
	}

	private Result(ResultEnum resultEnum, String message, Object data) {
		this.code = resultEnum.getCode();
		this.message = message;
		this.data = data;
	}

	private Result(ResultEnum resultEnum, String message) {
		this.code = resultEnum.getCode();
		this.message = message;
	}

	private Result(String message, Object data) {
		this.code = ResultEnum.SUCCESS.getCode();
		this.message = message;
		this.data = data;
	}

	private Result(Object data) {
		this.code = ResultEnum.SUCCESS.getCode();
		this.message = "success";
		this.data = data;
	}

	public static Result resultJson(ResultEnum resultEnum, String message, Object data) {
		return new Result(resultEnum, message, data);
	}

	/**
	 * 无返回结果
	 */
	public static Result resultJson(ResultEnum resultEnum, String message) {
		return new Result(resultEnum, message);
	}

	/**
	 * 请求成功返回自定义消息
	 */
	public static Result resultJson(String message, Object data) {
		return new Result(message, data);
	}

	/**
	 * 请求成功返回默认消息
	 */
	public static Result resultJson(Object data) {
		return new Result(data);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result{" +
				"code=" + code +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}
