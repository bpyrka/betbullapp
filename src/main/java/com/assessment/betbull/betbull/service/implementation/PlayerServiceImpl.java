package com.assessment.betbull.betbull.service.implementation;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.betbull.betbull.entity.Player;
import com.assessment.betbull.betbull.entity.PlayerType.PlayerPosition;
import com.assessment.betbull.betbull.mapper.PlayerMapper;
import com.assessment.betbull.betbull.repository.PlayerRepository;
import com.assessment.betbull.betbull.request.PlayerRequestDto;
import com.assessment.betbull.betbull.response.PlayerResponseDto;
import com.assessment.betbull.betbull.service.PlayerService;

import static com.assessment.betbull.betbull.mapper.PlayerMapper.mapToPlayer;
import static com.assessment.betbull.betbull.mapper.PlayerMapper.mapToPlayerResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

  private final PlayerRepository playerRepository;

  @Override
  public PlayerResponseDto createPlayer(PlayerRequestDto playerRequestDto,
                                        PlayerPosition playerPosition) {
    Player player = mapToPlayer(playerRequestDto);
    player.setPlayerPosition(playerPosition);

    return mapToPlayerResponse(playerRepository.save(player));
  }

  @Override
  public PlayerResponseDto updatePlayer(Long playerId,
                                        PlayerRequestDto playerRequestDto,
                                        PlayerPosition playerPosition) throws NotFoundException {

    Player player = findById(playerId);
    player.setPlayerPosition(playerPosition);
    player.setAge(playerRequestDto.getAge());
    player.setLastName(playerRequestDto.getLastName());
    player.setMonthsOfExperience(playerRequestDto.getMonthsOfExperience());
    player.setName(playerRequestDto.getName());

    return mapToPlayerResponse(playerRepository.save(player));

  }

  @Override
  public Player findById(Long id) throws NotFoundException {
    return playerRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Could not find player with id: %s",
                                                               id)));
  }

  @Override
  public void deleteById(Long id) throws NotFoundException {
    Player player = findById(id);
    playerRepository.delete(player);

  }

  @Override
  public List<PlayerResponseDto> findAllByIdIn(List<Long> ids) {
    List<Player> players = playerRepository.findAllById(ids);

    return players.stream().map(PlayerMapper::mapToPlayerResponse).collect(Collectors.toList());
  }

  @Override
  public List<PlayerResponseDto> findAll() {

    return playerRepository.findAll().stream().map(PlayerMapper::mapToPlayerResponse).collect(Collectors.toList());
  }
}
