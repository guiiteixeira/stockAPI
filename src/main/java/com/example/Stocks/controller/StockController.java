package com.example.Stocks.controller;

import com.example.Stocks.exception.StockException;
import com.example.Stocks.model.Stock;
import com.example.Stocks.service.StockService;
import com.example.Stocks.view.StockView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<StockView> read(@RequestParam(required = false) String name) throws StockException {
        if(name != null) {
            Stock stock = stockService.findByName(name).orElseThrow(
                    () -> new StockException("Stock '" + name + "' não foi encontrado.")
            );
            List<StockView> stockViews = new ArrayList<>();
            stockViews.add(new StockView(stock));
            return stockViews;
        }else{
            return stockService.list().stream().map(stock -> new StockView(stock)).collect(Collectors.toList());
        }
    }

    @PostMapping
    public StockView createStock(@RequestBody StockView stockView) throws StockException {
        if(stockView.getName() == null){
            throw new StockException("O nome deve ser informado.");
        }
        if(stockView.getQuotes() == null){
            stockView.setQuotes(new ArrayList<>());
        }
        stockView = stockService.create(stockView);
        return stockView;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.PATCH)
    public StockView updateStock(@RequestBody StockView stockView, @PathVariable("name") String name) throws StockException {
        if(stockView.getQuotes() == null || stockView.getQuotes().isEmpty()){
            throw new StockException("Pelo menos uma Quote deve ser informada.");
        }
        stockView.setName(name);
        stockView = stockService.update(stockView);
        return stockView;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public void deleteStock(@PathVariable(value = "name") String name) throws StockException {
        stockService.delete(name);
    }

}
