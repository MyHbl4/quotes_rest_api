package com.moon.quotes_rest_api.service;

import com.moon.quotes_rest_api.controller.dto.QuoteNewDTO;
import com.moon.quotes_rest_api.controller.dto.QuoteUpdateDTO;
import com.moon.quotes_rest_api.entity.Quote;
import java.security.Principal;
import java.util.List;

public interface QuoteService {

    Quote save(Principal principal, QuoteNewDTO quote);

    List<Quote> findAllByUserId(Long id);

    Quote findById(long id);

    Quote update(Principal principal, QuoteUpdateDTO quote);

    void deleteById(Principal principal, long id);

    List<Quote> getTop10();

    List<Quote> getFlop10();

    Quote getRandomQuote();
}
