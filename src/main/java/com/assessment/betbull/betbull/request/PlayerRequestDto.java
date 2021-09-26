package com.assessment.betbull.betbull.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequestDto {

  @Size(min = 3, max = 100)
  private String name;

  @Size(min = 3, max = 100)
  private String lastName;

  @Positive
  private Integer monthsOfExperience;

  @Min(value = 18, message = "Player cannot be under 18")
  private Integer age;

}
