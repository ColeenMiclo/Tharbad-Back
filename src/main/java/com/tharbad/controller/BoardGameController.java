package com.tharbad.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tharbad.dto.BoardGameDto;
import com.tharbad.service.BoardGameService;

@RestController
@RequestMapping("/api/games")
public class BoardGameController {
    // reference
    @Autowired
    private final BoardGameService boardGameService;

    // constructor
    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardGameDto> getGame(@PathVariable String id) {
            return ResponseEntity.ok(boardGameService.getGameById(id));
    }

    @GetMapping("/hot")
    public ResponseEntity<List<BoardGameDto>> getHotGames() {
        return ResponseEntity.ok(boardGameService.getHotGames());
    }

}
