package com.mjc.school.service.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewsDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdatedDate;

    private Long authorId;
}
