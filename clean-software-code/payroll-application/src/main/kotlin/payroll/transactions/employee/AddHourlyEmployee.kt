package payroll.transactions.employee

import payroll.core.HourlyClassification
import payroll.core.PaymentClassification
import payroll.core.PaymentSchedule
import payroll.core.WeeklySchedule
import java.math.BigDecimal

class AddHourlyEmployee(
    employeeId: Int,
    name: String,
    address: String,
    val hourlyRate: BigDecimal,
) : AddEmployeeTransaction(
    employeeId = employeeId,
    name = name,
    address = address,
) {
    override fun getClassification(): PaymentClassification {
        return HourlyClassification(this.hourlyRate)
    }

    override fun getSchedule(): PaymentSchedule {
        return WeeklySchedule()
    }
}
