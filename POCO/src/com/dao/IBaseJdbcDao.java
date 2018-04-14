package com.dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;

/**
 * 基于JDBC的DAO的公共操作接口，对数据库表提供标准的增删改查功能
 * 
 */
public interface IBaseJdbcDao {
	
	/**
	 * 执行更新SQL语句
	 * @param sql
	 * @return    返回受影响的数据行数
	 * @throws DaoException
	 */
	public int update(String sql) throws Exception;
	/**
	 * 执行带参数的更新SQL语句
	 * @param sql   带参数的SQL语句
	 * @param args  参数值
	 * @return
	 * @throws DaoException
	 */
	public int update(String sql,Object[] args) throws Exception;
	/**
	 * 执行查询SQL语句，返回String值
	 * @param sql
	 * @return
	 * @throws DaoException
	 */
	public String queryForString(String sql) throws Exception;
	/**
	 * 执行查询SQL语句，返回int值
	 * @param sql
	 * @return
	 * @throws DaoException
	 */
	public int queryForInt(String sql) throws Exception;
	
    /**
     * 执行查询SQL语句，返回Map对象，此方法只在返回一条数据是才可调用
     * 
     * @param sql
     *            SQL语句
     * @return MAP对象，如果查询结果为空时返回null
     * @throws DaoException
     */
    Map queryForMap(String sql) throws Exception;

	/**
	 * 执行查询SQL语句，返回int值
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public int queryForInt(String sql,Object[] args) throws Exception;

	/**
	 * 执行查询SQL语句，返回long值
	 * @param sql
	 * @return
	 * @throws DaoException
	 *
	 */
	public long queryForLong(String sql) throws Exception;
	
	/**
	 * 执行查询SQL语句
	 * @param sql
	 * @param objectClass
	 * @return 返回Class的对象
	 * @throws DaoException
	 *
	 */
	public Object queryForObject(String sql,Class objectClass) throws Exception;
	
	/**
	 * 执行查询SQL语句
	 * @param sql
	 * @return 返回结果列表
	 * @throws DaoException
	 *
	 */
	public List<Map<String, Object>> queryForList(String sql) throws Exception;  
	/**
	 * 执行带参数的查询SQL语句
	 * @param sql   带参数的查询SQL语句
	 * @param args  参数值
	 * @return
	 * @throws DaoException
	 */
//	public List<Map> queryForList(String sql,Object[] args) throws Exception;  
	/**
	 * 执行查询SQL语句
	 * @param sql
	 * @throws DaoException
	 *
	 */
	public void execute(String sql) throws Exception;
	
	/**
	 * 行处理回调接口
	 * @param sql
	 * @param handler
	 * @throws DaoException
	 *
	 */
	public void query(String sql, RowCallbackHandler handler) throws Exception;
	
	/**
     * 校验表是否存在
     * @param tableName
     * @return
     * @throws DaoException
     */
    public boolean isExistTable (String tableName) throws Exception;
    
    /**
     * 校验数据是否存在。
     * @param tableName 需要校验的表名
     * @param whereClause 查询条件子句，不需要包含“where”关键字
     * @return
     * @throws DaoException
     *
     */
    public boolean isExistData (String tableName, String whereClause) throws Exception;

    /**
     * 返回存在的表名
     * @param tableName
     * @return
     * @throws DaoException
     */
    public String[] getExistTables (String[] tableName) throws Exception;
    /**
     * 得到系统日期
     * @return
     * @throws DaoException
     */
    public Timestamp getSysDate() throws Exception;
    
    /**
     * 返回指定的sequence的下一个值
     * @param name
     * @return
     * @throws DaoException
     */
    public long getSequence (String name) throws Exception;
	
	public Connection getJdbcConnection() throws Exception;
	
	/**
	 * 释放数据库连接
	 * @param conn
	 * @author lihf
	 */
	public void releaseJdbcConnection(Connection conn);
	
}
