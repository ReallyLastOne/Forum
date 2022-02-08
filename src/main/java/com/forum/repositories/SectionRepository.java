package com.forum.repositories;

import com.forum.model.Section;
import com.forum.model.dtos.SectionMainDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findByName(String name);

    @Query(value = "SELECT new com.forum.model.dtos.SectionMainDto(s.id, s.name, s.description) FROM Section s")
    List<SectionMainDto> getSectionsMainDto();
}
