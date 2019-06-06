package com.oldnoop.mapper;

import com.oldnoop.entity.MemberAccount;
import com.oldnoop.entity.MemberAccountExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MemberAccountMapper extends BaseMapper<MemberAccount>{
    long countByExample(MemberAccountExample example);

    int deleteByExample(MemberAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberAccount record);

    int insertSelective(MemberAccount record);

    List<MemberAccount> selectByExampleWithRowbounds(MemberAccountExample example, RowBounds rowBounds);

    List<MemberAccount> selectByExample(MemberAccountExample example);

    MemberAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberAccount record, @Param("example") MemberAccountExample example);

    int updateByExample(@Param("record") MemberAccount record, @Param("example") MemberAccountExample example);

    int updateByPrimaryKeySelective(MemberAccount record);

    int updateByPrimaryKey(MemberAccount record);
    
    MemberAccount findByAccount(String account);
    
    int deleteByAccount(String account);
    
    int updatePwdByMid(@Param("password") String password, @Param("mid") Long mid);
    
}