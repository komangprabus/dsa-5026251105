import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class latihanUnguided2 {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String[]> orders = readOrders("orders.txt");
        LinkedList<String[]> foodStock = createFoodStock();
        LinkedList<String[]> drinkStock = createDrinkStock();
        LinkedList<String[]> successOrders = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        processOrders(orders, foodStock, drinkStock, successOrders, failedOrders);

        System.out.println("=== Success ===");
        for (String[] o : successOrders) {
            System.out.println(o[0] + " " + o[1] + " " + o[2] + " " + o[3]);
        }

        System.out.println("=== Food Stock ===");
        for (String[] f : foodStock) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println("=== Drink Stock ===");
        for (String[] d : drinkStock) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println("=== Failed ===");
        while (!failedOrders.isEmpty()) {
            String[] o = failedOrders.pop();
            System.out.println(o[0] + " " + o[1] + " " + o[2] + " " + o[3]);
        }
    }

    static LinkedList<String[]> readOrders(String filename) throws FileNotFoundException {
        LinkedList<String[]> orders = new LinkedList<>();
        Scanner sc = new Scanner(new File(filename));

        while (sc.hasNext()) {
            String name = sc.next();
            String food = sc.next();
            String drink = sc.next();
            String table = sc.next();

            orders.add(new String[]{name, food, drink, table});
        }
        sc.close();
        return orders;
    }
    
    static LinkedList<String[]> createFoodStock() {
        LinkedList<String[]> foodStock = new LinkedList<>();
        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});
        return foodStock;
    }

    static LinkedList<String[]> createDrinkStock() {
        LinkedList<String[]> drinkStock = new LinkedList<>();
        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});
        return drinkStock;
    }

    static String[] findItem(LinkedList<String[]> stockList, String name) {
    for (String[] item : stockList) {
        if (item[0].equals(name)) {
            return item;
        }
    }
    return null;


    static void processOrders(LinkedList<String[]> orders, LinkedList<String[]> foodStock, LinkedList<String[]> drinkStock, LinkedList<String[]> successOrders, LinkedList<String[]> failedOrders) {
        Queue<String[]> queue = new LinkedList<>(orders);
        
        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String food = order[1];
            String drink = order[2];

            boolean available = true;

            if (!food.equals("-")){
                String[] foodItem = findItem(foodStock, food);
                int stock = Integer.parseInt(foodItem[1]);
                if (stock <= 0){
                    available = false;
                }
            }

            if (!drink.equals("-")) {
                String[] drinkItem = findItem(drinkStock, drink);
                int stock = Integer.parseInt(drinkItem[1]);
                if (stock <= 0) {
                    available = false;
                }
            }

            if (available) {
                if (!food.equals("-")) {
                    String[] drinkItem = findItem(drinkStock, drink);
                    int stock = Integer.parseInt(drinkItem[1]);
                    drinkItem[1] = String.valueOf(stock - 1);
                }
                successOrders.add(order);
            } else {
                failedOrders.add(order);
            }
        }
    }
}
