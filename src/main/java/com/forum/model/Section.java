package com.forum.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    public void addThread(Thread thread) {
        threads.add(thread);
        thread.setSection(this);
    }

    public void removeThread(Thread thread) {
        threads.remove(thread);
        thread.setSection(null);
    }
}
