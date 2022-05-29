package pro.aidar.alatoonews.model.dto.news;

import lombok.Data;
import pro.aidar.alatoonews.model.entity.news.News;

import java.util.List;

@Data
public class NewsDto {
    int totalPages;
    int page;
    List<News> news;
}
