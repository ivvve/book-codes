package payroll.transactions.employee

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import payroll.core.GPayrollDatabase
import payroll.core.UnionAffiliation
import java.math.BigDecimal

class ChangeMemberTransactionTest : StringSpec({
    "ChangeMemberTransaction은 직원의 조합 정보를 기록한다" {
        // given
        val employeeId = 1
        val memberId = 100
        AddSalariedEmployee(
            employeeId = employeeId,
            name = "bob",
            address = "home",
            salary = BigDecimal.valueOf(1_000.0),
        ).execute()

        // when
        val tx = ChangeMemberTransaction(employeeId = employeeId, memberId = memberId, dues = BigDecimal.valueOf(99.42))
        tx.execute()

        // then
        val employee = GPayrollDatabase.getEmployee(employeeId)!!
        with(employee.affiliation as UnionAffiliation) {
            this.memberId shouldBe memberId
            this.dues shouldBe BigDecimal.valueOf(99.42)
        }

        // Database도 적용되어야함
        employee shouldBe GPayrollDatabase.getUnionMember(memberId)
    }
})
