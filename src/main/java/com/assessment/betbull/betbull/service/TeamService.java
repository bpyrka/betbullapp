package com.assessment.betbull.betbull.service;

import javassist.NotFoundException;

import org.springframework.stereotype.Service;

import com.assessment.betbull.betbull.Exception.BusinessException;
import com.assessment.betbull.betbull.entity.Team;
import com.assessment.betbull.betbull.request.TeamRequestDto;
import com.assessment.betbull.betbull.response.TeamResponseDto;

@Service
public interface TeamService {

  TeamResponseDto createTeam(TeamRequestDto requestDto);

  TeamResponseDto updateTeam(Long teamId,
                             TeamRequestDto requestDto) throws NotFoundException;

  Team findById(Long teamId) throws NotFoundException;

  void deleteById(Long teamId) throws NotFoundException;

  TeamResponseDto addPlayerToTeam(Long playerId,
                                  Long teamId) throws NotFoundException, BusinessException;
}
