package payroll.transactions.employee

import payroll.core.GPayrollDatabase
import payroll.transactions.Transaction

class DeleteEmployeeTransaction(
    val employeeId: Int,
) : Transaction {
    override fun execute() {
        GPayrollDatabase.deleteEmployee(this.employeeId)
    }
}
