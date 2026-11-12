# E-Cart: Online Shopping Cart (Java Console Application)

MCA Sem I, OOP Using Java, Collaborative Assessment, Project-4.

## Concepts used
- Encapsulation (private fields, validation in constructors and setters)
- Composition (Cart has CartItem, CartItem has Product, Store has Product)
- Interfaces (Discount, Payment)
- Abstract class and inheritance (BasePayment, CardPayment, UpiPayment, CashPayment)
- Enum (Category with tax rate)
- Custom exceptions (ShopException, PaymentException)
- Collections (ArrayList, LinkedHashMap)
- Menu-driven Main class

## Folder structure
```
E_CART/
├── .gitignore
├── README.md
└── src/
    ├── Main.java
    ├── Category.java
    ├── Product.java
    ├── Customer.java
    ├── CartItem.java
    ├── Cart.java
    ├── Store.java
    ├── Discount.java
    ├── PercentageDiscount.java
    ├── FlatDiscount.java
    ├── Payment.java
    ├── BasePayment.java
    ├── CardPayment.java
    ├── UpiPayment.java
    ├── CashPayment.java
    ├── Order.java
    ├── ShopException.java
    └── PaymentException.java
```

## How to compile and run
Open the `E_CART` folder in VS Code, then run in the terminal:

```
javac -d out src/*.java
java -cp out Main
```

Or open `src/Main.java` and click Run.

## Team members
- Name (Roll No.): Module / class responsibility
- Name (Roll No.): Module / class responsibility
- Name (Roll No.): Module / class responsibility
- Name (Roll No.): Module / class responsibility
