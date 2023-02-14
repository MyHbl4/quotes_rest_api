package com.moon.quotes_rest_api.service.impl;

import static com.moon.quotes_rest_api.utils.StringConstants.COULD_NOT_SAVED;
import static com.moon.quotes_rest_api.utils.StringConstants.NF;

import com.moon.quotes_rest_api.entity.Quote;
import com.moon.quotes_rest_api.entity.User;
import com.moon.quotes_rest_api.entity.Vote;
import com.moon.quotes_rest_api.error.NotFoundException;
import com.moon.quotes_rest_api.error.ValidationException;
import com.moon.quotes_rest_api.repository.QuoteRepository;
import com.moon.quotes_rest_api.repository.UserRepository;
import com.moon.quotes_rest_api.repository.VoteRepository;
import com.moon.quotes_rest_api.service.VoteService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;

    @Override
    @Transactional
    public Vote savePlus(Principal principal, long quoteId) {
        User user = userRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new NotFoundException(NF.value));
        Vote vote = new Vote();
        vote.setVoice(1);
        vote.setUser(user);
        Quote quote = quoteRepository.findById(quoteId).orElseThrow(
            () -> new NotFoundException(NF.value));
        quote.setScore(quote.getScore() + 1);
        vote.setQuote(quote);
        try {
            quoteRepository.save(quote);
            return voteRepository.save(vote);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_SAVED.value);
        }
    }

    @Override
    @Transactional
    public Vote saveMinus(Principal principal, long quoteId) {
        User user = userRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new NotFoundException(NF.value));
        Vote vote = new Vote();
        vote.setVoice(-1);
        vote.setUser(user);
        Quote quote = quoteRepository.findById(quoteId).orElseThrow(
            () -> new NotFoundException(NF.value));
        quote.setScore(quote.getScore() - 1);
        vote.setQuote(quote);
        try {
            quoteRepository.save(quote);
            return voteRepository.save(vote);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_SAVED.value);
        }
    }
}
