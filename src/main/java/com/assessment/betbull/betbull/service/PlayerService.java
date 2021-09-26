package com.assessment.betbull.betbull.service;

import javassist.NotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.betbull.betbull.entity.Player;
import com.assessment.betbull.betbull.entity.PlayerType.PlayerPosition;
import com.assessment.betbull.betbull.request.PlayerRequestDto;
import com.assessment.betbull.betbull.response.PlayerResponseDto;

@Service
public interface PlayerService {

  PlayerResponseDto createPlayer(PlayerRequestDto playerRequestDto,
                                 PlayerPosition playerPosition);

  PlayerResponseDto updatePlayer(Long playerId,
                                 PlayerRequestDto playerRequestDto,
                                 PlayerPosition playerPosition) throws NotFoundException;

  Player findById(Long id) throws NotFoundException;

  void deleteById(Long id) throws NotFoundException;

  List<PlayerResponseDto> findAllByIdIn(List<Long> ids);

  List<PlayerResponseDto> findAll();

}
