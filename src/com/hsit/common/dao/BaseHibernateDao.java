/**   
* @Title: BaseHibernateDao.java
* @Package com.hsit.common.dao
* @Description: TODO
* @author XUJC 
* @date 2017年11月26日 下午6:01:26
* @version V1.0   
*/


package com.hsit.common.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.DomainObject;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.utils.PagingBean;


/**
 * @ProjectName:sizt-coupons
 * @ClassName: BaseHibernateDao
 * @Description:
 * @author XUJC
 * @date 2016年11月26日 下午6:01:26
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public interface BaseHibernateDao<T extends DomainObject, Q extends EntityQueryParam> extends Dao<T,Q>{

	
	/**
	 * 
	* @Title: load 
	* @Description: LOAD数据
	* @param @param id
	* @param @return
	* @param @throws ErrorException
	* @return T 
	* @throws
	 */
	public T load(String id)throws ErrorException;
	
	/**
	 * 
	* 方法描述:evict
	* 创建人: XUJC
	* 创建时间：2017年12月12日
	* @param t
	* @throws ErrorException
	 */
    public void evict(T t)throws ErrorException;
	
	/**
	 * 
	* @Title: findHql 
	* @Description: HQL查询
	* @param @param hql
	* @param @return
	* @param @throws ErrorException
	* @return List<T> 
	* @throws
	 */
	public List<T> findHql(final String hql,final Map<String, Object> paramMap)throws ErrorException;
	
	/**
	 * 
	* 方法描述:HQL查询一个实体类
	* 创建人: XUJC
	* 创建时间：2017年12月21日
	* @param hql
	* @param paramMap
	* @return
	* @throws ErrorException
	 */
	public T findHqlUniqueResult(final String hql,final Map<String, Object> paramMap)throws ErrorException;
	
	/**
	 * 
	* 方法描述:HQL查询
	* 创建人: XUJC
	* 创建时间：2017年12月12日
	* @param hql
	* @param pagingBean
	* @param paramMap
	* @return
	* @throws ErrorException
	 */
	public List<T> findHql(final String hql,final PagingBean pagingBean,final Map<String, Object> paramMap)throws ErrorException;
	
	/**
	 * 
	* @Title: findHqlToM 
	* @Description: HQL
	* @param @param hql
	* @param @return
	* @param @throws ErrorException
	* @return List<M> 
	* @throws
	 */
	public <M> List<M> findHqlToM(final String hql,final Map<String, Object> paramMap)throws ErrorException;
	
	/**
	 * 
	* 方法描述:HQL查询
	* 创建人: XUJC
	* 创建时间：2017年12月13日
	* @param hql
	* @param paramMap
	* @param pagingBean
	* @return
	* @throws ErrorException
	 */
	public <M> List<M> findHqlToM(final String hql,final Map<String, Object> paramMap,final PagingBean pagingBean)throws ErrorException;

	/**
	 * 
	* @Title: findSql 
	* @Description:SQL查询
	* @param @param sql
	* @param @param entityClass
	* @param @return
	* @param @throws ErrorException
	* @return List<?> 
	* @throws
	 */
	public <M> List<M> findSqlBeanToList(final String sql,final Class<?> beanClass,final Map<String, Object> paramMap)throws ErrorException;
	
	/**
	 * 
	* @Title: findSqlBeanToUniqueResult 
	* @Description: SQL查询返回单实体
	* @param @param sql
	* @param @param beanClass
	* @param @param paramMap
	* @param @return
	* @param @throws ErrorException
	* @return List<M> 
	* @throws
	 */
	public <M> M findSqlBeanToUniqueResult(final String sql,final Class<?> beanClass,final Map<String, Object> paramMap)throws ErrorException;
	/**
	 * 
	* @Title: findSqlToBean 
	* @Description: SQL查询分页查询
	* @param @param sql
	* @param @param beanClass
	* @param @param pagingBean
	* @param @return
	* @param @throws ErrorException
	* @return List<M> 
	* @throws
	 */
	public <M> List<M> findSqlBeanToList(final String sql,final Class<?> beanClass,final PagingBean pagingBean,final Map<String, Object> paramMap)throws ErrorException;
	/**
	 * 
	* @Title: findSql 
	* @Description: 
	* @param @param sql
	* @param @param entityClass
	* @param @return
	* @param @throws ErrorException
	* @return List<?> 
	* @throws
	 */
	public List<?> findSql(final String sql,final Class<?> entityClass)throws ErrorException;
	
	/**
	 * 
	* @Title: findSql 
	* @Description: SQL分页查询
	* @param @param sql
	* @param @param entityClass
	* @param @param pagingBean
	* @param @return
	* @param @throws ErrorException
	* @return List<?> 
	* @throws
	 */
	public <M> List<M> findSql(final String sql,final Class<?> entityClass,final PagingBean pagingBean)throws ErrorException;
	
	/**
	 * 
	* @Title: findSqlCounts 
	* @Description:查询SQL统计
	* @param @param sql
	* @param @return
	* @param @throws ErrorException
	* @return BigInteger 
	* @throws
	 */
	public BigInteger findSqlCounts(final String sql,final Map<String, Object> paramMap)throws ErrorException;
	
	/**
	 * 
	* 方法描述:查询HQL统计
	* 创建人: XUJC
	* 创建时间：2017年12月12日
	* @param hql
	* @param paramMap
	* @return
	* @throws ErrorException
	 */
	public Long findHqlCounts(final String hql,final Map<String, Object> paramMap)throws ErrorException;
	
	/**
	 * 
	* @Title: executeUpdateSql 
	* @Description: 执行SQL更新
	* @param @param sql
	* @param @return
	* @param @throws ErrorException
	* @return int 
	* @throws
	 */
	public Integer executeUpdateSql(final String sql,final Map<String, Object> paramMap)throws ErrorException;
	
	/**
	 * 
	* @Title: executeUpdateHql 
	* @Description: 执行HQL
	* @param @param hql
	* @param @return
	* @param @throws ErrorException
	* @return Integer 
	* @throws
	 */
	public Integer executeUpdateHql(final String hql,final Map<String, Object> paramMap)throws ErrorException;
	
	/**
	 * 
	* @Title: flush 
	* @Description: flush
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void flush()throws ErrorException;
	
}
