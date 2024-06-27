import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private final int startingBalance = 10000; 
    private ArrayList<String> transactions = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int currentBalance = startingBalance;
    private boolean loggedIn = false;

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }

    private void run() {
        while (true) {
            if (!loggedIn) {
                authenticateUser();
            } else {
                displayMenu();
                int choice = getChoice(); 
                processChoice(choice);
            }
        }
    }

    private void authenticateUser() {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your card details: ");
        int userId = scanner.nextInt();
        System.out.print("Enter your 4 digits PIN: ");
        int pin = scanner.nextInt();

        if (userId == 2111219 && pin == 96154) {
            loggedIn = true;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid User ID or PIN. Please try again.");
        }
    }

    private void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Deposit Cash");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Quit");
    }

    private int getChoice() {
        System.out.print("Enter your choice (1-5): ");
        int choice = scanner.nextInt();
        return choice;
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                viewTransactions();
                break;
            case 2:
                withdrawCash();
                break;
            case 3:
                depositCash();
                break;
            case 4:
                transferFunds();
                break;
            case 5:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("You have no recent transactions.");
        } else {
            System.out.println("Your recent transactions:");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    private void withdrawCash() {
        System.out.print("Enter amount to withdraw: ");
        int amount = scanner.nextInt();

        if (amount > currentBalance) {
            System.out.println("Insufficient funds. Please try a lower amount.");
        } else {
            currentBalance -= amount;
            System.out.println("Withdrawal successful. Please collect your cash.");
            transactions.add("Withdrew: " + amount);
        }
    }

    private void depositCash() {
        System.out.print("Enter amount to deposit: ");
        int amount = scanner.nextInt();

        currentBalance += amount;
        System.out.println("Deposit successful. Your new balance is: " + currentBalance);
        transactions.add("Deposited: " + amount);
    }

    private void transferFunds() {
        System.out.print("Enter recipient account number: ");
        String recipientAccount = scanner.next(); // Placeholder for account number validation

        System.out.print("Enter amount to transfer: ");
        int amount = scanner.nextInt();

        if (amount > currentBalance) {
            System.out.println("Insufficient funds. Please try a lower amount.");
        } else {
            currentBalance -= amount;
            System.out.println("Transfer successful. $" + amount + " has been transferred to account " + recipientAccount);
            transactions.add("Transferred: $" + amount + " to account " + recipientAccount);
        }
    }
}


