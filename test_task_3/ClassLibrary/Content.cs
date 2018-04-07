using System.Collections.Generic;
using System.Linq;

namespace ClassLibrary
{
    public class Content
    {
        public Vehicle[] Vehicles { get; set; }

        public Content()
        {
            Initializer();
        }

        private void Initializer()
        {
            Vehicles = new Vehicle[9];

            Vehicles[0] = new PassangerVehicle(23, 2000, 43);
            Vehicles[1] = new PassangerVehicle(25, 3000, 50);
            Vehicles[2] = new PassangerVehicle(29, 4000, 67);

            Vehicles[3] = new TruckVehicle(24, 3500, 150,2000);
            Vehicles[4] = new TruckVehicle(22, 3250, 130,1500);
            Vehicles[5] = new TruckVehicle(25, 3150, 110,1000);

            Vehicles[6] = new SpecialTruckVehicle(30, 3000, 50, 900,"№ 123 от 01/01/2017", "Цистерная");
            Vehicles[7] = new SpecialTruckVehicle(32, 3150, 70, 1100, "№ 124 от 02/01/2017","Рефрижератор");
            Vehicles[8] = new SpecialTruckVehicle(34, 3250, 90, 1200, "№ 125 от 03/01/2017","Самосвал");
        }

        public void SortByFuelConsumption()
        {
            Vehicles = Vehicles.OrderBy(x => x.FuelConsumption).ToArray();
        }

        public void SortByMaxWeight()
        {
            Vehicles = Vehicles.OrderByDescending(x => x.MaxWeight).ToArray();
        }

        public void SortByTypeVehicle()
        {
            List<Vehicle> sort = new List<Vehicle>();

            sort.AddRange(from vehicle in Vehicles
                          where vehicle is SpecialTruckVehicle
                          select vehicle);

            sort.AddRange(from vehicle in Vehicles
                          where vehicle is PassangerVehicle
                          select vehicle);

            sort.AddRange(from vehicle in Vehicles
                          where vehicle is TruckVehicle && !(vehicle is SpecialTruckVehicle)
                          select vehicle);

            Vehicles = sort.ToArray();
        }

        public Vehicle[] GetPassangerVehicleLessThan3500()
        {
            return (from vehicle in Vehicles
                    where vehicle is PassangerVehicle && vehicle.MaxWeight < 3500
                    select vehicle).ToArray();
        }
    }
}
