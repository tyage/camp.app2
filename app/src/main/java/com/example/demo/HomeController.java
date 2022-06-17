package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
  @Autowired
  HttpSession session;

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @PostMapping("/login")
  public String login(
      @RequestParam("username") String username,
      @RequestParam("password") String password) {
    if (username.equals("admin") &&
        password.equals("supersecretpasswordyuruseccamp2022")) {
      session.setAttribute("user", "admin");
      return "redirect:/admin";
    } else {
      return "redirect:/";
    }
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    String user = (String) session.getAttribute("user");
    if (user == null || !user.equals("admin")) {
      return "redirect:/";
    }
    model.addAttribute("user", user);
    return "admin";
  }
}
