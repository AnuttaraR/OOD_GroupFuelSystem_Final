package GasStationSystem;

import java.sql.*;
import java.util.ArrayList;

public class DieselDispenseManager implements FuelDispenserManager, Runnable{

    //Attributes
    private boolean isDispensing = true;
    private int dieselDispenseID;
    private double amountDispensed;
    private static double priceForLitre = 430.0;
    private double income;
    private DateTime date;

    //Association mapping
    private Repository repository;
    private ArrayList<Customer> listOfCustomers = new ArrayList<>();
    private ArrayList<String> listOfVehicleTypes = new ArrayList<>();

    //Constructors
    public DieselDispenseManager(ArrayList<Customer> listOfCustomers, Repository repository, boolean isDispensing, int dieselDispenseID, double amountDispensed, double income) {
        this.listOfCustomers = listOfCustomers;
        this.repository = repository;
        this.isDispensing = isDispensing;
        this.dieselDispenseID = dieselDispenseID;
        this.amountDispensed = amountDispensed;
        this.income = income;
    }

    public DieselDispenseManager(boolean isDispensing, int dieselDispenseID, double amountDispensed, double income) {
        this.isDispensing = isDispensing;
        this.dieselDispenseID = dieselDispenseID;
        this.amountDispensed = amountDispensed;
        this.income = income;
    }

    public DieselDispenseManager(){}

    //This is to read from Tables that has Dispenser data
    public static void readDataFromDieselDispenserTables(String table, DieselDispenseManager dispenser) {
        String url = "jdbc:mysql://localhost:3306/gasstation_cw";

        String displayDataTable = "SELECT * FROM " + table;
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(displayDataTable);

            while (resultSet.next()) {
                dispenser.setDieselDispenseID(resultSet.getInt("dispenserId"));
                dispenser.setAmountDispensed(resultSet.getDouble("amountDispensed"));
                dispenser.setIncome(resultSet.getDouble("income"));
                dispenser.setDate(new DateTime(resultSet.getInt("date"), resultSet.getInt("month"), resultSet.getInt("year")));
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    //This is to update the dispenser information to database
    public static void updateDispenserTables(DieselDispenseManager dispenser, String table){
        String url = "jdbc:mysql://localhost:3306/gasstation_cw";
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement statement = connection.createStatement();
            String query = "UPDATE " +table+
                    " SET income = "+dispenser.getIncome()+", amountDispensed = " + dispenser.getAmountDispensed()+
                    "WHERE date= "+dispenser.getDate().getDay()+" AND month= "+dispenser.getDate().getMonth()+";";
            PreparedStatement newStatement = connection.prepareStatement(query);
            newStatement.execute();
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    //this is to display the total fuel served per vehicle type
    public void displayingTotalFuelServedPerVehicleType(){

        //initializing number of vehicles per category
        int totalCars=0, totalVans=0, totalBikes=0, totalWheels=0, totalPublicTransportation=0, totalOthers = 0;
        //initializing total fuel served per vehicle category
        double totalFuelServedForCars = 0, totalFuelServedForVans =0, totalFuelServedForBikes=0, totalFuelServedForWheels=0, totalFuelServedForPublicTransportation=0, totalFuelServedForOthers= 0;

        //looping through the arraylist of customers to obtain the total fuel served per vehicle category
        for (Customer customer: listOfCustomers){
            if (customer.getVehicleType().equals("Car")){
                totalCars += 1;
                totalFuelServedForCars += customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Van")){
                totalVans =+ 1;
                totalFuelServedForVans += customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Motor Bikes")){
                totalBikes =+ 1;
                totalFuelServedForBikes += customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Three Wheel")){
                totalWheels =+ 1;
                totalFuelServedForWheels += customer.getFuelAmount();
            }else if (customer.getVehicleType().equals("Public Transportation")){
                totalPublicTransportation += 1;
                totalFuelServedForPublicTransportation += customer.getFuelAmount();
            }else {
                totalOthers += 1;
                totalFuelServedForOthers += customer.getFuelAmount();
            }
        }

        //Displaying total fuel served per vehicle category with number of vehicles
        System.out.println("\n----------Total diesel issued per vehicle category---------");
        System.out.println("Car category : Total Fuel: "+totalFuelServedForCars+" Total number of cars: "+totalCars);
        System.out.println("Van category : Total Fuel: "+totalFuelServedForVans+" Total number of vans: "+totalVans);
        System.out.println("Three Wheel category : Total Fuel: "+totalFuelServedForWheels+" Total number of vans: "+totalWheels);
        System.out.println("Motor Bikes category : Total Fuel: "+totalFuelServedForBikes+" Total number of motor bikes: "+totalBikes);
        System.out.println("Public transportation category : Total Fuel: "+totalFuelServedForPublicTransportation+" Total number of Public transportation: "+totalPublicTransportation);
        System.out.println("Other category : Total Fuel: "+totalFuelServedForOthers+" Total number of Other category: "+totalOthers);
    }

    //Displaying the total income per dispenser per day by reading data from the database
    public static void displayingTotalIncome(DieselDispenseManager dispenser, String table){

        String url = "jdbc:mysql://localhost:3306/gasstation_cw";

        String displayDataTable = "SELECT * FROM " + table;
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(displayDataTable);

            while (resultSet.next()) {
                System.out.println("Date: "+resultSet.getInt("date")+"-"+resultSet.getInt("month")+"-"+resultSet.getInt("year"));
                System.out.println("Dispenser Id: "+resultSet.getInt("dispenserId"));
                System.out.println("Total fuel dispensed: "+resultSet.getDouble("amountDispensed"));
                System.out.println("Total income: "+resultSet.getDouble("income"));
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
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
            isDispensing =false;
            System.out.println("Please wait, The Fuel is being Re-stocked!");
            restockFuel();
            return false;
        }
    }

    //Fuel restock if dispenser has stopped issuing fuel
    @Override
    public void restockFuel() {
        if (!isDispensing){
            repository.setAmountInRepository(25000.00);
            System.out.println("Restocked diesel repository!");
        }
    }

    //Stop dispensing fuel if repository capacity is less than 500L
    @Override
    public void stopFuelDispense() {
        if (repository.getAmountInRepository()<=500){
            isDispensing = false;
        }
    }

    //multithreading method
    public void run(){
        displayingTotalFuelServedPerVehicleType();
    }

    //getters and setters
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

    public int getDieselDispenseID() {
        return dieselDispenseID;
    }

    public void setDieselDispenseID(int dieselDispenseID) {
        this.dieselDispenseID = dieselDispenseID;
    }

    public double getAmountDispensed() {
        return amountDispensed;
    }

    public void setAmountDispensed(double amountDispensed) {
        this.amountDispensed = amountDispensed;
    }

    public static double getPriceForLitre() {
        return priceForLitre;
    }

    public static void setPriceForLitre(double priceForLitre) {
        DieselDispenseManager.priceForLitre = priceForLitre;
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

    //List manipulations
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
