package pro.aidar.alatoonews.model.service.news;

import pro.aidar.alatoonews.model.entity.news.Comment;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> getById(Long id);
    void deleteById(Long id);
}
