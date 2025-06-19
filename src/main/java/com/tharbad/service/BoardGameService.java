package com.tharbad.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BoardGameService {

    private final RestTemplate restTemplate;

    public BoardGameService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGameById(String id) {
        String url = "https://www.boardgamegeek.com/xmlapi2/thing?id=" + id + "&type=boardgame&stats=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
