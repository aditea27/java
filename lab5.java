interface BankInterface {
    void getBalance();
    void getInterestRate();
}


class BankA implements BankInterface {
    private float interest = 0.07f; 
    private float amount;

    BankA(float amount) {
        this.amount = amount; 
    }

    @Override
    public void getBalance() {
        System.out.println("Bank A Balance: " + amount);
    }

    @Override
    public void getInterestRate() {
        System.out.println("Bank A Interest Rate: " + (interest * 100) + "%");
    }
}


class BankB implements BankInterface {
    private float interest = 0.074f;
    private float amount;

    BankB(float amount) {
        this.amount = amount; 
    }

    @Override
    public void getBalance() {
        System.out.println("Bank B Balance: " + amount);
    }

    @Override
    public void getInterestRate() {
        System.out.println("Bank B Interest Rate: " + (interest * 100) + "%");
    }
}


class BankC implements BankInterface {
    private float interest = 0.079f; 
    private float amount;
 
    BankC(float amount) {
        this.amount = amount; 
    }

    @Override
    public void getBalance() {
        System.out.println("Bank C Balance: " + amount);
    }

    @Override
    public void getInterestRate() {
        System.out.println("Bank C Interest Rate: " + (interest * 100) + "%");
    }
}


public class lab5 {
    public static void main(String[] args) {
        
        BankA bankA = new BankA(10000);
        BankB bankB = new BankB(150000);
        BankC bankC = new BankC(200000);

        
        bankA.getBalance();
        bankA.getInterestRate();

        
        bankB.getBalance();
        bankB.getInterestRate();

        
        bankC.getBalance();
        bankC.getInterestRate();
    }
}