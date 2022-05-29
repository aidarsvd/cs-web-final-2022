package pro.aidar.alatoonews.model.entity.news;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pro.aidar.alatoonews.utils.ResponseUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String title;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String content;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date date;

    private String thumbnail;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    private void init() {
        date = new Date();
    }

    public String getThumbnailUrl() {
        return ResponseUtils.getImageUrl(thumbnail);
    }
}
