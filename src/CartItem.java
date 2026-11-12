public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increase(int by) {
        this.quantity += by;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    // Tax on this line item, based on the product's category
    public double getTaxAmount() {
        return getSubtotal() * product.getCategory().getTaxRate() / 100.0;
    }
}
