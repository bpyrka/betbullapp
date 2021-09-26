package com.assessment.betbull.betbull.service.implementation;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.betbull.betbull.Exception.BusinessException;
import com.assessment.betbull.betbull.Exception.ErrorCode;
import com.assessment.betbull.betbull.entity.Player;
import com.assessment.betbull.betbull.entity.Team;
import com.assessment.betbull.betbull.mapper.TeamMapper;
import com.assessment.betbull.betbull.repository.PlayerRepository;
import com.assessment.betbull.betbull.repository.TeamRepository;
import com.assessment.betbull.betbull.response.TeamResponseDto;
import com.assessment.betbull.betbull.service.TransferService;

import static com.assessment.betbull.betbull.calculator.TransferCalculator.calculateContract;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {

  private final PlayerRepository playerRepository;

  private final TeamRepository teamRepository;

  @Override
  public List<TeamResponseDto> transferPlayer(Long playerId,
                                              Long sellerId,
                                              Long buyerId) throws NotFoundException, BusinessException {

    log.warn("TRYING TO TRANSFER PLAYER");
    Player player = playerRepository
        .findById(playerId)
        .orElseThrow(() -> new NotFoundException(String.format("Could not find player with id: %s",
                                                               playerId)));

    Team seller = teamRepository
        .findById(sellerId)
        .orElseThrow(() -> new NotFoundException(String.format("Could not find team with id: %s",
                                                               sellerId)));

    Team buyer = teamRepository
        .findById(buyerId)
        .orElseThrow(() -> new NotFoundException(String.format("Could not find team with id: %s",
                                                               buyerId)));

    BigDecimal contractFee = calculateContract(player.getMonthsOfExperience(),
                                               player.getAge());

    boolean buyerBudgetValidation = budgetValidator(buyer.getBudget(),
                                                    contractFee);

    if (buyerBudgetValidation) {
      if (isPlayerInTeam(seller.getPlayers(),
                         player)) {

        buyer.getPlayers().add(player);
        buyer.setBudget(buyer.getBudget().subtract(contractFee));

        seller.getPlayers().remove(player);
        seller.setBudget(seller.getBudget().add(contractFee));
        player.setTeam(buyer);

      } else {
        throw new BusinessException(ErrorCode.PLAYER_NOT_FOUND,
                                    ErrorCode.PLAYER_NOT_FOUND.getErrorMessage());
      }
    } else {
      throw new BusinessException(ErrorCode.TEAM_OUT_OF_BUDGET,
                                  ErrorCode.TEAM_OUT_OF_BUDGET.getErrorMessage());
    }
    log.warn("TRANSFER COMPLETED SUCCESSFULLY");
    List<Team> savedTeams = teamRepository.saveAll(List.of(seller,
                                                           buyer));
    return savedTeams.stream().map(TeamMapper::mapToTeamResponse).collect(Collectors.toList());
  }

  private boolean isPlayerInTeam(Set<Player> players,
                                 Player player) {

    return players.contains(player);

  }

  private boolean budgetValidator(BigDecimal teamBudget,
                                  BigDecimal contractFee) {

    BigDecimal result = teamBudget.subtract(contractFee);

    return result.compareTo(BigDecimal.ZERO) > 0;
  }
}
