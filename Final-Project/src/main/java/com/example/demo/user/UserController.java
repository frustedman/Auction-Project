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
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping("/join")
	public void joinForm() {
	}
	
	@PostMapping("/join")
	public String join(UserDto u) {
		service.save(u);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public void loginForm() {
	}
	
	@PostMapping("/login")
	public String login(UserDto u, HttpSession session) {
		String msg = "로그인 실패";
		UserDto u2 = service.getUser(u.getId());
		if (u2 != null && u2.getPwd().equals(u.getPwd())) {
			session.setAttribute("loginId", u2.getId());
			session.setAttribute("type", u2.getType());
			msg = "로그인 성공";
		}
		session.setAttribute("msg", msg);
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	@RequestMapping("/out")
	public String out(String id) {
		service.delMember(id);
		return "redirect:/user/logout";
	}
	
	@PostMapping("/edit")
	public String edit(UserDto u) {
		UserDto d = service.getUser(u.getId());
		d.setPwd(u.getPwd());
		service.save(d);
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping("/idcheck")
	public Map idcheck(String id) {
		Map map = new HashMap();
		UserDto u = service.getUser(id);
		boolean flag = false;
		if (u == null) {
			flag = true;
		}
		map.put("flag", flag);
		return map;
	}
	

}
