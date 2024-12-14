import java.util.*;

// Customer Class
class Customer {
    private int id;
    private String name;
    private int loyaltyPoints;

    public Customer(int id, String name, int loyaltyPoints) {
        this.id = id;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateLoyaltyPoints(int points) {
        this.loyaltyPoints = points;
    }

    @Override
    public String toString() {
        return "Customer[ID=" + id + ", Name=" + name + ", Loyalty Points=" + loyaltyPoints + "]";
    }
}

// Product Class
class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product[ID=" + id + ", Name=" + name + ", Price=" + price + "]";
    }
}

// Order Class
class Order {
    private int id;
    private int customerId;
    private Date deliveryDate;
    private Set<Product> products;

    public Order(int id, int customerId, Date deliveryDate) {
        this.id = id;
        this.customerId = customerId;
        this.deliveryDate = deliveryDate;
        this.products = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Order[ID=" + id + ", Customer ID=" + customerId + ", Delivery Date=" + deliveryDate + ", Products=" + products + "]";
    }
}

// Comparators
class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

class CustomerLoyaltyComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return Integer.compare(c2.getLoyaltyPoints(), c1.getLoyaltyPoints());
    }
}

public class lab7 {
    public static void main(String[] args) {
        // Create Customers
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Alice", 150));
        customers.add(new Customer(2, "Bob", 200));
        customers.add(new Customer(3, "Charlie", 120));

        // Create Products
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(101, "Laptop", 750.0));
        products.add(new Product(102, "Phone", 500.0));
        products.add(new Product(103, "Tablet", 300.0));

        // Create Orders
        ArrayList<Order> orders = new ArrayList<>();
        Order order1 = new Order(1, 1, new Date());
        order1.addProduct(products.get(0));
        order1.addProduct(products.get(1));
        orders.add(order1);

        Order order2 = new Order(2, 2, new Date());
        order2.addProduct(products.get(2));
        orders.add(order2);

        // Fast Retrieval with HashMap
        HashMap<Integer, Customer> customerMap = new HashMap<>();
        for (Customer c : customers) {
            customerMap.put(c.getId(), c);
        }

        HashMap<Integer, Product> productMap = new HashMap<>();
        for (Product p : products) {
            productMap.put(p.getId(), p);
        }

        // TreeSet for Sorted Products by Price
        TreeSet<Product> sortedProducts = new TreeSet<>(new ProductPriceComparator());
        sortedProducts.addAll(products);

        System.out.println("Sorted Products by Price:");
        for (Product p : sortedProducts) {
            System.out.println(p);
        }

        // TreeSet for Sorted Customers by Loyalty Points
        TreeSet<Customer> sortedCustomers = new TreeSet<>(new CustomerLoyaltyComparator());
        sortedCustomers.addAll(customers);

        System.out.println("\nSorted Customers by Loyalty Points:");
        for (Customer c : sortedCustomers) {
            System.out.println(c);
        }
    }
}


