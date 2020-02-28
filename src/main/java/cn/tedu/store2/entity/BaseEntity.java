package cn.tedu.store2.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
abstract class BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;
	

}
