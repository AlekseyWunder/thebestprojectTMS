package com.example.egida.domain.useCase.day

import com.example.egida.domain.entity.Day
import kotlinx.coroutines.flow.Flow

interface DayRepository {
    suspend fun getDay()
    var day: Flow<Day>
    fun createDay(day: Flow<Day>)
//    I'm pretty sure, that there is no real need to pass flow inside of repository.
}
