package com.oldnoop.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.oldnoop.mapper.BaseMapper;

/**
 * <pre>
 * 泛型service
 * 业务service继承BaseService,需要重写setBaseMapper方法,注入Mapper属性
 * </pre>
 * @author oldnoop
 *
 * @param <T>	实体类型
 */
@Transactional(rollbackFor=Exception.class)
public abstract class BaseService<T> {
	
	private BaseMapper<T> mapper;
	
	protected abstract void setBaseMapper(); 
	
	protected void setMapper(BaseMapper<T> mapper){
		this.mapper = mapper;
	}

	/**
	 * 通过主键查找实体对象
	 * @param id
	 * @return	T 实体对象
	 */
	public T findById(Serializable id){
		return mapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 查找所有的实体对象
	 * @return	List<T>	实体对象集合
	 */
	public List<T> findAll(){
		return mapper.selectByExample(null);
	}
	
	/**
	 * 添加实体对象
	 * @param t	实体对象
	 */
	public void add(T t){
		mapper.insertSelective(t);
	}
	
	/**
	 * 修改实体对象
	 * @param t	实体对象
	 */
	public void update(T t){
		mapper.updateByPrimaryKeySelective(t);
	}
	
	/**
	 * 通过主键id删除实体对象
	 * @param id	实体主键id
	 */
	public void deleteById(Serializable id){
		mapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据主键id的集合批量删除
	 * @param ids	实体主键的集合
	 */
	public void deleteBatch(Serializable[] ids){
		for (int i = 0; i < ids.length; i++) {
			mapper.deleteByPrimaryKey(ids[i]);
		}
	}
	
	private Class<?> entityClass;
	
	public BaseService(){
		init();
	}
	
	private void init(){
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();  
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		this.entityClass = (Class<?>) actualTypeArguments[0];
	}
	
	/**
	 * 逻辑删除的字段是is_del,对应的实体类属性是isDel
	 * 主键在实体类中的属性名称是id
	 * @param id
	 */
	public void deleteLogicallyById(Serializable id){
		//判断泛型中的实体类类型是否有 isDel属性
		try {
			//isDel的属性
			Field isDelField = entityClass.getDeclaredField("isDel");
			isDelField.setAccessible(true);
			@SuppressWarnings("unchecked")
			T entity = (T)entityClass.newInstance();
			//设置isDel属性的值为1
			isDelField.set(entity, 1);
			
			//主键的属性名称叫id
			Field pkField = entityClass.getDeclaredField("id");
			pkField.setAccessible(true);
			//设置主键id属性的值
			pkField.set(entity, id);
			mapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类"+entityClass.getName()+"没有属性isDel", e);
		}
	}
	
	public void deleteLogicallyBatch(Serializable[] ids){
		for (int i = 0; i < ids.length; i++) {
			deleteLogicallyById(ids[i]);
		}
	}
	
}
