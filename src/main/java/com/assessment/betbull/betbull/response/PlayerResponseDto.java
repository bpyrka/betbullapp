package com.assessment.betbull.betbull.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.assessment.betbull.betbull.entity.PlayerType.PlayerPosition;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponseDto {

  private Long id;

  private String name;

  private String lastName;

  private PlayerPosition playerPosition;

  private Integer monthsOfExperience;

  private Integer age;

  private String teamName;
}
