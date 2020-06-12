package pl.michalperlak.querydsl.service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.michalperlak.querydsl.bo.SubscriptionProjectionBo;
import pl.michalperlak.querydsl.bo.UserProjectionBo;
import pl.michalperlak.querydsl.repository.UserRepository;
import pl.michalperlak.querydsl.ro.QSubscriptionRo;
import pl.michalperlak.querydsl.ro.QUserRo;
import pl.michalperlak.querydsl.service.base.QueryBuilder;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final QueryBuilder queryBuilder;

   public UserServiceImpl(UserRepository userRepository, QueryBuilder queryBuilder) {
        this.userRepository = userRepository;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public Page<UserProjectionBo> getFirstNameLikePage(String firstNameLike, Pageable pageable) {
        QUserRo user = QUserRo.userRo;
        return userRepository.findAll(
                Projections.constructor(UserProjectionBo.class, user.id, user.username),
                user.firstName.like(firstNameLike),
                pageable
        );
    }

    @Override
    public List<SubscriptionProjectionBo> getAllSubscriptions() {
        QUserRo user = QUserRo.userRo;
        QSubscriptionRo subscription = QSubscriptionRo.subscriptionRo;
        JPQLQuery<SubscriptionProjectionBo> query = queryBuilder
                .createQuery()
                .select(Projections.constructor(SubscriptionProjectionBo.class, subscription.name, user.username))
                .from(user)
                .innerJoin(subscription)
                .on(user.id.eq(subscription.userId));
        List<SubscriptionProjectionBo> list = userRepository.findAll(query);

        return list;
        //return null;
    }
}
