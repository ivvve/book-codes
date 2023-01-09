package payroll.transactions.employee

import payroll.core.Employee

class ChangeNameTransaction(
    val newName: String,
    employeeId: Int,
) : ChangeEmployeeTransaction(employeeId) {
    override fun change(employee: Employee) {
        employee.changeName(newName)
    }
}
