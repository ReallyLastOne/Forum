package com.forum.repositories;

import com.forum.model.Post;
import com.forum.model.dtos.PostDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT new com.forum.model.dtos.PostDto(p.id, p.creationDate) FROM Post p INNER JOIN Thread t ON" +
            " p.thread.id = t.id AND t.section.id = :sectionId ORDER BY p.creationDate DESC")
    List<PostDto> findAllPostsDtoFromSection(@Param("sectionId") Long sectionId, Pageable pageable);
}
