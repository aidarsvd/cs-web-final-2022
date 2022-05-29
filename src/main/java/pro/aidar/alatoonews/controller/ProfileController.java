package pro.aidar.alatoonews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pro.aidar.alatoonews.model.entity.user.User;
import pro.aidar.alatoonews.model.service.files.FileService;
import pro.aidar.alatoonews.model.service.user.UserService;

import java.net.MalformedURLException;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final FileService fileService;

    @GetMapping("/profile/{id}")
    public String me(@PathVariable Long id, Principal principal, Model model) {
        Optional<User> user = userService.findById(id);
        Long myId = 0L;
        if (principal == null) {
            model.addAttribute("is_my_page", false);
        } else {
            myId = userService.findByUsername(principal.getName()).getId();
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

    @PostMapping("/profile/{id}")
    public String updatePhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws MalformedURLException {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            if (file.isEmpty()){
                return "redirect:/profile/" + id + "?error=true";
            }
            if (user.get().getAvatar() != null){
                fileService.delete(user.get().getAvatar());
            }
            String image = fileService.save(file);
            user.get().setAvatar(image);
            userService.updateUser(user.get());
        } else {
            return "not_found";
        }
        return "redirect:/profile/" + id;
    }


}
