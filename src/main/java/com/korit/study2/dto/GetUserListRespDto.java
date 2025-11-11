package com.korit.study2.dto;

import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
public class GetUserListRespDto {
    private Integer userId;
    private String username;
    private String email;
    private LocalDateTime createDt;
}
