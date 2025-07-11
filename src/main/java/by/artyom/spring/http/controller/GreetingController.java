package by.artyom.spring.http.controller;

import by.artyom.spring.database.entity.Role;
import by.artyom.spring.database.repository.CompanyRepository;
import by.artyom.spring.dto.UserReadDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/api/v1/")
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello(ModelAndView mv,
                              CompanyRepository companyRepository,
                              HttpServletRequest request,
                              @RequestParam(value = "age", required = false) Integer age,
                              @RequestHeader("accept") String accept,
                              @CookieValue("JSESSIONID") String sessionId,
                              @PathVariable("id") Integer id) {

        mv.setViewName("greeting/hello");
        mv.addObject("user", new UserReadDto(1L, "Andrei"));

        return mv;
    }

    @GetMapping("bye")
    public ModelAndView bye(ModelAndView mv,
                            @SessionAttribute("user") UserReadDto userReadDto) {
        mv.setViewName("greeting/bye");


        return mv;
    }


}
