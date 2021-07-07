package com.example.egida.domain.useCase.scoreBall

import com.example.egida.data.localSource.LocalCalculationScorbal
import com.example.egida.domain.entity.Day

class UseCaseScoreBalImpl(
    private val localCalculationScorbal: LocalCalculationScorbal
) : UseCaseScoreBal {
    override fun calculationScoreBal(day: Day): Day {
        return localCalculationScorbal.calculationScoreBal(day)
    }

}
