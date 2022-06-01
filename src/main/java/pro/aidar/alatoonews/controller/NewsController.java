package pro.aidar.alatoonews.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.aidar.alatoonews.model.dto.news.CommentDto;
import pro.aidar.alatoonews.model.dto.news.NewsDto;
import pro.aidar.alatoonews.model.entity.news.News;
import pro.aidar.alatoonews.model.entity.user.User;
import pro.aidar.alatoonews.model.service.news.CommentService;
import pro.aidar.alatoonews.model.service.news.NewsService;
import pro.aidar.alatoonews.model.service.user.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@Slf4j
public class NewsController {

    private final NewsService newsService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping
    public String mainPage(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            Model model,
            Principal principal
    ) {
        NewsDto newsDto = newsService.findAll(page);
        model.addAttribute("news", newsDto.getNews());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsDto.getTotalPages());
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
            model.addAttribute("userId", user.getId());
        }
        if (page != 1 && page > newsDto.getTotalPages()) {
            return "not_found";
        }
        return "index";
    }

    @GetMapping("/{id}")
    public String newsDetail(@PathVariable Long id, Model model, Principal principal) {
        Optional<News> news = newsService.findById(id);
        if (news.isPresent()) {
            model.addAttribute("commentDto", new CommentDto());
            model.addAttribute("news", news.get());
            if (principal != null) {
                User user = userService.findByUsername(principal.getName());
                model.addAttribute("user", user);
            }
            return "news_detail";
        }
        return "not_found";
    }

    @PostMapping("/like/{id}")
    public String like(
            @PathVariable Long id,
            Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        Optional<News> news = newsService.findById(id);
        if (news.isPresent()) {
            if (news.get().isLiked(user.getId())) {
                news.get().getLikedUsers().remove(user);
            } else {
                news.get().getLikedUsers().add(user);
            }
            newsService.update(news.get());
        }
        return "redirect:/";
    }

    @PostMapping("/comment")
    public String postComment(
            @RequestParam Long news_id,
            @Valid @ModelAttribute("item") CommentDto commentDto,
            BindingResult bindingResult,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/" + news_id + "?error=true";
        }
        User user = userService.findByUsername(principal.getName());
        newsService.addComment(news_id, user, commentDto.getComment());
        return "redirect:/" + news_id;
    }

    @DeleteMapping("/comment/{id}")
    public String deleteComment(
            @RequestParam Long news_id,
            @PathVariable Long id,
            Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        if (user != null) {
            commentService.getById(id).ifPresent(value -> {
                if (value.getAuthor().getId().equals(user.getId())) {
                    commentService.deleteById(id);
                }
            });
        }
        return "redirect:/" + news_id;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
