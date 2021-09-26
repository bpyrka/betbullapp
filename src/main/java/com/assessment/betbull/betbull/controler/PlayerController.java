package com.assessment.betbull.betbull.controler;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.betbull.betbull.entity.PlayerType.PlayerPosition;
import com.assessment.betbull.betbull.request.PlayerRequestDto;
import com.assessment.betbull.betbull.response.PlayerResponseDto;
import com.assessment.betbull.betbull.service.PlayerService;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
@Validated
public class PlayerController {

  private final PlayerService playerService;

  @PostMapping("/create")
  public ResponseEntity<PlayerResponseDto> createPlayer(@RequestBody @Valid PlayerRequestDto requestDto,
                                                        @RequestParam PlayerPosition playerPosition) {

    return ResponseEntity.ok(playerService.createPlayer(requestDto,
                                                        playerPosition));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<PlayerResponseDto> updatePlayer(@RequestBody @Valid PlayerRequestDto requestDto,
                                                        @PathVariable Long id,
                                                        @RequestParam PlayerPosition playerPosition) throws NotFoundException {

    return ResponseEntity.ok(playerService.updatePlayer(id,
                                                        requestDto,
                                                        playerPosition));

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deletePlayer(@PathVariable Long id) throws NotFoundException {

    playerService.deleteById(id);
    return ResponseEntity.ok(String.format("Player with id: %s deleted",
                                           id));
  }

  @GetMapping("/players/byids")
  public ResponseEntity<List<PlayerResponseDto>> getAllPlayersById(@RequestParam List<Long> ids){

    return ResponseEntity.ok(playerService.findAllByIdIn(ids));
  }

  @GetMapping("/players/all")
  public ResponseEntity<List<PlayerResponseDto>> getAllPlayers(){

    return ResponseEntity.ok(playerService.findAll());
  }




}