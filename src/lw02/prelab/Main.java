import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = readTransactions("transactions.txt");
        LinkedList<String[]> customers = buildCustomerList(transactions);
        Queue<String[]> queue = new LinkedList<>(transactions);
        Stack<String[]> failedTransactions = new Stack<>();
        processQueue(queue, customers, failedTransactions);

        printFinalBalances(customers);
        printFailedTransactions(failedTransactions);
    }

    static LinkedList<String[]> readTransactions(String filename) {
        LinkedList<String[]> transactions = new LinkedList<>();
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                transactions.add(parts);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return transactions;
    }

    static LinkedList<String[]> buildCustomerList(LinkedList<String[]> transactions) {
        LinkedList<String[]> customers = new LinkedList<>();
        for (String[] t : transactions) {
            String name = t[0];
            if (findCustomerIndex(customers, name) == -1) {
                customers.add(new String[]{name, "0"});
            }
        }
        return customers;
    }

    static int findCustomerIndex(LinkedList<String[]> customers, String name) {
        for (int i = 0; i<customers.size(); i++) {
            if (customers.get(i)[0].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    static void processQueue(Queue<String[]> queue, LinkedList<String[]> customers, Stack<String[]> failedTransactions) {
        String[] t;
        while ((t = queue.poll()) != null) {
            String name = t[0];
            String type = t[1];
            int amount = Integer.parseInt(t[2]);
            int index = findCustomerIndex(customers, name);
            String[] customer = customers.get(index);
            int balance = Integer.parseInt(customer[1]);
            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failedTransactions.push(t);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }
    }

    static void printFinalBalances(LinkedList<String[]> customers) {
        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }
    }

    static void printFailedTransactions(Stack<String[]> failedTransactions) {
        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] t = failedTransactions.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }
}
