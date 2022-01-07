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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "conversation", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Message> messages;
    private String title;

    public Conversation() {
        this.messages = new ArrayList<>();
    }

    // should always be present, if not - something is wrong
    public Message findLastMessage() {
        return messages.stream().max(Comparator.comparing(Message::getSentDate)).get();
    }

    // only when there are valid messages in conversations (probably bad)
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

    public void addMessage(User sender, User receiver, String content) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setConversation(this);
        message.setContent(content);
        message.setSentDate(DateUtils.toBase(LocalDateTime.now()));
        messages.add(message);
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
