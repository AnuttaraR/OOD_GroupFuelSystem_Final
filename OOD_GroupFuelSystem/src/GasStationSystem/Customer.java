package GasStationSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer {

    //Attributes
    private String vehicleNumber;
    private String vehicleType;
    private int ticketId;
    private Queue queue;
    private double fuelAmount;

    //Constructors
    public Customer(String vehicleNumber, String vehicleType, int ticketId, Queue queue) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.ticketId = ticketId;
        this.queue = queue;
    }

    public Customer(String vehicleNumber, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public Customer(String vehicleNumber, String vehicleType, double fuelAmount) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.fuelAmount = fuelAmount;
    }

    public Customer(){}

    //Other methods
    public static void joinQueue(Queue queue, Customer customer){
        queue.addCustomer(customer); //Adding a customer to queue
    }

    //Getters and setters
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public static void readDataFromDispenserTable(String table, ArrayList<Customer> customerList){ //This is to read from Tables that has Customer information of each Dispenser
        String url = "jdbc:mysql://localhost:3306/gasstation_cw";

        String displayDataTable = "SELECT * FROM " + table;
        try{
            Connection connection = DriverManager.getConnection(url,"root","");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(displayDataTable);

            while (resultSet.next()){
               customerList.add(new Customer(resultSet.getString("vehicleNumber"),resultSet.getString("vehicleType"),resultSet.getDouble("fuelAmount")));
            }

        }catch(Exception e){
            System.out.println("Error!");

        }

    }
}
