public class Product {
    private final int id;
    private final String name;
    private final double price;
    private final Category category;

    public Product(int id, String name, double price, Category category) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name required");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public Category getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("[%d] %-12s Rs.%,10.2f  (%s)", id, name, price, category);
    }
}
