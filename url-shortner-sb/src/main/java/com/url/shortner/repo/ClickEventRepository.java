package com.url.shortner.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.shortner.model.ClickEvent;
import com.url.shortner.model.UrlMapping;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent, Long>  {
    
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping urlMapping, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMappings, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
