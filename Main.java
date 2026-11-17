
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();

        while (true) {

            System.out.println("\n===== CONSOLE BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Check Balance");
            System.out.println("6. Transfer Money");
            System.out.println("7. Transaction History");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Deposit: ");
                    double initial = sc.nextDouble();

                    bank.createAccount(name, initial);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int detailId = sc.nextInt();

                    BankAccount account = bank.findAccount(detailId);

                    if (account != null) {
                        account.displayAccount();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    int depositId = sc.nextInt();

                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = sc.nextDouble();

                    bank.deposit(depositId, depositAmount);
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    int withdrawId = sc.nextInt();

                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = sc.nextDouble();

                    bank.withdraw(withdrawId, withdrawAmount);
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    bank.showBalance(sc.nextInt());
                    break;

                case 6:
                    System.out.print("Enter Sender Account Number: ");
                    int sender = sc.nextInt();

                    System.out.print("Enter Receiver Account Number: ");
                    int receiver = sc.nextInt();

                    System.out.print("Enter Transfer Amount: ");
                    double transferAmount = sc.nextDouble();

                    bank.transfer(sender, receiver, transferAmount);
                    break;

                case 7:
                    System.out.print("Enter Account Number: ");
                    bank.showTransactions(sc.nextInt());
                    break;

                case 8:
                    System.out.println("Thank you for banking with us!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
