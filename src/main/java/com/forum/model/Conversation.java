package com.forum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Getter
@Setter
public class Conversation {
    @Id
    private Long id;
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> messages;

    private String title;

    // should always be present, if not - something is wrong
    public Message findLastMessage() {
        return messages.stream().max(Comparator.comparing(Message::getSentDate)).get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(id, that.id) && Objects.equals(messages, that.messages) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messages, title);
    }
}
