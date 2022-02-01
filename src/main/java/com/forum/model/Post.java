package com.forum.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Thread thread;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private User author;

    @NonNull
    private LocalDateTime creationDate;

    @OneToOne
    private Warn warn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(content, post.content) && Objects.equals(thread, post.thread) && Objects.equals(author, post.author) && Objects.equals(creationDate, post.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, thread, author, creationDate);
    }

    @Override
    public String toString() {
        return "Post={id=" + id + ", content=" + content + ", thread= " + thread.toString() + ", author=" + author.getName()
                + ", creationDate=" + creationDate + ", warn=" + warn;
    }
}
