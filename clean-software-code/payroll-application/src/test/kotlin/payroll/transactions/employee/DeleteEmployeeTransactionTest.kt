package payroll.transactions.employee

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import payroll.core.GPayrollDatabase
import java.math.BigDecimal

class DeleteEmployeeTransactionTest : StringSpec({
    "DeleteEmployee는 직원을 삭제한다" {
        // given
        val employeeId = 1
        AddSalariedEmployee(
            employeeId = employeeId,
            name = "bob",
            address = "home",
            salary = BigDecimal.valueOf(1_000.0),
        ).execute()
        var employee = GPayrollDatabase.getEmployee(employeeId)
        employee shouldNotBe null

        // when
        val tx = DeleteEmployeeTransaction(
            employeeId = employeeId,
        )
        tx.execute()

        // then
        employee = GPayrollDatabase.getEmployee(employeeId)
        employee shouldBe null
    }
})
