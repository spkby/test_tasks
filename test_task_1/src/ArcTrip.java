public class ArcTrip extends Trip {

    public ArcTrip(double distance, int speed) {
        super(distance, speed, 0);
    }

    public ArcTrip(double distance, int speed, int pause) {
        super(distance, speed, pause);
    }

    public ArcTrip(int radius, int angel, int speed, int pause) {
        super(calcDistance(radius, angel), speed, pause);
    }

    public ArcTrip(int radius, int angel, int speed) {
        super(calcDistance(radius, angel), speed, 0);
    }

    private static double calcDistance(int radius, int angel) {
        return (Math.PI * radius * angel) / 180;
    }

    @Override
    public String toString() {
        return "Движение по дуге";
    }
}
