namespace ClassLibrary
{
    public abstract class Vehicle
    {
        //расход топлива л/100км
        public int FuelConsumption { get; set; }

        //максимальная допустимая масса, кг
        public int MaxWeight { get; set; }

        public override string ToString()
        {
            return "Расход: " + FuelConsumption + " МДМ: " + MaxWeight;
        }
    }
}
