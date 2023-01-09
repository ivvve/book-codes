package payroll.transactions.employee

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import payroll.core.GPayrollDatabase
import payroll.core.HourlyClassification
import payroll.core.WeeklySchedule
import java.math.BigDecimal

class ChangeHourlyTransactionTest : StringSpec({
    "ChangeHourlyTransaction는 직원의 임금 종류를 시급으로 변경한다" {
        // given
        val employeeId = 1
        AddSalariedEmployee(
            employeeId = employeeId,
            name = "bob",
            address = "home",
            salary = BigDecimal.valueOf(1_000.0),
        ).execute()

        // when
        val tx = ChangeHourlyTransaction(employeeId = employeeId, hourlyRate = BigDecimal.valueOf(50))
        tx.execute()

        // then
        val employee = GPayrollDatabase.getEmployee(employeeId)!!
        with(employee.classification as HourlyClassification) {
            this.hourlyRate shouldBe BigDecimal.valueOf(50)
        }
        employee.schedule::class shouldBe WeeklySchedule::class
    }
})
