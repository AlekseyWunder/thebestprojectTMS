package com.example.egida.domain.entity

const val valueScoreBal: Int = 0
const val valueWork: Int = 8
const val valueLeisure: Int = 8
const val valueMeal: Int = 180
const val valueWater: Int = 180
const val valueAlcohol: Int = 180
const val valueRunning: Int = 8
const val valueBikeRide: Int = 8
const val valueSleep: Int = 8

data class Day(
    var scoreBal: Int = valueScoreBal,
    var work: Int = valueWork,
    var leisure: Int = valueLeisure,
    var meal: Int = valueMeal,
    var water: Int = valueWater,
    var alcohol: Int = valueAlcohol,
    var running: Int = valueRunning,
    var bikeRide: Int = valueBikeRide,
    var sleep: Int = valueSleep,
)
