package GasStationSystem;

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
}
