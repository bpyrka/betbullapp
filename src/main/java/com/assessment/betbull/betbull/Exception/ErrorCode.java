package com.assessment.betbull.betbull.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {

  PLAYER_HAS_TEAM("PLAYER IS ALREADY IN TEAM, TRY TO TRANSFER!"),
  TEAM_OUT_OF_BUDGET("CANNOT TRANSFER PLAYER, OUT OF BUDGET"),
  PLAYER_NOT_FOUND("PLAYER IS NOT IN TEAM");

  @Getter
  private final String errorMessage;
}
