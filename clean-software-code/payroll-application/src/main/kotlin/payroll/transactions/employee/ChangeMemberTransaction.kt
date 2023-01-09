package payroll.transactions.employee

import payroll.core.Affiliation
import payroll.core.Employee
import payroll.core.GPayrollDatabase
import payroll.core.UnionAffiliation
import java.math.BigDecimal

class ChangeMemberTransaction(
    val memberId: Int,
    val dues: BigDecimal,
    employeeId: Int,
) : ChangeAffiliationTransaction(employeeId) {

    override fun recordMembership(employee: Employee) {
        GPayrollDatabase.addUnionMember(memberId = this.memberId, employee = employee)
    }

    override fun getAffiliation(): Affiliation {
        return UnionAffiliation(memberId = this.memberId, dues = this.dues)
    }
}
