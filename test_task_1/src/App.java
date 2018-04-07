public class App {

    public static void main(String[] args) {

        ArrayTrips array = new ArrayTrips();
        System.out.println("Массив создан и проинициализирован" + "\n");

        System.out.println("Список всех поездок");
        showTrips(array.getTrips());

        System.out.println("\n" + "Создание копии списка поездок" + "\n");
        ArrayTrips array2 = array.getCopy();

        System.out.println("Сортировка копии списка поездок" + "\n");
        array2.sort();

        System.out.println("Отсортированный список всех поездок");
        showTrips(array2.getTrips());

        System.out.println("\n" + "Самая быстрая поездка");
        showTrips(new Trip[]{array.getMinTrip()});

        if (array.checkBetween()) {
            System.out.println("\n" + "Есть как минимум одна поездка длительностью от 25 до 30 минут");
        } else {
            System.out.println("\n" + "Нет ни одной поездки длительностью от 25 до 30 минут");
        }
    }

    private static void showTrips(Trip[] trips) {
        System.out.printf("%-22s | %-9s | %-8s | %6s\n", "Тип маршрута", "Дистанция", "Скорость", "Время");
        for (int i = 0; i < trips.length; i++) {
            System.out.printf("%-22s | %9.2f | %8d | %6.2f\n", trips[i], trips[i].getDistance(),
                    trips[i].getSpeed(), trips[i].getTime());
        }
    }
}
