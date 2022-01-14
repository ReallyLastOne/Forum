package com.forum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Warn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
    private int value;
    @ManyToOne
    private User receiver;
    @ManyToOne
    private User givenBy;

    @OneToOne
    private Post post;

    @Override
    public String toString() {
        return "Warn={reason=" + reason + ", value=" + value + ", givenBy=" + givenBy.getName() + ", post=" + post.getContent() + "}";
    }
}
