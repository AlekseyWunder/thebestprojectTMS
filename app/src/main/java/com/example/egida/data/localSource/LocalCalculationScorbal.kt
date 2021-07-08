package com.example.egida.data.localSource

import android.util.Log
import com.example.egida.Constants
import com.example.egida.domain.entity.Day
import com.example.egida.domain.entity.User
import com.example.egida.domain.useCase.localsource.LocalSourceUserRepository
import com.example.egida.domain.useCase.scoreBall.ScorebalRepository

class LocalCalculationScorbal(
    localSourceUser: LocalSourceUser
) : ScorebalRepository,
    LocalSourceUserRepository {

    companion object {
        const val TAG = "UseCaseScoreBalImpl"
        const val steep: Int = 35
        const val valueBodyMassIndex = 0
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

    private var bodyMassIndex: Int = valueBodyMassIndex
    private var waterNorm: Double = baseValueWaterNorm

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

    override fun calculationScoreBal(day: Day): Day {
        val weight = localUser.weight
        val height = localUser.height
        calculationWaterNorm(weight)
        calculationBodyMassIndex(weight, height)
        Log.d(TAG, " waterNorm $waterNorm")
        Log.d(TAG, " bodyMassIndex $bodyMassIndex")

        day.scoreBal = Constants.baseValueScoreBal
        if (bodyMassIndex.toDouble() < valueBodyMassIndexMin
            || bodyMassIndex.toDouble() > valueBodyMassIndexMax
        ) {
            day.scoreBal = day.scoreBal - steep
            Log.d(TAG, " bodyMassIndex ${day.scoreBal}")
        }

        if ((day.water.toDouble() / 1000) != waterNorm) {
            day.scoreBal = day.scoreBal - steep
            Log.d(TAG, " water ${day.scoreBal}")
        }

        if (day.work > Constants.baseValueWork) {
            day.scoreBal = day.scoreBal - steep
            Log.d(TAG, " work ${day.scoreBal}")
        }

        if (day.leisure >= Constants.baseValueLeisure) {
            Log.d(TAG, " value leisure ${day.leisure}")
            day.scoreBal = day.scoreBal + steep
            Log.d(TAG, " leisure ${day.scoreBal}")
        }

        if (day.alcohol > Constants.baseValueAlcohol) {
            day.scoreBal = day.scoreBal - steep
            Log.d(TAG, " alcohol ${day.scoreBal}")
        }

        if (day.meal < Constants.baseValueMeal ||
            bodyMassIndex.toDouble() > valueMealNormMax
        ) {
            day.scoreBal = day.scoreBal - steep
            Log.d(TAG, " meal ${day.scoreBal}")
        }

        if (day.running > Constants.baseValueRunning) {
            day.scoreBal = day.scoreBal + steep
            Log.d(TAG, " running ${day.scoreBal}")
        }

        if (day.bikeRide > Constants.baseValueBikeRide) {
            day.scoreBal = day.scoreBal + steep
            Log.d(TAG, " bikeRide ${day.scoreBal}")
        }

        if (day.sleep < Constants.baseValueSleep) {
            day.scoreBal = day.scoreBal - steep
            Log.d(TAG, " sleep ${day.scoreBal}")
        }
        return day
    }
}