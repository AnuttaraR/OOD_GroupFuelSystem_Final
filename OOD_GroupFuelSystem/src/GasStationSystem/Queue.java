package GasStationSystem;

import java.util.ArrayList;
import java.util.Collections;

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

    public static ArrayList<Customer> moveUpQueue(ArrayList<Customer> list){
        for (int j = 0; j < 10; j++) {
            if (j+1 != 10) {
                list.set(j, list.get(j + 1));
            }
        }
        list.remove(list.get(list.size()-1));
        return list;
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
