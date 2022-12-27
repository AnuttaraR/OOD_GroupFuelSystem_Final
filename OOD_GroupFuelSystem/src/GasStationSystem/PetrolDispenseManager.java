package GasStationSystem;

import java.util.ArrayList;
import java.util.Date;

public class PetrolDispenseManager implements FuelDispenserManager{

    //Attributes
    private boolean isDispensing;
    private int petrolDispenseID;
    private double amountDispensed;
    private double income;
    private static double priceForLitre = 450.0;
    private DateTime date;

    //Association mapping
    private Repository repository;
    private ArrayList<Customer> listOfCustomers = new ArrayList<>();
    private ArrayList<String> listOfVehicleTypes = new ArrayList<>();

    //Constructors
    public PetrolDispenseManager(ArrayList<Customer> listOfCustomers, Repository repository,  boolean isDispensing, int petrolDispenseID, double amountDispensed, double income) {
        this.listOfCustomers = listOfCustomers;
        this.repository = repository;
        this.isDispensing = isDispensing;
        this.petrolDispenseID = petrolDispenseID;
        this.amountDispensed = amountDispensed;
        this.income = income;
    }

    public PetrolDispenseManager(Repository repository, boolean isDispensing, int petrolDispenseID, double amountDispensed, double income) {
        this.repository = repository;
        this.isDispensing = isDispensing;
        this.petrolDispenseID = petrolDispenseID;
        this.amountDispensed = amountDispensed;
        this.income = income;
    }

    public PetrolDispenseManager(){}

    public void displayingTotalFuelServedPerVehicleType(){

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

        //Displaying total fuel served per vehicle category with number of vehicles
        System.out.println("Total petrol issued per vehicle category");
        System.out.println("Car category : Total Fuel: "+totalFuelServedForCars+" Total number of cars: "+totalCars);
        System.out.println("Van category : Total Fuel: "+totalFuelServedForVans+" Total number of vans: "+totalVans);
        System.out.println("Three Wheel category : Total Fuel: "+totalFuelServedForWheels+" Total number of vans: "+totalWheels);
        System.out.println("Motor Bikes category : Total Fuel: "+totalFuelServedForBikes+" Total number of motor bikes: "+totalBikes);
        System.out.println("Public transportation category : Total Fuel: "+totalFuelServedForPublicTransportation+" Total number of cars: "+totalPublicTransportation);
        System.out.println("Other category : Total Fuel: "+totalFuelServedForOthers+" Total number of cars: "+totalOthers);
    }

    //Displaying the total income per dispenser per day
    public void displayingTotalIncome(double fuelAmount){
        income = income+fuelAmount*priceForLitre;
        System.out.println("Total income of this dispenser on "+date.toString()+" : LKR"+income);
    }


    //Overriding methods

    //Checking whether the repository capacity is greater than 500L and issuing fuel if not display fuel out of stock
    @Override
    public boolean dispenseFuel(double fuelAmount) {
        if (repository.getAmountInRepository()>500){
            System.out.println("Issued "+fuelAmount+"L successfully");
            repository.setAmountInRepository(repository.getAmountInRepository()-fuelAmount);
            setAmountDispensed(getAmountDispensed()+fuelAmount);
            return true;
        }else {
            System.out.println("Oops! Fuel is out of stock");
            stopFuelDispense();
            restockFuel();
            dispenseFuel(fuelAmount);
            return false;
        }
    }

    //Fuel restock if dispenser has stopped issuing fuel
    @Override
    public void restockFuel() {
        if (!isDispensing){
            repository.setAmountInRepository(25000.00);
            System.out.println("Restocked petrol repository!");
        }
    }

    //Stop dispensing fuel if repository capacity is less than 500L
    @Override
    public void stopFuelDispense() {
        if (repository.getAmountInRepository()<=500){
            isDispensing = false;
        }
    }

    //Getters and setters
    public ArrayList<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(ArrayList<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }

    public ArrayList<String> getListOfVehicleTypes() {
        return listOfVehicleTypes;
    }

    public void setListOfVehicleTypes(ArrayList<String> listOfVehicleTypes) {
        this.listOfVehicleTypes = listOfVehicleTypes;
    }

    public static double getPriceForLitre() {
        return priceForLitre;
    }

    public static void setPriceForLitre(double priceForLitre) {
        PetrolDispenseManager.priceForLitre = priceForLitre;
    }

    public double getAmountDispensed() {
        return amountDispensed;
    }

    public void setAmountDispensed(double amountDispensed) {
        this.amountDispensed = amountDispensed;
    }

    public int getPetrolDispenseID() {
        return petrolDispenseID;
    }

    public void setPetrolDispenseID(int petrolDispenseID) {
        this.petrolDispenseID = petrolDispenseID;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public boolean isDispensing() {
        return isDispensing;
    }

    public void setDispensing(boolean dispensing) {
        isDispensing = dispensing;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    //List manipulation
    public boolean addCustomer(Customer e){
        return(listOfCustomers.add(e));
    }

    public boolean removeCustomer(Customer e){
        return(listOfCustomers.remove(e));
    }

    public boolean addVehicleType(String e){
        return(listOfVehicleTypes.add(e));
    }

    public boolean removeVehicleType(String e){
        return(listOfVehicleTypes.remove(e));
    }
}
