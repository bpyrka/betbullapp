package com.assessment.betbull.betbull.mapper;

import com.assessment.betbull.betbull.entity.Player;
import com.assessment.betbull.betbull.request.PlayerRequestDto;
import com.assessment.betbull.betbull.response.PlayerResponseDto;

public class PlayerMapper {

  public static Player mapToPlayer(PlayerRequestDto playerRequestDto) {
    return Player
        .builder()
        .age(playerRequestDto.getAge())
        .lastName(playerRequestDto.getLastName())
        .monthsOfExperience(playerRequestDto.getMonthsOfExperience())
        .name(playerRequestDto.getName())
        .build();
  }

  public static PlayerResponseDto mapToPlayerResponse(Player player) {

    PlayerResponseDto responseDto = PlayerResponseDto
        .builder()
        .age(player.getAge())
        .playerPosition(player.getPlayerPosition())
        .lastName(player.getLastName())
        .name(player.getName())
        .id(player.getId())
        .monthsOfExperience(player.getMonthsOfExperience())
        .build();

    if (player.getTeam() != null) {
      responseDto.setTeamName(player.getTeam().getTeamName());
    }
    return responseDto;
  }
}
