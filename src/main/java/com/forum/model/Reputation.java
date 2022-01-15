package com.forum.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Reputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private int value;
    @ManyToOne
    @NonNull
    private User receiver;
    @NonNull
    private long senderId;
    @NonNull
    private long postId;
    @NonNull
    private String date;
}
