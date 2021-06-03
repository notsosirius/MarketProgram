package com.javsrc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * session信息实体类---->对象数据
 * @author Administrator
 */

public class OperationInfo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6387968151140312967L;
	@Override
	public String toString() {
		return "SessionInfo [id=" + id + ", user_cate=" + user_cate + ", email=" + email + ", mobile=" + mobile
				+ ", ip=" + ip + ", intime=" + intime + ", outtime=" + outtime + ", user_id=" + user_id + "]";
	}
	private Integer id;
	private Integer user_cate;
	private String email;
	private String mobile;
	private String ip;
	private Date intime;
	private Date outtime;
	private Integer user_id;
	
	public Integer getId() {
		return id;
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
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public Date getOuttime() {
		return outtime;
	}
	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}

