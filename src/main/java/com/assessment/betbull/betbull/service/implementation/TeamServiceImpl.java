package com.assessment.betbull.betbull.service.implementation;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.betbull.betbull.Exception.BusinessException;
import com.assessment.betbull.betbull.Exception.ErrorCode;
import com.assessment.betbull.betbull.entity.Player;
import com.assessment.betbull.betbull.entity.Team;
import com.assessment.betbull.betbull.repository.PlayerRepository;
import com.assessment.betbull.betbull.repository.TeamRepository;
import com.assessment.betbull.betbull.request.TeamRequestDto;
import com.assessment.betbull.betbull.response.TeamResponseDto;
import com.assessment.betbull.betbull.service.TeamService;

import static com.assessment.betbull.betbull.mapper.TeamMapper.mapToTeam;
import static com.assessment.betbull.betbull.mapper.TeamMapper.mapToTeamResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

  private final TeamRepository teamRepository;

  private final PlayerRepository playerRepository;

  @Override
  public TeamResponseDto createTeam(TeamRequestDto requestDto) {

    Team team = mapToTeam(requestDto);

    return mapToTeamResponse(teamRepository.save(team));
  }

  @Override
  public TeamResponseDto updateTeam(Long teamId,
                                    TeamRequestDto requestDto) throws NotFoundException {
    Team team = findById(teamId);
    team.setTeamName(requestDto.getTeamName());
    team.setBudget(requestDto.getBudget());

    return mapToTeamResponse(teamRepository.save(team));

  }

  @Override
  public Team findById(Long teamId) throws NotFoundException {
    return teamRepository
        .findById(teamId)
        .orElseThrow(() -> new NotFoundException(String.format("Could not find team with id: %s",
                                                               teamId)));
  }

  @Override
  public void deleteById(Long teamId) throws NotFoundException {
    Team team = findById(teamId);
    teamRepository.delete(team);

  }

  @Override
  public TeamResponseDto addPlayerToTeam(Long playerId,
                                         Long teamId) throws NotFoundException, BusinessException {

    Player player = playerRepository
        .findById(playerId)
        .orElseThrow(() -> new NotFoundException(String.format("Could not find player with id: %s",
                                                               playerId)));

    Team team = findById(teamId);

    if (player.getTeam() != null) {
      throw new BusinessException(ErrorCode.PLAYER_HAS_TEAM, ErrorCode.PLAYER_HAS_TEAM.getErrorMessage());
    } else {
      team.getPlayers().add(player);
      player.setTeam(team);
    }
    return mapToTeamResponse(teamRepository.save(team));
  }
}
