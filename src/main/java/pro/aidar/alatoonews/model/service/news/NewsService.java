package pro.aidar.alatoonews.model.service.news;

import pro.aidar.alatoonews.model.dto.news.NewsDto;
import pro.aidar.alatoonews.model.entity.news.News;

import java.util.Optional;

public interface NewsService {

    NewsDto findAll(int page);

    Optional<News> findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);

}
