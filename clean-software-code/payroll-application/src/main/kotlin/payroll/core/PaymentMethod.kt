package payroll.core

abstract class PaymentMethod {
    abstract fun pay(paycheck: Paycheck)
}

class HoldMethod : PaymentMethod() {
    override fun pay(paycheck: Paycheck) {
        println("hold paycheck")
    }
}
