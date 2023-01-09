package payroll.transactions.payment_related

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import payroll.core.GPayrollDatabase
import payroll.core.HourlyClassification
import payroll.core.TimeCard
import payroll.transactions.employee.AddHourlyEmployee
import java.math.BigDecimal
import java.time.LocalDate

class TimeCardTransactionTest : StringSpec({
    "TimeCardTransaction은 시급 직원의 타임카드를 등록한다" {
        // given
        val employeeId = 1
        val today = LocalDate.now()

        AddHourlyEmployee(
            employeeId = employeeId,
            name = "Bill",
            address = "Seoul",
            hourlyRate = BigDecimal.valueOf(15.25),
        ).execute()

        // when
        TimeCardTransaction(
            employeeId = employeeId,
            date = today,
            hours = BigDecimal.valueOf(8.0)
        ).execute()

        // then
        var employee = GPayrollDatabase.getEmployee(employeeId)!!
        val hourlyClassification = employee.classification as HourlyClassification
        val timeCard = hourlyClassification.getTimeCard(today)
        timeCard shouldBe TimeCard(date = today, hours = BigDecimal.valueOf(8.0))
    }
})
