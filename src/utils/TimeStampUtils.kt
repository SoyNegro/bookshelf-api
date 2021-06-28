package dev.coffeecult.workshopmanager.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

object TimeStampUtils {
    val DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val TIME_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val DATE_TIME_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

    private val FIRST_DAY_OF_WEEK: DayOfWeek = WeekFields.of(Locale.FRANCE).firstDayOfWeek
    private val LAST_DAY_OF_WEEK: DayOfWeek = DayOfWeek.SUNDAY

    fun firstDayOfCurrentMonth(): LocalDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth())

    fun lastDayOfCurrentMonth(): LocalDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())

    fun firstDayOfCurrentWeek(): LocalDate = LocalDate.now()
        .with(TemporalAdjusters.previous(FIRST_DAY_OF_WEEK.minus(1L)))

    fun lastDayOfCurrentWeek(): LocalDate = LocalDate.now().with(TemporalAdjusters.next(LAST_DAY_OF_WEEK))


    fun firstDayOfCurrentYear(): LocalDate = LocalDate.now().with(TemporalAdjusters.firstDayOfYear())

    fun lastDayOfCurrentYear(): LocalDate = LocalDate.now().with(TemporalAdjusters.lastDayOfYear())
}