package pro.aidar.alatoonews.model.service.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pro.aidar.alatoonews.model.dto.news.NewsDto;
import pro.aidar.alatoonews.model.entity.news.News;

public interface NewsService {

    NewsDto findAll(int page);

    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);

}
