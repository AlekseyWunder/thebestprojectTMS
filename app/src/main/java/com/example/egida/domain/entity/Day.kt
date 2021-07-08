package com.example.egida.domain.entity

import com.example.egida.Constants.baseValueAlcohol
import com.example.egida.Constants.baseValueBikeRide
import com.example.egida.Constants.baseValueLeisure
import com.example.egida.Constants.baseValueMeal
import com.example.egida.Constants.baseValueRunning
import com.example.egida.Constants.baseValueScoreBal
import com.example.egida.Constants.baseValueSleep
import com.example.egida.Constants.baseValueWater
import com.example.egida.Constants.baseValueWork

data class Day(
    var scoreBal: Int = baseValueScoreBal,
    var work: Int = baseValueWork,
    var leisure: Int = baseValueLeisure,
    var meal: Int = baseValueMeal,
    var water: Int = baseValueWater,
    var alcohol: Int = baseValueAlcohol,
    var running: Int = baseValueRunning,
    var bikeRide: Int = baseValueBikeRide,
    var sleep: Int = baseValueSleep,
)
