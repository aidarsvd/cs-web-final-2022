package pro.aidar.alatoonews.model.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.aidar.alatoonews.model.entity.news.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
