package com.forum.services;

import com.forum.model.Post;
import com.forum.model.dtos.PostDto;
import com.forum.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public List<PostDto> getAllPostsFromSection(Long id, Pageable pageable) {
        return postRepository.findAllPostsDtoFromSection(id, pageable);
    }
}
