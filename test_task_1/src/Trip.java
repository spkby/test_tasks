public abstract class Trip {

    private double distance;
    private int speed;
    private double time;
    private int pause;

    public Trip(double distance, int speed, int pause) {
        this.speed = speed;
        this.distance = distance;
        this.pause = pause;
        calcTime();
    }

    public double getTime() {
        return time;
    }

    public double getDistance() {
        return distance;
    }

    public int getSpeed() {
        return speed;
    }

    private void calcTime() {
        time = pause + distance / speed * 60;
    }
}
