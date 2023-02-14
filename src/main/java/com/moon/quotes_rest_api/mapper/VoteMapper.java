package com.moon.quotes_rest_api.mapper;

import com.moon.quotes_rest_api.controller.dto.VoteDTO;
import com.moon.quotes_rest_api.entity.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteMapper {

    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);

    VoteDTO toVoteDtoFromVote(Vote vote);
}
