package com.example.egida.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.domain.entity.Day
import com.example.egida.domain.useCase.day.DayUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


class DayViewModel : ViewModel() {

    private val dayUseCase: DayUseCase by lazy { Dependencies.dayUseCase() }

    var day = dayUseCase.day
        .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = Day())

    fun save() {
        dayUseCase.createDay(day)
    }
}
