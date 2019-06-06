package com.oldnoop.service;

import com.oldnoop.entity.Member;
import com.oldnoop.entity.MemberAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-*.xml"})
@WebAppConfiguration
public class MemberServiceImplTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void register() {
    }

    @Test
    public void login() {
        MemberAccount ma = new MemberAccount();
        ma.setAccount("oldnoop");
        ma.setPassword("oldnoop");
        Member memberDB = memberService.login(ma);
        System.out.println(memberDB);
    }

    @Test
    public void edit() {
    }

    @Test
    public void editPwd() {
    }
}