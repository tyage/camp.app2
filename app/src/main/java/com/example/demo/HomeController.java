package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HomeController {
  @GetMapping("/{username}")
  public String user(@PathVariable String username) {
    return username + "さんこんにちは！ お知らせは特にありません。";
  }

  @GetMapping("/admin")
  public String admin() {
    return "管理者向けのお知らせ: Flag{BlahBlahBlah}";
  }
}
