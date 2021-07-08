package com.example.egida.data.cloudSource

import android.util.Log
import com.example.egida.data.localSource.LocalSourceDay
import com.example.egida.domain.entity.Day
import com.example.egida.domain.useCase.day.DayRepository
import com.example.egida.domain.useCase.localsource.LocalSourceDayRepository
import com.example.egida.utils.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*

class DatabaseDay(
    localSourceDay: LocalSourceDay
) : DayRepository, LocalSourceDayRepository {

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
    private var dateMap = mutableMapOf<String, Any>()
    private val date = Calendar.getInstance().time
    private val formatter = SimpleDateFormat(
        "dd-MM-yyyy",
        Locale.getDefault(Locale.Category.FORMAT)
    ) //or use getDateInstance()
    private val CHILD_DAY = formatter.format(date)

    init {
        initFirebase()
        initDatabase()
        CURRENT_UID = AUTH.currentUser?.uid.toString()
    }

    private fun initDay(): Day {
        return Day()
    }
    override var localDay: Day = localSourceDay.localDay

    override fun saveDayInDatabase() {
        scope.launch {
            delay(1000)
            Log.d(TAG, " start fun createDay")
            val map = addDay()
            Log.d(TAG, "dateChildrenMap: ${addDay()} ")
            REF_DATABASE_ROOT.child(NODE_DAY).child(CHILD_DAY).child(CURRENT_UID)
                .updateChildren(map)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "database day complete")
                    }
                }
        }
    }

    override suspend fun getDay() {
        REF_DATABASE_ROOT.child(NODE_DAY).child(CHILD_DAY).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEventListener { data ->
                scope.launch {
                    localDay = data.getValue(Day::class.java) ?: Day()
                    _day.emit(localDay)
                    updateValueDay(localDay)
                }
            })
    }


    private fun addDay(): Map<String, Any> {
        dateMap[CHILD_SCORE_BAL] = localDay.scoreBal
        dateMap[CHILD_WORK] = localDay.work
        dateMap[CHILD_LEISURE] = localDay.leisure
        dateMap[CHILD_MEAL] = localDay.meal
        dateMap[CHILD_WATER] = localDay.water
        dateMap[CHILD_ALCOHOL] = localDay.alcohol
        dateMap[CHILD_RUNNING] = localDay.running
        dateMap[CHILD_BIKE_RIDE] = localDay.bikeRide
        dateMap[CHILD_SLEEP] = localDay.sleep
        Log.d(TAG, "Finish fun addDay $dateMap")
        return dateMap
    }

    override fun updateValueDay(day: Day) {
        localDay.scoreBal = day.scoreBal
        localDay.work = day.work
        localDay.leisure = day.leisure
        localDay.meal = day.meal
        localDay.water = day.water
        localDay.alcohol = day.alcohol
        localDay.running = day.running
        localDay.bikeRide = day.bikeRide
        localDay.sleep = day.sleep
        Log.d(TAG, "update value $localDay")
    }
}
