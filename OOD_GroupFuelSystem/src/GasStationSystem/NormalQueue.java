package GasStationSystem;

public class NormalQueue extends Queue{

    //Attributes
    private int queueNumber;
    private String queueType;

    //Constructor
    public NormalQueue() {
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
