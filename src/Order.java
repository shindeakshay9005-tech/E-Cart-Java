import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int counter = 1000;

    private final int orderId;
    private final Customer customer;
    private final List<CartItem> items;
    private final Discount discount;
    private final double subtotal;
    private final double discountAmount;
    private final double tax;
    private final double total;
    private Payment payment;

    public Order(Customer customer, Cart cart, Discount discount) throws ShopException {
        if (cart.isEmpty()) {
            throw new ShopException("Cart is empty, nothing to order");
        }
        this.orderId = ++counter;
        this.customer = customer;
        this.items = new ArrayList<>(cart.getItems());
        this.discount = discount;

        this.subtotal = cart.getSubtotal();
        this.discountAmount = (discount == null) ? 0
                : Math.min(discount.calculateDiscount(subtotal), subtotal);

        double discounted = subtotal - discountAmount;
        double ratio = (subtotal == 0) ? 0 : discounted / subtotal;
        this.tax = round(cart.getRawTax() * ratio);
        this.total = round(discounted + tax);
    }

    public void pay(Payment payment) throws PaymentException {
        if (this.payment != null) {
            throw new PaymentException("Order #" + orderId + " is already paid");
        }
        payment.pay(total);
        this.payment = payment;
    }

    public double getTotal() {
        return total;
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("------ Order #").append(orderId).append(" ------\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        for (CartItem item : items) {
            sb.append(String.format("%-14s x%-3d Rs.%10.2f%n",
                    item.getProduct().getName(), item.getQuantity(), item.getSubtotal()));
        }
        sb.append(String.format("Subtotal:   Rs.%10.2f%n", subtotal));
        if (discount != null) {
            sb.append(String.format("Discount:  -Rs.%10.2f (%s)%n", discountAmount, discount.getDescription()));
        }
        sb.append(String.format("Tax:        Rs.%10.2f%n", tax));
        sb.append(String.format("TOTAL:      Rs.%10.2f%n", total));
        if (payment != null) {
            sb.append("Paid via:   ").append(payment.getMethodName()).append("\n");
            sb.append("Txn ID:     ").append(payment.getTransactionId()).append("\n");
        }
        return sb.toString();
    }

    private static double round(double value) {
        return Math.round(value * 100) / 100.0;
    }
}
