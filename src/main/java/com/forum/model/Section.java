package com.forum.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(orphanRemoval = true, mappedBy = "section")
    private List<Thread> threads;

    private String description;

    public void addThread(Thread thread) {
        threads.add(thread);
        thread.setSection(this);
    }

    public void removeThread(Thread thread) {
        threads.remove(thread);
        thread.setSection(null);
    }

    public Optional<Thread> findMostRecentThread() {
        return threads.stream().map(Thread::findMostRecentPost).filter(Optional::isPresent).map(Optional::get)
                .max(Comparator.comparing(Post::getCreationDate)).map(Post::getThread);
    }
}
