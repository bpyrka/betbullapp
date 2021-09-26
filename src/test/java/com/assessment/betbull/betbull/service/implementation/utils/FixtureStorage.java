package com.assessment.betbull.betbull.service.implementation.utils;

import java.math.BigDecimal;

import com.assessment.betbull.betbull.request.PlayerRequestDto;
import com.assessment.betbull.betbull.request.TeamRequestDto;

public class FixtureStorage {

 public static PlayerRequestDto playerFixtureRequest(Integer age, Integer experience){
    return PlayerRequestDto
        .builder()
        .age(age)
        .lastName("mocky last name")
        .name("mocky name")
        .monthsOfExperience(experience)
        .build();
  }

  public static TeamRequestDto teamFixtureRequest(Integer value){
    return TeamRequestDto
        .builder()
        .budget(BigDecimal.valueOf(value))
        .teamName("Mock team")
        .build();
  }
}
