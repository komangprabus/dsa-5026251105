import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        //insertion sort descending for 3 pass
        for (int i = 1; i <= 3; i++) {
            int key = branches[i];
            int j = i - 1;
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
        }

        //tampilan array setelah 3 pass
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(branches[i]);
        }
        System.out.println();

        //linear search
        for (int i = 0; i < m; i++) {
            int found = -1;
            for (int j = 0; j < n; j++) {
                if (branches[j] == targets[i]) {
                    found = j;
                    break;
                }
            }

            if (found != -1) {
                System.out.println(targets[i] + ": FOUND at index " + found);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }
}