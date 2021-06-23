package com.example.egida.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.egida.Dependencies
import com.example.egida.domain.useCase.day.DayUseCase


class DayViewModel: ViewModel() {

    private val dayUseCase: DayUseCase by lazy { Dependencies.dayUseCase() }

    var day = dayUseCase.day

    fun save() {
        dayUseCase.createDay(day)
    }

}