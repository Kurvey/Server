package com.kurvey.u_life_kurly.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId; //email 대신 userId

    private String name;

    private String password;

    private LocalDate birthDay;

    @Builder
    public User(Long id, String userId, String password){
        this.id = id;
        this.userId = userId;
        this.password = password;
    }
}
