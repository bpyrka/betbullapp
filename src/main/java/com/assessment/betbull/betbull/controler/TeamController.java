package com.assessment.betbull.betbull.controler;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.betbull.betbull.Exception.BusinessException;
import com.assessment.betbull.betbull.request.TeamRequestDto;
import com.assessment.betbull.betbull.response.TeamResponseDto;
import com.assessment.betbull.betbull.service.TeamService;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
@Validated
public class TeamController {

  private final TeamService teamService;

  @PostMapping("/create")
  public ResponseEntity<TeamResponseDto> createTeam(@RequestBody @Valid TeamRequestDto requestDto) {

    return ResponseEntity.ok(teamService.createTeam(requestDto));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<TeamResponseDto> updateTeam(@RequestBody @Valid TeamRequestDto requestDto,
                                                    @PathVariable Long id) throws NotFoundException {

    return ResponseEntity.ok(teamService.updateTeam(id,
                                                    requestDto));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> updateTeam(@PathVariable Long id) throws NotFoundException {
    teamService.deleteById(id);

    return ResponseEntity.ok(String.format("Team with id: %s deleted",
                                           id));
  }

  @PutMapping("/addPlayer/{playerId}/team/{teamId}")
  public ResponseEntity<TeamResponseDto> addPlayerToTeam(@PathVariable Long playerId,
                                                         @PathVariable Long teamId) throws NotFoundException, BusinessException {
    return ResponseEntity.ok(teamService.addPlayerToTeam(playerId,
                                                         teamId));

  }
}
