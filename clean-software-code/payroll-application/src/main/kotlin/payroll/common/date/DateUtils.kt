package payroll.common.date

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

fun LocalDate.isLastdayOfMonth(): Boolean {
    return (this == this.atLastOfMonth())
}

fun LocalDate.atLastOfMonth(): LocalDate {
    return this.with(TemporalAdjusters.lastDayOfMonth())
}

fun LocalDate.`is`(dayOfWeek: DayOfWeek): Boolean {
    return (this.dayOfWeek == dayOfWeek)
}
