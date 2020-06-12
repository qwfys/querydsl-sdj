package pl.michalperlak.querydsl.bi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionBi {

    private Long id;

    private Long userId;

    private String name;

    private LocalDateTime activated;
}
