package payroll.transactions.employee

import payroll.core.Employee
import payroll.core.GPayrollDatabase
import payroll.transactions.Transaction

abstract class ChangeEmployeeTransaction(
    val employeeId: Int,
) : Transaction {
    override fun execute() {
        val employee = GPayrollDatabase.getEmployee(employeeId)
            ?: throw IllegalStateException("Employee not found")

        this.change(employee)

        GPayrollDatabase.updateEmployee(employee)
    }

    abstract fun change(employee: Employee)
}
