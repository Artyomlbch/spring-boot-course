package by.artyom.spring.http.controller;

import by.artyom.spring.dto.LoginDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class LoginController {

    @PostConstruct
    public void init() {
        log.info("LoginController init");
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("login") LoginDto loginDto) {
        System.out.println(loginDto);
        return "redirect:/login";
    }

}
