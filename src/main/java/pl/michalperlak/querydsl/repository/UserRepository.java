package pl.michalperlak.querydsl.repository;

import org.springframework.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalperlak.querydsl.ro.UserRo;

public interface UserRepository extends JpaRepository<UserRo, Long>, ExtendedQuerydslPredicateExecutor<UserRo> {
}
