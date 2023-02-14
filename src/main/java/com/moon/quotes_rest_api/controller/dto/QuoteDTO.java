package com.moon.quotes_rest_api.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteDTO {

    private Long id;
    private String description;
    @JsonProperty("user")
    private UserShortDTO user;
    private Integer score;

}
