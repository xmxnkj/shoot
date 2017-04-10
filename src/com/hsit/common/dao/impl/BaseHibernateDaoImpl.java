/**   
* @Title: BaseHibernateDaoImpl.java
* @Package com.hsit.common.dao.impl
* @Description: TODO
* @author XUJC 
* @date 2017年11月26日 下午6:04:00
* @version V1.0   
*/


package com.hsit.common.dao.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.admin.jdbc.Associated;
import com.admin.jdbc.CatchInfo;
import com.admin.jdbc.Order;
import com.admin.jdbc.QueryInfo;
import com.admin.jdbc.SqlWhereType;
import com.admin.jdbc.annotation.MTM;
import com.admin.jdbc.annotation.MTO;
import com.admin.jdbc.annotation.OTO;
import com.admin.jdbc.annotation.OTM;
import com.hsit.common.annotation.GlobalLanguage;
import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.DomainObject;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.utils.PagingBean;
import com.util.caseConversion;


/**
 * @ProjectName:sizt-coupons
 * @ClassName: BaseHibernateDaoImpl
 * @Description:
 * @author XUJC
 * @date 2016年11月26日 下午6:04:00
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class BaseHibernateDaoImpl<T extends DomainObject, Q extends EntityQueryParam> extends HibernateDao<T, Q> implements
		BaseHibernateDao<T, Q> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<T> findHql(final String hql,final Map<String, Object> paramMap) throws ErrorException {
		try {
			@SuppressWarnings("unchecked")
			List<T> list = (List<T>)this.getTemplate().execute(new HibernateCallback<List<T>>(){
				@Override
				public List<T> doInHibernate(final Session session) throws HibernateException, SQLException {
					final Query query = session.createQuery(hql);
					createQueryParameter(query,paramMap);
					return query.list();
				}
			});		
			return list;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	
	@Override
	public T findHqlUniqueResult(final String hql,final Map<String, Object> paramMap)
			throws ErrorException {
		try {
			@SuppressWarnings("unchecked")
			T list = (T)this.getTemplate().execute(new HibernateCallback<T>(){
				@Override
				public T doInHibernate(final Session session) throws HibernateException, SQLException {
					final Query query = session.createQuery(hql);
					createQueryParameter(query,paramMap);
					return (T)query.uniqueResult();
				}
			});		
			return list;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	
	@Override
	public List<T> findHql(final String hql,final PagingBean pagingBean,
			               final Map<String, Object> paramMap) throws ErrorException {
		try {
			
			StringBuilder totalHQL = new StringBuilder(" select count(*) ");
			totalHQL.append(hql);
			
			Long counts = this.findHqlCounts(totalHQL.toString(),paramMap);
			pagingBean.setTotalItems(counts.intValue());
			pagingBean.setTotalPages();
			pagingBean.setStartIndex();
			pagingBean.setLastIndex();
			
			@SuppressWarnings("unchecked")
			List<T> list = (List<T>)this.getTemplate().execute(new HibernateCallback<List<T>>(){
				@Override
				public List<T> doInHibernate(final Session session) throws HibernateException, SQLException {
					final Query query = session.createQuery(hql);
					createQueryParameter(query,paramMap);
				    query.setFirstResult(pagingBean.getStartIndex())
					.setMaxResults(pagingBean.getPageSize())
					.setFetchSize(pagingBean.getPageSize());
					return query.list();
				}
			});		
			return list;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	
	@Override
	public <M> List<M> findHqlToM(final String hql,final Map<String, Object> paramMap) throws ErrorException {
		try {
			@SuppressWarnings("unchecked")
			List<M> list = (List<M>)this.getTemplate().execute(new HibernateCallback<List<M>>(){
				@Override
				public List<M> doInHibernate(final Session session) throws HibernateException, SQLException {
				    Query query = session.createQuery(hql);
				    createQueryParameter(query,paramMap);
					return query.list();
				}
			});		
			return list;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	@Override
	public <M> List<M> findHqlToM(final String hql, 
			                      final Map<String, Object> paramMap,
			                      final PagingBean pagingBean) throws ErrorException {
		try {
			
			StringBuilder totalHQL = new StringBuilder(" select count(*) ");
			totalHQL.append(hql);
			
			Long counts = this.findHqlCounts(totalHQL.toString(),paramMap);
			pagingBean.setTotalItems(counts.intValue());
			pagingBean.setTotalPages();
			pagingBean.setStartIndex();
			pagingBean.setLastIndex();
			
			
			@SuppressWarnings("unchecked")
			List<M> list = (List<M>)this.getTemplate().execute(new HibernateCallback<List<M>>(){
				@Override
				public List<M> doInHibernate(final Session session) throws HibernateException, SQLException {
				    Query query = session.createQuery(hql);
				    createQueryParameter(query,paramMap);
				    query.setFirstResult(pagingBean.getStartIndex())
					.setMaxResults(pagingBean.getPageSize())
					.setFetchSize(pagingBean.getPageSize());
					return query.list();
				}
			});		
			return list;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}


	@Override
	public List<?> findSql(final String sql, final Class<?> entityClass)
			throws ErrorException {
		try {
			List<?> list = (List<?>)this.getTemplate().execute(new HibernateCallback<List<?>>(){
				@Override
				public List<?> doInHibernate(final Session session) throws HibernateException, SQLException {
					final SQLQuery sqlQuery = session.createSQLQuery(sql);
					sqlQuery.addEntity(entityClass);
					return sqlQuery.list();
				}
			});		
			return list;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	
	@Override
	public <M> List<M> findSqlBeanToList(final String sql,final Class<?> beanClass,final Map<String, Object> paramMap)
			throws ErrorException {
		try {
			List<M> list = (List<M>)this.getTemplate().execute(new HibernateCallback<List<M>>(){
				@SuppressWarnings("unchecked")
				@Override
				public List<M> doInHibernate(final Session session) throws HibernateException, SQLException {
				    SQLQuery sqlQuery = session.createSQLQuery(sql);
				    if(paramMap!=null){
				    	sqlQuery.setProperties(paramMap);
				    }
				    sqlQuery.setResultTransformer(Transformers.aliasToBean(beanClass));
					return sqlQuery.list();
				}
			});		
			return list;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	
	@Override
	public <M> List<M> findSqlBeanToList(final String sql, final Class<?> beanClass,
			final PagingBean pagingBean,final Map<String, Object> paramMap) throws ErrorException {
		try {
			
			StringBuilder totalSQL = new StringBuilder(" SELECT count(*) FROM ( ");
			totalSQL.append(sql);
			totalSQL.append(" ) totalTable ");
			
			BigInteger counts = this.findSqlCounts(totalSQL.toString(),paramMap);
			pagingBean.setTotalItems(counts.intValue());
			pagingBean.setTotalPages();
			pagingBean.setStartIndex();
			pagingBean.setLastIndex();
			
			List<M> list = (List<M>)this.getTemplate().execute(new HibernateCallback<List<M>>(){
				@SuppressWarnings("unchecked")
				@Override
				public List<M> doInHibernate(final Session session) throws HibernateException, SQLException {
				    SQLQuery sqlQuery = session.createSQLQuery(sql);
				    if(paramMap!=null){
				    	sqlQuery.setProperties(paramMap);
				    }
					sqlQuery.setFirstResult(pagingBean.getStartIndex())
					.setMaxResults(pagingBean.getPageSize())
					.setFetchSize(pagingBean.getPageSize());
				    sqlQuery.setResultTransformer(Transformers.aliasToBean(beanClass));
					return sqlQuery.list();
				}
			});		
			
			return list;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	@Override
	public <M> List<M> findSql(final String sql, final Class<?> entityClass,
			final PagingBean pagingBean) throws ErrorException {
		try {
			List<M> list = (List<M>)this.getTemplate().execute(new HibernateCallback<List<M>>(){
				@SuppressWarnings("unchecked")
				@Override
				public List<M> doInHibernate(final Session session) throws HibernateException, SQLException {
					final SQLQuery sqlQuery = session.createSQLQuery(sql);
					sqlQuery.addEntity(entityClass);
					sqlQuery.setFirstResult(pagingBean.getStartIndex())
					.setMaxResults(pagingBean.getPageSize())
					.setFetchSize(pagingBean.getPageSize());
					return sqlQuery.list();
				}
			});		
			return list;
		} catch (Exception e) {
			throw new ErrorException(e);
		}

	}

	@Override
	public BigInteger findSqlCounts(final String sql,final Map<String, Object> paramMap) throws ErrorException {
		try {
			BigInteger result = getTemplate().execute(new HibernateCallback<BigInteger>() {
				public BigInteger doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery sqlQuery = session.createSQLQuery(sql);
				    if(paramMap!=null){
				    	sqlQuery.setProperties(paramMap);
				    }
					return (BigInteger)sqlQuery.uniqueResult();
				}
			});
			return result;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	
	@Override
	public Long findHqlCounts(final String hql, final Map<String, Object> paramMap)
			throws ErrorException {
		try {
			Long result = getTemplate().execute(new HibernateCallback<Long>() {
				public Long doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					createQueryParameter(query,paramMap);
					return (Long)query.uniqueResult();
				}
			});
			return result;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}


	@Override
	public Integer executeUpdateSql(final String sql,final Map<String, Object> paramMap) throws ErrorException {
		try {
			Integer result = getTemplate().execute(new HibernateCallback<Integer>() {
				public Integer doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery sqlQuery = session.createSQLQuery(sql);
					if(paramMap!=null){
						sqlQuery.setProperties(paramMap);
					}
					return sqlQuery.executeUpdate();
				}
			});
			return result;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	
	@Override
	public Integer executeUpdateHql(final String hql, final Map<String, Object> paramMap)
			throws ErrorException {
		try {
			Integer result = getTemplate().execute(new HibernateCallback<Integer>() {
				public Integer doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					if(paramMap!=null){
						query.setProperties(paramMap);
					}
					return query.executeUpdate();
				}
			});
			return result;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	@Override
	public void flush() throws ErrorException {
	   try {
		   getTemplate().flush();
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(String id) throws ErrorException {
		try {
			Class<T> entityClass=  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			return getTemplate().load(entityClass, id);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}


	@Override
	public <M> M findSqlBeanToUniqueResult(final String sql, final Class<?> beanClass,
			final Map<String, Object> paramMap) throws ErrorException {
		try {
			M result = (M)this.getTemplate().execute(new HibernateCallback<M>(){
				@SuppressWarnings("unchecked")
				@Override
				public M doInHibernate(final Session session) throws HibernateException, SQLException {
				    SQLQuery sqlQuery = session.createSQLQuery(sql);
				    if(paramMap!=null){
				    	sqlQuery.setProperties(paramMap);
				    }
				    sqlQuery.setResultTransformer(Transformers.aliasToBean(beanClass));
					return (M)sqlQuery.uniqueResult();
				}
			});		
			return result;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}


	@Override
	public void evict(final T t) throws ErrorException {
		try {
			 getTemplate().execute(new HibernateCallback<Integer>() {
				public Integer doInHibernate(Session session) throws HibernateException, SQLException {
					session.evict(t);
					return 0;
				}
			});
		} catch (Exception e) {
			throw new ErrorException(e);
		}		
	}

	
	/**
	 * 
	* 方法描述:创建Query对象查询参数
	* 创建人: XUJC
	* 创建时间：2017年12月13日
	* @param query
	* @param paramMap
	 */
	private void createQueryParameter(Query query, Map<String, Object> paramMap){
		if (paramMap != null) {
			Iterator<String> keys = paramMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = paramMap.get(key);
				if (value instanceof Collection)
					query.setParameterList(key, (Collection<?>) value);
				else if (value instanceof Object[])
					query.setParameterList(key, (Object[]) value);
				else
					query.setParameter(key, value);
			}
		}	
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 按id查找
	 * 统一继承id类可以使用id类获取子类的id不需要标记注释
	 * @param queryinfo sql查询配置
	 * @param clas 映射实体
	 * @return
	 * @throws Exception 
	 */
	public final  T getObjId(Object id,QueryInfo qi,Class<?> cl) throws Exception {
		qi.setBegin(0);
		qi.setEnd(1);
//		qi.getSearch().put(getIdName(cl), id);
		qi.getSearch().put("ID",id );
		List<T> li = executeGets(qi,cl,true);
		return (li==null || li.size()==0)? null : li.get(0);
	}

	/**
	 * @param queryinfo sql查询配置
	 * @param ma where 参数 用加号分割查询条件 如 EQ+id 等于id = xxx
	 * @param clas 映射实体
	 * @return
	 * @throws Exception 
	 */
	public final  T getEntity(QueryInfo qi,Class<?> cl) throws Exception {
		qi.setBegin(0);
		qi.setEnd(1);
		List<T> li = executeGets(qi,cl,true);
		return (li==null || li.size()==0)? null : li.get(0);
	}
	
	/**
	 * @param queryinfo sql查询配置
	 * @param ma where 参数 用加号分割查询条件 如 EQ+id 等于id = xxx
	 * @param clas 映射实体
	 * @return
	 * @throws Exception 
	 */
	// 这边参数是对象 传的是引用 不要修改全局参数
	public final  List<T> executeGets(HashMap<String,Object> where,Class<?> cl) throws Exception {
		return executeGets(new QueryInfo(where),cl,true);
	}
	
	public static Object setWhereValue(String key,Object value){
		Object v = value;
		if(SqlWhereType.LIKE.toString().equals(key)){
			v ="%"+v+"%";
		}else if(SqlWhereType.BEFORELIKE.toString().equals(key)){
			v ="%"+v;
		}else if(SqlWhereType.AFTERLIKE.toString().equals(key)){
			v =v+"%";
		}
		return v;
	}
	
	/**
	 * 拼接where
	 * @param where
	 */
	public static void addWhere(StringBuffer where,List<Object> whereVlaue,HashMap<String,Object> whereMa,String alias){
		if(whereMa != null && whereMa.size()>0){
			where.append(" where ");
			String usealias = null;
			if(alias != null)
				usealias = alias+".";
			else
				usealias = "";
			for (Map.Entry<String, Object> map : whereMa.entrySet()) {
				String[] name = map.getKey().split("\\_");
				String whe = "EQ";//没有默认等于
				if(name.length>1)
					whe = name[0];
				where.append(usealias+(name.length>1 ? caseConversion.camel2Underline(name[1]) : caseConversion.camel2Underline(name[0]))+SqlWhereType.getKey(whe).getExpression()+" and ");
				if(!"".equals(map.getValue().toString().trim())){
					whereVlaue.add(setWhereValue(whe,map.getValue()));
				}
			}
			where.delete(where.length()-4,where.length());
		}
	}
	
	/**
	 * 设置排序
	 */
	public final String setSort(ArrayList<Order> or,String alias){
		StringBuffer s = new StringBuffer();
		if(or!=null && or.size()>0){
			s.append(" ORDER BY ");
			for(Order order : or){
				s.append(order.getAlias()==null?alias:order.getAlias()+order.getName()+" "+order.getSort()+",");
			}
			s.deleteCharAt(s.length()-1);
		}
		return s.length()>0 ? s.toString() : null;
	}
	
	/**
	 * 返回sql语句 片段
	 * 有时间把sql片段和缓存在抽出来 如果要加get（id）方法需要自定义注释idFeild 记录id字段 在这边保存key用SQLID注意别出现相同名字的对象 不然会被盖掉
	 * @param info 该实体的sql配置
	 * @param ma where 参数
	 * @param qi 自定义sql配置
	 * @return
	 */
	public final HashMap<String,Object> createSQLDebris(CatchInfo info,QueryInfo qi,boolean b){
		HashMap<String,Object> rema= new HashMap<String,Object>();
		StringBuffer sqlPrefix =  new StringBuffer();;
		List<Object> whereVlaue = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		final String  myForm  = info.getTable();
		//添加查询条件
		addWhere(where,whereVlaue,qi.getSearch(),info.getAlias());
		if(qi.getSort()!=null)//排序
			where.append(" ORDER BY "+ qi.getSort()+qi.getOrder());
		else{
			String stort = setSort(qi.getOr(),info.getAlias());
			if(stort!=null)
				where.append(stort);
		}
		
		if(qi.getBegin()!=null && qi.getEnd()!=null){//mysql 分页
			where.append(" limit ?,? ");
			whereVlaue.add(qi.getBegin());
			whereVlaue.add(qi.getEnd());
		}
		
		StringBuffer from = new  StringBuffer("from "+myForm+" "+info.getAlias()+" ");
		List<String> cancelField = null;//不查询的字段
		List<String> cancelObj = null;//不查询的对象
		//提取cancelField
		if(qi.getCancel()!=null && qi.getCancel().size()>0){
			cancelField = new ArrayList<String>();
			cancelObj = new ArrayList<String>();
			for(int i=0;i<qi.getCancel().size();i++){
				String[] spper = qi.getCancel().get(i).split("\\.");
				if(spper.length>1)
					cancelField.add(qi.getCancel().get(i));
				else
					cancelObj.add(qi.getCancel().get(i));
			}
		}
		if(qi.getMeSelect()!=null){//只查某些字段 
			sqlPrefix = new StringBuffer(qi.getMeSelect().trim().replaceAll("\\*", info.getSqlPrefix())+" ");
			if(!",".equals(sqlPrefix.substring(sqlPrefix.length()-1))){
				sqlPrefix.append(",");
			}
		}
		if(!qi.isCancelConnection()){//是否进行关联
			if(info.getSqlPrefix().trim().length()>0 && qi.getMeSelect()==null)
				sqlPrefix = new StringBuffer(info.getSqlPrefix()+",");
			//处理关联对象
			if(info.getAss()!=null){
				outer:
				for (Map.Entry<String, Associated> map : info.getAss().entrySet()) {
					//一对一和 多对一
					if(OTO.class.getSimpleName().equals(map.getValue().getType()) || MTO.class.getSimpleName().equals(map.getValue().getType())){
						CatchInfo dx = getSqlInfo(map.getValue().getClas());
						if(qi.getMeSelect()==null){
							if(cancelObj!=null){
								for(int i=0;i<cancelObj.size();i++){
									//存在不查询的对象取消关联 只返回关联id 并且HeirOwn为为true
									if(map.getKey().equals(cancelObj.get(i)) && map.getValue().getHeirOwn()){
										sqlPrefix.append(info.getAlias()+"."+caseConversion.camel2Underline(map.getValue().getAssField().trim())+",");
										continue outer;
									}
								}
							}
							//替换关联对象的前缀为字段name
							sqlPrefix.append(dx.getSqlPrefix().replaceAll(map.getValue().getClas().getSimpleName()+".",map.getKey()+".")+",");
							//如果关联对象有一对一和多对一 也应该返回id字段
							for (Map.Entry<String, Associated> dxma : dx.getAss().entrySet()) {
								if(OTO.class.getSimpleName().equals(dxma.getValue().getType()) 
										|| MTO.class.getSimpleName().equals(dxma.getValue().getType())){
									if(map.getValue().getHeirOwn())
										sqlPrefix.append(map.getKey()+"."+caseConversion.camel2Underline(dxma.getValue().getAssField())+",");
								}
							}
						}
						//拼接from的关联关系
						if(qi.getConnection()!=null && qi.getConnection().get(map.getKey())!=null)
							from.append(" "+qi.getConnection().get(map.getKey()));
						else
							from.append(" "+map.getValue().getConnection());
						from.append(" "+dx.getTable()+" "+map.getKey()+"");
						from.append(" on "+map.getValue().getRelationship());
					}
				}
			}
		}else
			sqlPrefix = new StringBuffer(info.getSqlPrefix());
		sqlPrefix.deleteCharAt(sqlPrefix.length()-1);
		if(cancelField != null && cancelField.size()>0){
			String pre = sqlPrefix.toString();
			for(int i=0;i<cancelField.size();i++){
				pre = pre.replaceAll(cancelField.get(i)+",", "");
			}
			sqlPrefix = new StringBuffer(pre);
		}
		rema.put("sqlPrefix", sqlPrefix.toString());
		rema.put("from", from.toString());
		rema.put("where", where);
		rema.put("cancelObj", cancelObj);
		rema.put("whereVlaue", whereVlaue);//查询防注入按顺序返回参数
		return rema;
	}
	
	
	/**
	 * 添加参数
	 * @param pstmt
	 * @param whereValue
	 * @throws SQLException
	 */
	public static void setWhereValue(PreparedStatement pstmt,List<Object> whereValue) throws SQLException{
		if(whereValue!=null){
			int i = 1;
		    for(Object obj: whereValue){ 
		    	pstmt.setObject(i++,obj);  
		    }  
		}
	}
	
	public static boolean isJavaClass(Class<?> clz) {  
	    return clz != null && clz.getClassLoader() == null;  
	}  
	
	public static Field getAccessibleField(final Class<?> clas, final String fieldName) {
		for (Class<?> superClass = clas; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}
	
	public static Field getAccessibleField(final Object obj, final String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}
	
	/**
	 * 取消关联时为子对象赋值
	 */
	public static Object createChild(String fieldName,Class<?> faClass,Object val){
		try {
//			Field f = faClass.getDeclaredField(caseConversion.underline2Camel(fieldName,true) );
			Field f = getAccessibleField(faClass, caseConversion.underline2Camel(fieldName,true));
			if(f.getType().isEnum()){
				Object[] ob = f.getType().getEnumConstants();
				return ob[(int)val];
			}
			Class<?> childClass = f.getType();
			String name = "id";//默认值是id
			if(f.getAnnotation(OTO.class)!=null){
				name = f.getAnnotation(OTO.class).pointAssField();
			}else if(f.getAnnotation(MTO.class)!=null){
				name = f.getAnnotation(MTO.class).pointAssField();
			}else{
				throw new Exception(faClass+"."+f.getName()+"没有多对一或一对一的注释");
			}
			Field chif = getAccessibleField(childClass,name);
			if(chif == null)
				throw new Exception("createChild方法chifField找不到");
			Object chiObj = childClass.newInstance();
			chif.set(chiObj, val);
			return chiObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 封装对象
	 * @param rs
	 * @param clas
	 * @param sqlDebris
	 * @param fields
	 * @param m
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public final static Object sealResults(ResultSet rs,Class<?> clas,String[] fields,HashMap<String,Method[]> method) 
			throws Exception{
		Object obj = clas.newInstance();
		HashMap<String,Object> objdx = new HashMap<String,Object>();//当前类中的对象集合
		HashMap<String,Object> objdxField = new HashMap<String,Object>();//当前类中的对象的对象字段
		for (int i = 0; i < fields.length; i++) {
			Object o = rs.getObject(i+1);//不用key防止同名比如id
			if(o != null){
				String[] name = fields[i].split("\\.");
				if((clas.getSimpleName()).equals(name[0].trim())){
					//枚举的参数类型不匹配所以实体属性不要出现重构方法
//					Method m = clas.getMethod("set"+caseConversion.underline2Camel(name[1],false),o.getClass());
					Field field = getAccessibleField(clas, caseConversion.underline2Camel(name[1],true));
					if(!isJavaClass(field.getType())){//set的参数是对象
						o = createChild(name[1],clas,o);
					}
					field.set(obj, o);
				}else{
					//如果需要给子对象赋值就换递归
					if(objdx.get(name[0])==null){
						Field fidx = clas.getDeclaredField(name[0]);//获取子对象field
						objdx.put(name[0], fidx.getType().newInstance());//初始化
						objdxField.put(name[0], getSqlInfo(fidx.getType()).getAss());
						fidx.set(obj, objdx.get(name[0]));//赋值给主对象
					}
					Class<?> childc = objdx.get(name[0]).getClass();
					Field field = getAccessibleField(childc, caseConversion.underline2Camel(name[1],true));
					if(!isJavaClass(field.getType())){//set的参数是对象
						o = createChild(name[1],childc,o);
					}
					field.setAccessible(true);
					field.set(objdx.get(name[0]), o);
				}
			}
		}
		return obj;
	}
	
	/**
	 * @param queryinfo sql查询配置
	 * @param ma where 参数 用加号分割查询条件 如 EQ+id 等于id = xxx
	 * @param clas 映射实体
	 * @param boolean b  true 先分页在关联 false 先关联在分页（前提使用子查询）
	 * @return
	 * @throws Exception 
	 */
	// 这边参数是对象 传的是引用 不要修改全局参数
	public final  List<T> executeGets(final QueryInfo qi,Class<?> cl,boolean b) throws Exception {
		final Class<?> clas = cl;
		final CatchInfo info = getSqlInfo(clas);
		if(qi == null)
			throw new  Exception("QueryInfo不能为空");
		final HashMap<String,Object> sqlDebris =  createSQLDebris(info,qi,b);
		System.out.println("SQL:"+"select "+sqlDebris.get("sqlPrefix")+" "+sqlDebris.get("from")+" "+(sqlDebris.get("where")==null ? "" : sqlDebris.get("where")));
		List result = jdbcTemplate.query("select "+sqlDebris.get("sqlPrefix")+" "+sqlDebris.get("from")+" "+(sqlDebris.get("where")==null ? "" : sqlDebris.get("where")), 
		new PreparedStatementSetter() {  
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				//添加参数
				List<Object> li = (List<Object>) sqlDebris.get("whereVlaue");
				setWhereValue(pstmt,li);
			}
		},	
		new ResultSetExtractor<List>() {
			@Override
			public List extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Object> result = new ArrayList<Object>();
				String[] fields = sqlDebris.get("sqlPrefix").toString().split(",");
				try {
					HashMap<String,Method[]> method = new HashMap<String,Method[]>();
					while (rs.next()) {
						Object obj = sealResults(rs, clas, fields,method);
						if(!qi.isCancelConnection()){
							outer:
								for (Map.Entry<String, Associated> map : info.getAss().entrySet()) {
									//有指定取消list查询结束
									if(sqlDebris.get("cancelObj")!=null){
										for(int i=0;i<((List<String>) sqlDebris.get("cancelObj")).size();i++){
											if(map.getKey().equals(((List<String>) sqlDebris.get("cancelObj")).get(i).trim())){
												 continue outer;
											}
										}
									}
								}
						}
						result.add(obj);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return result;
			}
		});
		return result;
	}
	
	public static Object[] getParam(List<Object> li){
		Object[] obj = null;
		if(li!=null && li.size()>0){
			obj = new Object[li.size()];
			for(int i = 0;i<li.size();i++){
				obj[i]=li.get(i);
			}
		}
		return obj;
	} 
	
	
	
	private static HashMap<String,CatchInfo> CacheSqlInfo = new HashMap<String,CatchInfo>();
	
	private final static CatchInfo getSqlInfo(Class<?> clas) {
		if(BaseHibernateDaoImpl.CacheSqlInfo.get(clas.getName())== null){
			createInfo(clas);
		}
		return  BaseHibernateDaoImpl.CacheSqlInfo.get(clas.getName());
	}

	
	/**
	 * 设置class的select拼接
	 * @param clas
	 * @return sql；
	 */
	public static String  getSelect(Class<?> clas){
		CatchInfo cat = getSqlInfo(clas);
		String select = cat.getSqlPrefix();
		StringBuffer jt = new StringBuffer();
		Map<String,Associated> ma = cat.getAss();
		//添加对象部分字段 
		for (Map.Entry<String, Associated> map : ma.entrySet()) {
			//非list的对象
			if(!map.getValue().getType().equals(MTM.class.getSimpleName()) &&
					!map.getValue().getType().equals(MTO.class.getSimpleName())){
				if(map.getValue().getHeirOwn()){//当使用自身字段时添加该字段
					jt.append(","+cat.getAlias()+"."+map.getValue().getAssField());
				}
			}
		}
		return select+(jt==null?"":jt.toString());
	}
	
	
	private static void  createInfo(Class<?> clas){
		CatchInfo catchinfo = new CatchInfo();
		StringBuffer select = new StringBuffer();
		final String  alias = clas.getSimpleName();
		HashMap<String,Associated> ma = new HashMap<String,Associated>();
		//查找当前并向上查找
		for (Class<?> superClass = clas; superClass != Object.class; superClass = superClass.getSuperclass()) {
				Field[] fi = superClass.getDeclaredFields();
				setSelectAndConfiguration(fi,select,ma,alias);
		}
		if(select.length()>0)
			select.deleteCharAt(select.length()-1);
		//获取表名
		Table annotation = (Table)clas.getAnnotation(Table.class);
		//表名
        if(annotation != null)
        	catchinfo.setTable(annotation.name());
        else
        	catchinfo.setTable(caseConversion.camel2Underline(clas.getSimpleName()));
        catchinfo.setAlias(alias);
        catchinfo.setAss(ma);
        catchinfo.setSqlPrefix(select.toString());
        BaseHibernateDaoImpl.CacheSqlInfo.put(clas.getName(),catchinfo );//用class做key保存到全局变量中 
	}
	
	
	/**
	 * 拼接select部分并记录关联对象配置
	 * @param fi
	 * @param select
	 * @param alias
	 */
	public  static void setSelectAndConfiguration(Field[] fi,StringBuffer select,HashMap<String,Associated> ma,String alias){
		if(fi!=null && fi.length>0){
			for(Field f : fi){
				//私有 非静态非fianl属性
				if (f.getModifiers() == 2 && !(Modifier.toString(f.getModifiers()).indexOf("static") > -1)
						&& !(Modifier.toString(f.getModifiers()).indexOf("final") > -1)) {
					if (f.getType() == List.class) {
						continue;
					} else if (f.getType().getClassLoader() != null && !f.isEnumConstant()) {
						continue;
					} else if (f.getAnnotation(Transient.class) != null)
						continue;
					// 添加该实体所有的关联对象
					if (f.getAnnotation(OTO.class) != null) {
						ma.put(f.getName(), setAnnotation(f,OTO.class.getSimpleName(),alias));
						continue;
					} else if (f.getAnnotation(MTO.class) != null) {
						ma.put(f.getName(), setAnnotation(f,MTO.class.getSimpleName(),alias));
						continue;
					} else if (f.getAnnotation(MTM.class) != null) {
						ma.put(f.getName(), setAnnotation(f,MTM.class.getSimpleName(),alias));
						continue;
					} else if (f.getAnnotation(OTM.class) != null) {
						ma.put(f.getName(), setAnnotation(f,OTM.class.getSimpleName(),alias));
						continue;
					}
					select.append(
							alias + "." + caseConversion.camel2Underline(f.getName()) + ",");
				}
			}
		}
	}
	
	/**
	 * 记录下每个实体的关联关系
	 * @param f
	 * @param annClass Annotation的getSimpleName
	 * @return
	 */
	private static Associated  setAnnotation(Field f,String annClass,String alias){
		Associated ass = new Associated();
		Class<?> c = null;
		if(annClass.equals(OTM.class.getSimpleName())){
			c = (Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
			ass.setConnection(f.getAnnotation(OTM.class).associated());
			ass.setAssField(f.getAnnotation(OTM.class).assField());
			ass.setPointAssField(f.getAnnotation(OTM.class).pointAssField());
//			ass.setHeirOwn(f.getAnnotation(OTM.class).heirOwn());
			//一对多没有自身字段 值false
			ass.setHeirOwn(false);
		}else if(annClass.equals(MTM.class.getSimpleName())){
			c = (Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
			ass.setTabName(f.getAnnotation(MTM.class).middleTable());
			ass.setConnection(f.getAnnotation(MTM.class).associated());
			ass.setAssField(f.getAnnotation(MTM.class).assField());
			ass.setPointAssField(f.getAnnotation(MTM.class).pointAssField());
//			ass.setHeirOwn(f.getAnnotation(MTM.class).heirOwn());
			//多对多没有自身字段 值false
			ass.setHeirOwn(false);
		}else if(annClass.equals(MTO.class.getSimpleName())){
			c  = f.getType();
			ass.setConnection(f.getAnnotation(MTO.class).associated());
			ass.setAssField(f.getAnnotation(MTO.class).assField());
			ass.setPointAssField(f.getAnnotation(MTO.class).pointAssField());
			ass.setHeirOwn(f.getAnnotation(MTO.class).heirOwn());
		}else if(annClass.equals(OTO.class.getSimpleName())){
			c  = f.getType();
			ass.setConnection(f.getAnnotation(OTO.class).associated());
			ass.setAssField(f.getAnnotation(OTO.class).assField());
			ass.setPointAssField(f.getAnnotation(OTO.class).pointAssField());
			ass.setHeirOwn(f.getAnnotation(OTO.class).heirOwn());
		}
		ass.setClas(c);
		ass.setType(annClass);
		//生成该实体和主实体的关联关系
		ass.setRelationship(alias + "." + caseConversion.camel2Underline((ass.getAssField())) + " = "
				+ f.getName() + "_" + "." + caseConversion.camel2Underline(ass.getPointAssField()));
		return ass;
	}
	
//	@Override
//	public List<HashMap<String, String>> findSQLSerch(String sql, List<QueryParam> query, HashMap<String, String> map)
//			throws ErrorException {
//		StringBuffer s  = new StringBuffer(sql);
//		setParam(s,query);
//		this.getJdbcTemplate().queryForList(s.toString());
//		return null;
//	}

//	/**
//	 * 设置sql参数
//	 * @param sql
//	 * @param query
//	 */
//	private void setParam(StringBuffer sql,List<QueryParam> query){
//		if(query!=null && query.size()>0){
//			for(int i=0;i<query.size();i++){
//				sql.append(" where 1=1 ");
//				QueryParam q = query.get(i);
//				if(q.getName()!=null || q.getValue()!=null){
//					sql.append(" and "+q.getName()).append(getCompareType(q.getCompareType())).append(" ? ");
//					q.setName2(String.valueOf(i));
//				} 
//			}
//		}
//	}
//	
//	private String getCompareType(ParamCompareType ct){
//		String comparaType = " = ";
//		if(ct != null){
//			if(ParamCompareType.Large ==ct){
//				comparaType = " > ";
//			}else if(ParamCompareType.Small ==ct){
//				comparaType = " < ";
//			}else if(ParamCompareType.LargeEqual ==ct ){
//				comparaType = " >= ";
//			}else if(ParamCompareType.SmallEqual ==ct ){
//				comparaType = " <= ";
//			}
//		}
//		return comparaType;
//	}


}
