package com.tharbad.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

// import javax.xml.bind.Element; // Removed incorrect import
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;

import com.tharbad.dto.BoardGameDto;

@Service
public class BoardGameService {

    private final RestTemplate restTemplate;

    public BoardGameService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BoardGameDto getGameById(String id) {
        String url = "https://www.boardgamegeek.com/xmlapi2/thing?id=" + id + "&type=boardgame&stats=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(response.getBody())));

            Element item = (Element) document.getElementsByTagName("item").item(0);

            Long gameId = Long.parseLong(item.getAttribute("id"));
            String gameName = ((Element) item.getElementsByTagName("name").item(0)).getAttribute("value");
            String gameDescription = item.getElementsByTagName("description").item(0).getTextContent();
            String gameImage = item.getElementsByTagName("image").item(0).getTextContent();

            BoardGameDto boardGameDto = new BoardGameDto();
            boardGameDto.setId(gameId);
            boardGameDto.setName(gameName);
            boardGameDto.setDescription(gameDescription);
            boardGameDto.setImage(gameImage);

            return boardGameDto;
        } catch (Exception e) {
            throw new RuntimeException("Ereur lors du parsing"+ e.getMessage(), e);
        }
    }

    public List<BoardGameDto> getHotGames() {
        String url = "https://www.boardgamegeek.com/xmlapi2/hot";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        System.out.println("Response: " + response.getBody());

        List<BoardGameDto> hotGames =new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(response.getBody())));

            NodeList items = document.getElementsByTagName("item");

            for (int i = 0; i < 4; i++) {
                Element item = (Element) items.item(i);

                Long gameId = Long.parseLong(item.getAttribute("id"));
                String gameName = ((Element) item.getElementsByTagName("name").item(0)).getAttribute("value");
                String gameImage = ((Element) item.getElementsByTagName("thumbnail").item(0)).getAttribute("value");

                BoardGameDto boardGameDto = new BoardGameDto();
                boardGameDto.setId(gameId);
                boardGameDto.setName(gameName);
                boardGameDto.setImage(gameImage);

                hotGames.add(boardGameDto);
            }
            return hotGames;
        } catch (Exception e) {
            throw new RuntimeException("Ereur lors du parsing"+ e.getMessage(), e);
        }
    }
}
