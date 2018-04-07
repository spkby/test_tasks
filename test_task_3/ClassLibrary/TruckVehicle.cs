namespace ClassLibrary
{
    class TruckVehicle : Vehicle
    {
        //максимальный объём перевозимого груза, м3
        public int CargoVolume { get; set; }

        //максимальная масса перевозимого груза, кг
        public int CargoWeight { get; set; }


        public TruckVehicle(int fuelConsumption, int maxWeight, int cargoVolume, int cargoWeight)
        {
            FuelConsumption = fuelConsumption;
            MaxWeight = maxWeight;
            CargoVolume = cargoVolume;
            CargoWeight = cargoWeight;
        }

        public override string ToString()
        {
            return "Груз.: " + base.ToString() + " Объём: " + CargoVolume + " Масса: " + CargoWeight;
        }
    }
}
