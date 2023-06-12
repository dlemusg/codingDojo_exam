package com.example.LoginRegister.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.LoginRegister.models.LoginUser;
import com.example.LoginRegister.models.User;
import com.example.LoginRegister.services.AppService;

@Controller
public class UserController {
	
	@Autowired
	private AppService service;
	
	@GetMapping("/")
	public String index(@ModelAttribute("nuevoUsuario") User nuevoUsuario,
			@ModelAttribute("nuevoLogin") LoginUser nuevoLogin) {
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("nuevoUsuario") User nuevoUsuario,
			BindingResult result,
			Model model, 
			HttpSession session) {
		
		service.register(nuevoUsuario, result);
		if(result.hasErrors()) {
			model.addAttribute("nuevoLogin", new LoginUser());
			return "index.jsp";
		}else {
			session.setAttribute("userSession", nuevoUsuario);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
		User currentUser = (User)session.getAttribute("userSession");
		if(currentUser == null) {
			return "redirect:/";
		}
		
		return "dashboard.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("nuevoLogin") LoginUser nuevoLogin,
			BindingResult result, 
			Model model, 
			HttpSession session) {
		
		User user = service.login(nuevoLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("nuevoUsuario", new User());
			return "index.jsp";
		}
		
		session.setAttribute("userSession", user);
		return "redirect:/dashboard";
		
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userSession");
		return "redirect:/";
	}
	
	

}
