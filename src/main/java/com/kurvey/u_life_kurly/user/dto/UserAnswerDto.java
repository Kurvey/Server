package com.kurvey.u_life_kurly.user.dto;

import com.kurvey.u_life_kurly.user.entity.UserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class UserAnswerDto {
    @ApiModelProperty(value = "동거인 수", example = "2")
    @NotNull
    private Integer numberOfFamily;

    @ApiModelProperty(value = "영유아 유무", example = "true")
    @NotNull
    private Boolean hasBaby;

    @ApiModelProperty(value = "반려견 유무", example = "false")
    @NotNull
    private Boolean hasDog;

    @ApiModelProperty(value = "반려묘 유무", example = "true")
    @NotNull
    private Boolean hasCat;

    @ApiModelProperty(value = "반려식물 유무", example = "false")
    @NotNull
    private Boolean hasPlant;

    @ApiModelProperty(value = "선택한 항목", example = "[3, 2, 6]")
    @NotNull
    private List<Long> selects;

    public UserAnswerDto(UserInfo userInfo, List<Long> answers) {
        this.numberOfFamily = userInfo.getNumberOfFamily();
        this.hasBaby = userInfo.getHasBaby();
        this.hasDog = userInfo.getHasDog();
        this.hasCat = userInfo.getHasCat();
        this.hasPlant = userInfo.getHasPlant();
        this.selects = answers;
    }
}
