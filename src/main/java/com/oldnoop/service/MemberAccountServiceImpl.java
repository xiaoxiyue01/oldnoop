package com.oldnoop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oldnoop.entity.MemberAccount;
import com.oldnoop.mapper.MemberAccountMapper;
@Service
@Transactional(rollbackFor=Exception.class)
public class MemberAccountServiceImpl extends BaseService<MemberAccount> {
	
	@Autowired
	private MemberAccountMapper memberAccountMapper;

	@Override
	@Autowired
	public void setBaseMapper() {
		super.setMapper(memberAccountMapper);
		
	}
	
	//查询账号是否存在
	public MemberAccount findByAccount(MemberAccount ma){
		return memberAccountMapper.findByAccount(ma.getAccount());
	}

}
