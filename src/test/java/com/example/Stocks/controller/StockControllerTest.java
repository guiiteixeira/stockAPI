package com.example.Stocks.controller;

import com.example.Stocks.model.Stock;
import com.example.Stocks.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    public void listAll() throws Exception {
        List<Stock> result = new ArrayList<>();

        Stock stock = new Stock();
        stock.setName("Teste");
        stock.setQuotes(new ArrayList<>());

        Stock stock2 = new Stock();
        stock2.setName("Teste2");
        stock2.setQuotes(new ArrayList<>());

        result.add(stock);
        result.add(stock2);

        Mockito.when(stockService.list()).thenReturn(result);
        this.mockMvc.perform(get("/stock"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[{name: 'Teste', quotes: []},{name: 'Teste2', quotes: []}]"));
    }

    @Test
    public void getOne() throws Exception {

        Stock stock2 = new Stock();
        stock2.setName("Teste2");
        stock2.setQuotes(new ArrayList<>());

        Mockito.when(stockService.findByName("Teste2")).thenReturn(Optional.of(stock2));
        this.mockMvc.perform(get("/stock?name=Teste2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{name: 'Teste2', quotes: []}]"));
    }

    @Test
    public void createSameName() throws Exception {
        Stock stock = new Stock();
        stock.setName("Teste");
        stock.setQuotes(new ArrayList<>());

        Mockito.when(stockService.findByName("Teste")).thenReturn(Optional.of(stock));
        this.mockMvc.perform(post("/stock").content("{name: 'Teste', quotes: [10,11]}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void createWithoutName() throws Exception {
        this.mockMvc.perform(post("/stock").content("{quotes: [10,11]}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void updateWithoutQuotes() throws Exception {
        this.mockMvc.perform(patch("/stock").content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void deleteWithoutName() throws Exception {
        this.mockMvc.perform(delete("/stock"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}
