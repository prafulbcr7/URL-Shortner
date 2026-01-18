package com.url.shortner.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.shortner.model.UrlMapping;
import com.url.shortner.model.User;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long>  {

    UrlMapping findByShortUrl(String shortUrl);

    List<UrlMapping> findByUser(User user);

    
    
}
