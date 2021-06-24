package com.example.egida.data

import android.util.Log
import com.example.egida.domain.entity.Day
import com.example.egida.domain.useCase.day.DayRepository
import com.example.egida.utils.*

class DatabaseDay() : DayRepository {

    companion object {
        const val TAG = " databaseDay"
        const val NODE_DAY = "day"
        const val CHILD_DAY = "day"// нужно предавать текущую дату
        const val CHILD_SCORE_BAL = "scoreBal"
        const val CHILD_WORK = "work"
        const val CHILD_LEISURE = "leisure"
        const val CHILD_MEAL = "meal"
        const val CHILD_WATER = "water"
        const val CHILD_ALCOHOL = "alcohol"
        const val CHILD_RUNNING = "running"
        const val CHILD_BIKE_RIDE = "bikeRide"
        const val CHILD_SLEEP = "sleep"
    }

    override var day = Day()

    init {
        initFirebase()
        initDatabase()
        UID = AUTH.currentUser?.uid.toString()

    }

    override fun createDay(day: Day) {
        addDay(day)
        REF_DATABASE_ROOT.child(NODE_DAY).child(CHILD_DAY).child(UID).updateChildren(addDay(day))
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "database day complete")
                }
            }
    }

    override fun getDay() {
        REF_DATABASE_ROOT.child(NODE_DAY).child(CHILD_DAY).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {data ->
                day =data.getValue(Day::class.java) ?: Day()
                Log.d(TAG, " load day: $day")
            })
    }

    private fun addDay(day: Day): Map<String, Any> {
        val dateMap = mutableMapOf<String, Any>()
        dateMap[CHILD_SCORE_BAL] = day.scoreBal
        dateMap[CHILD_WORK] = day.work
        dateMap[CHILD_LEISURE] = day.leisure
        dateMap[CHILD_MEAL] = day.meal
        dateMap[CHILD_WATER] = day.water
        dateMap[CHILD_ALCOHOL] = day.alcohol
        dateMap[CHILD_RUNNING] = day.running
        dateMap[CHILD_BIKE_RIDE] = day.bikeRide
        dateMap[CHILD_SLEEP] = day.sleep
        return dateMap
    }
}

