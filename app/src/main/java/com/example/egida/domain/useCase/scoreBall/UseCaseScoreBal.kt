package com.example.egida.domain.useCase.scoreBall

import com.example.egida.domain.entity.Day

interface UseCaseScoreBal {
    fun calculationScoreBal(day: Day): Day
}
