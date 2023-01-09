package payroll.core

import payroll.common.date.`is`
import payroll.common.date.isLastdayOfMonth
import java.time.DayOfWeek
import java.time.LocalDate

abstract class PaymentSchedule {
    abstract fun isPayDate(payDate: LocalDate): Boolean
}

class MonthlySchedule : PaymentSchedule() {
    override fun isPayDate(payDate: LocalDate): Boolean {
        return payDate.isLastdayOfMonth()
    }
}

class WeeklySchedule : PaymentSchedule() {
    override fun isPayDate(payDate: LocalDate): Boolean {
        return payDate.`is`(DayOfWeek.FRIDAY)
    }
}

class BiweeklySchedule : PaymentSchedule() {
    override fun isPayDate(payDate: LocalDate): Boolean {
        TODO("Not yet implemented")
    }
}
