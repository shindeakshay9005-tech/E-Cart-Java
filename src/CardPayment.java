public class CardPayment extends BasePayment {
    private final String maskedCard;

    public CardPayment(String cardNumber) throws PaymentException {
        String digits = cardNumber.replaceAll("\\s", "");
        if (!digits.matches("\\d{16}")) {
            throw new PaymentException("Card number must be 16 digits");
        }
        this.maskedCard = "**** **** **** " + digits.substring(12);
    }

    @Override
    public void pay(double amount) throws PaymentException {
        validateAmount(amount);
        System.out.printf("Charging Rs.%.2f to card %s%n", amount, maskedCard);
    }

    @Override
    public String getMethodName() {
        return "Card (" + maskedCard + ")";
    }
}
