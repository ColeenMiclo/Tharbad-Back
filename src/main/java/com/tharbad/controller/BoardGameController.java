package com.tharbad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String getGameById(@PathVariable String id) {
        return boardGameService.getGameById(id);
    }
}
