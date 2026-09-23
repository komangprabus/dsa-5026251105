public class Motorcycle extends WashService {

    public Motorcycle(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int perUnit = getDays() * 15000 + 5000;
        return perUnit * getUnits();
    }

    @Override 
    public String label() {
        return "Motorcycle";
    }
}   