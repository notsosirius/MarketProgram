package com.javsrc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.javsrc.common.DbHelper;
//import com.javsrc.entity.Product;
import com.javsrc.entity.Saler;
import com.javsrc.entity.common.Page;

/**
 * 销售员相关的业务逻辑类---->CRUD操作
 */
public class RevenueService {

	/**common-dbutils包提供的一个SQL执行器类*/
	private QueryRunner qr = new QueryRunner();
	
	/**单行单列的结果集处理器*/
	private ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();
	/**单行多列的结果集处理*/
	private BeanHandler<Saler> beanHandler = new BeanHandler<>(Saler.class);
	private BeanListHandler<Saler> beanListHandler = new BeanListHandler<>(Saler.class);
	
	/**
	 * 新增一个销售员 
	 * @param Saler 要新增的销售员对象
	 * @return 新增成功后的销售员对象
	 */
	public Saler save(Saler member){
		String sql = "insert into saler(mobile,pwd,real_name,email,"
				+ "register_time,category) values(?,?,?,?,?,?)";
		
		Object[] params = { member.getMobile(), member.getPwd(), 
				member.getReal_name(), member.getEmail(), 
				 member.getRegister_time(), member.getCategory()
		};
		
		Connection conn = null;
		try {
			conn = DbHelper.getConn(); //获取连接
			conn.setAutoCommit(false); //启动事务
			
			//执行数据库的插入操作，返回生成的主键值
			Number Temp = (Number)qr.insert(conn, sql, scalarHandler, params);
			Long temp = Temp.longValue();
			if(temp != null){
				member.setId(temp.intValue());
			}
			
			DbUtils.commitAndCloseQuietly(conn); //提交事务并关闭连接
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);//回滚事务并关闭连接
			
			e.printStackTrace();
		} 
		
		return member;
	}
	
	/**
	 * 根据ID删除该管理员
	 * @param id 管理员的ID
	 */
	public void delete(Integer id){
		String sql = "delete from saler where id=?";
		
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			conn.setAutoCommit(false);
			
			qr.update(conn, sql, id);
			
			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新会员的详细信息
	 * @param member 要更新的会员对象
	 */
	public void update(Saler member){
		String sql = "update saler set mobile=?,pwd=?,real_name=?,"
				+ "email=?,register_time=?,category=? where id=?";
		
		Object[] params = { member.getMobile(), member.getPwd(), 
				member.getReal_name(), member.getEmail(), 
				member.getRegister_time(), member.getId(),
				member.getCategory()
		};
		
		Connection conn = null;
		try {
			conn = DbHelper.getConn(); //获取连接
			conn.setAutoCommit(false); //启动事务
			
			qr.update(conn, sql, params);
			
			DbUtils.commitAndCloseQuietly(conn); //提交事务并关闭连接
		} catch (SQLException e) {
			
			DbUtils.rollbackAndCloseQuietly(conn);//回滚事务并关闭连接
			
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * 根据ID获取该会员对象
	 * @param id 会员对象的ID
	 * @return 返回指定编号的会员对象
	 */
	public Saler findOne(Integer id){
		Saler member = null;
		
		//id,mobile,pwd,real_name,nick_name,email,gender,register_time
		String sql = "select * from saler where id=?";
		
		Connection conn = null;
		try {
			conn = DbHelper.getConn(); //获取连接
			
			member = qr.query(conn, sql, beanHandler, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtils.closeQuietly(conn);
		}
		return member;
	}
	
	/**
	 * 根据邮箱获取该会员对象
	 * @param mobile
	 * @return
	 */
	public Saler findByEmail(String email){
		Saler member = null;
		
		String sql = "select * from saler where email=?";
		
		Connection conn = null;
		try {
			conn = DbHelper.getConn(); //获取连接
			
			member = qr.query(conn, sql, beanHandler, email);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtils.closeQuietly(conn);
		}
		return member;
	}
	
	
	/**
	 * 获取所有会员的列表
	 * @return 会员列表对象
	 */
	public List<Saler> findAll(){
		List<Saler> list = new ArrayList<Saler>();
		
		String sql = "select * from saler";
		
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			
			list = qr.query(conn, sql, beanListHandler);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtils.closeQuietly(conn);
		}
		
		return list;
	}
	
	
	/**
	 * 统计会员总数量
	 * @return
	 */
	public long count(){
		long count = 0;
		
		String sql = "select count(id) from saler";
		
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler);
			if(temp != null){
				count = temp.longValue();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtils.closeQuietly(conn);
		}
		
		return count;
	}
	
	/**
	 * 根据email模糊查询，返回分页列表
	 * @param email 邮箱
	 * @param number 当前页号
	 * @param size 每页要显示的记录数
	 * @return 分页对象
	 * @throws RuntimeException
	 */
	public Page<Saler> findByLikeName(String email, int number, int size) throws RuntimeException{
		Page<Saler> page = new Page<>(number, size);
		
		if(email != null){
			email = email.replaceAll("%", "\\%");
		}
		System.out.println(email);
		
		String sql = "SELECT COUNT(id) FROM saler WHERE email LIKE ?";
		String sql2 = "SELECT * FROM saler WHERE email LIKE ? ORDER BY id DESC LIMIT ?,?";

		Connection conn = null;
		try{
			conn = DbHelper.getConn();
			
			Long temp = qr.query(conn, sql, scalarHandler, "%" + email + "%");
			if(temp != null && temp.longValue() > 0){
				page.setTotalElements(temp.longValue());
				
				Object[] params = {"%" + email + "%",
						Integer.valueOf((number - 1) * size),
						Integer.valueOf(size)};
				
				List<Saler> list = qr.query(conn, sql2, beanListHandler, params);
				page.setItems(list);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return page;
	}
	
	/**
	 * 
	 * @param number 页号
	 * @param size 每页的记录数
	 */
	public Page<Saler> findByPager(int number, int size){
		 Page<Saler> page = new Page<Saler>(number, size);
		 
		 long totalElements = count();
		 if(totalElements > 0){
			 page.setTotalElements(totalElements);
			 
			 String sql = "select * from saler limit ?,?";
			 Object[] params = { (number - 1) * size, size };
			 
			 Connection conn = null;
				try {
					conn = DbHelper.getConn();
					
					List<Saler> list = qr.query(conn, sql, beanListHandler, params);
					
					page.setItems(list);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					DbUtils.closeQuietly(conn);
				}
		 }
		 
		 return page;
	}

	public void setCategory(Integer category_id, Integer id) {
		String sql = "UPDATE saler SET category=? WHERE id=?";
		Connection conn = null;
		try{
			conn = DbHelper.getConn(); 
			conn.setAutoCommit(false); 
			
			qr.update(conn, sql, category_id, id);
			
			DbUtils.commitAndCloseQuietly(conn); 
		}catch(Exception e){
			 DbUtils.rollbackAndCloseQuietly(conn); 
			 
			 throw new RuntimeException(e);
		}
		
	}
}
