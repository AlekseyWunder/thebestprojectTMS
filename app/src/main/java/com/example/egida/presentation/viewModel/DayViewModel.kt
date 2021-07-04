package com.example.egida.presentation.viewModel

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.domain.entity.Day
import com.example.egida.domain.useCase.day.DayUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class DayViewModel : ViewModel() {

    private val dayUseCase: DayUseCase by lazy { Dependencies.dayUseCase() }

    var day = dayUseCase.day
        .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = Day())

    fun save() {
        dayUseCase.createDay(day)
    }

    fun minusRunning(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.running--
                textView.text = it.running.toString()
            }
        }
    }

    fun plusRunning(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.running++
                textView.text = it.running.toString()
            }
        }
    }

    fun minusBikeRide(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.bikeRide--
                textView.text = it.bikeRide.toString()
            }
        }
    }

    fun plusBikeRide(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.bikeRide++
                textView.text = it.bikeRide.toString()
            }
        }
    }

    fun minusSleep(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.sleep--
                textView.text = it.sleep.toString()
            }
        }
    }

    fun plusSleep(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.sleep++
                textView.text = it.sleep.toString()
            }
        }
    }

    fun minusMeal(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.meal--
                textView.text = it.meal.toString()
            }
        }
    }

    fun plusMeal(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.meal++
                textView.text = it.meal.toString()
            }
        }
    }

    fun minusWater(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.water--
                textView.text = it.water.toString()
            }
        }
    }

    fun plusWater(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.water++
                textView.text = it.water.toString()
            }
        }
    }

    fun minusAlcohol(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.alcohol--
                textView.text = it.alcohol.toString()
            }
        }
    }

    fun plusAlcohol(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.alcohol++
                textView.text = it.alcohol.toString()
            }
        }
    }

    fun minusValueWork(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.work--
                textView.text = it.work.toString()
            }
        }
    }

    fun plusValueWork(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.work++
                textView.text = it.work.toString()
            }
        }
    }

    fun minusValueLeisure(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.leisure--
                textView.text = it.leisure.toString()
            }
        }
    }

    fun plusValueLeisure(textView: TextView) {
        viewModelScope.launch {
            day.collect {
                it.leisure++
                textView.text = it.leisure.toString()
            }
        }
    }

}
