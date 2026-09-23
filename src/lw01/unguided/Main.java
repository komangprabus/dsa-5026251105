import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("washes.txt"));
        List<WashService> services = new ArrayList<>();
        int a = sc.nextInt();

        while (sc.hasNext()) {
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            int units = sc.nextInt();

            if (type.equals( "MOTORCYCLE")) {
                services.add(new Motorcycle(id, days, units));
            } else if (type.equals("CAR")) {
                services.add(new Carwash(id, days, units));
            }
        }
        sc.close();

        for (WashService s : services) {
            System.out.println(s.summary());
        }
    }
}