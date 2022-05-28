package pro.aidar.alatoonews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.aidar.alatoonews.model.dto.news.NewsDto;
import pro.aidar.alatoonews.model.service.news.NewsService;


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
        return "index";
    }
}
