package com.example.egida.domain.useCase.scoreBall

import android.util.Log
import com.example.egida.Dependencies
import com.example.egida.domain.entity.UserDatabase
import com.example.egida.domain.useCase.day.DayUseCase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class UseCaseScoreBalImpl : UseCaseScoreBal {
    companion object {
        const val TAG = "UseCaseScoreBalImpl"
        const val steep: Int = 35
    }

    private val dayUseCase: DayUseCase by lazy { Dependencies.dayUseCase() }
    private val userDatabaseUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())
    var day = dayUseCase.day
        .shareIn(scope, started = SharingStarted.Eagerly, replay = 1)
    var user: SharedFlow<UserDatabase> = userDatabaseUseCase.databaseUser
        .shareIn(scope, started = SharingStarted.Lazily, replay = 1)
    private var bodyMassIndex: Int = 0
    private var waterNorm: Double = 0.00
    private var weight: Int = 0
    private var height: Int = 0
    private var userDatabase: UserDatabase = UserDatabase()

    private fun calculationBodyMassIndex(weight: Int, height: Int): Int {

        val doubleHeight = ((height.toDouble() / 100) * (height.toDouble() / 100))
        bodyMassIndex = (weight / doubleHeight).toInt()
        Log.d(TAG, " bodyMassIndex $bodyMassIndex")
        return bodyMassIndex
    }

    private fun calculationWaterNorm(weight: Int): Double {

        when (weight) {
            in 0..9 -> waterNorm = 0.25
            in 9..18 -> waterNorm = 0.5
            in 18..27 -> waterNorm = 0.75
            in 27..36 -> waterNorm = 1.00
            in 36..45 -> waterNorm = 1.25
            in 45..54 -> waterNorm = 1.5
            in 54..63 -> waterNorm = 1.75
            in 63..72 -> waterNorm = 2.00
            in 72..81 -> waterNorm = 2.25
            in 81..90 -> waterNorm = 2.5
            in 90..99 -> waterNorm = 2.75
            in 99..108 -> waterNorm = 3.00
            in 108..117 -> waterNorm = 3.25
            in 117..126 -> waterNorm = 3.50
            in 126..135 -> waterNorm = 3.75
            in 135..144 -> waterNorm = 4.0
        }
        Log.d(TAG, " waterNorm $waterNorm")
        return waterNorm
    }

    override fun calculationScoreBal() {

        scope.launch {

            weight = userDatabase.weight
            height = userDatabase.height
            calculationWaterNorm(weight)
            calculationBodyMassIndex(weight, height)
            Log.d(TAG, " waterNorm $waterNorm")
            Log.d(TAG, " bodyMassIndex $bodyMassIndex")
            day.collect { day ->
                day.scoreBal = 1000
                if (bodyMassIndex.toDouble() < 18.50 || bodyMassIndex.toDouble() > 25) {
                    day.scoreBal = day.scoreBal - steep
                    Log.d(TAG, " bodyMassIndex ${day.scoreBal}")
                }

                if ((day.water.toDouble() / 1000) != waterNorm) {
                    day.scoreBal = day.scoreBal - steep
                    Log.d(TAG, " water ${day.scoreBal}")
                }

                if (day.work > 8) {
                    day.scoreBal = day.scoreBal - steep
                    Log.d(TAG, " work ${day.scoreBal}")
                }

                if (day.leisure >= 3) {
                    day.scoreBal = day.scoreBal + steep
                    Log.d(TAG, " leisure ${day.scoreBal}")
                }

                if (day.alcohol > 0) {
                    day.scoreBal = day.scoreBal - steep
                    Log.d(TAG, " alcohol ${day.scoreBal}")
                }

                if (day.meal < 2000 || bodyMassIndex.toDouble() > 2800) {
                    day.scoreBal = day.scoreBal - steep
                    Log.d(TAG, " meal ${day.scoreBal}")
                }

                if (day.running > 0) {
                    day.scoreBal = day.scoreBal + steep
                    Log.d(TAG, " running ${day.scoreBal}")
                }

                if (day.bikeRide > 0) {
                    day.scoreBal = day.scoreBal + steep
                    Log.d(TAG, " bikeRide ${day.scoreBal}")
                }

                if (day.sleep < 7) {
                    day.scoreBal = day.scoreBal - steep
                    Log.d(TAG, " sleep ${day.scoreBal}")
                }
            }
        }
    }

    override fun gettingParametersHeightAndWeight() {
        scope.launch {
            user.collect {
                userDatabase.weight = it.weight
                userDatabase.height = it.height
                Log.d(TAG, " weight ${userDatabase.weight}")
                Log.d(TAG, " height ${userDatabase.height}")
            }
        }
    }
}