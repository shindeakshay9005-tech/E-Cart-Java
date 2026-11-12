public class CashPayment extends BasePayment {

    @Override
    public void pay(double amount) throws PaymentException {
        validateAmount(amount);
        System.out.printf("Collected Rs.%.2f in cash%n", amount);
    }

    @Override
    public String getMethodName() {
        return "Cash";
    }
}
