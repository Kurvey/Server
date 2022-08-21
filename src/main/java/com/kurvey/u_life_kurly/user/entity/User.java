package com.kurvey.u_life_kurly.user.entity;

import com.kurvey.u_life_kurly.user.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId; //email 대신 userId

    private String name;

    private String password;

    private LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private Gender gender;
}
