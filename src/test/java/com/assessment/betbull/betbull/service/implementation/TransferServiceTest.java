package com.assessment.betbull.betbull.service.implementation;

import javassist.NotFoundException;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
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
import com.assessment.betbull.betbull.repository.TeamRepository;
import com.assessment.betbull.betbull.response.TeamResponseDto;
import com.assessment.betbull.betbull.service.TeamService;
import com.assessment.betbull.betbull.service.TransferService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@ActiveProfiles("local")
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransferServiceTest {

  @Autowired
  private TransferService transferService;
  @Autowired
  PlayerRepository playerRepository;
  @Autowired
  TeamRepository teamRepository;
  @Autowired
  TeamService teamService;

  @Test
  void should_transferPlayer() throws NotFoundException, BusinessException {
    Team seller = teamRepository.findById(1L).get();

    Team buyer = teamRepository.findById(2L).get();

    Player player = playerRepository.findById(1L).get();

    TeamResponseDto sellerTeam = teamService.addPlayerToTeam(1L,
                                                             1L);

    assertThat(seller.getPlayers()).contains(player);

    assertThat(seller.getBudget()).isEqualTo(BigDecimal.valueOf(100000L));

    assertThat(buyer.getBudget()).isEqualTo(BigDecimal.valueOf(10000000L));

    List<TeamResponseDto> result = transferService.transferPlayer(1L,
                                                                  1L,
                                                                  2L);

    assertThat(seller.getPlayers()).hasSize(0);
    assertThat(buyer.getPlayers()).contains(player);

    assertThat(seller.getBudget()).isEqualTo(BigDecimal.valueOf(334643.2));

    assertThat(buyer.getBudget()).isEqualTo(BigDecimal.valueOf(9765356.8));

  }

  @Test
  void should_throw_when_transferPlayer__budget_exception() throws NotFoundException, BusinessException {
    Team seller = teamRepository.findById(1L).get();

    Team buyer = teamRepository.findById(4L).get();

    Player player = playerRepository.findById(2L).get();

    TeamResponseDto sellerTeam = teamService.addPlayerToTeam(2L,
                                                             1L);

    assertThat(seller.getPlayers()).contains(player);

    assertThat(seller.getBudget()).isEqualTo(BigDecimal.valueOf(100000L));

    assertThat(buyer.getBudget()).isEqualTo(BigDecimal.valueOf(25000));

    BusinessException businessException = assertThrows(BusinessException.class,
                                                       () -> transferService.transferPlayer(1L,
                                                                                            1L,
                                                                                            4L));

    assertThat(businessException.getCode()).isEqualTo(ErrorCode.TEAM_OUT_OF_BUDGET);

  }

  @Test
  void should_throw_when_transferPlayer__player_not_in_team() throws NotFoundException, BusinessException {

    BusinessException businessException = assertThrows(BusinessException.class,
                                                       () -> transferService.transferPlayer(3L,
                                                                                            1L,
                                                                                            2L));

    assertThat(businessException.getCode()).isEqualTo(ErrorCode.PLAYER_NOT_FOUND);
  }
}