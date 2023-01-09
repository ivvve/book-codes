package payroll.transactions.employee

import payroll.core.Affiliation
import payroll.core.Employee
import payroll.core.GPayrollDatabase

abstract class ChangeAffiliationTransaction(
    employeeId: Int,
) : ChangeEmployeeTransaction(employeeId) {

    override fun change(employee: Employee) {
        this.recordMembership(employee)
        employee.setAffiliation(this.getAffiliation())
        GPayrollDatabase.updateEmployee(employee)
    }

    abstract fun recordMembership(employee: Employee)

    abstract fun getAffiliation(): Affiliation
}

