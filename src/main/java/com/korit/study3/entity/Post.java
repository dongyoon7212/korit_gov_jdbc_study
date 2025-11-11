package com.korit.study3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Post {
    private Integer postId;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createDt;
}
