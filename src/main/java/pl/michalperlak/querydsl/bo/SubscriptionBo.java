package pl.michalperlak.querydsl.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionBo {

    private Long id;

    private Long userId;

    private String name;

    private LocalDateTime activated;
}
