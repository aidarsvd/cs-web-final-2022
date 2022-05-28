package pro.aidar.alatoonews.model.service.news;

import pro.aidar.alatoonews.model.dto.news.NewsDto;
import pro.aidar.alatoonews.model.entity.news.News;
import pro.aidar.alatoonews.model.entity.user.User;

import java.util.Optional;

public interface NewsService {

    NewsDto findAll(int page);

    Optional<News> findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);

    void addComment(Long newsId, User user, String comment);

}
