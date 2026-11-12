import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Store {
    private final Map<Integer, Product> products = new LinkedHashMap<>();

    public Store() {
        products.put(1, new Product(1, "Laptop", 55000, Category.ELECTRONICS));
        products.put(2, new Product(2, "Headphones", 2500, Category.ELECTRONICS));
        products.put(3, new Product(3, "Rice (5kg)", 1200, Category.GROCERY));
        products.put(4, new Product(4, "Shirt", 999, Category.CLOTHING));
        products.put(5, new Product(5, "Notebook", 60, Category.GROCERY));
    }

    public Product getProduct(int id) throws ShopException {
        Product p = products.get(id);
        if (p == null) {
            throw new ShopException("No product with ID " + id);
        }
        return p;
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }
}
