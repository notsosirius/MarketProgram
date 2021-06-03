package com.javsrc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.javsrc.common.DbHelper;
import com.javsrc.entity.Address;
import com.javsrc.entity.Category;


public class CategoryService {
	private QueryRunner qr = new QueryRunner();
	private ScalarHandler<Double> doubleScalarHandler = new ScalarHandler<Double>();
	private ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();
	private ScalarHandler<Integer> intScalarHandler = new ScalarHandler<Integer>();
	private BeanHandler<Category> beanHandler = new BeanHandler<Category>(Category.class);
	private BeanListHandler<Category> beanListHandler = new BeanListHandler<Category>(Category.class);
	//private BeanListHandler<String> stringBeanListHandler = new BeanListHandler<String>(String.class);
	//private BeanHandler<Integer> intBeanHandler = new BeanHandler<Integer>(Integer.class);

	public Category save(Category cate) throws RuntimeException{
		String sql = "INSERT INTO category(name, alias, order_weight, p_id) VALUES(?,?,?,?)";
		
		Object[] params = {cate.getName(), cate.getAlias(), 
				cate.getOrder_weight(), cate.getP_id()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); //获取数据库连接
			conn.setAutoCommit(false); //开启事务
			System.out.println(sql);
			//执行数据库操作的插入操作，返回生成的主键值
			Number temp=(Number)qr.insert(conn, sql, scalarHandler, params);
			Long id = temp.longValue();
			cate.setId(id.intValue());
			
			DbUtils.commitAndCloseQuietly(conn); //提交事务并关闭连接
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
		
		return cate;
	}
	
	public void update(Category cate) throws RuntimeException{
		String sql = "UPDATE category SET name=?, alias=?, order_weight=?, p_id=? WHERE id=?";
		
		Object[] params = {cate.getName(), cate.getAlias(), 
				cate.getOrder_weight(), cate.getP_id(), cate.getId()};
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); //获取数据库连接
			conn.setAutoCommit(false); //开启事务
			
			System.out.println(sql);
			//执行数据库的更新操作
			qr.update(conn, sql, params);
			
			DbUtils.commitAndCloseQuietly(conn); //提交事务并关闭连接
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
	}
	
	public Category findOne(Integer id)throws RuntimeException{
		Category cate = null;
		String sql = "SELECT * FROM category WHERE id=?";
	
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			System.out.println(sql);
			//执行数据库的查询操作
			cate = qr.query(conn, sql, beanHandler, id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
	
		return cate;
	}
	
	public List<Category> findAll()throws RuntimeException{
		List<Category> list = new ArrayList<Category>();
		String sql = "SELECT * FROM category ORDER BY order_weight DESC,id ASC";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			System.out.println(sql);
			//执行数据库的查询操作
			list = qr.query(conn, sql, beanListHandler);
			
			list = convert(list);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return list;
	}
	
	public List<Category> findAllSub()throws RuntimeException{
		List<Category> list = new ArrayList<Category>();
		String sql = "SELECT * FROM category WHERE id>7 ORDER BY order_weight DESC,id ASC";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			System.out.println(sql);
			//执行数据库的查询操作
			list = qr.query(conn, sql, beanListHandler);
			
			//list = convert(list);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		/*
		 * List<String>cateList=new ArrayList<String>(); for(Category cate:list) {
		 * cateList.add(cate.getName()); }
		 */
		return list;
	}
	

	public void delete(Integer id) throws RuntimeException{
		String sql = "SELECT count(id) FROM category where p_id=?";
		String sql2 = "DELETE FROM  category WHERE id=?";
		
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			conn.setAutoCommit(false); 
			
			System.out.println(sql);
			
			Long count = qr.query(sql, scalarHandler, id);
			if(count !=null && count.longValue() > 0){
				 throw new RuntimeException("删除失败，有子类目");
			} else {
				 qr.update(conn, sql2, id);
			}
			
			DbUtils.commitAndCloseQuietly(conn); 
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn);
			 throw new RuntimeException(e);
		}
	}
	
	//组装父子关系
	private List<Category> convert(List<Category> categories){
		List<Category> parents = new ArrayList<Category>();
		List<Category> childs = new LinkedList<Category>();
		for (Category category : categories) {
			if(category.getP_id() == null){
				parents.add(category);
			}else{
				childs.add(category);
			}
		}
		for (Category parent : parents) {
			for (Category child : childs) {
				if(parent.getId().equals(child.getP_id())){
					parent.getChilds().add(child);
				}
			}
		}
		
		return parents;
	}
	
	
	
	public Double getRevenueByName(String name) throws RuntimeException{
		String sql = "SELECT SUM(payment_price) FROM `item` , `category` , `product`  WHERE `item`.`product_id`=`product`.`id` AND `product`.`cate_id`=`category`.`id` AND `category`.`name` = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Double whole=0.0;
		//Object[] params = {name};
		//String whole;
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			//Double whole=qr.query(conn, sql, doubleScalarHandler, name);
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				whole=rs.getDouble(1);
			}
			pstmt.close();
			conn.close();
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
		return whole;
	}

	@Override
	public String toString() {
		return "CategoryService [qr=" + qr + ", scalarHandler=" + scalarHandler + ", intScalarHandler="
				+ intScalarHandler + ", beanHandler=" + beanHandler + ", beanListHandler=" + beanListHandler + "]";
	}

	public Integer getIdByName(String name) {
		String sql = "SELECT id FROM `category`  WHERE `category`.`name`= ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer id=0;
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			//Double whole=qr.query(conn, sql, doubleScalarHandler, name);
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
			}
			pstmt.close();
			conn.close();
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
		return id;
	}

	public Double getRevenueByCondition(String cate,Timestamp startTime,Timestamp endTime) {
		String sql = "SELECT SUM(`item`.`payment_price`) FROM `item` , `category` , `product` , `orders`  WHERE `item`.`order_id` = `orders`.`id` AND `item`.`product_id`=`product`.`id` AND `product`.`cate_id`=`category`.`id` AND `category`.`name` = ? AND `orders`.`create_time` > ? AND `orders`.`create_time` < ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Double whole=0.0;
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			//Double whole=qr.query(conn, sql, doubleScalarHandler, name);
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, cate);
			pstmt.setObject(2, startTime);
			pstmt.setObject(3, endTime);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				whole=rs.getDouble(1);
			}
			pstmt.close();
			conn.close();
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
		return whole;
	}

	public Double getRevenueById(int cate) {
		String sql = "SELECT SUM(payment_price) FROM `item` , `category` , `product`  WHERE `item`.`product_id`=`product`.`id` AND `product`.`cate_id`=`category`.`id` AND `category`.`id` = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Double whole=0.0;
		//Object[] params = {name};
		//String whole;
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			//Double whole=qr.query(conn, sql, doubleScalarHandler, name);
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, cate);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				whole=rs.getDouble(1);
			}
			pstmt.close();
			conn.close();
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
		return whole;
	}

	public String getNameById(int cateId) {
		String sql = "SELECT name FROM `category`  WHERE `category`.`id`= ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			//Double whole=qr.query(conn, sql, doubleScalarHandler, name);
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, cateId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				name=rs.getString(1);
			}
			pstmt.close();
			conn.close();
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); //回滚事务并关闭连接
			 
			 throw new RuntimeException(e);
		}
		return name;
	}
	
}