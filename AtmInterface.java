package PROJECTS;

import java.util.Scanner;

// Class representing a simple Bank Account system with basic functionalities.
class BankAccount {

    // Instance variables to store user details and account information.
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 10000f; // Initial balance set to 10000
    int transactions = 0;   // Track number of transactions
    String transactionHistory = ""; // Store transaction history

    // Global Scanner object for input handling throughout the program
    Scanner sc = new Scanner(System.in);

    // Method to register a new user with their details
    public void register() {
        System.out.println("\nEnter your Name: ");
        this.name = sc.nextLine();
        System.out.println("\nEnter your Username: ");
        this.userName = sc.nextLine();
        System.out.println("\nEnter your Password: ");
        this.password = sc.nextLine();
        System.out.println("\nEnter your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration Successful. Please Log in to your Bank Account");
    }

    // Method to handle user login process
    public boolean login() {
        boolean isLogin = false;
        while (!isLogin) {
            System.out.println("\nEnter your username: ");
            String Username = sc.nextLine();
            // Username check (case-insensitive for flexibility)
            if (Username.equalsIgnoreCase(userName)) {
                while (!isLogin) {
                    System.out.println("\nEnter your password: ");
                    String Password = sc.nextLine();
                    // Password check for successful login
                    if (Password.equals(password)) {
                        System.out.println("\nLogin Successful");
                        isLogin = true; // Mark login as successful
                    } else {
                        System.out.println("\nIncorrect Password");
                    }
                }
            } else {
                System.out.println("\nUsername not found");
            }
        }
        return isLogin;
    }

    // Method to handle withdrawal of funds
    public void withdraw() {
        System.out.println("\nEnter Amount to Withdraw: ");
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                transactions++; // Increment transaction count
                balance -= amount; // Deduct amount from balance
                System.out.println("\nWithdrawal Successful.");
                String str = amount + " Rs Withdrawn\n";
                transactionHistory = transactionHistory.concat(str); // Update transaction history
            } else {
                System.out.println("\nInsufficient Balance.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during withdrawal. Please try again.");
        }
    }

    // Method to handle deposit of funds
    public void deposit() {
        System.out.println("\nEnter Amount to Deposit: ");
        float amount = sc.nextFloat();
        try {
            if (amount <= 10000f) { // Deposit limit check
                transactions++; // Increment transaction count
                balance += amount; // Add amount to balance
                System.out.println("\nDeposit Successful.");
                String str = amount + " Rs deposited\n";
                transactionHistory = transactionHistory.concat(str); // Update transaction history
            } else {
                System.out.println("\nSorry. The limit is 10000.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during deposit. Please try again.");
        }
    }

    // Method to handle fund transfer to another user
    public void transfer() {
        sc.nextLine();  // To consume leftover newline
        System.out.println("\nEnter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.println("\nEnter Amount to Transfer: ");
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) { // Balance check before transfer
                if (amount <= 50000f) { // Transfer limit check
                    transactions++; // Increment transaction count
                    balance -= amount; // Deduct amount from balance
                    System.out.println("\nSuccessfully Transferred to " + recipient);
                    String str = amount + " Rs transferred to " + recipient + "\n";
                    transactionHistory = transactionHistory.concat(str); // Update transaction history
                } else {
                    System.out.println("\nSorry. The limit is 50000.");
                }
            } else {
                System.out.println("\nInsufficient Balance.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during transfer. Please try again.");
        }
    }

    // Method to display current balance
    public void checkBalance() {
        System.out.println("\nBalance: " + balance + " Rs");
    }

    // Method to display transaction history
    public void transHistory() {
        if (transactions == 0) {
            System.out.println("No transactions occurred.");
        } else {
            System.out.print("\n" + transactionHistory);
        }
    }
}

// Main class for handling the ATM interface
public class AtmInterface {

    // Utility method to safely take integer input within a limit
    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;
        Scanner sc = new Scanner(System.in);

        while (!flag) {
            try {
                input = sc.nextInt(); // Capture integer input
                flag = true;

                // Input validation to ensure it's within the given limit
                if (flag && (input > limit || input < 1)) {
                    System.out.println("Choose a number between 1 to " + limit);
                    flag = false; // Reset flag to prompt input again
                }
            } catch (Exception e) {
                System.out.println("Enter only an integer value.");
                sc.next(); // Clear the invalid input
                flag = false; // Reset flag to prompt input again
            }
        }
        return input; // Return validated integer input
    }

    // Main method to simulate ATM operations
    public static void main(String[] args) {
        System.out.println("\n********************WELCOME TO HBFC ATM INTERFACE******************");
        System.out.println("\n1. Register \n2. Exit");
        System.out.println("Choose one option: ");
        int choose = takeIntegerInput(2); // Input choice

        if (choose == 1) {
            BankAccount b = new BankAccount(); // Create a new BankAccount object
            b.register(); // Call register method to register the user
            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.println("Enter your choice: ");
                int ch = takeIntegerInput(2); // Input choice for login or exit
                if (ch == 1) {
                    if (b.login()) { // If login is successful
                        System.out.println("\n********************WELCOME BACK " + b.name + " *******************");
                        boolean isFinished = false; // Flag to check if user wants to logout
                        while (!isFinished) {
                            // Display ATM menu options
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Logout");
                            System.out.println("Enter your choice: ");
                            int c = takeIntegerInput(6); // Input choice for ATM operations
                            switch (c) {
                                case 1:
                                    b.withdraw(); // Call withdraw method
                                    break;
                                case 2:
                                    b.deposit(); // Call deposit method
                                    break;
                                case 3:
                                    b.transfer(); // Call transfer method
                                    break;
                                case 4:
                                    b.checkBalance(); // Call check balance method
                                    break;
                                case 5:
                                    b.transHistory(); // Call transaction history method
                                    break;
                                case 6:
                                    isFinished = true; // Set flag to logout
                                    System.out.println("\nLogged out successfully.");
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0); // Exit the program
                }
            }
        } else {
            System.exit(0); // Exit the program
        }
    }
}
