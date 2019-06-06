package com.oldnoop.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oldnoop.entity.Member;
import com.oldnoop.entity.MemberAccount;
import com.oldnoop.entity.MemberInfoLog;
import com.oldnoop.entity.MemberLoginLog;
import com.oldnoop.mapper.MemberAccountMapper;
import com.oldnoop.mapper.MemberInfoLogMapper;
import com.oldnoop.mapper.MemberLoginLogMapper;
import com.oldnoop.mapper.MemberMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl extends BaseService<Member> {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberAccountMapper memberAccountMapper;
	@Autowired
	private MemberLoginLogMapper loginMapper;
	@Autowired
	private MemberInfoLogMapper infoMapper;

	@Override
	@Autowired
	public void setBaseMapper() {
		super.setMapper(memberMapper);
	}

	/**
	 * 注册
	 * 
	 * @param member
	 * @return
	 */
	public String register(String username, String password) {
		// 判断用户名是否存在
		MemberAccount maDB = memberAccountMapper.findByAccount(username);
		if (maDB != null) {
			return "02";// 02表示用户名已存在
		}
		// 将用户名及密码添加到数据库member表
		Member member = new Member();
		member.setUsername(username);
		member.setCreateTime(new Date());
		memberMapper.insertSelective(member);
		// 将用户名及id添加到数据库member_account表
		Long id = member.getId();
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setMid(id);
		memberAccount.setAccount(username);
		memberAccount.setPassword(password);
		memberAccount.setCreateTime(new Date());
		memberAccountMapper.insertSelective(memberAccount);

		return "1";// 注册成功
	}

	/**
	 * 登录
	 * 
	 * @param ma
	 * @param password
	 * @return
	 */
	public Member login(MemberAccount ma) {
		// 判断账户名是否为空
		if (ma == null || StringUtils.isBlank(ma.getAccount()) || StringUtils.isBlank(ma.getPassword())) {
			return null;
		}
		// 判断账户是否存在
		MemberAccount maDB = memberAccountMapper.findByAccount(ma.getAccount());
		if (maDB == null) {
			return null;
		}
		// 记录登录日志通用数据
		MemberLoginLog log = new MemberLoginLog();
		log.setMid(maDB.getMid());
		log.setAccount(ma.getAccount());
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = req.getRemoteAddr();
		log.setLoginIp(ip);
		// String ip = "111.193.131.8";
		// String zone = "北京市";
		// log.setLoginZone(zone);
		log.setLoginTime(new Date());

		// 判断密码是否正确
		if (!ma.getPassword().equalsIgnoreCase(maDB.getPassword())) {
			// 记录登录失败状态日志
			log.setStatus(0);
			loginMapper.insertSelective(log);
			return null;
		}

		// 登录成功
		// 记录登录日志
		log.setStatus(1);
		loginMapper.insertSelective(log);

		Member memberDB = memberMapper.selectByPrimaryKey(maDB.getMid());

		return memberDB;

	}

	/**
	 * 完善或修改账号信息(电话，邮箱)
	 * 
	 * @param member
	 * @return
	 */
	public String edit(Member member) {

		// 判断手机邮箱是否为空
		String email = member.getEmail();
		String phone = member.getPhone();
		if (StringUtils.isBlank(email) || StringUtils.isBlank(phone)) {
			return "03";
		}

		// 判断是否是 已经存在的邮箱或手机号
		// 如果邮箱或手机号 是当前用户的,那就是相当于不修改,拒绝
		// 如果邮箱或手机号 是其他用户已注册的,那也是不允许的,拒绝
		// 再判断输入的邮箱是否存在
		MemberAccount maExist = null;
		maExist = memberAccountMapper.findByAccount(email);
		if (maExist != null) {
			return "01";
		}
		// 再判断输入的手机是否存在
		maExist = memberAccountMapper.findByAccount(phone);
		if (maExist != null) {
			return "02";
		}

		// 先删除member_account表原有的账号信息,包括邮件和电话的账号
		Member memberDB = memberMapper.selectByPrimaryKey(member.getId());
		if (memberDB.getEmail() != null) {
			memberAccountMapper.deleteByAccount(memberDB.getEmail());
		}
		if (memberDB.getPhone() != null) {
			memberAccountMapper.deleteByAccount(memberDB.getPhone());
		}

		// 修改member表的phone及email字段
		member.setModifyTime(new Date());
		memberMapper.updateByPrimaryKeySelective(member);
		// 添加phone和email到member_account表
		Long mid = member.getId();
		MemberAccount ma1 = new MemberAccount();
		ma1.setMid(mid);
		ma1.setAccount(member.getPhone());
		ma1.setCreateTime(new Date());
		memberAccountMapper.insertSelective(ma1);
		MemberAccount ma2 = new MemberAccount();
		ma2.setMid(mid);
		ma2.setAccount(member.getEmail());
		ma2.setCreateTime(new Date());
		memberAccountMapper.insertSelective(ma2);

		// 记录修改日志
		MemberInfoLog memberInfoLog = new MemberInfoLog();
		memberInfoLog.setMid(member.getId());
		memberInfoLog.setPhone(member.getPhone());
		memberInfoLog.setEmail(member.getEmail());
		memberInfoLog.setModifyTime(new Date());
		infoMapper.insertSelective(memberInfoLog);

		return "1";
	}

	/**
	 * 修改密码
	 * 
	 * @param member
	 * @param password
	 *            新密码
	 * @param passwordOld
	 *            原密码
	 * @return
	 */
	public String editPwd(Member member, String password, String passwordOld) {
		MemberAccount ma = memberAccountMapper.findByAccount(member.getUsername());
		if (!passwordOld.equals(ma.getPassword())) {
			return "05";
		}
		// 修改密码
		memberAccountMapper.updatePwdByMid(password, member.getId());
		// 记录修改日志
		MemberInfoLog memberInfoLog = new MemberInfoLog();
		memberInfoLog.setMid(member.getId());
		memberInfoLog.setPassword(password);
		memberInfoLog.setModifyTime(new Date());
		infoMapper.insertSelective(memberInfoLog);
		return "1";
	}

}
