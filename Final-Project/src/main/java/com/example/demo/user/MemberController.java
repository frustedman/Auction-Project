package com.example.demo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	@GetMapping("/join")
	public String joinForm() {
		return "member/join";
	}
	
	@PostMapping("/join")
	public String join(MemberDto u) {
		service.save(u);
		return "redirect:/";
	}
	
	@GetMapping("/loginform")
	public String loginForm() {
		return "member/login";
	}

	@GetMapping("/auth/login")
	public void alogin() {

	}

	// 관리자가 로그인 후 이동할 경로
	@RequestMapping("/index_admin")
	public void adminHome() {
	}

	// 회원이 로그인 후 이동할 경로
	@RequestMapping("/index_member")
	public void memberHome() {
	}

	// 시큐리티에서 해줘서 필요없음 이거
//	@PostMapping("/login")
//	public String login(UserDto u, HttpSession session) {
//		String msg = "로그인 실패";
//		UserDto u2 = service.getUser(u.getId());
//		if (u2 != null && u2.getPwd().equals(u.getPwd())) {
//			session.setAttribute("loginId", u2.getId());
//			session.setAttribute("type", u2.getType());
//			msg = "로그인 성공";
//		}
//		session.setAttribute("msg", msg);
//		return "index";
//	}
	
	@RequestMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/auth/out")
	public String out(String id) {
		service.delMember(id);
		return "redirect:/logout";
	}

	@PostMapping("/edit")
	public String edit(MemberDto u) {
		MemberDto d = service.getUser(u.getId());
		d.setPwd(u.getPwd());
		service.save(d);
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping("/idcheck")
	public Map idcheck(String id) {
		Map map = new HashMap();
		MemberDto u = service.getUser(id);
		boolean flag = false;
		if (u == null) {
			flag = true;
		}
		map.put("flag", flag);
		return map;
	}
	

}
