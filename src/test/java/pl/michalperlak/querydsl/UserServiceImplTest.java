package pl.michalperlak.querydsl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.michalperlak.querydsl.bo.SubscriptionProjectionBo;
import pl.michalperlak.querydsl.bo.UserProjectionBo;
import pl.michalperlak.querydsl.repository.SubscriptionRepository;
import pl.michalperlak.querydsl.repository.UserRepository;
import pl.michalperlak.querydsl.ro.SubscriptionRo;
import pl.michalperlak.querydsl.ro.UserRo;
import pl.michalperlak.querydsl.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        List<UserRo> users = addUsers();
        addSubscriptions(users);
    }

    @Test
    void testGetPageByFirstName() {
        Page<UserProjectionBo> page = userService.getFirstNameLikePage("A%", PageRequest.of(0, 10));
        List<String> userNames = page
                .get()
                .map(UserProjectionBo::getUsername)
                .collect(Collectors.toList());
        List<String> expected = Collections.singletonList("UserA");
        assertEquals(expected, userNames);
    }

    @Test
    void testGetAllSubscriptions() {
        List<SubscriptionProjectionBo> subscriptions = userService.getAllSubscriptions();
        List<SubscriptionProjectionBo> expected = Arrays.asList(
                new SubscriptionProjectionBo("STANDARD", "UserA"),
                new SubscriptionProjectionBo("STANDARD", "UserB")
        );
        assertEquals(expected, subscriptions);
    }

    private List<UserRo> addUsers() {
        UserRo user1 = UserRo
                .builder()
                .username("UserA")
                .firstName("Andrew")
                .lastName("Test")
                .dateOfBirth(LocalDate.of(1990, 10, 11))
                .build();
        UserRo user2 = UserRo
                .builder()
                .username("UserB")
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(1950, 12, 1))
                .build();
        return userRepository.saveAll(Arrays.asList(user1, user2));
    }

    private void addSubscriptions(List<UserRo> users) {
        users
                .stream()
                .map(UserRo::getId)
                .map(userId -> SubscriptionRo
                        .builder()
                        .name("STANDARD")
                        .userId(userId)
                        .activated(LocalDateTime.now())
                        .build())
                .forEach(subscriptionRepository::save);
    }
}