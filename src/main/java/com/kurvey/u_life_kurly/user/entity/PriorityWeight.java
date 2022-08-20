package com.kurvey.u_life_kurly.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PriorityWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer priority;

    private Integer weight;

}
