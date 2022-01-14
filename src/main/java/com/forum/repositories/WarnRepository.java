package com.forum.repositories;

import com.forum.model.Warn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarnRepository extends JpaRepository<Warn, Long> {
}
