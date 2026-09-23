public class Carwash extends WashService {

    public Carwash(String id, int days, int units) {
        super(id, days, units);
    }

    @Override 
    public int calculateCharge() {
        int days = getDays();
        int perUnit;

        if (days <= 3) {
            perUnit = days * 35000;
        } else {
            perUnit = 3 * 35000 + (days - 3) * 25000;
        }
        perUnit = perUnit + 15000;
        return perUnit * getUnits();
    }

    @Override
    public String label() {
        return "Car";
    } 
}
