package com.kurvey.u_life_kurly.user.entity;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Integer numberOfFamily;

    private Boolean hasBaby;

    private Boolean hasDog;

    private Boolean hasCat;

    private Boolean hasPlant;

}
