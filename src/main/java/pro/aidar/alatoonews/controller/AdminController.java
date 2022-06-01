package pro.aidar.alatoonews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.aidar.alatoonews.model.entity.news.News;
import pro.aidar.alatoonews.model.service.files.FileService;
import pro.aidar.alatoonews.model.service.news.NewsService;
import pro.aidar.alatoonews.model.service.user.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final NewsService newsService;
    private final FileService fileService;
    private final UserService userService;

    @GetMapping
    public String getCreate(
            Principal principal,
            Model model
    ) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "add_news";
    }

    @GetMapping("/edit/{id}")
    public String editNews(
            @PathVariable Long id,
            Model model,
            Principal principal
    ) {
        Optional<News> byId = newsService.findById(id);
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        if (byId.isPresent()) {
            model.addAttribute("news", byId.get());
            return "edit_news";
        } else {
            return "not_found";
        }
    }

    @PostMapping("/create_news")
    public String create(
            @RequestParam MultipartFile thumbnail,
            @RequestParam String title,
            @RequestParam String content
    ) {
        News news = News.builder()
                .title(title)
                .content(content)
                .build();
        if (!thumbnail.isEmpty()) {
            String image = fileService.save(thumbnail);
            news.setThumbnail(image);
        }
        newsService.save(news);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            @RequestParam MultipartFile thumbnail,
            @RequestParam String title,
            @RequestParam String content
    ) {
        News news = News.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        if (!thumbnail.isEmpty()) {
            String image = fileService.save(thumbnail);
            news.setThumbnail(image);
        }
        newsService.update(news);
        return "redirect:/";
    }

    @DeleteMapping("/delete_news/{id}")
    public String deleteNews(@PathVariable Long id) {
        newsService.findById(id).ifPresent(news -> {
            if (news.getThumbnail() != null) {
                fileService.delete(news.getThumbnail());
            }
            newsService.deleteById(id);
        });
        return "redirect:/";
    }

}
