package payroll.core

import java.math.BigDecimal
import java.time.LocalDate

data class Paycheck(
    val payDate: LocalDate,
    val grossPay: BigDecimal = BigDecimal.ZERO,
    val deductions: BigDecimal = BigDecimal.ZERO,
    val netPay: BigDecimal = BigDecimal.ZERO,
)
