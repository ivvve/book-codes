package payroll.transactions.employee

import payroll.core.*
import java.math.BigDecimal

class AddSalariedEmployee(
    employeeId: Int,
    name: String,
    address: String,
    val salary: BigDecimal,
) : AddEmployeeTransaction(
    employeeId = employeeId,
    name = name,
    address = address,
) {
    override fun getClassification(): PaymentClassification {
        return SalariedClassification(this.salary)
    }

    override fun getSchedule(): PaymentSchedule {
        return MonthlySchedule()
    }
}
