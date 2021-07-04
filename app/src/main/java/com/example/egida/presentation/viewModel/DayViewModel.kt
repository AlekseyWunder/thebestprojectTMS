package com.example.egida.presentation.viewModel

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.domain.useCase.day.DayUseCase
import com.example.egida.domain.useCase.scoreBall.UseCaseScoreBal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DayViewModel : ViewModel() {

    private val dayUseCase: DayUseCase by lazy { Dependencies.dayUseCase() }
    private val scoreBalUseCase: UseCaseScoreBal by lazy { Dependencies.scoreBalUseCase() }
    var day = dayUseCase.day
        .shareIn(viewModelScope, started = SharingStarted.Eagerly, replay = 1)

    fun save() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                scoreBalUseCase.calculationScoreBal()
            }
        }
        dayUseCase.createDay(day)
    }

    fun minusRunning(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.running--
                textView.text = it.running.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun plusRunning(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.running++
                textView.text = it.running.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun minusBikeRide(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.bikeRide--
                textView.text = it.bikeRide.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun plusBikeRide(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.bikeRide++
                textView.text = it.bikeRide.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun minusSleep(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.sleep--
                textView.text = it.sleep.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun plusSleep(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.sleep++
                textView.text = it.sleep.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun minusMeal(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.meal--
                textView.text = it.meal.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun plusMeal(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.meal++
                textView.text = it.meal.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun minusWater(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.water--
                textView.text = it.water.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun plusWater(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.water++
                textView.text = it.water.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun minusAlcohol(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.alcohol--
                textView.text = it.alcohol.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun plusAlcohol(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.alcohol++
                textView.text = it.alcohol.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun minusValueWork(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.work--
                textView.text = it.work.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun plusValueWork(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.work++
                textView.text = it.work.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun minusValueLeisure(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.leisure--
                textView.text = it.leisure.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

    fun plusValueLeisure(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.leisure++
                textView.text = it.leisure.toString()
            }
            dayUseCase.updateValueDay(day)
        }
    }

}
