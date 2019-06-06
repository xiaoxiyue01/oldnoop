package com.oldnoop.mapper;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 泛型Mapper
 * 业务Mapper继承BaseMapper
 * </pre>
 * @author oldnoop
 *
 * @param <T>	实体类型
 */
public interface BaseMapper<T>{

	List<T> selectByExample(Object example);
	T selectByPrimaryKey(Serializable id);
    int insertSelective(T record);
    int updateByPrimaryKeySelective(T record);
    int deleteByPrimaryKey(Serializable id);
    
}
