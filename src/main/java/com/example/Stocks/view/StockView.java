package com.example.Stocks.view;

import com.example.Stocks.model.Stock;

import java.util.List;
import java.util.stream.Collectors;

public class StockView {
    private String name;
    private List<Double> quotes;

    public StockView() {
    }

    public StockView(Stock stock){
        this.name = stock.getName();
        this.quotes = stock.getQuotes().stream().map(quote -> quote.getValue()).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Double> quotes) {
        this.quotes = quotes;
    }
}
