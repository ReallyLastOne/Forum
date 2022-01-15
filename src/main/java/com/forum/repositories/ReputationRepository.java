package com.forum.repositories;

import com.forum.model.Reputation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReputationRepository extends JpaRepository<Reputation, Long> {
    List<Reputation> findByPostId(Long id);
    List<Reputation> findBySenderId(Long id);
}
