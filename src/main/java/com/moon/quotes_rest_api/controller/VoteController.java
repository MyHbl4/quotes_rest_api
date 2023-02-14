package com.moon.quotes_rest_api.controller;

import com.moon.quotes_rest_api.controller.dto.VoteDTO;
import com.moon.quotes_rest_api.mapper.VoteMapper;
import com.moon.quotes_rest_api.service.VoteService;
import io.swagger.annotations.Api;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/votes")
@Api(tags = "Votes")
public class VoteController {

    private final VoteService voteService;

    @PostMapping(path = "plus/{id}")
    public VoteDTO savePlus(Principal principal, @PathVariable(name = "id") long id) {
        return VoteMapper.INSTANCE.toVoteDtoFromVote(voteService.savePlus(principal, id));
    }

    @PostMapping(path = "minus/{id}")
    public VoteDTO saveMinus(Principal principal, @PathVariable(name = "id") long id) {
        return VoteMapper.INSTANCE.toVoteDtoFromVote(voteService.saveMinus(principal, id));
    }

}
