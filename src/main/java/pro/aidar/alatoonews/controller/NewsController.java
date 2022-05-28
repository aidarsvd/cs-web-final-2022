package pro.aidar.alatoonews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pro.aidar.alatoonews.model.dto.news.NewsDto;
import pro.aidar.alatoonews.model.entity.news.News;
import pro.aidar.alatoonews.model.service.news.NewsService;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public String mainPage(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            Model model
    ) {
        NewsDto newsDto = newsService.findAll(page);
        model.addAttribute("news", newsDto.getNews());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsDto.getTotalPages());
        if (page > newsDto.getTotalPages()){
            return "not_found";
        }
        return "index";
    }

    @GetMapping("/{id}")
    public String newsDetail(@PathVariable Long id, Model model) {
        Optional<News> news = newsService.findById(id);
        if (news.isPresent()){
            model.addAttribute("news", news.get());
            return "news_detail";
        }
        return "not_found";

    }

}
