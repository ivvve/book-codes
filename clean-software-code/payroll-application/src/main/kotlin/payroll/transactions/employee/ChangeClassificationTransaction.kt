package payroll.transactions.employee

import payroll.core.Employee
import payroll.core.PaymentClassification
import payroll.core.PaymentSchedule

abstract class ChangeClassificationTransaction(
    employeeId: Int,
) : ChangeEmployeeTransaction(employeeId) {

    override fun change(employee: Employee) {
        val classification = this.getClassification()
        employee.changeClassification(classification)
        val schedule = this.getSchedule()
        employee.changeSchedule(schedule)
    }

    abstract fun getClassification(): PaymentClassification

    abstract fun getSchedule(): PaymentSchedule
}
