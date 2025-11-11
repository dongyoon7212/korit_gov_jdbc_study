package com.korit.study3.dto;

import com.korit.study3.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AddPostReqDto {
    private String title;
    private String content;
    private String username;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .username(username)
                .build();
    }
}
