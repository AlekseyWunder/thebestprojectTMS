package com.example.egida.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.domain.entity.Day
import com.example.egida.domain.useCase.day.DayUseCase
import com.example.egida.domain.useCase.scoreBall.UseCaseScoreBal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DayViewModel : ViewModel() {

    private val dayUseCase: DayUseCase by lazy { Dependencies.dayUseCase() }
    private val scoreBalUseCase: UseCaseScoreBal by lazy { Dependencies.scoreBalUseCase() }
    var day = dayUseCase.day
    var viewModelDay = Day()

    init {
        viewModelScope.launch {
            day.collect {
                viewModelDay = it
            }
//            day.emit(_day)
        }
    }

    fun save() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                scoreBalUseCase.calculationScoreBal(viewModelDay)
            }
        }
        dayUseCase.updateValueDay(viewModelDay)
        dayUseCase.saveDayInDatabase()
    }

    fun minusRunning() {
        viewModelDay.running--
    }

    fun plusRunning() {
        viewModelDay.running++
    }

    fun minusBikeRide() {
        viewModelDay.bikeRide--
    }

    fun plusBikeRide() {
        viewModelDay.bikeRide++
    }

    fun minusSleep() {
        viewModelDay.sleep--
    }

    fun plusSleep() {
        viewModelDay.sleep++
    }

    fun minusMeal() {
        viewModelDay.meal--
    }

    fun plusMeal() {
        viewModelDay.meal++
    }

    fun minusWater() {
        viewModelDay.water--
    }

    fun plusWater() {
        viewModelDay.water++
    }

    fun minusAlcohol() {
        viewModelDay.alcohol--
    }

    fun plusAlcohol() {
        viewModelDay.alcohol++
    }

    fun minusValueWork() {
        viewModelDay.work--
    }

    fun plusValueWork() {
        viewModelDay.work++
    }

    fun minusValueLeisure() {
        viewModelDay.leisure--
    }

    fun plusValueLeisure() {
        viewModelDay.leisure++
    }

}
