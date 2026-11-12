public enum Category {
    ELECTRONICS(18.0),
    GROCERY(5.0),
    CLOTHING(12.0);

    private final double taxRate; // percentage

    Category(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
