namespace ClassLibrary
{
    class SpecialTruckVehicle : TruckVehicle
    {
        //номер и дата освидетельствования, строка
        public string InspectionDateNumber { get; set; }

        //специализация, строка(перевозка топлива, рефрижератор, перевозка животных и т.д.)
        public string Specialization { get; set; }

        public SpecialTruckVehicle(int fuelConsumption, int maxWeight, int cargoVolume, int cargoWeight,
            string inspectionDateNumber, string specialization) : base(fuelConsumption, maxWeight, cargoVolume, cargoWeight)
        {
            FuelConsumption = fuelConsumption;
            MaxWeight = maxWeight;
            CargoVolume = cargoVolume;
            CargoWeight = cargoWeight;
            InspectionDateNumber = inspectionDateNumber;
            Specialization = specialization;
        }

        public override string ToString()
        {
            return "Спец"+base.ToString() + " "+ InspectionDateNumber + " " + Specialization;
        }
    }
}
