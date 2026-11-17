
import java.util.*;

public class BankService {

    private Map<Integer, BankAccount> accounts = new HashMap<>();

    private Map<Integer, List<Transaction>> history = new HashMap<>();

    private int nextAccountNumber = 1001;

    public void createAccount(String name, double initialBalance) {

        if (initialBalance < 0) {
            System.out.println("Invalid initial balance!");
            return;
        }

        int accountNumber = nextAccountNumber++;

        BankAccount account = new BankAccount(
            accountNumber, name, initialBalance
        );

        accounts.put(accountNumber, account);
        history.put(accountNumber, new ArrayList<>());

        if (initialBalance > 0) {
            addTransaction(accountNumber, "Initial Deposit",
                           initialBalance);
        }

        System.out.println("Account created successfully!");
        System.out.println("Your Account Number: " + accountNumber);
    }

    public BankAccount findAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    private void addTransaction(int accountNumber,
                                String type, double amount) {
        history.get(accountNumber).add(
            new Transaction(type, amount)
        );
    }

    public void deposit(int accountNumber, double amount) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero!");
            return;
        }

        account.deposit(amount);
        addTransaction(accountNumber, "Deposit", amount);

        System.out.println("Deposit successful!");
    }

    public void withdraw(int accountNumber, double amount) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        if (account.withdraw(amount)) {
            addTransaction(accountNumber, "Withdrawal", amount);
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Invalid amount or insufficient balance!");
        }
    }

    public void transfer(int senderId, int receiverId,
                         double amount) {

        BankAccount sender = findAccount(senderId);
        BankAccount receiver = findAccount(receiverId);

        if (sender == null || receiver == null) {
            System.out.println("Invalid account number!");
            return;
        }

        if (senderId == receiverId) {
            System.out.println("Cannot transfer to the same account!");
            return;
        }

        if (amount <= 0 || amount > sender.getBalance()) {
            System.out.println("Invalid amount or insufficient balance!");
            return;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        addTransaction(senderId, "Transfer Sent", amount);
        addTransaction(receiverId, "Transfer Received", amount);

        System.out.println("Transfer successful!");
    }

    public void showBalance(int accountNumber) {

        BankAccount account = findAccount(accountNumber);

        if (account != null) {
            System.out.printf("Available Balance: ₹%.2f%n",
                              account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    public void showTransactions(int accountNumber) {

        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account not found!");
            return;
        }

        List<Transaction> transactions = history.get(accountNumber);

        if (transactions.isEmpty()) {
            System.out.println("No transactions found!");
            return;
        }

        System.out.println("\n===== TRANSACTION HISTORY =====");

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }
}
