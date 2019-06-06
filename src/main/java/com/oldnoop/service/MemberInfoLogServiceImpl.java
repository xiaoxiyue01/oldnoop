package com.oldnoop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oldnoop.entity.MemberInfoLog;
import com.oldnoop.entity.MemberInfoLogExample;
import com.oldnoop.mapper.MemberInfoLogMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberInfoLogServiceImpl extends BaseService<MemberInfoLog> {

	@Autowired
	private MemberInfoLogMapper infoLogMapper;

	@Override
	@Autowired
	public void setBaseMapper() {
		super.setMapper(infoLogMapper);

	}

	public List<MemberInfoLog> findByMid(Long mid) {
		MemberInfoLogExample example = new MemberInfoLogExample();
		example.createCriteria().andMidEqualTo(mid);
		example.setOrderByClause("id desc");
		List<MemberInfoLog> list = infoLogMapper.selectByExample(example);
		return list;
	}

}
