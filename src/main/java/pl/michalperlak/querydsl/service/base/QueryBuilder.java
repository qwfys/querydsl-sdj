package pl.michalperlak.querydsl.service.base;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class QueryBuilder {
    @PersistenceContext
    private final EntityManager entityManager;

   public QueryBuilder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

   public  <T> JPQLQuery<T> createQuery() {
        return new JPAQuery<>(entityManager);
    }
}
