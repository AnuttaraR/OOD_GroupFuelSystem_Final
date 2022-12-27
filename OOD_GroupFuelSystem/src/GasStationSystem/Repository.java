package GasStationSystem;

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

    public void displayingRemainingStock(){
        System.out.println("Remaining "+fuelType+" fuel stock: "+amountInRepository);
    }
    //other methods
    public void checkFuelDispensedPerVehicleCatergory(ArrayList<Customer> listOfCustomers){
        //initializing number of vehicles per category
        int totalCars=0, totalVans=0, totalBikes=0, totalWheels=0, totalPublicTransportation=0, totalOthers = 0;
        //initializing total fuel served per vehicle category
        double totalFuelServedForCars = 0, totalFuelServedForVans =0, totalFuelServedForBikes=0, totalFuelServedForWheels=0, totalFuelServedForPublicTransportation=0, totalFuelServedForOthers= 0;

        //looping through the arraylist of customers to obtain the total fuel served per vehicle category
        for (Customer customer: listOfCustomers){
            if (customer.getVehicleType().equals("Car")){
                totalCars =+ 1;
                totalFuelServedForCars =+ customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Van")){
                totalVans =+ 1;
                totalFuelServedForVans =+ customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Motor Bikes")){
                totalBikes =+ 1;
                totalFuelServedForBikes =+ customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Three Wheel")){
                totalWheels =+ 1;
                totalFuelServedForWheels =+ customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Public Transportation")){
                totalPublicTransportation =+ 1;
                totalFuelServedForPublicTransportation =+ customer.getFuelAmount();
            }else {
                totalOthers =+ 1;
                totalFuelServedForOthers =+ customer.getFuelAmount();
            }
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
