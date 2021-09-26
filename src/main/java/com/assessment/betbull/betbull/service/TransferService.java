package com.assessment.betbull.betbull.service;

import javassist.NotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.betbull.betbull.Exception.BusinessException;
import com.assessment.betbull.betbull.response.TeamResponseDto;

@Service
public interface TransferService {

  List<TeamResponseDto> transferPlayer(Long playerId, Long sellerId, Long buyerId) throws NotFoundException, BusinessException;
}
