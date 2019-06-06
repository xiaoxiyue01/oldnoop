package com.oldnoop.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-*.xml"})
@Rollback(false)
public class MemberMapperTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void testCountByExample() {
		long count = memberMapper.countByExample(null);
		System.out.println(count);
	}

	@Test
	public void testDeleteByExample() {
		
	}

	@Test
	public void testDeleteByPrimaryKeyLong() {
		int deleteByPrimaryKey = memberMapper.deleteByPrimaryKey(100L);
		System.out.println(deleteByPrimaryKey);
	}

	@Test
	public void testInsert() {
		
	}

	@Test
	public void testInsertSelectiveMember() {
		
	}

	@Test
	public void testSelectByExampleWithRowbounds() {
		
	}

	@Test
	public void testSelectByExampleMemberExample() {
		
	}

	@Test
	public void testSelectByPrimaryKeyLong() {
		
	}

	@Test
	public void testUpdateByExampleSelective() {
		
	}

	@Test
	public void testUpdateByExample() {
		
	}

	@Test
	public void testUpdateByPrimaryKeySelectiveMember() {
		
	}

	@Test
	public void testUpdateByPrimaryKey() {
		
	}
	

}
