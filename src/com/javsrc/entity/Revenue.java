package com.javsrc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员实体类---->对象数据
 * @author Administrator
 */

public class Revenue implements Serializable{

	
	public Revenue(Integer cateId, String cateName, Double cateWhole) {
		super();
		this.cateId = cateId;
		this.cateName = cateName;
		this.cateWhole = cateWhole;
	}
	public Revenue() {
		// TODO 自动生成的构造函数存根
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2418690843362956080L;
			
	private Integer cateId;
	private String cateName;
	private Double cateWhole;

	public Integer getCateId() {
		return cateId;
	}
	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public Double getCateWhole() {
		return cateWhole;
	}
	public void setCateWhole(Double cateWhole) {
		this.cateWhole = cateWhole;
	}	
	public boolean isEmpty() {
		if(this.cateId==null)  return true;
		if(this.cateName==null) return true;
		if(this.cateWhole==null) return true;
		return false;
	}
	@Override
	public String toString() {
		return "Revenue [cateId=" + cateId + ", cateName=" + cateName + ", cateWhole=" + cateWhole + "]";
	}
}

