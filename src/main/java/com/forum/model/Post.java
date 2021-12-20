package com.forum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Thread thread;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    private LocalDateTime creationDate;

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
}
