package com.forum.model.htmlforms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageForm {
    private String receiver;
    private String title;
    private String content;
}
