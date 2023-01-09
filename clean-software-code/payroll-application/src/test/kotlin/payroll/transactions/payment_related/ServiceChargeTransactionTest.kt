package payroll.transactions.payment_related

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import payroll.core.GPayrollDatabase
import payroll.core.UnionAffiliation
import payroll.transactions.employee.AddHourlyEmployee
import java.math.BigDecimal
import java.time.LocalDate

class ServiceChargeTransactionTest : StringSpec({
    "ServiceChargeTransaction은 조합원 공재액을 기록한다" {
        // given
        val employeeId = 1
        val memberId = 100
        val today = LocalDate.now()

        // 시간제 직원 생성
        AddHourlyEmployee(
            employeeId = employeeId,
            name = "Bill",
            address = "Seoul",
            hourlyRate = BigDecimal.valueOf(15.25),
        ).execute()

        // 조합원 등록
        var employee = GPayrollDatabase.getEmployee(employeeId)!!
        val unionAffiliation = UnionAffiliation(memberId = memberId, dues = BigDecimal.valueOf(12.5))
        employee.setAffiliation(unionAffiliation)
        GPayrollDatabase.addUnionMember(memberId, employee)

        // when
        val tx = ServiceChargeTransaction(
            memberId = memberId,
            date = today,
            charge = BigDecimal.valueOf(12.95),
        )
        tx.execute()

        // then
        val serviceCharge = unionAffiliation.getServiceCharge(today)
        serviceCharge.charge shouldBe BigDecimal.valueOf(12.95)
    }
})
