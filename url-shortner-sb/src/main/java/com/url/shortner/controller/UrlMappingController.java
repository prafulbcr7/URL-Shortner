package com.url.shortner.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.dto.ClickEventDto;
import com.url.shortner.dto.UrlMappingDto;
import com.url.shortner.model.User;
import com.url.shortner.service.UrlMappingService;
import com.url.shortner.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/url")
@AllArgsConstructor
public class UrlMappingController {
    
    private UrlMappingService urlMappingService;
    private UserService userService;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDto> createShortUrl(@RequestBody Map<String, String> request, 
                                                        Principal principal) {
        String originalUrl = request.get("originalUrl");
        User user = userService.findByUsername(principal.getName());

        UrlMappingDto urlMappingDto = urlMappingService.createShortUrl(originalUrl, user);
        return ResponseEntity.ok(urlMappingDto);
    } 

    @GetMapping("/myUrls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDto>> getUsersUrls(Principal principal){

        User user = userService.findByUsername(principal.getName());
        List<UrlMappingDto> urlList = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urlList);
    }

    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClickEventDto>> getShortUrlAnalytics(@PathVariable String shortUrl,
                                                                    @RequestParam("startDate") String startDate,
                                                                    @RequestParam("endDate") String endDate) {

            DateTimeFormatter formater = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime startTime = LocalDateTime.parse(startDate, formater);
            LocalDateTime endTime = LocalDateTime.parse(endDate, formater);
            
            List<ClickEventDto> clickEventsDtos = urlMappingService.getClickEventsByShortUrlWithStartAndEndDate(shortUrl, startTime, endTime);
            return ResponseEntity.ok(clickEventsDtos);
    }
    
    
    @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate, Long>> getTotalClicksByDate(Principal principal,
                                                                    @RequestParam("startDate") String startDate,
                                                                    @RequestParam("endDate") String endDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            User user = userService.findByUsername(principal.getName());

            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            Map<LocalDate, Long> totalClicks = urlMappingService.getTotalClicksByUserAndDate(user, start, end);
            return ResponseEntity.ok(totalClicks);
    }

}
