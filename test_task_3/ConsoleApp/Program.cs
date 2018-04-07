using System;
using ClassLibrary;

namespace ConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            // создание и инициализация ТС
            Content content = new Content();

            //вывод
            Console.WriteLine("Массив ТС");
            ShowVehicles(content.Vehicles);

            // сортировка по возврастанию расхода
            content.SortByFuelConsumption();
            Console.WriteLine("Массив ТС отсортированных по возврастанию расхода");
            ShowVehicles(content.Vehicles);

            // сортировка по убыванию МДМ
            content.SortByMaxWeight();
            Console.WriteLine("Массив ТС отсортированных по убыванию МДМ");
            ShowVehicles(content.Vehicles);

            // сортировка по типу ТС
            content.SortByTypeVehicle();
            Console.WriteLine("Массив ТС отсортированных по типу ТС");
            ShowVehicles(content.Vehicles);

            // вывести только пассажирские с МДМ менее 3500
            content.SortByTypeVehicle();
            Console.WriteLine("Массив пассажирсих ТС с МДМ менее 3500");
            ShowVehicles(content.GetPassangerVehicleLessThan3500());

            Console.Read();
        }

        static void ShowVehicles(Vehicle[] vehicles)
        {
            foreach (var vehicle in vehicles)
            {
                Console.WriteLine(vehicle);
            }
            Console.WriteLine();
        }
    }
}
