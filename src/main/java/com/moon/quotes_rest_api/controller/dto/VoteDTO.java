package com.moon.quotes_rest_api.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

@Data
public class VoteDTO {

    private Long id;
    private Integer voice;
    @JsonProperty("user")
    private UserShortDTO user;
    @JsonProperty("quote")
    private QuoteShortDTO quote;
    private LocalDate dateCreate;
}
