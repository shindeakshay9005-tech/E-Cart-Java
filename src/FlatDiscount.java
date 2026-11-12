public class FlatDiscount implements Discount {
    private final double amount;

    public FlatDiscount(double amount) throws ShopException {
        if (amount <= 0) {
            throw new ShopException("Flat discount must be positive");
        }
        this.amount = amount;
    }

    @Override
    public double calculateDiscount(double subtotal) {
        return Math.min(amount, subtotal);
    }

    @Override
    public String getDescription() {
        return "Flat Rs." + amount + " off";
    }
}
