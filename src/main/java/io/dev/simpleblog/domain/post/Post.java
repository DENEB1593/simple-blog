package io.dev.simpleblog.domain.post;

import io.dev.simpleblog.domain.AuditingEntity;
import io.dev.simpleblog.domain.comment.Comment;
import io.dev.simpleblog.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post
        extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "post",
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public PostDto toDto() {
        return new PostDto(title, content, user);
    }
}
