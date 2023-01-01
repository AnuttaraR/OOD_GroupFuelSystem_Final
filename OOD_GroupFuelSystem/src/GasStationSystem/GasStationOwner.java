package GasStationSystem;

import java.sql.*;
import java.util.ArrayList;

public class GasStationOwner {

    //Attributes
    private double totalDieselIncome;
    private double totalPetrolIncome;
    private static double totalPetrolDispensedAmount;
    private static double totalDieselDispensedAmount;
    private DateTime date;

    //Association mapping
    private ArrayList<DieselDispenseManager> listOfDieselDispensers = new ArrayList<>();
    private ArrayList<PetrolDispenseManager> listOfPetrolDispensers = new ArrayList<>();

    //Constructor
    public GasStationOwner() {
    }

    public GasStationOwner(ArrayList<DieselDispenseManager> listOfDieselDispensers, ArrayList<PetrolDispenseManager> listOfPetrolDispensers, DateTime date) {
        this.listOfDieselDispensers = listOfDieselDispensers;
        this.listOfPetrolDispensers = listOfPetrolDispensers;
        this.date = date;
    }

    //Other methods

    //Displaying the total income of the gas station per day per fuel category
    public void displayTotalIncome(){
        //looping through the list of dispensers arraylist and getting the amount of the fuel dispensed and calculating the total income per fuel type
        for (DieselDispenseManager dieselDispenseManager: listOfDieselDispensers){
            totalDieselDispensedAmount = totalDieselDispensedAmount + dieselDispenseManager.getAmountDispensed();
        }
        for (PetrolDispenseManager petrolDispenseManager: listOfPetrolDispensers){
            totalPetrolDispensedAmount = totalPetrolDispensedAmount + petrolDispenseManager.getAmountDispensed();
        }
        totalDieselIncome = totalDieselDispensedAmount*DieselDispenseManager.getPriceForLitre();
        totalPetrolIncome = totalPetrolDispensedAmount*PetrolDispenseManager.getPriceForLitre();

        String url = "jdbc:mysql://localhost:3306/gasstation_cw";

        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement statement = connection.createStatement();
            String query = "UPDATE gas_station"+
                    " SET totalDieselIncome = "+ getTotalDieselIncome()+", totalDieselDispensedAmount = " +getTotalDieselDispensedAmount()+
                    " ,totalPetrolIncome = "+ getTotalPetrolIncome()+", totalPetrolDispensedAmount= " +getTotalPetrolDispensedAmount()+";"+
                    "WHERE date= "+getDate().getDay()+" AND month= "+getDate().getMonth()+";";
            PreparedStatement newStatement = connection.prepareStatement(query);
            newStatement.setDouble(1, totalPetrolIncome);
            newStatement.setDouble(2, totalPetrolDispensedAmount);
            newStatement.setDouble(3, totalDieselIncome);
            newStatement.setDouble(4, totalDieselDispensedAmount);
            newStatement.execute();
        } catch (Exception e) {
            System.out.println("Error!");
        }
        System.out.println("The total Petrol income: "+getTotalPetrolIncome());
        System.out.println("The total Petrol fuel dispensed: "+getTotalPetrolDispensedAmount());
        System.out.println("The total diesel income: "+getTotalDieselIncome());
        System.out.println("The total diesel fuel dispensed: "+getTotalDieselDispensedAmount());
    }

    public void displayingVehicleWithLargestFuelDispensed(){
        //Initializing the customer and fuel amount variables with the largest fuel amounts
        Customer customerWithLargestDieselDispensed = null, customerWithLargestPetrolDispensed = null;
        double largestDieselFuelAmount = 0, largestPetrolFuelAmount=0;

        //looping through the list of diesel dispensers then through list of customer and finding the customer with the largest fuel dispensed amount
        for (DieselDispenseManager dieselDispenseManager: listOfDieselDispensers){
            for (int i=0; i<dieselDispenseManager.getListOfCustomers().size();i++){
                if (largestDieselFuelAmount <= dieselDispenseManager.getListOfCustomers().get(i).getFuelAmount()){
                    largestDieselFuelAmount = dieselDispenseManager.getListOfCustomers().get(i).getFuelAmount();
                    customerWithLargestDieselDispensed = dieselDispenseManager.getListOfCustomers().get(i);
                }
            }
        }
        for (PetrolDispenseManager petrolDispenseManager: listOfPetrolDispensers){
            for (int i=0; i<petrolDispenseManager.getListOfCustomers().size();i++){
                if (largestPetrolFuelAmount <= petrolDispenseManager.getListOfCustomers().get(i).getFuelAmount()){
                    largestPetrolFuelAmount = petrolDispenseManager.getListOfCustomers().get(i).getFuelAmount();
                    customerWithLargestPetrolDispensed = petrolDispenseManager.getListOfCustomers().get(i);
                }
            }
        }

        //Comparing the diesel and petrol the largest amounts and obtaining the customer with the largest fuel amount
        if (largestDieselFuelAmount>largestPetrolFuelAmount){
            System.out.println("\nThe vehicle with the largest amount of fuel received on "+date.toString());
            System.out.println("Fuel type: Diesel");
            System.out.println("Vehicle: "+customerWithLargestDieselDispensed.getVehicleNumber());
        }else {
            System.out.println("The vehicle with the largest amount of fuel received on "+date.toString());
            System.out.println("Fuel type: Petrol");
            System.out.println("Vehicle: "+customerWithLargestPetrolDispensed.getVehicleNumber());
        }
    }


    //Getters and setters
    public ArrayList<DieselDispenseManager> getListOfDieselDispensers() {
        return listOfDieselDispensers;
    }

    public void setListOfDieselDispensers(ArrayList<DieselDispenseManager> listOfDieselDispensers) {
        this.listOfDieselDispensers = listOfDieselDispensers;
    }

    public ArrayList<PetrolDispenseManager> getListOfPetrolDispensers() {
        return listOfPetrolDispensers;
    }

    public void setListOfPetrolDispensers(ArrayList<PetrolDispenseManager> listOfPetrolDispensers) {
        this.listOfPetrolDispensers = listOfPetrolDispensers;
    }

    public double getTotalDieselIncome() {
        return totalDieselIncome;
    }

    public void setTotalDieselIncome(double totalDieselIncome) {
        this.totalDieselIncome = totalDieselIncome;
    }

    public double getTotalPetrolIncome() {
        return totalPetrolIncome;
    }

    public void setTotalPetrolIncome(double totalPetrolIncome) {
        this.totalPetrolIncome = totalPetrolIncome;
    }

    public static double getTotalPetrolDispensedAmount() {
        return totalPetrolDispensedAmount;
    }

    public static void setTotalPetrolDispensedAmount(double totalPetrolDispensedAmount) {
        GasStationOwner.totalPetrolDispensedAmount = totalPetrolDispensedAmount;
    }

    public static double getTotalDieselDispensedAmount() {
        return totalDieselDispensedAmount;
    }

    public static void setTotalDieselDispensedAmount(double totalDieselDispensedAmount) {
        GasStationOwner.totalDieselDispensedAmount = totalDieselDispensedAmount;
    }

    public boolean addPetrolDispenser(PetrolDispenseManager e){
        return(listOfPetrolDispensers.add(e));
    }

    public boolean removePetrolDispenser(PetrolDispenseManager e){
        return(listOfPetrolDispensers.remove(e));
    }

    public boolean addDieselDispenser(DieselDispenseManager e){
        return(listOfDieselDispensers.add(e));
    }

    public boolean removeDieselDispenser(DieselDispenseManager e){
        return(listOfDieselDispensers.remove(e));
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
