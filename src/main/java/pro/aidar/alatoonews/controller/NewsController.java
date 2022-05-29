package pro.aidar.alatoonews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.aidar.alatoonews.model.dto.news.CommentDto;
import pro.aidar.alatoonews.model.dto.news.NewsDto;
import pro.aidar.alatoonews.model.entity.news.News;
import pro.aidar.alatoonews.model.entity.user.User;
import pro.aidar.alatoonews.model.service.news.NewsService;
import pro.aidar.alatoonews.model.service.user.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final UserService userService;

    @GetMapping
    public String mainPage(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            Model model
    ) {
        NewsDto newsDto = newsService.findAll(page);
        model.addAttribute("news", newsDto.getNews());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsDto.getTotalPages());
        if (page > newsDto.getTotalPages()) {
            return "not_found";
        }
        return "index";
    }

    @GetMapping("/{id}")
    public String newsDetail(@PathVariable Long id, Model model) {
        Optional<News> news = newsService.findById(id);
        if (news.isPresent()) {
            model.addAttribute("commentDto", new CommentDto());
            model.addAttribute("news", news.get());
            return "news_detail";
        }
        return "not_found";
    }

    @PostMapping("/comment")
    public String postComment(
            @RequestParam Long news_id,
            @Valid @ModelAttribute("item") CommentDto commentDto,
            BindingResult bindingResult,
            Principal principal
    ) {
        if (bindingResult.hasErrors()){
            return "redirect:/" + news_id + "?error=true";
        }
        User user = userService.findBuUsername(principal.getName());
        newsService.addComment(news_id, user, commentDto.getComment());
        return "redirect:/" + news_id;
    }

}
