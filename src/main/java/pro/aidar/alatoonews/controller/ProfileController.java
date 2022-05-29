package pro.aidar.alatoonews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pro.aidar.alatoonews.model.entity.user.User;
import pro.aidar.alatoonews.model.service.user.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile/{id}")
    public String me(@PathVariable Long id, Principal principal, Model model) {
        Optional<User> user = userService.findById(id);
        Long myId = 0L;
        if (principal == null) {
            model.addAttribute("is_my_page", false);
        } else {
            myId = userService.findBuUsername(principal.getName()).getId();
        }
        if (user.isPresent()) {
            model.addAttribute("is_my_page", myId.equals(user.get().getId()));
            model.addAttribute("user", user.get());
            model.addAttribute("stub", user.get().getName().charAt(0));
            return "my_page";
        } else {
            return "not_found";
        }
    }

}
