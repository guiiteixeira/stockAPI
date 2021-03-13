package com.example.Stocks.repository;

import com.example.Stocks.model.Quote;
import com.example.Stocks.model.Stock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class QuoteRepositoryImpl implements QuoteRepositoryCustom{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Quote> findByStock(long id) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Quote> query = builder.createQuery(Quote.class);
        Root<Quote> root = query.from(Quote.class);

        query.where(builder.equal(root.get("stock").get("id"), id));

        return manager.createQuery(query).getResultList();
    }
}
