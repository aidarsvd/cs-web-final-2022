package pro.aidar.alatoonews.model.entity.news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pro.aidar.alatoonews.model.entity.user.User;
import pro.aidar.alatoonews.utils.ResponseUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String thumbnail;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            }
    )
    @JoinTable(
            name = "news_likes",
            joinColumns = {
                    @JoinColumn(name = "news_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    private Collection<User> likedUsers = new ArrayList<>();

    public boolean isLiked(Long userId) {
        return likedUsers.stream().map(User::getId).anyMatch(likedUser -> likedUser.equals(userId));
    }

    @PrePersist
    private void init() {
        date = new Date();
    }

    public String getThumbnailUrl() {
        return ResponseUtils.getImageUrl(thumbnail);
    }
}
