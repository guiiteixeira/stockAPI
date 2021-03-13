package com.example.Stocks.view;

import com.example.Stocks.model.Quote;
import com.example.Stocks.model.Stock;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class StockViewTest {
    @Test
    public void constructFromStock(){
        Stock stock = new Stock();
        stock.setName("Teste");
        stock.setQuotes(new ArrayList<>());
        Quote quote = new Quote();
        quote.setValue(12.3);
        stock.getQuotes().add(quote);

        StockView stockView = new StockView(stock);
        assertEquals(stockView.getName(),stock.getName());
        assertEquals(stockView.getQuotes().size(),stock.getQuotes().size());
    }
}
