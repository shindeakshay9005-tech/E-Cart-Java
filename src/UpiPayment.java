public class UpiPayment extends BasePayment {
    private final String upiId;

    public UpiPayment(String upiId) throws PaymentException {
        if (upiId == null || !upiId.matches("[\\w.-]+@\\w+")) {
            throw new PaymentException("Invalid UPI ID (example: name@bank)");
        }
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) throws PaymentException {
        validateAmount(amount);
        System.out.printf("Collecting Rs.%.2f via UPI from %s%n", amount, upiId);
    }

    @Override
    public String getMethodName() {
        return "UPI (" + upiId + ")";
    }
}
