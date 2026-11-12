public class PercentageDiscount implements Discount {
    private final double percent;

    public PercentageDiscount(double percent) throws ShopException {
        if (percent <= 0 || percent > 100) {
            throw new ShopException("Percent must be between 0 and 100");
        }
        this.percent = percent;
    }

    @Override
    public double calculateDiscount(double subtotal) {
        return subtotal * percent / 100.0;
    }

    @Override
    public String getDescription() {
        return percent + "% off";
    }
}
