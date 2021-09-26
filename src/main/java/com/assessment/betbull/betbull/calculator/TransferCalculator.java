package com.assessment.betbull.betbull.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransferCalculator {

  public static BigDecimal calculateContract(Integer monthsOfExperience,
                                                Integer playerAge) {

    var months = BigDecimal.valueOf(monthsOfExperience);
    var age = BigDecimal.valueOf(playerAge);

    BigDecimal transferFee = months.multiply(BigDecimal
                                                 .valueOf(100000)
                                                 .divide(age,
                                                         0,
                                                         RoundingMode.HALF_UP));

    BigDecimal teamCommission = transferFee.multiply(BigDecimal.valueOf(0.1));

    return transferFee.add(teamCommission);

  }
}
