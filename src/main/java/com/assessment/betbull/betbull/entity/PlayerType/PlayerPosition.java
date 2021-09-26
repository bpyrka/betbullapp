package com.assessment.betbull.betbull.entity.PlayerType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerPosition {

  ATTACKER("ATTACKER"),
  HELPER("HELPER"),
  GOALKEEPER("GOALKEEPER"),
  STOPPER("STOPPER"),
  DEFENSE("DEFENSE");

  private final String description;
}
