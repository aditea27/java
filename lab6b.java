import java.util.Random;

// Custom Exception
class CounterEmptyException extends Exception {
    public CounterEmptyException(String message) {
        super(message);
    }
}

// CoffeeCounter class
class CoffeeCounter {
    private int counter = 0;
    private final int MAX_CAPACITY = 3;

    // Synchronized method for Baristas to add coffee
    public synchronized void addCoffee() throws InterruptedException {
        while (counter == MAX_CAPACITY) {
            System.out.println("Barista is waiting. Counter is full.");
            wait(); // Barista waits if the counter is full
        }
        counter++;
        System.out.println("Barista prepared coffee. Counter: " + counter);
        notifyAll(); // Notify customers and reviewers
    }

    // Synchronized method for Customers to pick up coffee
    public synchronized void pickCoffee(int amount) throws InterruptedException, CounterEmptyException {
        while (counter < amount) {
            System.out.println("Customer is waiting. Counter is empty.");
            wait(); // Customer waits if there isn't enough coffee
        }
        counter -= amount;
        System.out.println("Customer picked up coffee. Counter: " + counter);
        notifyAll(); // Notify baristas and reviewers
    }

    // Synchronized method for Reviewer to sample coffee
    public synchronized void sampleCoffee() throws InterruptedException, CounterEmptyException {
        while (counter == 0) {
            System.out.println("Reviewer is waiting. Counter is empty.");
            wait(); // Reviewer waits if there is no coffee
        }
        counter--;
        System.out.println("Coffee Reviewer sampled coffee. Counter: " + counter);
        notifyAll(); // Notify baristas and customers
    }
}

// Barista class (Producer)
class Barista extends Thread {
    private final CoffeeCounter counter;
    private final int coffeesToPrepare;

    public Barista(CoffeeCounter counter, int coffeesToPrepare) {
        this.counter = counter;
        this.coffeesToPrepare = coffeesToPrepare;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeesToPrepare; i++) {
                counter.addCoffee();
                Thread.sleep(new Random().nextInt(1000)); // Simulate time to prepare coffee
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Customer class (Consumer)
class Customer extends Thread {
    private final CoffeeCounter counter;
    private final int coffeesToPickUp;

    public Customer(CoffeeCounter counter, int coffeesToPickUp) {
        this.counter = counter;
        this.coffeesToPickUp = coffeesToPickUp;
    }

    @Override
    public void run() {
        try {
            counter.pickCoffee(coffeesToPickUp);
        } catch (InterruptedException | CounterEmptyException e) {
            e.printStackTrace();
        }
    }
}

// Reviewer class (Observer)
class Reviewer extends Thread {
    private final CoffeeCounter counter;

    public Reviewer(CoffeeCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            counter.sampleCoffee();
        } catch (InterruptedException | CounterEmptyException e) {
            e.printStackTrace();
        }
    }
}

public class lab6b {
    public static void main(String[] args) {
        // Initialize coffee counter
        CoffeeCounter counter = new CoffeeCounter();

        // Create and start Baristas
        Barista barista1 = new Barista(counter, 2); // Barista 1 prepares 2 coffees
        Barista barista2 = new Barista(counter, 3); // Barista 2 prepares 3 coffees

        // Create and start Customers
        Customer customer1 = new Customer(counter, 1); // Customer 1 picks 1 coffee
        Customer customer2 = new Customer(counter, 2); // Customer 2 picks 2 coffees
        Customer customer3 = new Customer(counter, 1); // Customer 3 picks 1 coffee

        // Create and start Coffee Reviewer
        Reviewer reviewer = new Reviewer(counter);

        // Start all threads
        barista1.start();
        barista2.start();
        customer1.start();
        customer2.start();
        customer3.start();
        reviewer.start();
    }
}
