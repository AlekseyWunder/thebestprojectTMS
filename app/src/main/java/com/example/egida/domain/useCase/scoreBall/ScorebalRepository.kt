package com.example.egida.domain.useCase.scoreBall

import com.example.egida.domain.entity.Day

interface ScorebalRepository {
    fun calculationScoreBal(day: Day): Day
}