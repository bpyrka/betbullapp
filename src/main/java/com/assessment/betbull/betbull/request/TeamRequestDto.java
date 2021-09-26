package com.assessment.betbull.betbull.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.assessment.betbull.betbull.entity.PlayerType.PlayerPosition;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDto {

  @Size(min = 3, max = 100)
  private String teamName;

  @Positive
  private BigDecimal budget;
}
