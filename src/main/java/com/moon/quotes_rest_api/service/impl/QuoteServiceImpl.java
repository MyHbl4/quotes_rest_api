package com.moon.quotes_rest_api.service.impl;

import static com.moon.quotes_rest_api.utils.StringConstants.ACCESS_DENIED;
import static com.moon.quotes_rest_api.utils.StringConstants.COULD_NOT_UPDATED;
import static com.moon.quotes_rest_api.utils.StringConstants.NF;

import com.moon.quotes_rest_api.controller.dto.QuoteNewDTO;
import com.moon.quotes_rest_api.controller.dto.QuoteUpdateDTO;
import com.moon.quotes_rest_api.entity.Quote;
import com.moon.quotes_rest_api.error.AuthException;
import com.moon.quotes_rest_api.error.NotFoundException;
import com.moon.quotes_rest_api.error.ValidationException;
import com.moon.quotes_rest_api.repository.QuoteRepository;
import com.moon.quotes_rest_api.repository.UserRepository;
import com.moon.quotes_rest_api.service.QuoteService;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    @Override
    public Quote save(Principal principal, QuoteNewDTO quote) {
        Quote newQuote = new Quote();
        newQuote.setUser(
            userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException(
                NF.value)));
        newQuote.setDescription(quote.getDescription());
        return quoteRepository.save(newQuote);
    }

    @Override
    public List<Quote> findAllByUserId(Long id) {
        return quoteRepository.findAllByUser_Id(id);
    }

    @Override
    public Quote findById(long id) {
        return quoteRepository.findById(id).orElseThrow(() -> new NotFoundException(NF.value));
    }

    @Override
    public Quote update(Principal principal, QuoteUpdateDTO updateQuote) {
        Quote quote = quoteRepository.findById(updateQuote.getId())
            .orElseThrow(() -> new NotFoundException(NF.value));
        if (!quote.getUser().getEmail().equals(principal.getName())) {
            throw new AuthException(ACCESS_DENIED.value);
        }
        quote.setDescription(quote.getDescription());
        quote.setDateUpdate(LocalDate.now());
        try {
            return quoteRepository.save(quote);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_UPDATED.value);
        }
    }

    @Override
    public void deleteById(Principal principal, long id) {
        Quote quote = findById(id);
        if (!quote.getUser().getEmail().equals(principal.getName())) {
            throw new AuthException(ACCESS_DENIED.value);
        }
        quoteRepository.deleteById(id);
    }

    @Override
    public List<Quote> getTop10() {
        return quoteRepository.findTop10();
    }

    @Override
    public List<Quote> getFlop10() {
        return quoteRepository.findFlop10();
    }

    @Override
    public Quote getRandomQuote() {
        return quoteRepository.getRandom();
    }
}
