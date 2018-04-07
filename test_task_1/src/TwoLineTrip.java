public class TwoLineTrip extends Trip {

    public TwoLineTrip(double distanceAC, double distanceCB, int speed) {
        super(distanceAC + distanceCB, speed, 0);
    }

    public TwoLineTrip(double distanceAC, double distanceCB, int speed, int pause) {
        super(distanceAC + distanceCB, speed, pause);
    }

    @Override
    public String toString() {
        return "Движение через точку C";
    }
}