package com.moon.quotes_rest_api.mapper;

import com.moon.quotes_rest_api.controller.dto.QuoteDTO;
import com.moon.quotes_rest_api.entity.Quote;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuoteMapper {

    QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);

    QuoteDTO toQuoteDtoFromQuote(Quote quote);
    List<QuoteDTO> toListQuoteDtoFromListQuote(List<Quote> quotes);
}
