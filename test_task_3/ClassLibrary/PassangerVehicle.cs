namespace ClassLibrary
{
    class PassangerVehicle : Vehicle
    {
        //количество пассажирских мест, число
        public int Places { get; set; }

        public PassangerVehicle(int fuelConsumption, int maxWeight, int places)
        {
            FuelConsumption = fuelConsumption;
            MaxWeight = maxWeight;
            Places = places;
        }

        public override string ToString()
        {
            return "Пасс.: " + base.ToString() + " Мест: " + Places;
        }
    }
}
