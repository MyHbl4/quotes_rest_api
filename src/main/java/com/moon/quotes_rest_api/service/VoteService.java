package com.moon.quotes_rest_api.service;

import com.moon.quotes_rest_api.entity.Vote;
import java.security.Principal;

public interface VoteService {
    Vote savePlus(Principal principal, long quoteId);

    Vote saveMinus(Principal principal, long quoteId);

}
