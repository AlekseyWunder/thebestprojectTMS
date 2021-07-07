package com.example.egida.domain.useCase.scoreBall

import android.util.Log
import com.example.egida.Constants
import com.example.egida.data.localSource.LocalSourceDay
import com.example.egida.data.localSource.LocalSourceUser
import com.example.egida.domain.entity.Day
import com.example.egida.domain.entity.User
import com.example.egida.domain.useCase.localsource.localSourceUser.LocalSourceUserUsecase
import com.example.egida.domain.useCase.localsource.localeSourceDay.LocalSourceDayUsecase

class UseCaseScoreBalImpl(
    localSourceDay: LocalSourceDay,
    localSourceUser: LocalSourceUser
) : UseCaseScoreBal, LocalSourceDayUsecase, LocalSourceUserUsecase {

    companion object {
        const val TAG = "UseCaseScoreBalImpl"
        const val steep: Int = 35
        const val baseValueWaterNorm: Double = 0.00
        const val valueWaterNormMin = 0.25
        const val valueWaterNormMiddle = 0.5
        const val valueWaterNormMax = 0.75
        const val valueWaterNormOne = 1.00
        const val valueWaterNormTwo = 2.00
        const val valueWaterNormThree = 3.00
        const val valueWaterNormFour = 4.00
        const val valueBodyMassIndexMin = 18.5
        const val valueBodyMassIndexMax = 25
        const val valueMealNormMax = 2800
    }

    private var bodyMassIndex: Int = 0
    private var waterNorm: Double = baseValueWaterNorm

    override var localDay: Day = localSourceDay.localDay
    override var localUser: User = localSourceUser.localUser

    private fun calculationBodyMassIndex(weight: Int, height: Int): Int {

        val doubleHeight = ((height.toDouble() / 100) * (height.toDouble() / 100))
        bodyMassIndex = (weight / doubleHeight).toInt()
        Log.d(TAG, " bodyMassIndex $bodyMassIndex")
        return bodyMassIndex
    }

    private fun calculationWaterNorm(weight: Int): Double {

        when (weight) {
            in 0 until 9 -> waterNorm = valueWaterNormMin
            in 9 until 18 -> waterNorm = valueWaterNormMiddle
            in 18 until 27 -> waterNorm = valueWaterNormMax
            in 27 until 36 -> waterNorm = valueWaterNormOne
            in 36 until 45 -> waterNorm = valueWaterNormOne + valueWaterNormMin
            in 45 until 54 -> waterNorm = valueWaterNormOne + valueWaterNormMiddle
            in 54 until 63 -> waterNorm = valueWaterNormOne + valueWaterNormMax
            in 63 until 72 -> waterNorm = valueWaterNormTwo
            in 72 until 81 -> waterNorm = valueWaterNormTwo + valueWaterNormMin
            in 81 until 90 -> waterNorm = valueWaterNormTwo + valueWaterNormMiddle
            in 90 until 99 -> waterNorm = valueWaterNormTwo + valueWaterNormMax
            in 99 until 108 -> waterNorm = valueWaterNormThree
            in 108 until 117 -> waterNorm = valueWaterNormThree + valueWaterNormMin
            in 117 until 126 -> waterNorm = valueWaterNormThree + valueWaterNormMiddle
            in 126 until 135 -> waterNorm = valueWaterNormThree + valueWaterNormMax
            in 135 until 144 -> waterNorm = valueWaterNormFour
        }
        Log.d(TAG, " waterNorm $waterNorm")
        return waterNorm
    }

    override fun calculationScoreBal() {

        val weight = localUser.weight
        val height = localUser.height
        calculationWaterNorm(weight)
        calculationBodyMassIndex(weight, height)
        Log.d(TAG, " waterNorm $waterNorm")
        Log.d(TAG, " bodyMassIndex $bodyMassIndex")

        localDay.scoreBal = Constants.baseValueScoreBal
        if (bodyMassIndex.toDouble() < valueBodyMassIndexMin
            || bodyMassIndex.toDouble() > valueBodyMassIndexMax
        ) {
            localDay.scoreBal = localDay.scoreBal - steep
            Log.d(TAG, " bodyMassIndex ${localDay.scoreBal}")
        }

        if ((localDay.water.toDouble() / 1000) != waterNorm) {
            localDay.scoreBal = localDay.scoreBal - steep
            Log.d(TAG, " water ${localDay.scoreBal}")
        }

        if (localDay.work > Constants.baseValueWork) {
            localDay.scoreBal = localDay.scoreBal - steep
            Log.d(TAG, " work ${localDay.scoreBal}")
        }

        if (localDay.leisure >= Constants.baseValueLeisure) {
            localDay.scoreBal = localDay.scoreBal + steep
            Log.d(TAG, " leisure ${localDay.scoreBal}")
        }

        if (localDay.alcohol > Constants.baseValueAlcohol) {
            localDay.scoreBal = localDay.scoreBal - steep
            Log.d(TAG, " alcohol ${localDay.scoreBal}")
        }

        if (localDay.meal < Constants.baseValueMeal ||
            bodyMassIndex.toDouble() > valueMealNormMax
        ) {
            localDay.scoreBal = localDay.scoreBal - steep
            Log.d(TAG, " meal ${localDay.scoreBal}")
        }

        if (localDay.running > Constants.baseValueRunning) {
            localDay.scoreBal = localDay.scoreBal + steep
            Log.d(TAG, " running ${localDay.scoreBal}")
        }

        if (localDay.bikeRide > Constants.baseValueBikeRide) {
            localDay.scoreBal = localDay.scoreBal + steep
            Log.d(TAG, " bikeRide ${localDay.scoreBal}")
        }

        if (localDay.sleep < Constants.baseValueSleep) {
            localDay.scoreBal = localDay.scoreBal - steep
            Log.d(TAG, " sleep ${localDay.scoreBal}")
        }
    }
}
