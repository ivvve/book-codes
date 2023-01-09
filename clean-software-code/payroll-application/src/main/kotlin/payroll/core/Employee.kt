package payroll.core

import java.time.LocalDate

class Employee(
    val id: Int,
    var name: String,
    val address: String,
    var classification: PaymentClassification,
    var schedule: PaymentSchedule,
    val method: PaymentMethod,
) {
    var affiliation: Affiliation = NoAffiliation()
        private set

    fun setAffiliation(affiliation: Affiliation) {
        this.affiliation = affiliation
    }

    fun changeName(newName: String) {
        this.name = newName
    }

    fun changeClassification(classification: PaymentClassification) {
        this.classification = classification
    }

    fun changeSchedule(schedule: PaymentSchedule) {
        this.schedule = schedule
    }

    fun payday(payDate: LocalDate): Paycheck {
        val grossPay = this.classification.calculatePay(payDate)
        val deductions = this.affiliation.calculateDeductions(payDate)
        val netPay = grossPay - deductions

        val paycheck = Paycheck(
            payDate = payDate,
            grossPay = grossPay,
            deductions = deductions,
            netPay = netPay,
        )
        this.method.pay(paycheck)
        return paycheck
    }

    fun isPayDate(payDate: LocalDate): Boolean {
        return this.schedule.isPayDate(payDate)
    }
}
