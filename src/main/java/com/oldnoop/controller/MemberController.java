package com.oldnoop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oldnoop.entity.Member;
import com.oldnoop.entity.MemberAccount;
import com.oldnoop.entity.MemberInfoLog;
import com.oldnoop.entity.MemberLoginLog;
import com.oldnoop.service.MemberInfoLogServiceImpl;
import com.oldnoop.service.MemberLoginLogServiceImpl;
import com.oldnoop.service.MemberServiceImpl;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberServiceImpl memberService;
	@Autowired
	private MemberLoginLogServiceImpl loginLogService;
	@Autowired
	private MemberInfoLogServiceImpl infoLogService;

	/**
	 * 跳转到注册页面
	 * 
	 * @return
	 */
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "member/register";
	}

	/**
	 * 注册，将用户名及密码添加到数据库
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public String register(String username, String password,String passwordConfirm) {
		// 判断2次密码是否一致
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(passwordConfirm)||
				!password.equalsIgnoreCase(passwordConfirm)) {
			return "01";// 01表示两次输入的密码不一致
		}
		String msg = memberService.register(username, password);
		return msg;
	}

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "member/login";
	}

	/**
	 * 登录
	 * 
	 * @param ma
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(MemberAccount ma, HttpSession session) {
		Member memberDB = memberService.login(ma);
		if (memberDB == null) {
			return "0";// 登录失败，用户名或密码不正确
		}

		// 将用户(数据库查到的)放进session中
		session.setAttribute("member", memberDB);

		// 判断信息是否完善
		if (memberDB.getEmail() != null && memberDB.getPhone() != null) {
			return "2";// 2表示登录成功且信息完善,不再跳转至完善信息页面，跳转至列表页面
		}
		return "1";// 1表示登录成功但信息不完善，跳转至完善信息页面
	}

	/**
	 * 跳转到完善账户（手机，邮箱）信息页面
	 * 
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(HttpSession session, Model model) {
		Member m = (Member) session.getAttribute("member");
		if(m==null){
			return "redirect:/member/toLogin";
		}
		Member member = memberService.findById(m.getId());
		model.addAttribute("member", member);
		return "member/edit";
	}

	/**
	 * 完善或修改账户信息(手机邮箱)
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(Member member,HttpSession session) {
		//判断当前是否登录
		Member m = (Member)session.getAttribute("member");
		if(m==null){
			return "04";
		}
		// update member表和insert into member_account表
		String msg = memberService.edit(member);
		//如果修改成功,修改session中的Member数据m,
		//修改为新的email和phone
		if("1".equals(msg)){
			m.setEmail(member.getEmail());
			m.setPhone(member.getPhone());
		}
		return msg;
	}
	
	/**
	 * 跳转到修改密码的页面
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/toEditPwd")
	public String toEditPwd(HttpSession session, Model model) {
		Member m = (Member) session.getAttribute("member");
		if(m==null){
			return "redirect:/member/toLogin";
		}
		Member member = memberService.findById(m.getId());
		model.addAttribute("member", member);
		return "member/edit_pwd";
	}
	
	/**
	 * 修改密码
	 * @param member
	 * @return
	 */
	@RequestMapping("/editPwd")
	@ResponseBody
	public String editPwd(String passwordOld,String password,String passwordConfirm,HttpSession session) {
		//判断当前是否登录
		Member member = (Member)session.getAttribute("member");
		if(member==null){
			return "04";
		}
		if(StringUtils.isBlank(passwordOld) ||StringUtils.isBlank(password)
				||StringUtils.isBlank(passwordConfirm)){
			return "01";
		}
		//判断新密码和确认密码是否相同
		if(!password.equalsIgnoreCase(passwordConfirm)){
			return "02";
		}
		//判断新密码是否和原密码相同
		if(password.equals(passwordOld)){
			return "03";
		}
		//判断原密码是否正确
		// update member表和insert into member_account表
		String msg = memberService.editPwd(member, password,passwordOld);
		return msg;
	}

	/**
	 * 查看日志
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/logs")
	public String logs(HttpSession session,Model model) {
		Member m = (Member) session.getAttribute("member");
		if(m==null){
			return "redirect:/member/toLogin";
		}
		Long mid = m.getId();
		List<MemberLoginLog> logs = loginLogService.findByMid(mid);
		model.addAttribute("logs", logs);
		List<MemberInfoLog> infoLogs = infoLogService.findByMid(mid);
		model.addAttribute("infoLogs", infoLogs);
		return "member/logs";
	}

	/**
	 * 退出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("member");
		return "redirect:/member/toLogin";
	}

}
