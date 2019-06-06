package com.oldnoop.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-*.xml"})
@Rollback(false)
public class MemberAccountMapperTest {
	
	@Autowired
	private MemberAccountMapper memberAccountMapper;

	@Test
	public void testCountByExample() {
		
	}

	@Test
	public void testDeleteByExample() {
		
	}

	@Test
	public void testDeleteByPrimaryKeyLong() {
		
	}

	@Test
	public void testInsert() {
		
	}

	@Test
	public void testInsertSelectiveMemberAccount() {
		
	}

	@Test
	public void testSelectByExampleWithRowbounds() {
		
	}

	@Test
	public void testSelectByExampleMemberAccountExample() {
		
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
	public void testUpdateByPrimaryKeySelectiveMemberAccount() {
		
	}

	@Test
	public void testUpdateByPrimaryKey() {
		
	}

	@Test
	public void testFindByAccount() {
		
	}

	@Test
	public void testDeleteByAccount() {
		int deleteByPrimaryKey = memberAccountMapper.deleteByPrimaryKey(100L);
		System.out.println(deleteByPrimaryKey);
		int deleteByAccount = memberAccountMapper.deleteByAccount("^*&^*");
		System.out.println(deleteByAccount);
	}

}
