package com.moon.quotes_rest_api.controller;

import com.moon.quotes_rest_api.controller.dto.QuoteDTO;
import com.moon.quotes_rest_api.controller.dto.QuoteNewDTO;
import com.moon.quotes_rest_api.controller.dto.QuoteUpdateDTO;
import com.moon.quotes_rest_api.mapper.QuoteMapper;
import com.moon.quotes_rest_api.service.QuoteService;
import io.swagger.annotations.Api;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quotes")
@Api(tags = "Quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping
    public QuoteDTO save(Principal principal, @RequestBody @Valid QuoteNewDTO quote) {
        return QuoteMapper.INSTANCE.toQuoteDtoFromQuote(quoteService.save(principal, quote));
    }

    @GetMapping(path = "/{id}")
    public QuoteDTO getById(@PathVariable(name = "id") long id) {
        return QuoteMapper.INSTANCE.toQuoteDtoFromQuote(quoteService.findById(id));
    }

    @GetMapping(path = "/top10")
    public List<QuoteDTO> getTop10() {
        return QuoteMapper.INSTANCE.toListQuoteDtoFromListQuote(quoteService.getTop10());
    }

    @GetMapping(path = "/flop10")
    public List<QuoteDTO> getFlop10() {
        return QuoteMapper.INSTANCE.toListQuoteDtoFromListQuote(quoteService.getFlop10());
    }

    @GetMapping(path = "/randomQuote")
    public QuoteDTO getRandomQuote() {
        return QuoteMapper.INSTANCE.toQuoteDtoFromQuote(quoteService.getRandomQuote());
    }

    @PutMapping
    public QuoteDTO update(Principal principal, @RequestBody QuoteUpdateDTO quote) {
        return QuoteMapper.INSTANCE.toQuoteDtoFromQuote(quoteService.update(principal, quote));
    }

    @DeleteMapping(path = "/{id}")
    void deleteById(Principal principal, @PathVariable(name = "id") long id) {
        quoteService.deleteById(principal, id);
    }
}
