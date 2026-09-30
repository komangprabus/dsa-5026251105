
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );
        
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> prosessed = new LinkedList<>();

        LinkedList<String[]> foods = new LinkedList<>();
        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        LinkedList<String[]> drinks = new LinkedList<>();
        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        while (sc.hasNext()) {
            String[] order = new String[4];
            order[0] = sc.next();
            order[1] = sc.next();
            order[2] = sc.next();
            order[3] = sc.next();
            orders.add(order);
        }

        sc.close();

        Queue<String[]> processOrders = new LinkedList<>();
        while (!orders.isEmpty()){
            processOrders.add(orders.removeFirst());
        }

        Stack<String[]> failed = new Stack<>();

        while (!processOrders.isEmpty()){
            String[] order = processOrders.poll();
            String foodName = order[1];
            String drinkName = order[2];

            boolean AvailabilityFood = true;

            String[] TargetFood = null;
            if (!foodName.equals("-")){
                AvailabilityFood = false;
                for (String[] f : foods){
                    if (f[0].equals(foodName)){
                        TargetFood = f;
                        if (Integer.parseInt(f[1]) > 0) {
                            AvailabilityFood = true;
                        }
                    }
                }
            }

            boolean AvailabilityDrink = true;

            String[] TargetDrink = null;
            if (!drinkName.equals("-")){
                AvailabilityDrink = false;
                for (String[] d : drinks){
                    if (d[0].equals(drinkName)){
                        TargetDrink = d;
                        if (Integer.parseInt(d[1]) > 0){
                            AvailabilityDrink = true;
                        }
                    }
                }
            }

            if (AvailabilityDrink && AvailabilityFood){
                if (TargetFood != null) {
                    TargetFood[1] = String.valueOf(Integer.parseInt(TargetFood[1]) - 1);
                }
                if (TargetDrink != null) {
                    TargetDrink[1] = String.valueOf(Integer.parseInt(TargetDrink[1]) - 1);
                } 
                prosessed.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] o : prosessed) {
            System.out.println(o[0] + " " + o[1] + " " + o[2] + " " + o[3]);
        }

        System.out.println();

        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foods){
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println();

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinks){
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println();

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()){
            String[] order = failed.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }   
}