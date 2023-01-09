package payroll.transactions.payment_related

import payroll.core.GPayrollDatabase
import payroll.core.ServiceCharge
import payroll.core.UnionAffiliation
import payroll.transactions.Transaction
import java.math.BigDecimal
import java.time.LocalDate

class ServiceChargeTransaction(
    val memberId: Int,
    val date: LocalDate,
    val charge: BigDecimal,
) : Transaction {
    override fun execute() {
        val employee = GPayrollDatabase.getUnionMember(this.memberId)
            ?: throw IllegalStateException("Member not found")

        if (employee.affiliation is UnionAffiliation) {
            (employee.affiliation as UnionAffiliation).addServiceCharge(ServiceCharge(date, charge))
        }
    }
}
