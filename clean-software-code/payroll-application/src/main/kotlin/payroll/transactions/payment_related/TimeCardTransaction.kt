package payroll.transactions.payment_related

import payroll.core.GPayrollDatabase
import payroll.core.HourlyClassification
import payroll.core.TimeCard
import payroll.transactions.Transaction
import java.math.BigDecimal
import java.time.LocalDate

class TimeCardTransaction(
    val employeeId: Int,
    val date: LocalDate,
    val hours: BigDecimal,
) : Transaction {
    override fun execute() {
        val employee = GPayrollDatabase.getEmployee(employeeId)
            ?: throw IllegalStateException("Employee not found")

        val timeCard = TimeCard(date = date, hours = hours)
        (employee.classification as HourlyClassification).addTimeCard(timeCard)
    }
}
