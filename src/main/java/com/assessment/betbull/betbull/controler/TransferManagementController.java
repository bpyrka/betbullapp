package com.assessment.betbull.betbull.controler;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.betbull.betbull.Exception.BusinessException;
import com.assessment.betbull.betbull.response.TeamResponseDto;
import com.assessment.betbull.betbull.service.TransferService;

@RestController
@RequestMapping("/api/transfer")
@RequiredArgsConstructor
@Validated
public class TransferManagementController {

  private final TransferService transferService;

  @PutMapping("/transfer/{playerId}/from/{sellerId}/to{buyerId}")
  public ResponseEntity<List<TeamResponseDto>> transferPlayer(@PathVariable Long playerId,
                                                              @PathVariable Long sellerId,
                                                              Long buyerId) throws NotFoundException, BusinessException {
    return ResponseEntity.ok(transferService.transferPlayer(playerId,
                                                            sellerId,
                                                            buyerId));
  }
}
