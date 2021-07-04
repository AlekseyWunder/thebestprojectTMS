package com.example.egida.domain.useCase.scoreBall

import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.Flow

interface UseCaseScoreBal {

    fun calculationScoreBal()
    fun gettingParametersHeightAndWeight()
}