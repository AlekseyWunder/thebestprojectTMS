package com.example.egida.domain.useCase.day

import com.example.egida.data.DatabaseDay
import com.example.egida.domain.entity.Day

class DayUseCaseImpl(
    private val databaseDay: DatabaseDay
) : DayUseCase {
    override fun createDay(day: Day) {
        return databaseDay.createDay(day)
    }

    override fun getDay() {
        return databaseDay.getDay()
    }

    override var day: Day
        get() = databaseDay.day
        set(value) {}
}