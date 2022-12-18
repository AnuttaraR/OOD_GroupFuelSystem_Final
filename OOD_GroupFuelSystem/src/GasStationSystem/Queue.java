package GasStationSystem;

import java.util.ArrayList;

public class Queue {

    //Association mapping
    private ArrayList<Customer> listOfCustomers = new ArrayList<>();

    //Constructors
    public Queue() {
    }
    public Queue(ArrayList<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }

    //Other methods
    public static int displayAvailablePositions(Queue queue){
        return (10 -queue.getListOfCustomers().size());
    }

    //Getters and Setters
    public void setListOfCustomers(ArrayList<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }
    public ArrayList<Customer> getListOfCustomers() {
        return listOfCustomers;
    }


    //List manipulation methods
    public boolean addCustomer(Customer customer){
        return(listOfCustomers.add(customer));
    }

    public boolean removeCustomer(Customer customer){
        return(listOfCustomers.remove(customer));
    }
}
