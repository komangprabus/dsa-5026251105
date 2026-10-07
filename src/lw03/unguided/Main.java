import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner registration = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        Scanner checkin = new Scanner(Main.class.getResourceAsStream("checkins.txt"));

        Set<String> registeredSet = new LinkedHashSet<String>();
        while (registration.hasNext()) {
            String id = registration.next();
            registeredSet.add(id);
        }

        Set<String> checkInSet = new LinkedHashSet<String>();
        List<String> results = new ArrayList<String>();
        int rejected = 0;

        while (checkin.hasNext()) {
            String id = checkin.next();

            if (!registeredSet.contains(id)) {
                results.add(id + ": Rejected (not registered)");
                rejected++;
            } else if (checkInSet.contains(id)) {
                results.add(id + ": Rejected (already checked in)");
                rejected++;
            } else {
                checkInSet.add(id);
                results.add(id + ": Checked in");
            }
        }

        System.out.println("===== Event Check-In Results =====");
        for (int i=0; i<results.size(); i++) {
            System.out.println(results.get(i));
        }

        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredSet.size());
        System.out.println("Successful check-ins: " + checkInSet.size());
        System.out.println("Absent students: " + (registeredSet.size() - checkInSet.size()));
        System.out.println("Rejected attempts: " + rejected);
    }
}