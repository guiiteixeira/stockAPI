package com.example.Stocks.repository;

import com.example.Stocks.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long>, StockRepositoryCustom {
}
