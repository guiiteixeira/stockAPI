package com.example.Stocks.repository;

import com.example.Stocks.model.Quote;
import com.example.Stocks.model.Stock;

import java.util.List;
import java.util.Optional;

public interface QuoteRepositoryCustom {
    List<Quote> findByStock(long id);
}
