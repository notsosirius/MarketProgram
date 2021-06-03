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
import com.javsrc.entity.Administrator;
import com.javsrc.entity.common.Page;

/**
 * 管理员相关的业务逻辑类---->CRUD操作
 */
public class AdministratorService {

	/**common-dbutils包提供的一个SQL执行器类*/
	private QueryRunner qr = new QueryRunner();
	
	/**单行单列的结果集处理器*/
	private ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();
	/**单行多列的结果集处理*/
	private BeanHandler<Administrator> beanHandler = new BeanHandler<>(Administrator.class);
	private BeanListHandler<Administrator> beanListHandler = new BeanListHandler<>(Administrator.class);
	
	/**
	 * 新增一个管理员 
	 * @param administrator 要新增的管理员对象
	 * @return 新增成功后的管理员对象
	 */
	public Administrator save(Administrator member){
		String sql = "insert into administrator(mobile,pwd,real_name,email,"
				+ "register_time) values(?,?,?,?,?)";
		
		Object[] params = { member.getMobile(), member.getPwd(), 
				member.getReal_name(), member.getEmail(), 
				 member.getRegister_time() 
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
		String sql = "delete from administrator where id=?";
		
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
	public void update(Administrator member){
		String sql = "update administrator  set mobile=?,pwd=?,real_name=?,"
				+ "email=?,register_time=? where id=?";
		
		Object[] params = { member.getMobile(), member.getPwd(), 
				member.getReal_name(), member.getEmail(), 
				member.getRegister_time(), member.getId()
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
	public Administrator findOne(Integer id){
		Administrator member = null;
		
		//id,mobile,pwd,real_name,nick_name,email,gender,register_time
		String sql = "select * from administrator where id=?";
		
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
	public Administrator findByEmail(String email){
		Administrator member = null;
		
		String sql = "select * from administrator where email=?";
		
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
	public List<Administrator> findAll(){
		List<Administrator> list = new ArrayList<Administrator>();
		
		String sql = "select * from administrator";
		
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
		
		String sql = "select count(id) from administrator";
		
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
	 * 
	 * @param number 页号
	 * @param size 每页的记录数
	 */
	public Page<Administrator> findByPager(int number, int size){
		 Page<Administrator> page = new Page<Administrator>(number, size);
		 
		 long totalElements = count();
		 if(totalElements > 0){
			 page.setTotalElements(totalElements);
			 
			 String sql = "select * from administrator limit ?,?";
			 Object[] params = { (number - 1) * size, size };
			 
			 Connection conn = null;
				try {
					conn = DbHelper.getConn();
					
					List<Administrator> list = qr.query(conn, sql, beanListHandler, params);
					
					page.setItems(list);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					DbUtils.closeQuietly(conn);
				}
		 }
		 
		 return page;
	}
}
