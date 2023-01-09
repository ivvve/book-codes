package payroll.core

interface PayrollDatabase {
    fun getEmployee(employeeId: Int): Employee?
    fun getAllEmployeeIds(): List<Int>
    fun addEmployee(employeeId: Int, employee: Employee)
    fun updateEmployee(employee: Employee)
    fun deleteEmployee(employeeId: Int)

    fun getUnionMember(memberId: Int): Employee?
    fun addUnionMember(memberId: Int, employee: Employee)
    fun removeUnionMember(memberId: Int)
}

class MemoryPayrollDatabase : PayrollDatabase {
    private val employees = mutableMapOf<Int, Employee>()
    private val members = mutableMapOf<Int, Employee>()

    override fun getEmployee(employeeId: Int): Employee? {
        return this.employees[employeeId]
    }

    override fun getAllEmployeeIds(): List<Int> {
        return this.employees.keys.toList()
    }

    override fun updateEmployee(employee: Employee) {
        this.employees[employee.id] = employee
    }

    override fun addEmployee(employeeId: Int, employee: Employee) {
        this.employees[employeeId] = employee
    }

    override fun deleteEmployee(employeeId: Int) {
        this.employees.remove(employeeId)
    }

    override fun getUnionMember(memberId: Int): Employee? {
        return this.members[memberId]
    }

    override fun addUnionMember(memberId: Int, employee: Employee) {
        this.members[memberId] = employee
    }

    override fun removeUnionMember(memberId: Int) {
        this.members.remove(memberId)
    }
}

val GPayrollDatabase = MemoryPayrollDatabase()
