package com.forum.model;

import com.forum.constraint.ValidPassword;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    @NotNull
    @NotBlank
    private String name;

    @ValidPassword
    private String password;

    @Column(unique = true)
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts;

    @Setter(AccessLevel.NONE)
    private LocalDateTime registerDate;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"
            ))
    private Set<Role> roles = new HashSet<>();

    private boolean banned = false;

    @OneToMany(mappedBy = "sender")
    private List<Message> messagesSent;

    @OneToMany(mappedBy = "receiver")
    private List<Message> messagesReceived;

    @Embedded
    private UserInfo userInfo;

    public void addRole(Role role) {
        roles.add(role);
        role.addUser(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.removeUser(this);
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setAuthor(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setAuthor(null);
    }

    public void initialize() {
        if (registerDate == null) registerDate = LocalDateTime.now();
        if (userInfo == null) userInfo = new UserInfo();
    }

    /**
     * Returns List of Conversations sorted by recentness.
     */
    public List<Conversation> findConversations() {
        Set<Conversation> conversations = new HashSet<>();
        messagesReceived.forEach(e -> conversations.add(e.getConversation()));
        messagesSent.forEach(e -> conversations.add(e.getConversation()));

        List<Conversation> array = new ArrayList<>(conversations);
        array.sort((o1, o2) -> {
            try {
                Date f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                        .parse(o1.findLastMessage().getSentDate());
                Date f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                        .parse(o2.findLastMessage().getSentDate());
                return f2.compareTo(f1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return 0;
        });
        return array;
    }

    @Override
    public String toString() {
        return "User={id=" + id + ", name=" + name + ", password=" + ", email=" + email + ", registerDate=" +
                registerDate + ", banned=" + banned + ", roles=" + roles + ", userInfo=" + userInfo + "}";
    }
}
