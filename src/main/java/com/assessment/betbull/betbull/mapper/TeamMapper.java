package com.assessment.betbull.betbull.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.assessment.betbull.betbull.entity.Team;
import com.assessment.betbull.betbull.request.TeamRequestDto;
import com.assessment.betbull.betbull.response.PlayerResponseDto;
import com.assessment.betbull.betbull.response.TeamResponseDto;

public class TeamMapper {

  public static Team mapToTeam(TeamRequestDto teamRequestDto) {
    return Team.builder().budget(teamRequestDto.getBudget()).teamName(teamRequestDto.getTeamName()).build();
  }

  public static TeamResponseDto mapToTeamResponse(Team team) {

    TeamResponseDto teamResponseDto =
        TeamResponseDto.builder().budget(team.getBudget()).id(team.getId()).teamName(team.getTeamName()).build();

    if (team.getPlayers() != null && !team.getPlayers().isEmpty()) {
      Set<PlayerResponseDto> players = team.getPlayers().stream().map(PlayerMapper::mapToPlayerResponse).collect(Collectors.toSet());
      teamResponseDto.setPlayers(players);
    }
    return teamResponseDto;
  }
}
