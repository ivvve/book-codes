package payroll.transactions.employee

import payroll.core.HourlyClassification
import payroll.core.PaymentClassification
import payroll.core.PaymentSchedule
import payroll.core.WeeklySchedule
import java.math.BigDecimal

class ChangeHourlyTransaction(
    val hourlyRate: BigDecimal,
    employeeId: Int,
) : ChangeClassificationTransaction(employeeId) {

    override fun getClassification(): PaymentClassification {
        return HourlyClassification(hourlyRate)
    }

    override fun getSchedule(): PaymentSchedule {
        return WeeklySchedule()
    }
}
