package com.tharbad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoardGameDto {

    @JsonProperty("id")
    private Long gameId;

    @JsonProperty("name")
    private String gameName;

    @JsonProperty("description")
    private String gameDescription;

    @JsonProperty("image")
    private String gameImage;

    // Getters and Setters

    public Long getId() {
        return gameId;  
    }
    public void setId(Long gameId) {
        this.gameId = gameId;
    }
    public String getName() {
        return gameName;
    }
    public void setName(String gameName) {
        this.gameName = gameName;
    }
    public String getDescription() {
        return gameDescription;
    }
    public void setDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }
    public String getImage() {
        return gameImage;
    }
    public void setImage(String gameImage) {
        this.gameImage = gameImage;
    }

    //Constructor
    public BoardGameDto() {}

    public BoardGameDto(Long gameId, String gameName, String gameDescription, String gameImage) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameImage = gameImage;
    }
}
