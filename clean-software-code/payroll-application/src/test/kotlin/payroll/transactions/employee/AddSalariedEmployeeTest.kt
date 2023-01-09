package payroll.transactions.employee

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import payroll.core.GPayrollDatabase
import payroll.core.HoldMethod
import payroll.core.SalariedClassification
import java.math.BigDecimal

class AddSalariedEmployeeTest : StringSpec({
    "AddSalariedEmployee는 월급을 받는 직원을 추가한다" {
        // given
        val employeeId = 1

        // when
        val tx = AddSalariedEmployee(
            employeeId = employeeId,
            name = "Bob",
            address = "Home",
            salary = BigDecimal.valueOf(1_000.0),
        )
        tx.execute()

        // then
        val employee = GPayrollDatabase.getEmployee(employeeId)!!
        employee.name shouldBe "Bob"
        employee.classification::class shouldBe SalariedClassification::class
        employee.method::class shouldBe HoldMethod::class
    }
})
