package com.example.egida.domain.useCase.day

import com.example.egida.domain.entity.Day

interface DayRepository {
    fun createDay(day: Day)
    fun getDay()
    var  day: Day
}