package by.artyom.spring.http.controller;

import by.artyom.spring.database.entity.Role;
import by.artyom.spring.dto.UserCreateEditDto;
import by.artyom.spring.dto.UserReadDto;
import by.artyom.spring.service.CompanyService;
import by.artyom.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id:\\d+}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies", companyService.findAll());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto userCreateEditDto) {
        model.addAttribute("user", userCreateEditDto);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());

        return "user/registration";
    }

    @PostMapping
    public String create(@ModelAttribute UserCreateEditDto user, RedirectAttributes redirectAttributes) {
        if (true) {


            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/users/registration";
        }
        UserReadDto newUser = userService.create(user);
        return "redirect:/users/" + newUser.getId();
    }

//    @PutMapping("/{id}")
    @PostMapping("/{id:\\d+}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute UserCreateEditDto user) {
        return userService.update(id, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }
}
