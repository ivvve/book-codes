package payroll.transactions.payroll

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import payroll.transactions.employee.AddHourlyEmployee
import payroll.transactions.employee.AddSalariedEmployee
import payroll.transactions.payment_related.TimeCardTransaction
import java.math.BigDecimal
import java.time.LocalDate

class PaydayTransactionTest : StringSpec({
    "Pay single salaried employee test" {
        // given
        val employeeId = 1
        val payDate = LocalDate.of(2001, 11, 30)
        AddSalariedEmployee(
            employeeId = employeeId,
            name = "Bill",
            address = "Seoul",
            salary = BigDecimal.valueOf(1_000),
        ).execute()

        // when
        val tx = PaydayTransaction(payDate)
        tx.execute()

        // then
        val paycheck = tx.getPaycheck(employeeId)
        paycheck.payDate shouldBe payDate
        paycheck.grossPay shouldBe BigDecimal.valueOf(1_000)
        paycheck.deductions shouldBe BigDecimal.ZERO
        paycheck.netPay shouldBe BigDecimal.valueOf(1_000)
    }

    "Pay single hourly employee test" {
        // given
        val employeeId = 1
        val payDate = LocalDate.of(2001, 11, 9) // 금요일
        AddHourlyEmployee(
            employeeId = employeeId,
            name = "Bill",
            address = "Seoul",
            hourlyRate = BigDecimal.valueOf(15.25),
        ).execute()
        // time card
        TimeCardTransaction(
            employeeId = employeeId,
            date = LocalDate.of(2001, 11, 8),
            hours = BigDecimal.valueOf(5),
        ).execute()
        TimeCardTransaction(
            employeeId = employeeId,
            date = LocalDate.of(2001, 11, 9),
            hours = BigDecimal.valueOf(2),
        ).execute()

        // when
        val tx = PaydayTransaction(payDate)
        tx.execute()

        // then
        val paycheck = tx.getPaycheck(employeeId)
        paycheck.payDate shouldBe payDate
        paycheck.grossPay shouldBe BigDecimal.valueOf(106.75)
        paycheck.deductions shouldBe BigDecimal.ZERO
        paycheck.netPay shouldBe BigDecimal.valueOf(106.75)
    }
})
