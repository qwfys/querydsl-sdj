package pl.michalperlak.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalperlak.querydsl.ro.SubscriptionRo;

public interface SubscriptionRepository extends JpaRepository<SubscriptionRo, Long> {
}
