package com.forum.model.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class PostDto {
    @NonNull
    private Long id;
    @NonNull
    private LocalDateTime creationDate;
}
