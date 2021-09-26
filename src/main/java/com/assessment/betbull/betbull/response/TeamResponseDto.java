package com.assessment.betbull.betbull.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponseDto {

  private Long id;

  private String teamName;

  private BigDecimal budget;

  private Set<PlayerResponseDto> players = new HashSet<>();

}
