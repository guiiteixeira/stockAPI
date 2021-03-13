package com.example.Stocks.service;

import com.example.Stocks.exception.StockException;
import com.example.Stocks.model.Quote;
import com.example.Stocks.model.Stock;
import com.example.Stocks.repository.StockRepository;
import com.example.Stocks.view.StockView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private QuoteService quoteService;

    public List<Stock> list(){
        return stockRepository.findAll();
    }

    public Optional<Stock> findById(long id){
        return stockRepository.findById(id);
    }

    public Optional<Stock> findByName(String name){
        return stockRepository.findByName(name);
    }

    public StockView create(StockView stockView) throws StockException {
        if(findByName(stockView.getName()).orElse(null) != null){
            throw new StockException("Stock '" + stockView.getName() + "' já existe");
        }

        final Stock stock = new Stock();
        stock.setName(stockView.getName());

        final Stock savedStock = stockRepository.save(stock);

        List<Quote> quotes =
                stockView
                        .getQuotes()
                        .stream()
                        .map((value)-> new Quote(value, savedStock))
                        .collect(Collectors.toList());

        stock.setQuotes(quoteService.create(quotes));

        stockView = new StockView(stock);

        return stockView;
    }

    public StockView update(StockView stockView) throws StockException {
        final Stock stock = findByName(stockView.getName()).orElseThrow(
            () -> new StockException("Stock '" + stockView.getName() + "' não foi encontrado.")
        );

        List<Quote> quotes =
                stockView
                        .getQuotes()
                        .stream()
                        .map((value)-> new Quote(value, stock))
                        .collect(Collectors.toList());

        quoteService.create(quotes);

        return new StockView(findById(stock.getId()).get());
    }

    public void delete(String name) throws StockException {
        final Stock stock = findByName(name).orElseThrow(
                () -> new StockException("Stock '" + name + "' não foi encontrado.")
        );
        stockRepository.delete(stock);
    }
}
