package GasStationSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Repository {

    //Attributes
    private double amountInRepository;
    private String fuelType;
    private static final double capacityOfRepository = 25000.00;

    //Constructor
    public Repository(double amountInRepository, String fuelType) {
        this.amountInRepository = amountInRepository;
        this.fuelType = fuelType;
    }

    public Repository() {
    }

    //other methods

    //This is to get the remaining fuel stock per fuel type
    public double displayingRemainingStock(){
       return amountInRepository;
    }

    //this is to get fuel dispensed per vehicle type per fuel type
    public static void checkFuelDispensedPerVehicleCatergory(ArrayList<Customer> listOfCustomers){
        //initializing total fuel served per vehicle category
        double totalFuelServedForCars = 0, totalFuelServedForVans =0, totalFuelServedForBikes=0, totalFuelServedForWheels=0, totalFuelServedForPublicTransportation=0, totalFuelServedForOthers= 0;

        //looping through the arraylist of customers to obtain the total fuel served per vehicle category
        for (Customer customer: listOfCustomers){
            if (customer.getVehicleType().equals("Car")){
                totalFuelServedForCars += customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Van")){
                totalFuelServedForVans += customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Motor Bikes")){
                totalFuelServedForBikes += customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Three Wheel")){
                totalFuelServedForWheels += customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Public Transportation")){
                totalFuelServedForPublicTransportation += customer.getFuelAmount();
            }else {
                totalFuelServedForOthers += customer.getFuelAmount();
            }
        }

        System.out.println("Car category : Total Fuel: "+totalFuelServedForCars);
        System.out.println("Van category : Total Fuel: "+totalFuelServedForVans);
        System.out.println("Three Wheel category : Total Fuel: "+totalFuelServedForWheels);
        System.out.println("Motor Bikes category : Total Fuel: "+totalFuelServedForBikes);
        System.out.println("Public transportation category : Total Fuel: "+totalFuelServedForPublicTransportation);
        System.out.println("Other category : Total Fuel: "+totalFuelServedForOthers);

    }

    //This is to read from Tables that has repository Data
    public static void readDataFromRepositoryTables(String table, Repository repository) {
        String url = "jdbc:mysql://localhost:3306/gasstation_cw";

        String displayDataTable = "SELECT * FROM " + table;
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(displayDataTable);

            while (resultSet.next()) {
                repository.setAmountInRepository(resultSet.getDouble("amountInRepository"));
                repository.setFuelType(resultSet.getString("fuelType"));
            }

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
    //Getters and setters
    public double getAmountInRepository() {
        return amountInRepository;
    }

    public void setAmountInRepository(double amountInRepository) {
        this.amountInRepository = amountInRepository;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public static double getCapacityOfRepository() {
        return capacityOfRepository;
    }
}
