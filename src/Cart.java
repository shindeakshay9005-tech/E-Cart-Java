import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) throws ShopException {
        if (quantity <= 0) {
            throw new ShopException("Quantity must be positive");
        }
        CartItem existing = findItem(product.getId());
        if (existing != null) {
            existing.increase(quantity);
        } else {
            items.add(new CartItem(product, quantity));
        }
    }

    public void removeItem(int productId) throws ShopException {
        CartItem item = findItem(productId);
        if (item == null) {
            throw new ShopException("Product " + productId + " is not in cart");
        }
        items.remove(item);
    }

    public void updateQuantity(int productId, int quantity) throws ShopException {
        if (quantity <= 0) {
            throw new ShopException("Quantity must be positive (use remove to delete)");
        }
        CartItem item = findItem(productId);
        if (item == null) {
            throw new ShopException("Product " + productId + " is not in cart");
        }
        item.setQuantity(quantity);
    }

    private CartItem findItem(int productId) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                return item;
            }
        }
        return null;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        double sum = 0;
        for (CartItem item : items) sum += item.getSubtotal();
        return sum;
    }

    public double getRawTax() {
        double sum = 0;
        for (CartItem item : items) sum += item.getTaxAmount();
        return sum;
    }

    public void clear() {
        items.clear();
    }
}
