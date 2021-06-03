package com.javsrc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * session信息实体类---->对象数据
 * @author Administrator
 */

public class SessionInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6387968151140312967L;
	
	private Integer id;
	private Integer user_cate;
	private String email;
	private String mobile;
	private String ip;
	private Date time;
	private String operation;
	private Integer user_id;
	private String goods;
	
	/*
	 * public SessionInfo(Integer id, Integer user_cate, String email, String
	 * mobile, String ip, Date intime, Date outtime, Integer user_id) { this.id =
	 * id; this.user_cate = user_cate; this.email = email; this.mobile = mobile;
	 * this.ip = ip; this.intime = intime; this.outtime = outtime; this.user_id =
	 * user_id; }
	 */
	
	public Integer getId() {
		return id;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getUser_cate() {
		return user_cate;
	}
	public void setUser_cate(Integer user_cate) {
		this.user_cate = user_cate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
}

