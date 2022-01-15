package com.forum.services;

import com.forum.model.Reputation;
import com.forum.repositories.ReputationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReputationService {
    private final ReputationRepository reputationRepository;

    public ReputationService(ReputationRepository reputationRepository) {
        this.reputationRepository = reputationRepository;
    }

    public boolean hasUserGrantedReputToPost(Long userId, Long postId) {
        List<Reputation> sendReputation = reputationRepository.findBySenderId(userId);
        return sendReputation.stream().map(Reputation::getPostId).anyMatch(e -> e.equals(postId));
    }

    public Reputation save(Reputation reputation) {
       return reputationRepository.save(reputation);
    }
}
