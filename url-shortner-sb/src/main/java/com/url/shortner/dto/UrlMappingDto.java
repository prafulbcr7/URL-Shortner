package com.url.shortner.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UrlMappingDto {

    private Long id;
    private int clickCount;
    private String originalUrl;
    private String shortUrl;

    private LocalDateTime createdDate;

    private String username;
    
}
