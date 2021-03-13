package com.example.Stocks.service;

import com.example.Stocks.model.Quote;
import com.example.Stocks.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public List<Quote> list(){
        return quoteRepository.findAll();
    }

    public List<Quote> create(List<Quote> quotes){
        return quoteRepository.saveAll(quotes);
    }
}
