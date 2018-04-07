public class LineTrip extends Trip {

    public LineTrip(double distance, int speed) {
        super(distance, speed, 0);
    }

    public LineTrip(double distance, int speed, int pause) {
        super(distance, speed, pause);
    }

    @Override
    public String toString() {
        return "Движение по прямой";
    }
}
