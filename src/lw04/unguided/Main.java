import java.util.*;

public class Main {

    static void bubbleSort(int[] arr, int passes) {
        int n = arr.length;
        for (int i=0; i<passes && i<n-1; i++) {
            for (int j=0; j<n-1-i; j++) {
                if (arr[j] < arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    static void binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;
        int found = -1;

        while (low <= high) {
            int mid = (low + high)/2;
            steps++;

            if (arr[mid] == target) {
                found = mid;
                break;
            } else if (arr[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (found != -1) {
            System.out.println(target + ": FOUND at index " + found + " (steps: " + steps + ")");
        } else {
            System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = scanner.nextInt();
        int[] parcels = new int[n];
        for (int i=0; i<n; i++) {
            parcels[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];
        for (int i=0; i<m; i++) {
            targets[i] = scanner.nextInt();
        }
        scanner.close();

        bubbleSort(parcels, 2);
        int[] afterTwo = new int[n];
        for (int i=0; i<n; i++) {
            afterTwo[i] = parcels[i];
        }

        bubbleSort(parcels, n-1);

        System.out.println("==== After 2 Passes ===="); 
        for (int i=0; i<n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(afterTwo[i]);
        }
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        for (int i=0; i<n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(parcels[i]);
        }
        System.out.println();

        System.out.println("==== Search Results ====");
        for (int i=0; i<m; i++) {
            binarySearch(parcels, targets[i]);
        }
    }
}