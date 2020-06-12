package pl.michalperlak.querydsl.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBo {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;
}
