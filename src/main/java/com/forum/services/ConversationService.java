package com.forum.services;

import com.forum.model.Conversation;
import com.forum.model.Message;
import com.forum.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }
}
