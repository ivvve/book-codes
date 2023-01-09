package payroll.transactions.payroll

import payroll.core.Employee
import payroll.core.GPayrollDatabase
import payroll.core.Paycheck
import payroll.transactions.Transaction
import java.lang.IllegalStateException
import java.time.LocalDate

class PaydayTransaction(
    val payDate: LocalDate
) : Transaction {
    private var _employeePaychecks = mutableMapOf<Int, Paycheck>()

    override fun execute() {
        val employeeIds = GPayrollDatabase.getAllEmployeeIds()

        for (employeeId in employeeIds) {
            GPayrollDatabase.getEmployee(employeeId)?.let(this::payrollIfPayDate)
        }
    }

    private fun payrollIfPayDate(employee: Employee) {
        if (employee.isPayDate(this.payDate).not()) {
            return
        }

        val paycheck = employee.payday(payDate)
        this._employeePaychecks[employee.id] = paycheck
    }

    fun getPaycheck(employeeId: Int): Paycheck {
        return this._employeePaychecks[employeeId]
            ?: throw IllegalStateException("paycheck of the employee not found")
    }
}
