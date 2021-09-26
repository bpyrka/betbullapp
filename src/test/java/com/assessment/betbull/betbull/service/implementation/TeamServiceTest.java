package com.assessment.betbull.betbull.service.implementation;

import javassist.NotFoundException;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.assessment.betbull.betbull.Exception.BusinessException;
import com.assessment.betbull.betbull.Exception.ErrorCode;
import com.assessment.betbull.betbull.entity.Player;
import com.assessment.betbull.betbull.entity.Team;
import com.assessment.betbull.betbull.repository.PlayerRepository;
import com.assessment.betbull.betbull.request.TeamRequestDto;
import com.assessment.betbull.betbull.response.TeamResponseDto;
import com.assessment.betbull.betbull.service.TeamService;

import static com.assessment.betbull.betbull.service.implementation.utils.FixtureStorage.teamFixtureRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@ActiveProfiles("local")
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TeamServiceTest {

  @Autowired
  private TeamService teamService;
  @Autowired
  private PlayerRepository playerRepository;

  @Test
  @Order(1)
  public void should_createTeam() {
    TeamRequestDto teamRequestDto = teamFixtureRequest(100000);

    TeamResponseDto result = teamService.createTeam(teamRequestDto);

    assertThat(result).isNotNull();
    assertThat(result.getBudget()).isEqualTo(BigDecimal.valueOf(100000L));
    assertThat(result.getTeamName()).isEqualTo("Mock team");
  }

  @Test
  @Order(2)
  public void should_updateTeam() throws NotFoundException {
    Team team = teamService.findById(1L);

    TeamRequestDto requestDto = teamFixtureRequest(0);

    TeamResponseDto result = teamService.updateTeam(team.getId(),
                                                    requestDto);

    assertThat(result).isNotNull();
    assertThat(result.getBudget()).isEqualTo(BigDecimal.valueOf(0L));
    assertThat(result.getTeamName()).isEqualTo("Mock team");
  }

  @Test
  @Order(3)
  public void findById() throws NotFoundException {
    Team result = teamService.findById(1L);

    assertThat(result).isNotNull();
    assertThat(result.getBudget()).isEqualTo(BigDecimal.valueOf(100000L));
    assertThat(result.getTeamName()).isEqualTo("FC_BARCELONA");

  }

  @Test
  public void should_throw_then_findById() {

    assertThrows(NotFoundException.class,
                 () -> teamService.findById(100L));

  }

  @Test
  @Order(4)
  public void should_deleteById() throws NotFoundException {
    teamService.deleteById(1L);

    assertThrows(NotFoundException.class,
                 () -> teamService.findById(1L));
  }

  @Test
  public void should_throw_when_deleteById() {
    assertThrows(NotFoundException.class,
                 () -> teamService.findById(100L));
  }

  @Test
  @Order(5)
  public void should_addPlayerToTeam() throws NotFoundException, BusinessException {
    teamService.addPlayerToTeam(1L,
                                2L);
    Player player = playerRepository.findById(1L).get();
    Team team = teamService.findById(2L);

    assertThat(team.getPlayers()).contains(player);

  }

  @Test
  @Order(6)
  public void should_throw_when_addPlayerToTeam() throws NotFoundException, BusinessException {
    teamService.addPlayerToTeam(1L,
                                2L);
    Player player = playerRepository.findById(1L).get();
    Team team = teamService.findById(2L);

    assertThat(team.getPlayers()).contains(player);

    BusinessException businessException = assertThrows(BusinessException.class,
                                                       () -> teamService.addPlayerToTeam(1L,
                                                                                         1L));

    assertThat(businessException.getCode()).isEqualTo(ErrorCode.PLAYER_HAS_TEAM);

  }

}