import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner playlist = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        Scanner participants = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Scanner inventory = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        var playlistList = new ArrayList<String>();
        while (playlist.hasNextLine()) {
            String line = playlist.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ", 3);
            String type = parts[0];

            switch (type) {
                case "ADD":
                    playlistList.add(line.substring(4));
                    break;
                case "INSERT":
                    playlistList.add(Integer.parseInt(parts[1]), parts[2]);
                    break;
                case "REMOVE":
                    playlistList.remove(line.substring(7));
                    break;
            }
        }
        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlistList.size());
        for (int i=0; i<playlistList.size(); i++) {
            System.out.println((i+1) + ": " + playlistList.get(i));
        }
        playlist.close();

        var participantSet = new LinkedHashSet<String>();
        int duplicates = 0;
        while (participants.hasNextLine()) {
            String name = participants.nextLine().trim();
            if (name.isEmpty()) {
                continue;
            }
            if (participantSet.contains(name)) {
                duplicates++;
            } else {
                participantSet.add(name);
            }
        }
        System.out.println();
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participantSet.size());
        int no = 1;
        for (String name : participantSet) {
            System.out.println(no + ". " + name);
            no++;
        }
        System.out.println("Duplicate registrations: " + duplicates);
        participants.close();

        var inventoryMap = new LinkedHashMap<String, Integer>();
        int failedSales = 0;
        while (inventory.hasNext()) {
            String type = inventory.next();
            String product = inventory.next();
            int amount = inventory.nextInt();

            switch (type) {
                case "ADD":
                    inventoryMap.put(product, inventoryMap.getOrDefault(product, 0) + amount);
                    break;
                case "SELL":
                    if (inventoryMap.containsKey(product) && inventoryMap.get(product) >= amount) {
                        inventoryMap.put(product, inventoryMap.get(product) - amount);
                    } else {
                        failedSales++;
                    }
                    break;
            }
        }
        System.out.println();
        System.out.println("===== Problem 3 =====");
        for (String product : inventoryMap.keySet()) {
            System.out.println(product + ": " + inventoryMap.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
        inventory.close();
    }
}