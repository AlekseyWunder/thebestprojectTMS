package com.example.egida.domain.useCase.day

import com.example.egida.domain.entity.Day
import kotlinx.coroutines.flow.Flow

interface DayRepository {
    suspend fun getDay()
    var day: Flow<Day>
    fun saveDayInDatabase()
    fun updateValueDay(day: Day)
}
