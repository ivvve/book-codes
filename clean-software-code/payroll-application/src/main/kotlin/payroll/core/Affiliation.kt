package payroll.core

import java.math.BigDecimal
import java.time.LocalDate

abstract class Affiliation {
    abstract fun calculateDeductions(payDate: LocalDate): BigDecimal
}

class NoAffiliation : Affiliation() {
    override fun calculateDeductions(payDate: LocalDate): BigDecimal {
        return BigDecimal.ZERO
    }
}

class UnionAffiliation(
    val memberId: Int,
    val dues: BigDecimal,
) : Affiliation() {
    private val serviceCharges = mutableListOf<ServiceCharge>()

    fun addServiceCharge(serviceCharge: ServiceCharge) {
        this.serviceCharges.add(serviceCharge)
    }

    fun getServiceCharge(date: LocalDate): ServiceCharge {
        return this.serviceCharges.firstOrNull { it.date == date }
            ?: throw IllegalArgumentException("service charge not found")
    }

    override fun calculateDeductions(payDate: LocalDate): BigDecimal {
        TODO("Not yet implemented")
    }
}

data class ServiceCharge(
    val date: LocalDate,
    val charge: BigDecimal,
)
