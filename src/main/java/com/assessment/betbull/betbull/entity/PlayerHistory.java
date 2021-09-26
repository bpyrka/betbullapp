package com.assessment.betbull.betbull.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "player")
public class PlayerHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "player_name")
  private String playerName;

  @Column(name = "player_last_name")
  private Long playerlastName;

  @Column(name = "team_name")
  private String teamName;

  @Column(name = "transfer_date")
  private OffsetDateTime transferDate;

  @PrePersist
  public void transfer() {
    transferDate = OffsetDateTime.now();
  }
}
