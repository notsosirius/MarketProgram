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
import com.javsrc.entity.SessionInfo;
import com.javsrc.entity.common.Page;

/**
 * session数据相关的业务逻辑类---->CRUD操作
 */
public class SessionInfoService {

	/**common-dbutils包提供的一个SQL执行器类*/
	private QueryRunner qr = new QueryRunner();
	
	/**单行单列的结果集处理器*/
	private ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();
	/**单行多列的结果集处理*/
	private BeanHandler<Saler> beanHandler = new BeanHandler<>(Saler.class);
	private BeanListHandler<SessionInfo> beanListHandler = new BeanListHandler<>(SessionInfo.class);
	
	
	public SessionInfo save(SessionInfo member){
		String sql = "insert into session(user_cate,email,mobile,ip,"
				+ "time,operation,user_id,goods) values(?,?,?,?,?,?,?,?)";
		
		Object[] params = { member.getUser_cate(), member.getEmail(), 
				member.getMobile(), member.getIp(), 
				 member.getTime(), member.getOperation(), 
				 member.getUser_id(), member.getGoods()
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
	
	
	public void delete(Integer id){
		String sql = "delete from session where id=?";
		
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
	
	
	public void update(SessionInfo member){
		String sql = "update saler set user_cate=?,email=?,mobile=?,"
				+ "ip=?,time=?,operation=?,user_id=? where id=?";
		
		Object[] params = { member.getUser_cate(), member.getEmail(), 
				member.getMobile(), member.getIp(), 
				 member.getTime(), member.getOperation(), member.getUser_id()
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
	

	public List<SessionInfo> findAll(){
		List<SessionInfo> list = new ArrayList<SessionInfo>();
		
		String sql = "select * from session order by id desc";
		
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
	
	public List<SessionInfo> findAllInout(){
		List<SessionInfo> list = new ArrayList<SessionInfo>();
		
		String sql = "select * from session where operation='登录' or operation='登出' order by id desc";
		
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
	
	public List<SessionInfo> findAllBrowse(){
		List<SessionInfo> list = new ArrayList<SessionInfo>();
		
		String sql = "select * from session where operation='浏览' order by id desc";
		
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
	 * 
	 * @param number 页号
	 * @param size 每页的记录数
	 */
	public Page<SessionInfo> findInoutByPager(int number, int size){
		 Page<SessionInfo> page = new Page<SessionInfo>(number, size);
		 
		 long totalElements = count();
		 if(totalElements > 0){
			 page.setTotalElements(totalElements);
			 
			 String sql = "select * from session where operation='登录' or operation='登出' limit ?,?";
			 Object[] params = { (number - 1) * size, size };
			 
			 Connection conn = null;
				try {
					conn = DbHelper.getConn();
					
					List<SessionInfo> list = qr.query(conn, sql, beanListHandler, params);
					
					page.setItems(list);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					DbUtils.closeQuietly(conn);
				}
		 }
		 
		 return page;
	}
	
	public long count(){
		long count = 0;
		
		String sql = "select count(id) from session";
		
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
	
}
