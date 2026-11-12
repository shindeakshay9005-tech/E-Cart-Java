import java.util.UUID;

public abstract class BasePayment implements Payment {
    private final String transactionId;

    protected BasePayment() {
        this.transactionId = "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    protected void validateAmount(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Payment amount must be positive");
        }
    }
}
