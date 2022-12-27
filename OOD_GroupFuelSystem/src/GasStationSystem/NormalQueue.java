package GasStationSystem;

import java.util.ArrayList;

public class NormalQueue extends Queue{

    //Attributes
    private int queueNumber;
    private String queueType;

    //Constructor


    public NormalQueue(ArrayList<Customer> listOfCustomers, int queueNumber, String queueType) {
        super(listOfCustomers);
        this.queueNumber = queueNumber;
        this.queueType = queueType;
    }

    //Getters and setters
    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }
}
