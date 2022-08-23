package com.kurvey.u_life_kurly.user.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LifeStyleQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "설문문항", example = "채식주의")
    @Column(length = 45)
    private String question;

    @ApiModelProperty(value = "설문문항 이미지 URL", example = "http://...")
    private String imageUrl;
}
