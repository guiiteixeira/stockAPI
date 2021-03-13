package com.example.Stocks.repository;

import com.example.Stocks.model.Stock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class StockRepositoryImpl implements StockRepositoryCustom{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Stock> findByName(String name) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
        Root<Stock> root = query.from(Stock.class);

        query.where(builder.equal(root.get("name"), name));

        try {
            return Optional.of(manager.createQuery(query).getSingleResult());
        }catch(NoResultException ex){
            return Optional.empty();
        }
    }
}
