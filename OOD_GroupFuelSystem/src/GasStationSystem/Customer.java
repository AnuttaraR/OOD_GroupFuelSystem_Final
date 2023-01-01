package GasStationSystem;

import java.sql.*;
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


    //Other methods

    //This is to read from Tables that has Customer information of each Dispenser
    public static void readDataFromCustomerTable(String table, ArrayList<Customer> customerList){
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

    public static void updateCustomerLists(Customer customer, String table){
        String url = "jdbc:mysql://localhost:3306/gasstation_cw";

        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO "+table+" (vehicleNumber, vehicleType, fuelAmount) VALUES (?,?,?)";
            PreparedStatement newStatement = connection.prepareStatement(query);
            newStatement.setString(1,customer.getVehicleNumber());
            newStatement.setString(2,customer.getVehicleType());
            newStatement.setDouble(3,customer.getFuelAmount());
            newStatement.execute();
        } catch (Exception e) {
            System.out.println("Error!");
        }

    }
    //Adding a customer to queue
    public static void joinQueue(Queue queue, Customer customer){
        queue.addCustomer(customer);
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


}
