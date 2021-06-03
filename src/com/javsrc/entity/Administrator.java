package com.javsrc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员实体类---->对象数据
 * @author Administrator
 */

public class Administrator implements Serializable{
	private static final long serialVersionUID = 2674304631684590335L;
	
	private Integer id;
	private String mobile;
	private String pwd;
	private String real_name;
	private String email;
	private Date register_time;
	
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", mobile=" + mobile + ", pwd=" + pwd + ", real_name=" + real_name + ", email=" + email + ", register_time=" + register_time + "]";
	}
}

