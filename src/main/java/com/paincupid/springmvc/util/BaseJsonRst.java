package com.paincupid.springmvc.util;

import java.io.Serializable;
import java.util.Map;

public class BaseJsonRst<T> extends Page implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean success;	//成功或失败的标志
	private T result;	//结果集
	private String message;	//成功或失败的信息

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static <T> BaseJsonRst<T> create() {
		return new BaseJsonRst<T>();
	}
	
	public BaseJsonRst<T> success(T t){
		this.setResult(t);
		this.setSuccess(true);
		return this;
	}
	public BaseJsonRst<T> fail(String msg){
		this.setMessage(msg);
		this.setSuccess(false);
		this.setTotalCounts(1);
		return this;
	}
	public static <T> BaseJsonRst<T> newSucc(T t){
		BaseJsonRst<T> rst = new BaseJsonRst<T>();
		rst.setResult(t);
		rst.setSuccess(true);
		return rst;
	}
	public static <T> BaseJsonRst<T> newFail(String msg){
		BaseJsonRst<T> rst = new BaseJsonRst<T>();
		return rst.fail(msg);
	}
	public BaseJsonRst<T> debug(String di){
		return this;
	}
}
