package pl.michalperlak.querydsl.bo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class SubscriptionProjectionBo {
    private final String subscriptionName;
    private final String username;
}
