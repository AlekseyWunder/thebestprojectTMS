package com.example.egida.domain.useCase.day

import com.example.egida.data.DatabaseDay
import com.example.egida.domain.entity.Day
import kotlinx.coroutines.flow.Flow

class DayUseCaseImpl(
    private val databaseDay: DatabaseDay
) : DayUseCase {
    override fun createDay(day: Flow<Day>) {
        return databaseDay.createDay(day)
    }

    override suspend fun getDay() {
        return databaseDay.getDay()
    }

    override var day: Flow<Day>
        get() = databaseDay.day
        set(value) {}

    override fun updateValueDay(day: Flow<Day>) {
        return databaseDay.updateValueDay(day)
    }


}