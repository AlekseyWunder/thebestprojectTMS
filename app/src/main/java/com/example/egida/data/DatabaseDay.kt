package com.example.egida.data

import android.util.Log
import com.example.egida.domain.entity.Day
import com.example.egida.domain.useCase.day.DayRepository
import com.example.egida.utils.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*

class DatabaseDay : DayRepository {

    companion object {
        const val TAG = " databaseDay"
        const val NODE_DAY = "day"
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

    private var _day = MutableStateFlow(initDay())
    override var day: Flow<Day> = _day.asStateFlow()
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private var baseDay: Day = Day()
    private var dateMap = mutableMapOf<String, Any>()
    private val date = Calendar.getInstance().time
    private val formatter = SimpleDateFormat("dd-MM-yyyy",Locale.getDefault(Locale.Category.FORMAT)) //or use getDateInstance()
    private val CHILD_DAY = formatter.format(date)
    init {
        initFirebase()
        initDatabase()
        UID = AUTH.currentUser?.uid.toString()
    }

    private fun initDay(): Day {
        return Day()
    }

    override fun createDay(day: Flow<Day>) {
        scope.launch {
            delay(1000)
            Log.d(TAG, " start fun createDay")
            addDay()
            Log.d(TAG, "dateChildrenMap: ${addDay()} ")
            REF_DATABASE_ROOT.child(NODE_DAY).child(CHILD_DAY).child(UID)
                .updateChildren(addDay())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "database day complete")
                    }
                }
        }
    }

    override suspend fun getDay() {
        REF_DATABASE_ROOT.child(NODE_DAY).child(CHILD_DAY).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener { data ->
                scope.launch {
                    _day.emit((data.getValue(Day::class.java) ?: Day()))
                }
                Log.d(TAG, " load day: $day")
            })
    }


    private fun addDay(): Map<String, Any> {
        updateValueDay(day)
        dateMap[CHILD_SCORE_BAL] = baseDay.scoreBal
        dateMap[CHILD_WORK] = baseDay.work
        dateMap[CHILD_LEISURE] = baseDay.leisure
        dateMap[CHILD_MEAL] = baseDay.meal
        dateMap[CHILD_WATER] = baseDay.water
        dateMap[CHILD_ALCOHOL] = baseDay.alcohol
        dateMap[CHILD_RUNNING] = baseDay.running
        dateMap[CHILD_BIKE_RIDE] = baseDay.bikeRide
        dateMap[CHILD_SLEEP] = baseDay.sleep
        Log.d(TAG, "Finish fun addDay $dateMap")
        return dateMap
    }

    override fun updateValueDay(day: Flow<Day>) {
        scope.launch {
            day.collect {
                baseDay.scoreBal = it.scoreBal
                baseDay.work = it.work
                baseDay.leisure = it.leisure
                baseDay.meal = it.meal
                baseDay.water = it.water
                baseDay.alcohol = it.alcohol
                baseDay.running = it.running
                baseDay.bikeRide = it.bikeRide
                baseDay.sleep = it.sleep
            }
        }
    }
}
