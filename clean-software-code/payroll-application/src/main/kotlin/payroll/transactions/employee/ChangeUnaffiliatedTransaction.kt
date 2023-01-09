package payroll.transactions.employee

import payroll.core.*

class ChangeUnaffiliatedTransaction(
    employeeId: Int,
) : ChangeAffiliationTransaction(employeeId) {

    override fun recordMembership(employee: Employee) {
        if (employee.affiliation is UnionAffiliation) {
            val memberId = (employee.affiliation as UnionAffiliation).memberId
            GPayrollDatabase.removeUnionMember(memberId)
        }
    }

    override fun getAffiliation(): Affiliation {
        return NoAffiliation()
    }
}
