package com.forum.model.dtos;

import com.forum.model.Post;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
// dto used in main page view
public class SectionMainDto {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    private Post mostRecentPost;
}
