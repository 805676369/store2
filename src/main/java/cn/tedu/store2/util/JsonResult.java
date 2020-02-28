package cn.tedu.store2.util;

import java.io.Serializable;

/**
 * @author Administrator
 *
 * @param <T>
 */
public class JsonResult<T> implements Serializable{

	public JsonResult(Throwable e) {
		super();
		this.message = e.getMessage();
	}
	public JsonResult(Integer state) {
		super();
		this.state = state;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private Integer state;
	private T data;
	public JsonResult() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	public JsonResult(T data) {
		super();
		this.data = data;
	}
	public JsonResult(T data,Integer state) {
		this.data = data;
		this.state=state;
	}
	

}
