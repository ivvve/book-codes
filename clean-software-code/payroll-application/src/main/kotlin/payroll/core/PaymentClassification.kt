package payroll.core

import java.math.BigDecimal
import java.time.LocalDate

abstract class PaymentClassification {
    abstract fun calculatePay(payDate: LocalDate): BigDecimal
}

class SalariedClassification(
    val salary: BigDecimal,
) : PaymentClassification() {

    override fun calculatePay(payDate: LocalDate): BigDecimal {
        return this.salary
    }
}

class HourlyClassification(
    // 시급
    val hourlyRate: BigDecimal,
) : PaymentClassification() {
    private val timeCards = mutableListOf<TimeCard>()

    fun addTimeCard(timeCard: TimeCard) {
        this.timeCards.add(timeCard)
    }

    fun getTimeCard(date: LocalDate): TimeCard {
        return this.timeCards.firstOrNull { it.date == date }
            ?: throw IllegalStateException("TimeCard with the date(${date}) not found")
    }

    override fun calculatePay(payDate: LocalDate): BigDecimal {
        val timeCards = this.getTimeCardsInPeriod(payDate)
        return timeCards.sumOf { it.calculatePay(this.hourlyRate) }
    }

    private fun getTimeCardsInPeriod(payDate: LocalDate): List<TimeCard> {
        return this.timeCards // TODO: check TimeCard's date
    }
}

data class TimeCard(
    val date: LocalDate,
    val hours: BigDecimal,
) {
    fun calculatePay(hourlyRate: BigDecimal): BigDecimal {
        return (this.calculateStraightPay(hourlyRate) + this.calculateOvertimePay(hourlyRate))
    }

    private fun calculateStraightPay(hourlyRate: BigDecimal): BigDecimal {
        return (hourlyRate * this.hours)
    }

    private fun calculateOvertimePay(hourlyRate: BigDecimal): BigDecimal {
        if (this.hours <= OVERTIME_HOUR) {
            return BigDecimal.ZERO
        }

        val overtimes = (this.hours - OVERTIME_HOUR)
        return (hourlyRate * overtimes * BigDecimal.valueOf(1.5))
    }

    companion object {
        private val OVERTIME_HOUR: BigDecimal = BigDecimal.valueOf(8.0)
    }
}
