package com.mysite.sbb.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String join(UserCreateForm userCreateForm) {
        return "join_form";
    }

    @PostMapping("/join")
    public String join(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "join_form";

        if (!userCreateForm.getPassword().equals(userCreateForm.getPasswordCheck())) {
            bindingResult.rejectValue(
                    "passwordCheck",
                    "passwordIncorrect",
                    "2개의 비밀번호가 일치하지 않습니다."
            );
            return "join_form";
        }

        userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword());
        return "redirect:/";
    }
}

