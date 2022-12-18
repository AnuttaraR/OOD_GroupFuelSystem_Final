package GasStationSystem;

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
