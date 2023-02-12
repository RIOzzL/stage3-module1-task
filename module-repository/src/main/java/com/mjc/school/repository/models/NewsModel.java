package com.mjc.school.repository.models;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewsModel {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdatedDate;

    private Long authorId;
}
