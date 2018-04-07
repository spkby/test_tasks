public class ArrayTrips {

    private Trip[] trips;

    public ArrayTrips() {
        initTrips(12);
    }

    public ArrayTrips(Trip[] trips) {
        this.trips = trips;
    }

    public Boolean checkBetween() {
        double from = 25d;
        double to = 30d;

        if (getMinTrip().getTime() > to || getMaxTrip().getTime() < from) {
            return false;
        }
        for (int i = 0; i < trips.length; i++) {
            if (trips[i].getTime() <= to && trips[i].getTime() >= from) {
                return true;
            }
        }
        return false;
    }

    private void initTrips(int lenght) {
        trips = new Trip[lenght];

        trips[0] = new LineTrip(54, 91, 12);
        trips[1] = new ArcTrip(21d, 70);
        trips[2] = new TwoLineTrip(64, 56, 78, 26);
        trips[3] = new LineTrip(48, 45);
        trips[4] = new ArcTrip(49, 75, 73);
        trips[5] = new TwoLineTrip(56, 23, 56);
        trips[6] = new LineTrip(59, 120);
        trips[7] = new ArcTrip(159, 40, 62, 5);
        trips[8] = new TwoLineTrip(29, 56, 45, 0);
        trips[9] = new LineTrip(61, 90, 2);
        trips[10] = new ArcTrip(59d, 121, 2);
        trips[11] = new TwoLineTrip(58, 64, 53, 3);
    }

    public ArrayTrips getCopy() {
        Trip[] tmp = new Trip[trips.length];
        for (int i = 0; i < trips.length; i++) {
            tmp[i] = trips[i];
        }
        return new ArrayTrips(tmp);
    }

    public Trip[] getTrips() {
        return trips;
    }

    public Trip getMaxTrip() {
        int max = 0;
        for (int i = 1; i < trips.length; i++) {
            if (trips[i].getTime() > trips[max].getTime()) {
                max = i;
            }
        }
        return trips[max];
        /*
            если массив отсортирован, то можно просто
            return trips[0];
         */
    }

    public Trip getMinTrip() {
        int min = 0;
        for (int i = 1; i < trips.length; i++) {
            if (trips[i].getTime() < trips[min].getTime()) {
                min = i;
            }
        }
        return trips[min];
        /*
            если массив отсортирован, то можно просто
            return trips[trips.length - 1];
         */
    }

    public void sort() {
        for (int i = 0; i < trips.length; i++) {
            for (int j = trips.length - 1; j > i; j--) {
                if (trips[j].getTime() > trips[j - 1].getTime()) {
                    Trip tmp = trips[j];
                    trips[j] = trips[j - 1];
                    trips[j - 1] = tmp;
                }
            }
        }
    }
}
