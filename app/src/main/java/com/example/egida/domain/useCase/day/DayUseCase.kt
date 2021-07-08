package com.example.egida.domain.useCase.day

import com.example.egida.domain.entity.Day
import kotlinx.coroutines.flow.Flow

interface DayUseCase {
    fun saveDayInDatabase()
    suspend fun getDay()
    var day: Flow<Day>
    fun updateValueDay(day: Day)
}