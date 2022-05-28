package pro.aidar.alatoonews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pro.aidar.alatoonews.model.dto.user.UserDto;
import pro.aidar.alatoonews.model.service.user.UserService;
import pro.aidar.alatoonews.utils.ValidateUtils;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/sign_up")
    public String signUpPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign_up";
        }
        if (userService.isUsernameExist(userDto.getUsername())) {
            bindingResult.rejectValue("username", "", userDto.getUsername() + " already exists");
            return "sign_up";
        }
        if (userService.isEmailExist(userDto.getEmail())) {
            bindingResult.rejectValue("email", "", userDto.getEmail() + " already exists");
            return "sign_up";
        }
        if (!ValidateUtils.isPasswordValid(userDto.getPassword())) {
            bindingResult.rejectValue("password", "", "Password is too weak");
            return "sign_up";
        }
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            bindingResult.rejectValue("repeatPassword", "", "Passwords don't match");
            return "sign_up";
        }
        userService.saveUser(userDto);
        return "redirect:/login";
    }

}