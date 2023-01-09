package payroll.transactions.employee

import payroll.core.*
import payroll.transactions.Transaction

abstract class AddEmployeeTransaction(
    protected val employeeId: Int,
    protected val name: String,
    protected val address: String,
) : Transaction {
    final override fun execute() {
        val employee = Employee(
            id = this.employeeId,
            name = this.name,
            address = this.address,
            classification = this.getClassification(),
            schedule = this.getSchedule(),
            method = HoldMethod(),
        )

        GPayrollDatabase.addEmployee(this.employeeId, employee)
    }

    abstract fun getClassification(): PaymentClassification

    abstract fun getSchedule(): PaymentSchedule
}
