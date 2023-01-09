package payroll.transactions.employee

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import payroll.core.GPayrollDatabase
import java.math.BigDecimal

class ChangeNameTransactionTest : StringSpec({
    "ChangeNameTransaction은 직원 이름을 변경한다" {
        // given
        val employeeId = 1
        AddHourlyEmployee(
            employeeId = employeeId,
            name = "bob",
            address = "home",
            hourlyRate = BigDecimal.valueOf(1_000.0),
        ).execute()

        // when
        val tx = ChangeNameTransaction(employeeId = employeeId, newName = "sam")
        tx.execute()

        // then
        val employee = GPayrollDatabase.getEmployee(employeeId)!!
        employee.name shouldBe "sam"
    }
})
