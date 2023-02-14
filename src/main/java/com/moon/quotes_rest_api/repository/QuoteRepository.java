package com.moon.quotes_rest_api.repository;

import com.moon.quotes_rest_api.entity.Quote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findAllByUser_Id(Long id);

    @Query(value = "SELECT * FROM quotes WHERE id >= RAND() * ( SELECT MAX ( id ) FROM quotes ) ORDER BY id LIMIT 1", nativeQuery = true)
    Quote getRandom();

    @Query(value = "SELECT * FROM quotes ORDER BY score DESC LIMIT 10", nativeQuery = true)
    List<Quote> findTop10();

    @Query(value = "SELECT * FROM quotes ORDER BY score ASC LIMIT 10", nativeQuery = true)
    List<Quote> findFlop10();
}
