package com.assessment.betbull.betbull.service.implementation;

import javassist.NotFoundException;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.assessment.betbull.betbull.entity.Player;
import com.assessment.betbull.betbull.entity.PlayerType.PlayerPosition;
import com.assessment.betbull.betbull.request.PlayerRequestDto;
import com.assessment.betbull.betbull.response.PlayerResponseDto;
import com.assessment.betbull.betbull.service.PlayerService;

import static com.assessment.betbull.betbull.service.implementation.utils.FixtureStorage.playerFixtureRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@ActiveProfiles("local")
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayerServiceTest {

  @Autowired
  private PlayerService playerService;

  @Test
  @Order(1)
  public void should_createPlayer() {
    PlayerRequestDto requestDto = playerFixtureRequest(30,
                                                       100);
    PlayerResponseDto result = playerService.createPlayer(requestDto,
                                                          PlayerPosition.DEFENSE);

    assertThat(result).isNotNull();
    assertThat(result.getAge()).isEqualTo(30);
    assertThat(result.getMonthsOfExperience()).isEqualTo(100);

  }

  @Test
  @Order(2)
  public void should_findById() throws NotFoundException {

    Player result = playerService.findById(1L);
    assertThat(result).isNotNull();
    assertThat(result.getAge()).isEqualTo(30);
    assertThat(result.getMonthsOfExperience()).isEqualTo(64);
  }

  @Test
  public void should_throw_when_findById() {

    assertThrows(NotFoundException.class,
                 () -> playerService.findById(100L));
  }

  @Test
  @Order(3)
  public void should_updatePlayer() throws NotFoundException {
    PlayerRequestDto playerResponseDto = playerFixtureRequest(40,
                                                              123);
    Player player = playerService.findById(1L);

    PlayerResponseDto result = playerService.updatePlayer(player.getId(),
                                                          playerResponseDto,
                                                          PlayerPosition.ATTACKER);

    assertThat(result).isNotNull();
    assertThat(result.getAge()).isEqualTo(40);
    assertThat(result.getMonthsOfExperience()).isEqualTo(123);
    assertThat(result.getPlayerPosition()).isEqualTo(PlayerPosition.ATTACKER);

  }

  @Test
  @Order(4)
  public void should_deleteById() throws NotFoundException {
    playerService.deleteById(1L);

    assertThrows(NotFoundException.class,
                 () -> playerService.findById(1L));
  }

  @Test
  public void should_throw_when_deleteById() {

    assertThrows(NotFoundException.class,
                 () -> playerService.deleteById(100L));
  }

  @Test
  public void should_findAllByIdIn() {
    List<Long> ids = List.of(2L,
                             3L);

    List<PlayerResponseDto> result = playerService.findAllByIdIn(ids);

    assertThat(result).hasSize(2);
  }

  @Test
  public void should_findAll() {
    var result = playerService.findAll();

    assertThat(result).hasSize(4);
  }
}