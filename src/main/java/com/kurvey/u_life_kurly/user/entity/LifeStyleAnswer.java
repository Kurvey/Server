package com.kurvey.u_life_kurly.user.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LifeStyleAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private LifeStyleQuestion lifeStyleQuestion;

    private Integer priority;
}
