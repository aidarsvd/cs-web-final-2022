package pro.aidar.alatoonews.model.service.news.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.aidar.alatoonews.model.dto.news.NewsDto;
import pro.aidar.alatoonews.model.entity.news.Comment;
import pro.aidar.alatoonews.model.entity.news.News;
import pro.aidar.alatoonews.model.entity.user.User;
import pro.aidar.alatoonews.model.repository.news.NewsRepository;
import pro.aidar.alatoonews.model.service.news.NewsService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public NewsDto findAll(int page) {
        NewsDto newsDto = new NewsDto();
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<News> newsPaged = newsRepository.findAll(pageable);
        newsDto.setPage(page);
        newsDto.setTotalPages(newsPaged.getTotalPages());
        newsDto.setNews(newsPaged.getContent());
        return newsDto;
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void addComment(Long newsId, User user, String comment) {
        Optional<News> news = findById(newsId);
        news.ifPresent(value -> {
            value.getComments().add(
                    Comment.builder()
                            .comment(comment)
                            .author(user)
                            .build()
            );
            save(value);
        });
    }
}
