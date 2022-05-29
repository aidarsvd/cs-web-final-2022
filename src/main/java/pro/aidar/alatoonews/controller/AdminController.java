package pro.aidar.alatoonews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.aidar.alatoonews.model.entity.news.News;
import pro.aidar.alatoonews.model.service.files.FileService;
import pro.aidar.alatoonews.model.service.news.NewsService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final NewsService newsService;
    private final FileService fileService;

    @GetMapping
    public String getCreate() {
        return "add_news";
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
