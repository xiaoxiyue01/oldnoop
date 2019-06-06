package com.oldnoop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oldnoop.entity.MemberLoginLog;
import com.oldnoop.entity.MemberLoginLogExample;
import com.oldnoop.mapper.MemberLoginLogMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberLoginLogServiceImpl extends BaseService<MemberLoginLog> {

	@Autowired
	private MemberLoginLogMapper loginLogMapper;

	@Override
	@Autowired
	public void setBaseMapper() {
		super.setMapper(loginLogMapper);

	}

	public List<MemberLoginLog> findByMid(Long mid) {
		MemberLoginLogExample example = new MemberLoginLogExample();
		example.createCriteria().andMidEqualTo(mid);
		example.setOrderByClause("id desc");
		List<MemberLoginLog> list = loginLogMapper.selectByExample(example);
		return list;
	}

}
