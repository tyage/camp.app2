package seccamp.app2;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
  @Autowired
  HttpSession session;

  @Value("${app.admin.username}")
  private String adminUsername;

  @Value("${app.admin.password}")
  private String adminPassword;

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @PostMapping("/login")
  public String login(
      @RequestParam("username") String username,
      @RequestParam("password") String password) {
    if (username.equals(adminUsername) &&
        password.equals(adminPassword)) {
      // adminとしてログイン
      session.setAttribute("user", adminUsername);
      return "redirect:/admin";
    } else {
      return "redirect:/";
    }
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    String user = (String) session.getAttribute("user");

    // admin以外のユーザはアクセス禁止
    if (user == null || !user.equals(adminUsername)) {
      return "redirect:/";
    }

    return "admin";
  }
}
