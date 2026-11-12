public interface Payment {
    void pay(double amount) throws PaymentException;
    String getMethodName();
    String getTransactionId();
}
