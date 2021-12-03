package com.forum.repositories;

import com.forum.model.Section;
import com.forum.model.Subsection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
