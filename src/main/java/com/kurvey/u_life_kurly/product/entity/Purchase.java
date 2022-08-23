package com.kurvey.u_life_kurly.product.entity;

import com.kurvey.u_life_kurly.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime paidAt;

    @PrePersist
    public void paidAt(){
        this.paidAt = LocalDateTime.now();
    }
}
