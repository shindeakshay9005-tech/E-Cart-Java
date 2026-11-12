import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Store store = new Store();
    private static final Cart cart = new Cart();
    private static Customer customer;
    private static Discount discount = null;

    public static void main(String[] args) {
        System.out.println("=== Welcome to E-Cart ===");
        customer = createCustomer();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose option: ");
            try {
                switch (choice) {
                    case 1 -> showProducts();
                    case 2 -> addToCart();
                    case 3 -> removeFromCart();
                    case 4 -> updateQuantity();
                    case 5 -> viewCart();
                    case 6 -> applyCoupon();
                    case 7 -> checkout();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option, try again.");
                }
            } catch (ShopException | PaymentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Thank you for shopping with E-Cart!");
    }

    private static void printMenu() {
        System.out.println("\n1. Show products");
        System.out.println("2. Add to cart");
        System.out.println("3. Remove from cart");
        System.out.println("4. Update quantity");
        System.out.println("5. View cart");
        System.out.println("6. Apply / remove coupon");
        System.out.println("7. Checkout");
        System.out.println("0. Exit");
    }

    private static Customer createCustomer() {
        while (true) {
            try {
                String name = readLine("Enter your name: ");
                String email = readLine("Enter your email: ");
                return new Customer(1, name, email);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage() + ". Please try again.");
            }
        }
    }

    private static void showProducts() {
        System.out.println("\n--- Products ---");
        for (Product p : store.getAllProducts()) {
            System.out.println(p);
        }
    }

    private static void addToCart() throws ShopException {
        int id = readInt("Product ID: ");
        int qty = readInt("Quantity: ");
        Product p = store.getProduct(id);
        cart.addItem(p, qty);
        System.out.println(p.getName() + " added to cart.");
    }

    private static void removeFromCart() throws ShopException {
        int id = readInt("Product ID to remove: ");
        cart.removeItem(id);
        System.out.println("Item removed.");
    }

    private static void updateQuantity() throws ShopException {
        int id = readInt("Product ID: ");
        int qty = readInt("New quantity: ");
        cart.updateQuantity(id, qty);
        System.out.println("Quantity updated.");
    }

    private static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\n--- Your Cart ---");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%-14s x%-3d Rs.%10.2f%n",
                    item.getProduct().getName(), item.getQuantity(), item.getSubtotal());
        }
        System.out.printf("Subtotal (before tax): Rs.%.2f%n", cart.getSubtotal());
    }

    private static void applyCoupon() throws ShopException {
        System.out.println("1. Percentage coupon");
        System.out.println("2. Flat amount coupon");
        System.out.println("3. Remove coupon");
        int type = readInt("Choose: ");
        switch (type) {
            case 1 -> discount = new PercentageDiscount(readDouble("Percent off (1-100): "));
            case 2 -> discount = new FlatDiscount(readDouble("Flat amount off: "));
            case 3 -> discount = null;
            default -> System.out.println("Invalid choice.");
        }
        System.out.println("Coupon updated.");
    }

    private static void checkout() throws ShopException, PaymentException {
        Order order = new Order(customer, cart, discount);
        System.out.println();
        System.out.println(order.getSummary());

        System.out.println("Payment method:");
        System.out.println("1. Card   2. UPI   3. Cash");
        int method = readInt("Choose: ");

        Payment payment;
        switch (method) {
            case 1 -> payment = new CardPayment(readLine("Card number (16 digits): "));
            case 2 -> payment = new UpiPayment(readLine("UPI ID: "));
            case 3 -> payment = new CashPayment();
            default -> throw new ShopException("Invalid payment method");
        }

        order.pay(payment);
        System.out.println("\nPayment successful!");
        System.out.println(order.getSummary());

        cart.clear();
        discount = null;
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
