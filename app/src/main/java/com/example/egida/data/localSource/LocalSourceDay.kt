package com.example.egida.data.localSource

import com.example.egida.domain.entity.Day
import com.example.egida.domain.useCase.localsource.LocalSourceDayRepository

class LocalSourceDay : LocalSourceDayRepository {
    override var localDay: Day = Day()
}


