package com.forum.model;

import com.forum.utilities.DateUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
public class Conversation {
    @Id
    private Long id;
    @OneToMany(mappedBy = "conversation", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Message> messages;

    private String title;

    // should always be present, if not - something is wrong
    public Message findLastMessage() {
        return messages.stream().max(Comparator.comparing(Message::getSentDate)).get();
    }

    public void addMessage(User sender, String content) {
        Message message = new Message();
        message.setContent(content);
        message.setConversation(this);
        message.setSentDate(DateUtils.withHours(LocalDateTime.now()));
        message.setSender(sender);
        User receiver = null;
        for (Message msg : messages) {
            if (!Objects.equals(msg.getReceiver().getId(), sender.getId())) {
                receiver = msg.getReceiver();
                receiver.addMessageReceived(message);
                break;
            }
        }
        sender.addMessageSent(message);
        if (receiver == null) {
            sender.addMessageReceived(message);
        }

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
