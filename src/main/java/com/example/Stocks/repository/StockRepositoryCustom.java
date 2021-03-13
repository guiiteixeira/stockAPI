package com.example.Stocks.repository;

import com.example.Stocks.model.Stock;

import java.util.Optional;
public interface StockRepositoryCustom{
    Optional<Stock> findByName(String name);
}
