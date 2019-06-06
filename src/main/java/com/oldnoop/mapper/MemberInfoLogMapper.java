package com.oldnoop.mapper;

import com.oldnoop.entity.MemberInfoLog;
import com.oldnoop.entity.MemberInfoLogExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MemberInfoLogMapper extends BaseMapper<MemberInfoLog> {
	long countByExample(MemberInfoLogExample example);

	int deleteByExample(MemberInfoLogExample example);

	int deleteByPrimaryKey(Long id);

	int insert(MemberInfoLog record);

	int insertSelective(MemberInfoLog record);

	List<MemberInfoLog> selectByExampleWithRowbounds(MemberInfoLogExample example, RowBounds rowBounds);

	List<MemberInfoLog> selectByExample(MemberInfoLogExample example);

	MemberInfoLog selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") MemberInfoLog record, @Param("example") MemberInfoLogExample example);

	int updateByExample(@Param("record") MemberInfoLog record, @Param("example") MemberInfoLogExample example);

	int updateByPrimaryKeySelective(MemberInfoLog record);

	int updateByPrimaryKey(MemberInfoLog record);

}