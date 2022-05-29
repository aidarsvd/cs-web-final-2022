package pro.aidar.alatoonews.model.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.aidar.alatoonews.model.entity.news.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
