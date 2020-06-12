package pl.michalperlak.querydsl.bo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserProjectionBo {
    private final Long id;
    private final String username;
}
